package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.Scenario;

import java.util.List;

public interface ScenarioRepository {
     Scenario create(Scenario scenario);
     Scenario findById(Integer id);
     List<Scenario> findAll();
     Scenario update(Scenario scenario);
     void delete(Scenario scenario);
     void PurgeScenarios();

}
