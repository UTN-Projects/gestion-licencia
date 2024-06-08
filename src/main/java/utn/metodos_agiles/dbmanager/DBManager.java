package utn.metodos_agiles.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DBManager {
	private static String url = "jdbc:mysql://localhost:3306/gestion-licencia";
	private static String user = "root";
	private static String pass = "Ezequiel98";
	
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
}
