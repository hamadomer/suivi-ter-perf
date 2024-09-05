package fr.maif.suivi_tir_perf;

import fr.maif.suivi_tir_perf.models.Scenario;
import fr.maif.suivi_tir_perf.models.TirPerf;
import fr.maif.suivi_tir_perf.repositories.Impl.TirPerfRepositoryImpl;
import fr.maif.suivi_tir_perf.repositories.TirPerfRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TirPerfRepositoryTest {

    private TirPerfRepository tirPerfRepository;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
        tirPerfRepository = new TirPerfRepositoryImpl(entityManagerFactory.createEntityManager());
    }

    @AfterEach
    protected  void tearDown() throws Exception {
        tirPerfRepository.PurgeTirperfs();
        if(entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateTirPerf() {
        // Given
        TirPerf tirPerf = new TirPerf();
        tirPerf.setDate(LocalDate.of(2024, 8, 2));

        // When
       TirPerf savedTirPerf = tirPerfRepository.create(tirPerf);

        // Then
        assertEquals(savedTirPerf, tirPerf);
    }

    @Test
    public void testUpdateTirPerf() {
        // Given
        TirPerf tirPerf = new TirPerf();
        tirPerf.setDate(LocalDate.of(2024, 9, 3));
        TirPerf savedTirPerf = tirPerfRepository.create(tirPerf);

        // When
        savedTirPerf.setDate(LocalDate.of(1999, 9, 9));
        TirPerf updatedTirPerf = tirPerfRepository.update(savedTirPerf);

        // Then
        assertEquals(savedTirPerf, updatedTirPerf);
    }

    @Test
    public void testDeleteTirPerf() {
        // Given
        TirPerf tirPerf = new TirPerf();
        tirPerf.setDate(LocalDate.of(2024, 9, 3));
        tirPerfRepository.create(tirPerf);

        // When
        tirPerfRepository.delete(tirPerf);
        TirPerf deletedTirPerf = tirPerfRepository.findById(tirPerf.getId());

        // Then
        assertNull(deletedTirPerf);
    }

    @Test
    public void testGetAllTirPerfs() {

        // Given
        TirPerf tirPerf1 = new TirPerf();
        tirPerf1.setDate(LocalDate.of(2024, 9, 3));
        TirPerf tirPerf2 = new TirPerf();
        tirPerf2.setDate(LocalDate.of(1999, 9, 9));

        tirPerfRepository.create(tirPerf1);
        tirPerfRepository.create(tirPerf2);

        List<TirPerf> tirperfsExpected = List.of(tirPerf1, tirPerf2);

        // When
        List<TirPerf> tirPerfsSaved = tirPerfRepository.findAll();

        // Then
        tirPerfsSaved.forEach(tirPerf -> {
            assertTrue(tirperfsExpected.contains(tirPerf));
        });
    }

}
