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
       applicatif = applicatifRepository.createApplicatif(applicatif);

       // Then
       assertNotNull(applicatif.getId());
       assertEquals("Test Applicatif", applicatif.getName());

    }

    @Test
    public void testGetAllApplicatifs() {
        // Given
        Applicatif applicatif1 = new Applicatif("Test Applicatif");
        Applicatif applicatif2 = new Applicatif("Test Applicatif 2");
        applicatif1 = applicatifRepository.createApplicatif(applicatif1);
        applicatif2 = applicatifRepository.createApplicatif(applicatif2);
        
        List<Applicatif> applicatifsExpected = List.of(applicatif1, applicatif2);

        // When
        List<Applicatif> applicatifs = applicatifRepository.getAllApplicatifs();

        // Then
        assertEquals(2, applicatifs.size());
        
        applicatifs.forEach(app->{
            Assertions.assertTrue(applicatifsExpected.contains(app));
        });
        
    }


    @Test
    public void testUpdateApplicatifName() {
        // Given
        Applicatif applicatif = new Applicatif("Test Applicatif");
        applicatif = applicatifRepository.createApplicatif(applicatif);
        String newName = "Updated Applicatif Name";

        // When
        applicatif.setName(newName);
        Applicatif updatedApplicatif = applicatifRepository.updateApplicatif(applicatif);

        // Then
        assertEquals(newName, updatedApplicatif.getName());
        
        updatedApplicatif = applicatifRepository.getApplicatifById(applicatif.getId());
        assertEquals(newName, updatedApplicatif.getName());
    }

    @Test
    public void testDeleteApplicatif() {
        // Given
        Applicatif applicatif = new Applicatif("isDeleted5444");
        Applicatif savedApplicatif = applicatifRepository.createApplicatif(applicatif);

        // When
        applicatifRepository.deleteApplecatif(savedApplicatif);

        // Then
        assertNull(applicatifRepository.getApplicatifById(savedApplicatif.getId()));
    }
}
