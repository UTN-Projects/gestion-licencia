package utn.metodos_agiles.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.sql.Date;


@Entity
@Table(name="licencia")
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Licencia {

	@Id
	@UuidGenerator
	private String id;

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long numeroLicencia;

	private String nombreTitular;

	private String apellidoTitular;

	private Date fechaNacTitular;

	private String calleTitular;

	private int nroCasaTitular;

	private boolean esDonanteTitular;

	private String observaciones;

	private Date fechaEmision;

	private String administrador;
	
	private boolean vigente;

	private Date fechaVencimiento;

	@Enumerated(EnumType.STRING)
	private ClaseLicencia clase;

	@Enumerated(EnumType.ORDINAL)
	private TipoLicencia tipo;

	@Enumerated(EnumType.STRING)
	private GrupoSaguineo grupoSangTitular;

    @Enumerated(EnumType.STRING)
    private RH rhTitular;

    @ManyToOne
    @JoinColumn(name = "dni_titular")
    private Titular titular;

	public int calcularCostoLicencia() {
		int costoLicencia = 0;
		int aniosVigencia = this.getFechaVencimiento().toLocalDate().getYear() - this.getFechaEmision().toLocalDate().getYear();
		switch (this.getClase()) {
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
}
