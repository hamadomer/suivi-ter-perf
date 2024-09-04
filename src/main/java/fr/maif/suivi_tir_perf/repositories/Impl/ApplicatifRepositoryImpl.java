package fr.maif.suivi_tir_perf.repositories.Impl;

import fr.maif.suivi_tir_perf.models.Applicatif;
import fr.maif.suivi_tir_perf.models.TirPerf;
import fr.maif.suivi_tir_perf.repositories.ApplicatifRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Transactional
public class ApplicatifRepositoryImpl implements ApplicatifRepository {

    Logger logger = Logger.getLogger(ApplicatifRepositoryImpl.class.getName());

    private final EntityManager entityManager;

    public ApplicatifRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Applicatif getApplicatifById(int id) {
        return entityManager.find(Applicatif.class, id);
    }

    @Override
    public Applicatif getApplicatifByName(String name) {
        try {
            return entityManager.createQuery("SELECT a FROM Applicatif a WHERE a.name = :name", Applicatif.class)
                                .setParameter("name", name)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Applicatif> getAllApplicatifs() {
        return entityManager.createQuery("from Applicatif", Applicatif.class).getResultList();
    }

    @Override
    public Applicatif createApplicatif(Applicatif applicatif) {
        if(applicatif.getId() == null) {
            entityManager.getTransaction().begin();
            entityManager.persist(applicatif);
            entityManager.getTransaction().commit();
            logger.info("applicatif created" + applicatif.getId());
        }
        return applicatif;
    }

    @Override
    public Applicatif updateApplicatif(Applicatif applicatif) {
        Applicatif applicatifToUpdate = getApplicatifById(applicatif.getId());
        if (applicatifToUpdate != null) {
            applicatifToUpdate.setName(applicatif.getName());
            applicatifToUpdate.setFonction(applicatif.getFonction());
            entityManager.merge(applicatifToUpdate);
            return  applicatifToUpdate;
        }
        return null;
    }

     @Override
public List<TirPerf> getAllTirPerfs(Applicatif applicatif) {
    try {
        Query query = entityManager.createNativeQuery("SELECT t.* FROM tirperf t JOIN scenario s ON t.scenario_id = s.id WHERE s.applicatif_id = ?", TirPerf.class);
        query.setParameter(1, applicatif.getId());
        return (List<TirPerf>) query.getResultList();
    } catch (NoResultException e) {
        return Collections.emptyList();
    }
}

    @Override
    public void deleteApplecatif(Applicatif applicatifToDelete) {
        entityManager.getTransaction().begin();
        Applicatif managedApplicatif = entityManager.find(Applicatif.class, applicatifToDelete.getId());
        if (managedApplicatif != null) {
            entityManager.remove(managedApplicatif);
            logger.info("applicatif deleted" + applicatifToDelete.getId());
        }
        entityManager.getTransaction().commit();
    }



    @Override
    public void PurgeApplicatifs() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("TRUNCATE TABLE APPLICATIF CASCADE").executeUpdate();
        entityManager.getTransaction().commit();
    }


}
