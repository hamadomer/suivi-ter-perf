package fr.maif.suivi_tir_perf.repositories.Impl;

import fr.maif.suivi_tir_perf.models.RapportExecution;
import fr.maif.suivi_tir_perf.repositories.RapportExecutionRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RapportExecutionRepositoryImpl implements RapportExecutionRepository {

    private final EntityManager entityManager;

    public RapportExecutionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public RapportExecution create(RapportExecution rapportexecution) {
        if(rapportexecution.getId() == null) {
            entityManager.getTransaction().begin();
            entityManager.persist(rapportexecution);
            entityManager.getTransaction().commit();
        }
        return rapportexecution;
    }

    @Override
    public RapportExecution findById(int id) {
        return entityManager.find(RapportExecution.class, id);
    }

    @Override
    public RapportExecution update(RapportExecution rapportexecution) {
        RapportExecution rapportExecutionToUpdate = findById(rapportexecution.getId());

        if(rapportExecutionToUpdate != null) {
            rapportExecutionToUpdate.setCallsNumber(rapportexecution.getCallsNumber());
            rapportExecutionToUpdate.setErrors(rapportexecution.getErrors());
            rapportExecutionToUpdate.setDuration(rapportexecution.getDuration());
            rapportExecutionToUpdate.setSuccessRate(rapportexecution.getSuccessRate());
            rapportExecutionToUpdate.setTirPerf(rapportexecution.getTirPerf());
            entityManager.merge(rapportExecutionToUpdate);
            return rapportExecutionToUpdate;
        }
        return null;
    }

    @Override
    public List<RapportExecution> findAll() {
        return entityManager.createQuery("from RapportExecution", RapportExecution.class).getResultList();
    }

    @Override
    public void delete(RapportExecution rapportexecution) {
        entityManager.getTransaction().begin();
        RapportExecution managedRapportExecution = findById(rapportexecution.getId());

        if(managedRapportExecution != null) {
            entityManager.remove(managedRapportExecution);
        }
        entityManager.getTransaction().commit();

    }

    @Override
    public void PurgeRapportExecutions() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("TRUNCATE TABLE rapportexecution").executeUpdate();
        entityManager.getTransaction().commit();
    }
}
