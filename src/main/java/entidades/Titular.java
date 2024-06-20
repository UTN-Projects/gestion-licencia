package entidades;

import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import utn.metodos_agiles.db.DBManager;

public class Titular{

	private int dni;
	
	private String nombre;
	
	private String apellido;
	
	private Date fecha_nacimiento;
	
	private String calle;
	
	private int nro_casa;
	
	private String grupo_sanguineo;
	
	private String rh;
	
	private String es_donante;

	 public Titular(int dni, String nombre, String apellido, Date fecha_nac, String calle2, int nro_casa2, String grupo_sanguineo2, String rh2, String es_donante2) {
	        this.dni = dni;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.fecha_nacimiento = fecha_nac;
	        this.calle = calle2;
	        this.nro_casa = nro_casa2;
	        this.grupo_sanguineo = grupo_sanguineo2;
	        this.rh = rh2;
	        this.es_donante = es_donante2;
	    }


	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNro_casa() {
		return nro_casa;
	}

	public void setNro_casa(int nro_casa) {
		this.nro_casa = nro_casa;
	}

	public String getGrupo_sanguineo() {
		return grupo_sanguineo;
	}

	public void setGrupo_sanguineo(String grupo_sanguineo) {
		this.grupo_sanguineo = grupo_sanguineo;
	}

	public String getRh() {
		return rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public String getEs_donante() {
		return es_donante;
	}

	public void setEs_donante(String es_donante) {
		this.es_donante = es_donante;
	}
	
	
	public int getEdad() {
		
		 LocalDate fechaNacimientoLocal = this.fecha_nacimiento.toLocalDate(); 
	        LocalDate fechaActual = LocalDate.now();

	        return Period.between(fechaNacimientoLocal, fechaActual).getYears();
        
	}
	
	
	public Set<String> getLicencias(){
		
		Set<String> licenciasPoseidas = DBManager.recuperarClasesLicencias(dni);
		
		return licenciasPoseidas;
		
	}
	
	
	public int tiempoLicencias(){
		
		Boolean permitido = DBManager.permitidoClaseProfesional(this.dni);
		System.out.println(permitido);
        return permitido ? 1 : 0;
	}
	
	public int cantLicencias() {
		
		int cantidad = DBManager.cantLicTitular(this.dni);
		
		
		return cantidad;
	}
	
	
}
