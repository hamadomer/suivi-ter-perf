package fr.maif.suivi_tir_perf;

import fr.maif.suivi_tir_perf.models.Fonction;
import fr.maif.suivi_tir_perf.repositories.Impl.FonctionRepositoryImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FonctionRepositoryTest {

    private FonctionRepositoryImpl fonctionRepository;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
        fonctionRepository = new FonctionRepositoryImpl(entityManagerFactory.createEntityManager());
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateFonction() {
        Fonction fonction = new Fonction("Test Fonction");
        fonction = fonctionRepository.createFonction(fonction);
        assertNotNull(fonction.getId());
    }

    @Test
    public void testUpdateFonction() {
        Fonction fonction = new Fonction("Test Fonction");
        fonction = fonctionRepository.createFonction(fonction);
        fonction.setName("Updated Fonction");
        fonctionRepository.updateFonction(fonction);
        assertEquals("Updated Fonction", fonction.getName());
    }

    @Test
    public void testDeleteFonction() {
        Fonction fonction = new Fonction("Delete");
        fonction = fonctionRepository.createFonction(fonction);
        fonctionRepository.deleteFonction(fonction);
        assertNull(fonctionRepository.getFonctionById(fonction.getId()));
    }
}