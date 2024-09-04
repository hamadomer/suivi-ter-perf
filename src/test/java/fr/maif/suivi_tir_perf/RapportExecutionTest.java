package fr.maif.suivi_tir_perf;

import fr.maif.suivi_tir_perf.models.RapportExecution;
import fr.maif.suivi_tir_perf.repositories.Impl.RapportExecutionRepositoryImpl;
import fr.maif.suivi_tir_perf.repositories.RapportExecutionRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RapportExecutionTest {

    private RapportExecutionRepository rapportExecutionRepository;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
        rapportExecutionRepository = new RapportExecutionRepositoryImpl(entityManagerFactory.createEntityManager());
    }

    @AfterEach
    protected void tearDown() throws Exception {
        rapportExecutionRepository.PurgeRapportExecutions();
        if(entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateRapportExecution() {
        // Given
        RapportExecution rapportExecution = new RapportExecution();
        rapportExecution.setSuccessRate(100);

        // When
        RapportExecution savedRapportExecution = rapportExecutionRepository.create(rapportExecution);

        // Then
        assertEquals(savedRapportExecution, rapportExecution);
    }

    @Test
    public void testUpdateRapportExecution() {
        // Given
        RapportExecution rapportExecution = new RapportExecution();
        rapportExecution.setSuccessRate(100);
        RapportExecution savedRapportExecution = rapportExecutionRepository.create(rapportExecution);

        // When
        savedRapportExecution.setDuration(90);
        RapportExecution updatedRapportExecution = rapportExecutionRepository.update(savedRapportExecution);

        // Then
        assertEquals(savedRapportExecution, rapportExecution);
    }

    @Test
    public void testDeleteRapportExecution() {
        // Given
        RapportExecution rapportExecution = new RapportExecution();
        rapportExecutionRepository.create(rapportExecution);

        // When
        rapportExecutionRepository.delete(rapportExecution);
        RapportExecution deletedRapportExecution = rapportExecutionRepository.findById(rapportExecution.getId());

        // Then
        assertNull(deletedRapportExecution);
    }

    @Test
    public void testGetAllRapportExecutions() {

        // Given
        RapportExecution rapportExecution1 = new RapportExecution();
        RapportExecution rapportExecution2 = new RapportExecution();

        // TODO understand why JPA update all instance of objects even those who are in some data structures
        List<RapportExecution> rapportExecutionsExpected = List.of(rapportExecution1,rapportExecution2);

        rapportExecutionRepository.create(rapportExecution1);
        rapportExecutionRepository.create(rapportExecution2);


        // When
        List<RapportExecution> rapportExecutionsSaved = rapportExecutionRepository.findAll();

        // Then
        rapportExecutionsSaved.forEach(rapportExecution -> {
            assertTrue(rapportExecutionsExpected.contains(rapportExecution));
        });

    }
}
