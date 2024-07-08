package utn.metodos_agiles.entidades;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name="usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Usuario {

    @Id
    @UuidGenerator
    private String id;

    private String nombre;

    private String apellido;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String telefono;

    private String contrasena;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    private boolean privilegios;
    
 // Método para conseguir el nombre de usuario
    public String conseguirNombreUsuario() {
        if (nombre == null || apellido == null) {
            throw new IllegalStateException("Nombre y apellido deben estar presentes para generar el nombre de usuario.");
        }
        return nombre.substring(0, 1).toLowerCase() + apellido.toLowerCase();
        
        
    }

	public Usuario(String correo, String nombre2, String apellido2, String telefono2, String contrasena2) {
		
			
			
		 	this.correoElectronico = correo;
	        this.nombre = nombre2;
	        this.apellido = apellido2;
	        this.telefono = telefono2;
	        this.contrasena = contrasena2;
	        this.nombreUsuario = conseguirNombreUsuario();
		
		
	}

	public Usuario devolverUsuario() {
		return this;
	}
	
	public boolean devolverPrivilegios() {
		
		 return this.privilegios;
	}
}
