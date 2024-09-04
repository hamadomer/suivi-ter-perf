package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.RapportExecution;

import java.util.List;

public interface RapportExecutionRepository {
    RapportExecution create (RapportExecution rapportexecution);
    RapportExecution findById(int id);
    RapportExecution update(RapportExecution rapportexecution);
    List<RapportExecution> findAll();
    void delete(RapportExecution rapportexecution);
    void PurgeRapportExecutions();
}
