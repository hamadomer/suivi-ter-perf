package fr.maif.suivi_tir_perf.repositories.Impl;

import fr.maif.suivi_tir_perf.models.TirPerf;
import fr.maif.suivi_tir_perf.repositories.TirPerfRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TirPerfRepositoryImpl implements TirPerfRepository {
    private EntityManager entityManager;

    public TirPerfRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public TirPerf create(TirPerf tirPerf) {
        if(tirPerf.getId() == null) {
            entityManager.getTransaction().begin();
            entityManager.persist(tirPerf);
            entityManager.getTransaction().commit();
        }
        return tirPerf;
    }

    @Override
    public TirPerf update(TirPerf tirPerf) {
        TirPerf tirPerfToUpdate = entityManager.find(TirPerf.class, tirPerf.getId());
        if(tirPerfToUpdate != null) {
            tirPerfToUpdate.setDate(tirPerf.getDate());
            tirPerfToUpdate.setScenario(tirPerf.getScenario());
            entityManager.merge(tirPerfToUpdate);
            return tirPerfToUpdate;
        }
        return null;
    }

    @Override
    public TirPerf findById(Integer id) {
        return entityManager.find(TirPerf.class, id);
    }

    @Override
    public List<TirPerf> findAll() {
        return entityManager.createQuery("from TirPerf", TirPerf.class).getResultList();
    }

    @Override
    public void delete(TirPerf tirPerf) {
        entityManager.getTransaction().begin();
        TirPerf managedTirPerf = findById(tirPerf.getId());

        if(managedTirPerf != null) {
            entityManager.remove(managedTirPerf);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void PurgeTirperfs() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("TRUNCATE TABLE tirperf CASCADE").executeUpdate();
        entityManager.getTransaction().commit();
    }
}
