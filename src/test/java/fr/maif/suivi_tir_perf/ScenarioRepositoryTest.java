package fr.maif.suivi_tir_perf;

import fr.maif.suivi_tir_perf.models.Scenario;
import fr.maif.suivi_tir_perf.repositories.Impl.ScenarioRepositoryImpl;
import fr.maif.suivi_tir_perf.repositories.ScenarioRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScenarioRepositoryTest {

    private ScenarioRepository scenarioRepository;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
        scenarioRepository = new ScenarioRepositoryImpl(entityManagerFactory.createEntityManager());
    }

    @AfterEach
    protected void tearDown() throws Exception {
        scenarioRepository.PurgeScenarios();
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateScenario() {
        // Given
        Scenario scenario = new Scenario();
        scenario.setDescription("Description");

        // When
        Scenario savedScenario = scenarioRepository.create(scenario);

        // Then
        assertEquals(scenario, savedScenario);
    }

    @Test
    public void testUpdateScenario() {
        // Given
        Scenario scenario = new Scenario();
        scenario.setDescription("Description");
        Scenario savedScenario = scenarioRepository.create(scenario);

        // When
       savedScenario.setDescription("Description updated");
       Scenario updatedScenario = scenarioRepository.update(savedScenario);

       // Then
        assertEquals(savedScenario, updatedScenario);
    }

    @Test
    public void testDeleteScenario() {
        // Given
        Scenario scenario = new Scenario();
        scenario.setDescription("Description");
        scenarioRepository.create(scenario);

        // When
        scenarioRepository.delete(scenario);
        Scenario deletedScenario = scenarioRepository.findById(scenario.getId());

        // Then
        assertNull(deletedScenario);
    }

    @Test
    public void testGetAllScenarios() {

        // Given
        Scenario scenario1 = new Scenario();
        scenario1.setDescription("Description");
        Scenario scenario2 = new Scenario();
        scenario2.setDescription("Description2");

        scenarioRepository.create(scenario1);
        scenarioRepository.create(scenario2);

        List<Scenario> scenariosExpected = List.of(scenario1, scenario2);

        // When
        List<Scenario> scenariosSaved = scenarioRepository.findAll();

        // Then

        scenariosSaved.forEach(scenario -> {
            assertTrue(scenariosExpected.contains(scenario));
        });

    }

}
