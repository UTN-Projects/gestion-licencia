package utn.metodos_agiles.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
//import java.util.Date;
import java.util.List;
import java.util.Set;

import entidades.Licencia;
import entidades.Titular;

public class DBManager {
	private static String url = "jdbc:mysql://localhost:3306/metodos_agiles";
	private static String user = "root";
	private static String pass = "admin";
	
	public static void probarDB() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			st.close();
			conn.close();
		} catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void guardarTitular(int dni, String nombre, String apellido, Date fechaNac, String calle, int nro,
			String grupoSang, String rh, Boolean esDonante) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			String accion = "INSERT INTO TITULAR (dni, nombre, apellido, fecha_nacimiento, calle, nro casa, "
					+ "grupo_sanguineo, rh, es_donante) VALUES " + dni + ", '" + nombre + "', '" + apellido + "', '"
					+ fechaNac.getYear() + "-" + fechaNac.getMonth() + "-" + fechaNac.getDay() + "', '" + calle
					+ ", " + nro + ", '" + grupoSang + "', '" + rh + "', '";
			if(esDonante) accion = accion + "SI')";
			else accion = accion + "NO')";
			st.executeUpdate(accion);
			st.close();
			conn.close();
		} catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	public static Titular buscarPorDni(int dni) {
		
		String query = "SELECT * FROM titular WHERE dni = ?";
        Titular titular = null;
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            
	            stmt.setInt(1, dni);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                int dniObtenido = rs.getInt("dni");
	                String nombre = rs.getString("nombre");
	                String apellido = rs.getString("apellido");
	                Date fecha_nac = rs.getDate("fecha_nacimiento");
	                String calle = rs.getString("calle");
	                int nro_casa = rs.getInt("nro_casa");
	                String grupo_sanguineo = rs.getString("grupo_sanguineo");
	                String rh = rs.getString("rh");
	                String es_donante = rs.getString("es_donante");
	                
	                /*System.out.println(nombre);
	                System.out.println(apellido);*/
	                
	                titular = new Titular(dniObtenido, nombre, apellido,fecha_nac, calle, nro_casa, grupo_sanguineo, rh, es_donante);
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return titular;
	        
		
	}
	
		
	public static Set<String> recuperarClasesLicencias(int dni) {
        Set<String> licencias = new HashSet<>();

        String sql = "SELECT clase FROM licencia WHERE dni_titular = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dni);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    licencias.add(rs.getString("clase"));
                    
                    System.out.println(licencias);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores (registrar el error, lanzar una excepción, etc.)
        }

        return licencias;
	}
	
	public static Boolean permitidoClaseProfesional(int dni) {
		
		Boolean permitido = false;

        String sql = "SELECT fecha_emision FROM licencia WHERE dni_titular = ? AND clase = 'B'";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dni);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Date fechaEmision = rs.getDate("fecha_emision");
                    LocalDate fechaEmisionLocal = fechaEmision.toLocalDate();
                    LocalDate haceUnAno = LocalDate.now().minusYears(1);

                    if (fechaEmisionLocal.isBefore(haceUnAno)) {
                        permitido = true;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
           
        }

        return permitido;
	}

	public static int cantLicTitular(int dni) {
		
		int cantidad = 0;
        String sql = "SELECT COUNT(*) FROM licencia WHERE dni_titular = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, dni);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cantidad = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        
        }

        return cantidad;
	}

	public static void cargarLicencia(Licencia licencia) {
		
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int nuevoIdLicencia = 0;

        try {
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);  

            
            String sqlMaxId = "SELECT MAX(id_licencia) FROM licencia";
            stmt = conn.prepareStatement(sqlMaxId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                nuevoIdLicencia = rs.getInt(1) + 1;
            } else {
                nuevoIdLicencia = 1;  
            }
            
            
            String sqlUpdateVigente = "UPDATE licencia SET vigente = 'no' WHERE dni_titular = ? AND vigente = 'si'";
            stmt = conn.prepareStatement(sqlUpdateVigente);
            stmt.setInt(1, licencia.getDni_titular());
            stmt.executeUpdate();

            
            String sqlInsert = "INSERT INTO licencia (id_licencia, dni_titular, nombre_titular, apellido_titular, fecha_nac_titular, calle_titular, nro_casa_titular, clase, tipo, grupo_sang_titular, rh_titular, es_donante_titular, observaciones, fecha_emision, administrador, vigente, fecha_vencimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sqlInsert);
            stmt.setInt(1, nuevoIdLicencia);
            stmt.setInt(2, licencia.getDni_titular());
            stmt.setString(3, licencia.getNombre_titular());
            stmt.setString(4, licencia.getApellido_titular());
            stmt.setDate(5, licencia.getFecha_nac_titular());
            stmt.setString(6, licencia.getCalle_titular());
            stmt.setInt(7, licencia.getNro_casa_titular());
            stmt.setString(8, licencia.getClase());
            stmt.setString(9, licencia.getTipo());
            stmt.setString(10, licencia.getGrupo_sang_titular());
            stmt.setString(11, licencia.getRh_titular());
            stmt.setString(12, licencia.getEs_donante_titular());
            stmt.setString(13, licencia.getObservaciones());
            stmt.setDate(14, licencia.getFecha_emision());
            stmt.setString(15, licencia.getAdministrador());
            stmt.setString(16, licencia.getVigente());  
            stmt.setDate(17, licencia.getFecha_vencimiento());

            stmt.executeUpdate();

            conn.commit();  

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Deshacer la transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
		
		
	
}
