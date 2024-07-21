package utn.metodos_agiles.model.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.sql.Date;


@Entity
@Table(name="licencia")
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Licencia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

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

}
