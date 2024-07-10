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

    public Licencia renovarLicencia(Licencia licencia) {
        Date fechaEmision = Date.valueOf(LocalDate.now());
        Date vigencia = calcularVigencia(licencia.getTitular());

        //todo

        DBManager.getInstance().guardarLicencia(licencia);
        LicenciaController.getInstance().imprimirLicencia(licencia);
        LicenciaController.getInstance().imprimirFactura(licencia.getTitular(), licencia);
        return null;
    }

    public Titular obtenerTitular(int dni) {
        return DBManager.getInstance().buscarPorDni(dni);
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

        LicenciaDto l = LicenciaDto.builder()
                .number("" + DBManager.getInstance().IDLicencia(licencia.getTitular().getDni(), licencia.getClase()))
                .lastname(licencia.getTitular().getApellido())
                .name(licencia.getTitular().getNombre())
                .address(licencia.getCalleTitular() + " " + licencia.getNroCasaTitular())
                .birth(licencia.getFechaNacTitular().getDay() +
                        LicenciaDto.traductorMes(licencia.getFechaNacTitular().getMonth()) +
                        licencia.getFechaNacTitular().getYear())
                .emition(licencia.getFechaEmision().getDay() +
                        LicenciaDto.traductorMes(licencia.getFechaEmision().getMonth()) +
                        licencia.getFechaEmision().getYear())
                .expiration(licencia.getFechaVencimiento().getDay() +
                        LicenciaDto.traductorMes(licencia.getFechaVencimiento().getMonth()) +
                        licencia.getFechaVencimiento().getYear())
                .isDonor(licencia.isEsDonanteTitular()).bloodType(licencia.getGrupoSangTitular().toString() + licencia.getRhTitular())
                .cuil("" + licencia.getTitular().getDni()).observations(licencia.getObservaciones())
                .type(licencia.getTipo().toString()).build();
        LicenciaGenerator.generar(l, imagePath, filePath);
    }

    private void imprimirFactura(Titular titular, Licencia licencia) {
        float costoLicencia = licencia.calcularCostoLicencia();

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

    private Date calcularVigencia(Titular titular) {
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
}
