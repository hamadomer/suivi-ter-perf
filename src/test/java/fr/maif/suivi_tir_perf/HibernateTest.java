package fr.maif.suivi_tir_perf;

import fr.maif.suivi_tir_perf.models.Fonction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HibernateTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateFonction() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Fonction fonction = new Fonction();
        fonction.setName("Test Fonction");

        entityManager.persist(fonction);
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Fonction persistedFonction = entityManager.find(Fonction.class, fonction.getId());

        assertNotNull(persistedFonction);
        assertEquals(fonction.getName(), persistedFonction.getName());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}