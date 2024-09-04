package fr.maif.suivi_tir_perf;

import fr.maif.suivi_tir_perf.models.ContextExecution;
import fr.maif.suivi_tir_perf.repositories.ContextExecutionRepository;
import fr.maif.suivi_tir_perf.repositories.Impl.ContextExecutionRepositoryImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContextExecutionRepositoryTest {

    private  ContextExecutionRepository contextExecutionRepository;

    @BeforeEach
    public void setUp() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
        contextExecutionRepository = new ContextExecutionRepositoryImpl(entityManagerFactory.createEntityManager());
    }

    @AfterEach
    public void tearDown() throws Exception {
        contextExecutionRepository.PurgeContextExecutions();
    }

    @Test
    public  void testCreateContextExecution() {
        // Given
        ContextExecution contextExecution = new ContextExecution("env", "info");

        // When
        ContextExecution created = contextExecutionRepository.createContextExecution(contextExecution);

        // Then
//        assertNotNull(created);
//        assertNotNull(created.getId());
//        assertEquals(created.getEnvironment(), contextExecution.getEnvironment());
//        assertEquals(created.getComplementaryInformation(), contextExecution.getComplementaryInformation());
        assertEquals(created, contextExecution);

    }

    @Test
    public void testDeleteContextExecution() {
        // Given
        ContextExecution contextExecution = new ContextExecution("env", "info");
        contextExecutionRepository.createContextExecution(contextExecution);

        // When
        contextExecutionRepository.deleteContextExecution(contextExecution);
        ContextExecution deletedContext = contextExecutionRepository.getContextExecutionById(contextExecution.getId());


        // Then
        assertNull(deletedContext);
    }

    @Test
    public void testUpdateContextExecution() {
        // Given
        ContextExecution contextExecution = new ContextExecution("env", "info");
        contextExecutionRepository.createContextExecution(contextExecution);

        // When
        contextExecution.setComplementaryInformation("Updated info");
        ContextExecution updatedVersion = contextExecutionRepository.updateContextExecution(contextExecution);

        // Then
        assertEquals(updatedVersion, contextExecution);
    }

    @Test
    public void testGetAllContextExecutions() {
        // Given
        ContextExecution contextExecution1 = new ContextExecution("env", "info");
        ContextExecution contextExecution2 = new ContextExecution("env", "info");
        contextExecutionRepository.createContextExecution(contextExecution1);
        contextExecutionRepository.createContextExecution(contextExecution2);

        List<ContextExecution> ContextExecutionsExpected = List.of(contextExecution1, contextExecution2);
        // When
        List<ContextExecution> ContextExecutionsDB = contextExecutionRepository.getAllContextExecutions();

        // Then
        ContextExecutionsDB.forEach(contextExecution -> {
            assertTrue(ContextExecutionsExpected.contains(contextExecution));
        });
    }


}
