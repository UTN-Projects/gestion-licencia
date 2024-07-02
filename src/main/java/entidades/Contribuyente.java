package entidades;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(name="contribuyente")
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Contribuyente {

	@Id
	private Integer dni;

	private String nombre;

	private String apellido;

	private Date fechaNacimiento;

	private String calle;

	private Integer nroCasa;

	@Enumerated(EnumType.STRING)
	private GrupoSaguineo grupoSanguineo;

	@Enumerated(EnumType.STRING)
	private RH rh;

	private Boolean esDonante;

	public int getEdad() {
		LocalDate fechaNacimientoLocal = this.fechaNacimiento.toLocalDate();
		LocalDate fechaActual = LocalDate.now();

		return Period.between(fechaNacimientoLocal, fechaActual).getYears();

	}

}
