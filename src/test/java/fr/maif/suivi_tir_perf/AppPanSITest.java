package fr.maif.suivi_tir_perf;

import fr.maif.suivi_tir_perf.models.AppPanSI;
import fr.maif.suivi_tir_perf.models.Applicatif;
import fr.maif.suivi_tir_perf.models.PanSI;
import fr.maif.suivi_tir_perf.repositories.Impl.AppPanSIRepositoryImpl;
import fr.maif.suivi_tir_perf.repositories.Impl.ApplicatifRepositoryImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class AppPanSITest {

    private AppPanSIRepositoryImpl appPanSIRepository;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
     protected void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("tirperf");
        appPanSIRepository = new AppPanSIRepositoryImpl(entityManagerFactory.createEntityManager());
    }

    @AfterEach
    protected void tearDown() throws Exception {
        appPanSIRepository.PurgeAppPanSI();
        if(entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreateAppPanSIShouldPass() {
        // Given

        AppPanSI appPanSI = new AppPanSI();
        Applicatif applicatif = new Applicatif("test app");
        PanSI panSI = new PanSI();
        String version = "test version";
        appPanSI.setApplicatif(applicatif);
        appPanSI.setPanId(panSI);
        appPanSI.setVersion(version);

        // When

        AppPanSI savedAppPanSI = appPanSIRepository.create(appPanSI);

        // Then

        assertEquals(savedAppPanSI, appPanSI);
    }

    @Test
    public void testCreateAppPanSIShouldFail() {

        // Given

        AppPanSI appPanSI = new AppPanSI();
        Applicatif applicatif = new Applicatif("test app 2");
        PanSI panSI = new PanSI();
        String version = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.";
        appPanSI.setApplicatif(applicatif);
        appPanSI.setPanId(panSI);
        appPanSI.setVersion(version);

        // When, Then


        assertThrows(RollbackException.class, ()-> appPanSIRepository.create(appPanSI));



    }

}
