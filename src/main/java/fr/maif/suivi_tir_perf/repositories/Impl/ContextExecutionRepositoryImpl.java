package fr.maif.suivi_tir_perf.repositories.Impl;

import fr.maif.suivi_tir_perf.models.ContextExecution;
import fr.maif.suivi_tir_perf.repositories.ContextExecutionRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ContextExecutionRepositoryImpl implements ContextExecutionRepository {

    private final EntityManager entityManager;

    public ContextExecutionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ContextExecution getContextExecutionById(int id) {
        return entityManager.find(ContextExecution.class, id);
    }

    @Override
    public List<ContextExecution> getAllContextExecutions() {
        return entityManager.createQuery("from ContextExecution", ContextExecution.class).getResultList();
    }

    @Override
    public ContextExecution createContextExecution(ContextExecution contextExecution) {
        if(contextExecution.getId() == null) {
            entityManager.getTransaction().begin();
            entityManager.persist(contextExecution);
            entityManager.getTransaction().commit();
        }
        return contextExecution;
    }

    @Override
    public ContextExecution updateContextExecution(ContextExecution contextExecution) {
        ContextExecution contextExecutionToUpdate = getContextExecutionById(contextExecution.getId());

        if (contextExecutionToUpdate != null) {
            contextExecutionToUpdate.setEnvironment(contextExecution.getEnvironment());
            contextExecutionToUpdate.setComplementaryInformation(contextExecution.getComplementaryInformation());
            contextExecutionToUpdate.setPanSI(contextExecution.getPanSI());
            contextExecutionToUpdate.setTirPerf(contextExecution.getTirPerf());
            entityManager.merge(contextExecutionToUpdate);
            return contextExecutionToUpdate;
        }
        return null;
    }

    @Override
    public void deleteContextExecution(ContextExecution contextExecution) {
            entityManager.getTransaction().begin();
            ContextExecution managedContextExecution = entityManager.find(ContextExecution.class, contextExecution.getId());

            if(managedContextExecution != null) {
                entityManager.remove(managedContextExecution);
            }
            entityManager.getTransaction().commit();
    }

    @Override
    public void PurgeContextExecutions() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("TRUNCATE TABLE contextexecution CASCADE").executeUpdate();
        entityManager.getTransaction().commit();
    }
}
