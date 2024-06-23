package entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Licencia {
	
	private int dni_titular;
	
	private String nombre_titular;
	
	private String apellido_titular;
	
	private Date fecha_nac_titular;
	
	private String calle_titular;
	
	private int nro_casa_titular;
	
	private String clase;
	
	private String tipo;
	
	private String grupo_sang_titular;
	
	private String rh_titular;
	
	private String es_donante_titular;
	
	private String observaciones;
	
	private Date fecha_emision;
	
	private String administrador;
	
	private String vigente;
	
	private Date fecha_vencimiento;

	public int getDni_titular() {
		return dni_titular;
	}

	public void setDni_titular(int dni_titular) {
		this.dni_titular = dni_titular;
	}

	public String getNombre_titular() {
		return nombre_titular;
	}

	public void setNombre_titular(String nombre_titular) {
		this.nombre_titular = nombre_titular;
	}

	public String getApellido_titular() {
		return apellido_titular;
	}

	public void setApellido_titular(String apellido_titular) {
		this.apellido_titular = apellido_titular;
	}

	public Date getFecha_nac_titular() {
		return fecha_nac_titular;
	}

	public void setFecha_nac_titular(Date fecha_nac_titular) {
		this.fecha_nac_titular = fecha_nac_titular;
	}

	public String getCalle_titular() {
		return calle_titular;
	}

	public void setCalle_titular(String calle_titular) {
		this.calle_titular = calle_titular;
	}

	public int getNro_casa_titular() {
		return nro_casa_titular;
	}

	public void setNro_casa_titular(int nro_casa_titular) {
		this.nro_casa_titular = nro_casa_titular;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGrupo_sang_titular() {
		return grupo_sang_titular;
	}

	public void setGrupo_sang_titular(String grupo_sang_titular) {
		this.grupo_sang_titular = grupo_sang_titular;
	}

	public String getRh_titular() {
		return rh_titular;
	}

	public void setRh_titular(String rh_titular) {
		this.rh_titular = rh_titular;
	}

	public String getEs_donante_titular() {
		return es_donante_titular;
	}

	public void setEs_donante_titular(String es_donante_titular) {
		this.es_donante_titular = es_donante_titular;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	public String getVigente() {
		return vigente;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	
	public void setFecha_vencimiento(Date fecha_ven) {
		this.fecha_vencimiento = fecha_ven;
	}
	
	public Licencia(int dni_titular, String nombre_titular, String apellido_titular, Date fecha_nac_titular, String calle_titular, int nro_casa_titular, String clase, String tipo, String grupo_sang_titular,
			String rh_titular, String es_donante_titular, String observaciones, Date fecha_emision, String administrador) {
		
		this.dni_titular = dni_titular;
		this.nombre_titular = nombre_titular;
		this.apellido_titular = apellido_titular;
		this.fecha_nac_titular = fecha_nac_titular;
		this.calle_titular = calle_titular;
		this.nro_casa_titular= nro_casa_titular;
		this.clase=clase;
		this.tipo=tipo;
		this.grupo_sang_titular=grupo_sang_titular;
		this.rh_titular=rh_titular;
		this.es_donante_titular=es_donante_titular;
		this.observaciones=observaciones;
		this.fecha_emision=fecha_emision;
		this.administrador=administrador;
		
	}

	
	public Date calcularVigencia(Titular titular) {
		
		int edad = titular.getEdad();
	    int cantLic = titular.cantLicencias();
	    LocalDate fechaNacimiento = titular.getFecha_nacimiento().toLocalDate();
	    
	    int vigenciaAnos = 0;
	    if (edad < 21) {
	        if (cantLic == 1) {
	            vigenciaAnos = 1;
	        } else {
	            vigenciaAnos = 3;
	        }
	    } else if (edad <= 46) {
	        vigenciaAnos = 5;
	    } else if (edad <= 60) {
	        vigenciaAnos = 4;
	    } else if (edad <= 70) {
	        vigenciaAnos = 3;
	    } else if (edad > 70) {
	        vigenciaAnos = 1;
	    }

	    LocalDate fechaActual = LocalDate.now();
	    int anoVencimiento = fechaActual.getYear() + vigenciaAnos;

	    LocalDate fechaVencimiento = LocalDate.of(anoVencimiento, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
	    
	    System.out.println(fechaVencimiento);
	    
	    return Date.valueOf(fechaVencimiento);
	    
	}

	public int calcularCostoLicencia() {
		int costoLicencia = 0;
		int aniosVigencia = this.getFecha_vencimiento().toLocalDate().getYear() - this.getFecha_emision().toLocalDate().getYear();
		switch (this.getClase()) {
			case "A":
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
			case "B":
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
			case "C":
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
			case "D":
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
			case "E":
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
			case "F":
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
			case "G":
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
	
	
	
}

