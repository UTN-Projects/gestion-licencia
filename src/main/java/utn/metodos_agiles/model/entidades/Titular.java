package utn.metodos_agiles.model.entidades;

import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import utn.metodos_agiles.db.DBManager;

@Entity
@Table(name="titular")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Titular{

	@Id
	private int dni;
	
	private String nombre;
	
	private String apellido;
	
	private Date fechaNacimiento;
	
	private String calle;
	
	private int nroCasa;

	@Enumerated(EnumType.STRING)
	private GrupoSaguineo grupoSanguineo;

	@Enumerated(EnumType.STRING)
	private RH rh;
	
	private Boolean esDonante;

	public static Titular of(Contribuyente contribuyente) {
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(contribuyente, Titular.class);
	}
	
	public int getEdad() {
		 LocalDate fechaNacimientoLocal = this.fechaNacimiento.toLocalDate();
	        LocalDate fechaActual = LocalDate.now();

	        return Period.between(fechaNacimientoLocal, fechaActual).getYears();
        
	}

	public int tiempoLicencias(){
		Boolean permitido = DBManager.getInstance().permitidoClaseProfesional(this.dni);

        return permitido ? 1 : 0;
	}

}
