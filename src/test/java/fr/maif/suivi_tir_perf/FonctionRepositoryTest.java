package fr.maif.suivi_tir_perf;

import fr.maif.suivi_tir_perf.models.Fonction;
import fr.maif.suivi_tir_perf.repositories.Impl.FonctionRepositoryImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        fonctionRepository.PurgeFonctions();
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateFonction() {
        // Given
        Fonction fonction = new Fonction("Test Fonction");

        // When
        Fonction fonctionDB = fonctionRepository.createFonction(fonction);

        //
        assertEquals(fonctionDB, fonction);
    }

    @Test
    public void testUpdateFonction() {
        // Given
        Fonction fonction = new Fonction("Test Fonction");
        fonction = fonctionRepository.createFonction(fonction);

        // When
        fonction.setName("Updated Fonction");
        Fonction fonctionDB = fonctionRepository.updateFonction(fonction);

        // Then
        assertEquals(fonctionDB, fonction);
    }

    @Test
    public void testDeleteFonction() {
        // Given
        Fonction fonction = new Fonction("Delete");
        fonction = fonctionRepository.createFonction(fonction);

        // When
        fonctionRepository.deleteFonction(fonction);
        Fonction deletedFonction = fonctionRepository.getFonctionById(fonction.getId());

        //
        assertNull(deletedFonction);
    }

    @Test
    public void testGetAllFonctions() {
        // Given
        Fonction fonction1 = new Fonction("Test Fonction");
        Fonction fonction2 = new Fonction("Test Fonction1");
        fonctionRepository.createFonction(fonction1);
        fonctionRepository.createFonction(fonction2);

        List<Fonction> fonctionsExpected = List.of(fonction1, fonction2);

        // When
        List<Fonction> fonctionsDB = fonctionRepository.getAllFonctions();

        // Then

        fonctionsDB.forEach(fonction -> {
            assertTrue(fonctionsExpected.contains(fonction));
        });
    }

    @Test
    public void testGetFonctionByName () {

        // Given
        Fonction fonction = new Fonction("Test Fonction");
        fonctionRepository.createFonction(fonction);

        // When
        Fonction savedFonction = fonctionRepository.getFonctionByName(fonction.getName());

        // Then
        assertNotNull(savedFonction);
        assertEquals(savedFonction.getName(), fonction.getName());
    }
}