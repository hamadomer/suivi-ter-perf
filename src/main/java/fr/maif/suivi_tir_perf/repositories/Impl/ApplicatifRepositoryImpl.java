package fr.maif.suivi_tir_perf.repositories.Impl;

import fr.maif.suivi_tir_perf.models.Applicatif;
import fr.maif.suivi_tir_perf.repositories.ApplicatifRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

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
        return entityManager.find(Applicatif.class, name);
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
    public void deleteApplecatif(Applicatif applicatifToDelete) {
        entityManager.getTransaction().begin();
        Applicatif managedApplicatif = entityManager.find(Applicatif.class, applicatifToDelete.getId());
        if (managedApplicatif != null) {
            entityManager.remove(managedApplicatif);
            logger.info("applicatif deleted" + applicatifToDelete.getId());
        }
        entityManager.getTransaction().commit();
    }

}
