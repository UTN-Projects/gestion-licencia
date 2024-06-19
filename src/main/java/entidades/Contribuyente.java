package entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class Contribuyente extends Titular{
    

	 public Contribuyente(int dni, String nombre, String apellido, Date fecha_nac, String calle2, int nro_casa2, String grupo_sanguineo2, String rh2, String es_donante2) {
	        super(dni, nombre, apellido, fecha_nac, calle2, nro_casa2, grupo_sanguineo2, rh2, es_donante2);
	    }

}
