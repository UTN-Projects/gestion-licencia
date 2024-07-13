package utn.metodos_agiles.db;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import utn.metodos_agiles.entidades.ClaseLicencia;
import utn.metodos_agiles.entidades.Contribuyente;
import utn.metodos_agiles.entidades.Licencia;
import utn.metodos_agiles.entidades.Titular;
import utn.metodos_agiles.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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
        return entityManager.createQuery("SELECT licencia FROM Licencia licencia WHERE licencia.fechaVencimiento <= ?1")
                .setParameter(1, LocalDate.now())
                .getResultList();
    }

    public List<Licencia> recuperarLicenciasVigentes() {
        return  entityManager.createQuery("SELECT licencia FROM Licencia licencia WHERE licencia.fechaVencimiento > ?1")
                .setParameter(1, LocalDate.now())
                .getResultList();
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

        entityManager.createQuery("UPDATE Licencia SET vigente = false WHERE titular.dni = ?1 AND vigente = true AND clase =?2")
                .setParameter(1, licencia.getTitular().getDni())
                .setParameter(2, licencia.getClase())
                .executeUpdate();
        entityManager.persist(licencia);

        entityManager.getTransaction().commit();
    }

	public List<Licencia> cargarLicenciasTitular(int dni) {
        return entityManager.createQuery("SELECT licencia FROM Licencia licencia WHERE licencia.titular.dni = ?1 AND vigente = true")
				.setParameter(1, dni)
				.getResultList();
	}

	public Long IDLicencia(int dniTitular, ClaseLicencia clase) {
		return (Long) entityManager.createQuery("SELECT licencia.id FROM Licencia licencia WHERE licencia.titular.dni = ?1 AND clase = ?2 AND vigente = true")
				.setParameter(1, dniTitular)
				.setParameter(2, clase)
				.getSingleResult();
	}

	public void cargarUsuario(Usuario usuario) {

		entityManager.getTransaction().begin();

        entityManager.persist(usuario);

        entityManager.getTransaction().commit();

	}

	public Usuario verificarLogin(String usuario, String pass) {

		try {
            entityManager.getTransaction().begin();


            String jpql = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :usuario AND u.contrasena = :pass";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("usuario", usuario);
            query.setParameter("pass", pass);

            Usuario usuarioOBJ = (Usuario) query.getSingleResult();

            entityManager.getTransaction().commit();

            return usuarioOBJ;

        } catch (NoResultException e) {

            entityManager.getTransaction().rollback();
            return null;
        } catch (Exception e) {

            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        }

	}

	public Usuario buscarUser(String username) {

	    try {
	        entityManager.getTransaction().begin();

	        // Buscar el usuario por nombre de usuario
	        String jpql = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :username";
	        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
	        query.setParameter("username", username);
	        Usuario user = query.getSingleResult();

	        entityManager.getTransaction().commit();

	        return user;
	    } catch (NoResultException e) {
	        // Manejar el caso cuando no se encuentra ningún usuario con ese nombre de usuario
	        return null;
	    } catch (Exception e) {
	        entityManager.getTransaction().rollback();
	        throw new RuntimeException("Error al buscar usuario por nombre de usuario: " + username, e);
	    }
	}

	public void actualizarUsuario(Usuario usuarioActual, Usuario usuarioViejo) {

		 try {
		        entityManager.getTransaction().begin();

		        // Buscar el usuario por nombre de usuario (o cualquier otro criterio que uses)
		        Usuario usuarioEnBD = entityManager.find(Usuario.class, usuarioViejo.getId());

		        // Actualizar los campos del usuario recuperado con los nuevos valores de usuarioActual
		        usuarioEnBD.setCorreoElectronico(usuarioActual.getCorreoElectronico());
		        usuarioEnBD.setNombre(usuarioActual.getNombre());
		        usuarioEnBD.setApellido(usuarioActual.getApellido());
		        usuarioEnBD.setTelefono(usuarioActual.getTelefono());
		        usuarioEnBD.setContrasena(usuarioActual.getContrasena());
		        usuarioEnBD.setNombreUsuario(usuarioActual.getNombreUsuario());

		        entityManager.getTransaction().commit();
		    } catch (Exception e) {
		        entityManager.getTransaction().rollback();
		        throw new RuntimeException("Error al actualizar usuario", e);
		    }

	}

    public void actualizarTitular(Titular titularActual, Titular titularViejo) {

        try {
               entityManager.getTransaction().begin();

               // Buscar el usuario por nombre de usuario (o cualquier otro criterio que uses)
               Titular titularEnBD = entityManager.find(Titular.class, titularViejo.getDni());

               // Actualizar los campos del usuario recuperado con los nuevos valores de usuarioActual
               titularEnBD.setNombre(titularActual.getNombre());
               titularEnBD.setApellido(titularActual.getApellido());
               titularEnBD.setCalle(titularActual.getCalle());
               titularEnBD.setNroCasa(titularActual.getNroCasa());
               titularEnBD.setEsDonante(titularActual.getEsDonante());


               entityManager.getTransaction().commit();
           } catch (Exception e) {
               entityManager.getTransaction().rollback();
               throw new RuntimeException("Error al actualizar usuario", e);
           }

   }

}