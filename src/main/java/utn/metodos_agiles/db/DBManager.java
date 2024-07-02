package utn.metodos_agiles.db;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import entidades.ClaseLicencia;
import entidades.Contribuyente;
import entidades.Licencia;
import entidades.Titular;
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
        List licencias = entityManager.createQuery("SELECT licencia FROM Licencia licencia WHERE licencia.titular.dni = ?1")
                .setParameter(1, dni)
                .getResultList();

        return licencias.stream()
                .map(licencia -> {
                    return ((Licencia) licencia).getClase();
                }).toList();
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
	
}
