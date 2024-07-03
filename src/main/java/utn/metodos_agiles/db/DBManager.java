package utn.metodos_agiles.db;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import utn.metodos_agiles.entidades.ClaseLicencia;
import utn.metodos_agiles.entidades.Contribuyente;
import utn.metodos_agiles.entidades.Licencia;
import utn.metodos_agiles.entidades.Titular;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBManager {

    private static DBManager instance;

    private EntityManager entityManager;

    private DBManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static DBManager getInstance() {
        if(instance == null) {
            EntityManagerFactory entityManagerFactory = Persistence
                    .createEntityManagerFactory("utn.metodos_agiles.db");

            instance = new DBManager(entityManagerFactory.createEntityManager());
        }

        return instance;
    }

    public void guardarTitular(Titular titular) {
        entityManager.getTransaction().begin();

        entityManager.persist(titular);

        entityManager.getTransaction().commit();
    }

	public Titular buscarPorDni(int dni) {
        Titular titular = entityManager.find(Titular.class, dni);

        if(titular != null) entityManager.detach(titular);

		return titular;
	}

    public Contribuyente buscarContribuyentePorDni(int dni) {
        Contribuyente contribuyente = entityManager.find(Contribuyente.class, dni);

        if(contribuyente != null) entityManager.detach(contribuyente);

        return contribuyente;
	}
	
		
	public List<ClaseLicencia> recuperarClasesLicencias(int dni) {
		List<Licencia> licencias = entityManager.createQuery("SELECT licencia FROM Licencia licencia WHERE licencia.titular.dni = ?1")
				.setParameter(1, dni)
				.getResultList();

		return licencias.stream().map(l -> l.getClase()).toList();
	}

    public List<Licencia> recuperarLicenciasVencidas() {
        List<Licencia> licencias =  entityManager.createQuery("SELECT licencia FROM Licencia licencia WHERE licencia.fechaVencimiento <= ?1")
                .setParameter(1, LocalDate.now())
                .getResultList();

        return licencias;
    }

    public List<Licencia> recuperarLicenciasVigentes() {
        List<Licencia> licencias =  entityManager.createQuery("SELECT licencia FROM Licencia licencia WHERE licencia.fechaVencimiento > ?1")
                .setParameter(1, LocalDate.now())
                .getResultList();

        return licencias;
    }
	
	public Boolean permitidoClaseProfesional(int dni) {
        Date fechaEmision = (Date) entityManager.createQuery("SELECT licencia.fechaEmision FROM Licencia licencia WHERE licencia.titular.dni = ?1 AND clase = 'B'")
                .setParameter(1, dni)
                .getSingleResult();

        LocalDate fechaEmisionLocal = fechaEmision.toLocalDate();
        LocalDate haceUnAno = LocalDate.now().minusYears(1);

        return fechaEmisionLocal.isBefore(haceUnAno);
	}

	public int cantLicTitular(int dni) {
        return ((Long) entityManager.createQuery("SELECT COUNT(*) FROM Licencia licencia WHERE licencia.titular.dni = ?1").
                setParameter(1, dni)
                .getSingleResult())
                .intValue();
	}

	public void cargarLicencia(Licencia licencia) {
        entityManager.getTransaction().begin();

        entityManager.createQuery("UPDATE Licencia SET vigente = false WHERE titular.dni = ?1 AND vigente = true")
                .setParameter(1, licencia.getTitular().getDni())
                .executeUpdate();
        entityManager.persist(licencia);

        entityManager.getTransaction().commit();
    }

	public static Licencia[] cargarLicenciasTitular(int dni) {
		Licencia[] licencias = new Licencia[3];

		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM licencia WHERE dni_titular = " + dni + " AND vigente = 'si'";

        try {
        	int i = 0;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			Licencia licencia;
			while(rs.next()) {
				int dni_titular = rs.getInt("dni_titular");
				String nombre_titular = rs.getString("nombre_titular");
				String apellido_titular = rs.getString("apellido_titular");
				Date fecha_nac_titular = rs.getDate("fecha_nac_titular");
				String calle_titular = rs.getString("calle_titular");
				int nro_casa_titular = rs.getInt("nro_casa_titular");
				String clase = rs.getString("clase");
				String tipo = rs.getString("tipo");
				String grupo_sang_titular = rs.getString("grupo_sang_titular");
				String rh_titular = rs.getString("rh_titular");
				String es_donante_titular = rs.getString("es_donante_titular");
				String observaciones = rs.getString("observaciones");
				Date fecha_emision = rs.getDate("fecha_emision");
				String administrador = rs.getString("administrador");
				String vigente = rs.getString("vigente");
				Date fecha_vencimiento = rs.getDate("fecha_vencimiento");

				licencia = Licencia.builder()
                        .dni_titular(dni_titular)
                        .nombre_titular(nombre_titular)
                        .apellido_titular(apellido_titular)
                        .fecha_nac_titular(fecha_nac_titular)
                        .calle_titular(calle_titular)
                        .nro_casa_titular(nro_casa_titular)
                        .clase(clase)
                        .tipo(tipo)
                        .grupo_sang_titular(grupo_sang_titular)
                        .rh_titular(rh_titular)
                        .es_donante_titular(es_donante_titular)
                        .observaciones(observaciones)
                        .fecha_emision(fecha_emision)
                        .administrador(administrador)
                        .vigente(vigente)
                        .fecha_vencimiento(fecha_vencimiento)
                        .build();

				licencias[i] = licencia;
				i++;
			}
			st.close();
			conn.close();
		} catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch(SQLException e2) {
			e2.printStackTrace();
		}

		return licencias;
	}

	public static int IDLicencia(int dni_titular, String clase) {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT id FROM licencia WHERE dni_titular = " + dni_titular + " AND clase = "
        		+ clase +" AND vigente = 'si'";
        int id = -1;
        try {
        	int i = 0;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			id = rs.getInt("id");
			st.close();
			conn.close();
		} catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch(SQLException e2) {
			e2.printStackTrace();
		}

        return id;
	}


}
