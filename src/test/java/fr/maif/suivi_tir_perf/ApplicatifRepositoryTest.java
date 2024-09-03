package fr.maif.suivi_tir_perf;


import fr.maif.suivi_tir_perf.models.Applicatif;
import fr.maif.suivi_tir_perf.repositories.Impl.ApplicatifRepositoryImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicatifRepositoryTest {

   private ApplicatifRepositoryImpl applicatifRepository;

    /**
     * Initializes the test environment by creating an EntityManagerFactory and an ApplicatifRepository instance.
     *
     * @throws Exception	if an error occurs during setup
     */
    @BeforeEach
    protected void setUp() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
        applicatifRepository = new ApplicatifRepositoryImpl(entityManagerFactory.createEntityManager());
    }


    /**
     * Tests the creation of an Applicatif entity.
     *
     * Verifies that the applicatifRepository can successfully create a new Applicatif instance and that the created instance has a valid ID.
     *
     * @return          none
     */
    @Test
    public void testCreateApplicatif() {
       // Given
       Applicatif applicatif = new Applicatif("Test Applicatif");

       // When
       applicatifRepository.createApplicatif(applicatif);

       Integer test = 0;

       // Then
       assertNotNull(applicatif.getId());
       assertEquals("Test Applicatif", applicatif.getName());

    }

    @Test
    public void testGetAllApplicatifs() {
        // Given
        Applicatif applicatif1 = new Applicatif("Test Applicatif");
        Applicatif applicatif2 = new Applicatif("Test Applicatif 2");
        applicatifRepository.createApplicatif(applicatif1);
        applicatifRepository.createApplicatif(applicatif2);



        // When
        List<Applicatif> applicatifs = applicatifRepository.getAllApplicatifs();

        // Then
        assertNotNull(applicatifs);
        assertTrue(applicatifs.size() >= 2);
        Applicatif retrievedApplicatif = applicatifs.iterator().next();
        Assertions.assertEquals(applicatif1.getName(), retrievedApplicatif.getName()); // Check if the retrieved instance matches the created instance
    }


    @Test
    public void testUpdateApplicatifName() {
        // Given
        Applicatif applicatif = new Applicatif("Test Applicatif");
        Applicatif savedApplicatif = applicatifRepository.createApplicatif(applicatif);
        String newName = "Updated Applicatif Name";

        // When
        applicatif.setName(newName);
        applicatifRepository.updateApplicatif(applicatif, savedApplicatif.getId());

        // Then
        Applicatif updatedApplicatif = applicatifRepository.getApplicatifById(applicatif.getId());
        assertNotNull(updatedApplicatif);
        assertEquals(newName, updatedApplicatif.getName());
    }

    @Test
    public void testDeleteApplicatif() {
        // Given
        Applicatif applicatif = new Applicatif("isDeleted");

        // When
        applicatifRepository.createApplicatif(applicatif);
        applicatifRepository.deleteApplecatif(applicatif);

        // Then
        assertNull(applicatifRepository.getApplicatifById(applicatif.getId()));

    }
}
