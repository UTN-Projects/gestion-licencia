package utn.metodos_agiles.controller;

import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.model.entidades.ClaseLicencia;
import utn.metodos_agiles.model.entidades.Licencia;
import utn.metodos_agiles.model.entidades.TipoLicencia;
import utn.metodos_agiles.model.entidades.Titular;
import utn.metodos_agiles.model.ClientDto;
import utn.metodos_agiles.model.FacturaItem;
import utn.metodos_agiles.model.LicenciaDto;
import utn.metodos_agiles.util.FacturaGenerator;
import utn.metodos_agiles.util.LicenciaGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LicenciaController {

    private static LicenciaController instance;

    private LicenciaController(){}

    public static LicenciaController getInstance() {
        if(instance == null) {
            instance = new LicenciaController();
        }

        return instance;
    }

    public Licencia emitirLicencia(Titular titular, ClaseLicencia clase, String observacion) {

        Date fechaEmision = Date.valueOf(LocalDate.now());

        // todo DBManager.getInstance().guardarTitular(Titular.of(contribuyente));

        Licencia licencia = Licencia.builder()
                .titular(titular)
                .nombreTitular(titular.getNombre())
                .apellidoTitular(titular.getApellido())
                .fechaNacTitular(titular.getFechaNacimiento())
                .calleTitular(titular.getCalle())
                .nroCasaTitular(titular.getNroCasa())
                .clase(clase)
                .tipo(TipoLicencia.ORIGINAL)
                .grupoSangTitular(titular.getGrupoSanguineo())
                .rhTitular(titular.getRh())
                .esDonanteTitular(titular.getEsDonante())
                .observaciones(observacion)
                .fechaEmision(fechaEmision)
                .administrador("Juan Perez")
                .vigente(true)
                .fechaVencimiento(calcularVigencia(titular))
                .build();

        DBManager.getInstance().guardarLicencia(licencia);

        LicenciaController.getInstance().imprimirLicencia(licencia);
        LicenciaController.getInstance().imprimirFactura(titular, licencia);

        return licencia;
    }

    public Licencia copiarLicencia(Licencia licencia) {
        licencia.setTipo(TipoLicencia.fromNumber(licencia.getTipo().toNumber() + 1));

        LicenciaController.getInstance().imprimirLicencia(licencia);
        LicenciaController.getInstance().imprimirFactura(licencia.getTitular(), licencia);

        DBManager.getInstance().guardarLicencia(licencia);
        return null;
    }

    public Licencia renovarLicencia(Licencia licencia, String observacion) {
        Date fechaEmision = Date.valueOf(LocalDate.now());

        Titular titular = DBManager.getInstance().buscarPorDni(licencia.getTitular().getDni());

        Licencia licenciaRenovada = Licencia.builder()
                .titular(titular)
                .nombreTitular(titular.getNombre())
                .apellidoTitular(titular.getApellido())
                .fechaNacTitular(titular.getFechaNacimiento())
                .calleTitular(titular.getCalle())
                .nroCasaTitular(titular.getNroCasa())
                .clase(licencia.getClase())
                .tipo(TipoLicencia.ORIGINAL)
                .grupoSangTitular(titular.getGrupoSanguineo())
                .rhTitular(titular.getRh())
                .esDonanteTitular(titular.getEsDonante())
                .observaciones(observacion)
                .fechaEmision(fechaEmision)
                .administrador("Juan Perez")
                .vigente(true)
                .fechaVencimiento(calcularVigencia(titular))
                .build();

        DBManager.getInstance().guardarLicencia(licenciaRenovada);
        LicenciaController.getInstance().imprimirLicencia(licenciaRenovada);
        LicenciaController.getInstance().imprimirFactura(licenciaRenovada.getTitular(), licenciaRenovada);
        return null;
    }

    public Titular obtenerTitular(int dni) {
        return DBManager.getInstance().buscarPorDni(dni);
    }

    public void actualizarTitular(Titular titular) {
        DBManager.getInstance().actualizarTitular(titular);
    }

    public List<Licencia> licenciasDeTitular(int dni) {
        return DBManager.getInstance().cargarLicenciasTitular(dni);
    }

    public List<Licencia> licenciasVencidas() {
        return DBManager.getInstance().recuperarLicenciasVencidas().stream()
                .sorted(Comparator.comparingInt(licencia -> licencia.getTitular().getDni()))
                .toList();
    }

    public List<Licencia> licenciasVigentes() {
        return DBManager.getInstance().recuperarLicenciasVigentes();
    }

    private void imprimirLicencia(Licencia licencia) {
        Titular titular = licencia.getTitular();

        String filePath = "licencia" + titular.getDni() + ".pdf";
        String imagePath = "src/main/resources/fotos/" + titular.getDni() +".png";
        Long number = DBManager.getInstance()
                .IDLicencia(licencia.getTitular().getDni(), licencia.getClase());
        String numberS = String.format("%09d", number);

        LicenciaDto l = LicenciaDto.builder()
                .licencia(licencia.getClase().name())
                .number(numberS)
                .lastname(licencia.getTitular().getApellido())
                .name(licencia.getTitular().getNombre())
                .address(licencia.getCalleTitular() + " " + licencia.getNroCasaTitular())
                .birth(dateToString(licencia.getFechaNacTitular()))
                .emition(dateToString(licencia.getFechaEmision()))
                .expiration(dateToString(licencia.getFechaVencimiento()))
                .isDonor(licencia.isEsDonanteTitular()).bloodType(licencia.getGrupoSangTitular().toString() + licencia.getRhTitular())
                .cuil("" + licencia.getTitular().getDni()).observations(licencia.getObservaciones())
                .type(licencia.getTipo().toString()).build();
        LicenciaGenerator.generar(l, imagePath, filePath);
    }

    private void imprimirFactura(Titular titular, Licencia licencia) {
        float costoLicencia = this.calcularCostoLicencia(licencia);

        String filePath = "factura" + titular.getDni() + ".pdf";

        ClientDto c = ClientDto.builder().name(titular.getNombre() + " " + titular.getApellido())
                .address(titular.getCalle() + " " + titular.getNroCasa()).dni("" + titular.getDni()).build();

        List<FacturaItem> items = new ArrayList<FacturaItem>();
        items.add(FacturaItem.builder()
                .description("Licencia clase " + licencia.getClase())
                .value(costoLicencia)
                .build());

        FacturaGenerator.generar(c, items, filePath);
    }

    public Date calcularVigencia(Titular titular) {
        int edad = titular.getEdad();
        LocalDate vig = LocalDate.now();

        int mas = 0;
        if(edad < 21) {
            int nroLicTitular = DBManager.getInstance().cantLicTitular(titular.getDni());
            if(nroLicTitular == 1) {
                mas = 1;
            } else mas = 3;
        } else if(edad <= 46) {
            mas = 5;
        } else if(edad <= 60) {
            mas = 4;
        } else if(edad <= 70) {
            mas = 3;
        } else if(edad > 70) {
            mas = 1;
        }

        return Date.valueOf(vig.plusYears(mas));
    }

    public int calcularCostoLicencia(Licencia licencia) {
		int costoLicencia = 0;
		int aniosVigencia = licencia.getFechaVencimiento().toLocalDate().getYear() - licencia.getFechaEmision().toLocalDate().getYear();
		switch (licencia.getClase()) {
            case A:
				switch (aniosVigencia) {
					case 5:
						costoLicencia = 40;
						break;
					case 4:
						costoLicencia = 30;
						break;
					case 3:
						costoLicencia = 25;
						break;
					case 1:
						costoLicencia = 20;
						break;
					default:
						break;
				}
				break;
            case B:
				switch (aniosVigencia) {
					case 5:
						costoLicencia = 40;
						break;
					case 4:
						costoLicencia = 30;
						break;
					case 3:
						costoLicencia = 25;
						break;
					case 1:
						costoLicencia = 20;
						break;
					default:
						break;
				}
            case C:
			switch (aniosVigencia) {
				case 5:
					costoLicencia = 47;
					break;
				case 4:
					costoLicencia = 35;
					break;
				case 3:
					costoLicencia = 30;
					break;
				case 1:
					costoLicencia = 23;
					break;
				default:
					break;
			}
            case D:
			switch (aniosVigencia) {
				case 5:
					costoLicencia = 50;
					break;
				case 4:
					costoLicencia = 40;
					break;
				case 3:
					costoLicencia = 35;
					break;
				case 1:
					costoLicencia = 30;
					break;
				default:
					break;
			}
            case E:
			switch (aniosVigencia) {
				case 5:
					costoLicencia = 59;
					break;
				case 4:
					costoLicencia = 44;
					break;
				case 3:
					costoLicencia = 39;
					break;
				case 1:
					costoLicencia = 29;
					break;
				default:
					break;
			}
            case F:
			switch (aniosVigencia) {
				case 5:
					costoLicencia = 50;
					break;
				case 4:
					costoLicencia = 40;
					break;
				case 3:
					costoLicencia = 35;
					break;
				case 1:
					costoLicencia = 30;
					break;
				default:
					break;
			}
            case G:
			switch (aniosVigencia) {
				case 5:
					costoLicencia = 40;
					break;
				case 4:
					costoLicencia = 30;
					break;
				case 3:
					costoLicencia = 25;
					break;
				case 1:
					costoLicencia = 20;
					break;
				default:
					break;
			}
			default:
				break;
		}
		costoLicencia = costoLicencia + 8;
		return costoLicencia;
	}

    private String dateToString(Date date) {
        return date.getDay() + " " + traductorMes(date.getMonth()) + " " + ((1900+date.getYear())%100);
    }

    private static String traductorMes(int mes) {
        switch(mes) {
            case 1:
                return "ENE";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "ABR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AGO";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DIC";

        }
        return "";
    }
}
