package entidades;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Data
@Builder
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

	public static Date calcularVigencia(Titular titular) {
		
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

