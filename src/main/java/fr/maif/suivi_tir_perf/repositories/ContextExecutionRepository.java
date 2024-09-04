package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.ContextExecution;

import java.util.List;

public interface ContextExecutionRepository {
    ContextExecution getContextExecutionById(int id);
    List<ContextExecution> getAllContextExecutions();
    ContextExecution createContextExecution(ContextExecution contextExecution);
    ContextExecution updateContextExecution(ContextExecution contextExecution);
    void deleteContextExecution(ContextExecution contextExecution);
    void PurgeContextExecutions();
}
