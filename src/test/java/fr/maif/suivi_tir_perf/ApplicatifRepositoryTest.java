package fr.maif.suivi_tir_perf;


import fr.maif.suivi_tir_perf.models.Applicatif;
import fr.maif.suivi_tir_perf.models.Scenario;
import fr.maif.suivi_tir_perf.models.TirPerf;
import fr.maif.suivi_tir_perf.repositories.Impl.ApplicatifRepositoryImpl;
import fr.maif.suivi_tir_perf.repositories.Impl.ScenarioRepositoryImpl;
import fr.maif.suivi_tir_perf.repositories.Impl.TirPerfRepositoryImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicatifRepositoryTest {

   private ApplicatifRepositoryImpl applicatifRepository;
   private ScenarioRepositoryImpl scenarioRepository;
   private TirPerfRepositoryImpl tirPerfRepository;
   private EntityManagerFactory entityManagerFactory;


    /**
     * Initializes the test environment by creating an EntityManagerFactory and an ApplicatifRepository instance.
     *
     * @throws Exception	if an error occurs during setup
     */
    @BeforeEach
    protected void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
        applicatifRepository = new ApplicatifRepositoryImpl(entityManagerFactory.createEntityManager());
        scenarioRepository = new ScenarioRepositoryImpl(entityManagerFactory.createEntityManager());
        tirPerfRepository = new TirPerfRepositoryImpl(entityManagerFactory.createEntityManager());
    }

    @AfterEach
    protected void tearDown() throws Exception {
        applicatifRepository.PurgeApplicatifs();
        scenarioRepository.PurgeScenarios();
        tirPerfRepository.PurgeTirperfs();
        if(entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
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
        Applicatif applicatif1 = new Applicatif("Test Applicatif1");
        Applicatif applicatif2 = new Applicatif("Test Applicatif2");
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
    public void testGetApplicatifByname() {
        // Given
        Applicatif applicatif = new Applicatif("Find me if you can");
        applicatifRepository.createApplicatif(applicatif);

        // When
        Applicatif foundApplicatif = applicatifRepository.getApplicatifByName(applicatif.getName());

        // Then
        Assertions.assertNotNull(foundApplicatif);
        assertEquals("Find me if you can", foundApplicatif.getName());
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

    @Test
    public void testGetAllAssociatedTirPerfs() {
    // Given

    Applicatif applicatif1 = new Applicatif("Test Applicatif1");

    Scenario scenario1 = new Scenario();
    scenario1.setApplicatif(applicatif1);

    TirPerf tirPerf = new TirPerf();
    tirPerf.setScenario(scenario1);

    applicatifRepository.createApplicatif(applicatif1);
    scenarioRepository.create(scenario1);
    tirPerfRepository.create(tirPerf);

    // When
    List<TirPerf> tirPerfsSaved = applicatifRepository.getAllTirPerfs(applicatif1);


    // Then

    boolean found = false;
    for (TirPerf savedTirPerf : tirPerfsSaved) {
        if (savedTirPerf.getScenario().getId().equals(scenario1.getId())) {
            found = !found;
            assertEquals(tirPerf.getScenario().getId(), savedTirPerf.getScenario().getId());
            assertEquals(tirPerf.getScenario().getApplicatif().getId(), savedTirPerf.getScenario().getApplicatif().getId());

        }
    }

    assertTrue(found);
    }


}
