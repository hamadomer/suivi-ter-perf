package fr.maif.suivi_tir_perf.repositories.Impl;

import fr.maif.suivi_tir_perf.models.Scenario;
import fr.maif.suivi_tir_perf.repositories.ScenarioRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ScenarioRepositoryImpl implements ScenarioRepository {

    private EntityManager entityManager;

    public ScenarioRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Scenario create(Scenario scenario) {
        if(scenario.getId() == null) {
            entityManager.getTransaction().begin();
            entityManager.persist(scenario);
            entityManager.getTransaction().commit();
        }
        return scenario;
    }

    @Override
    public Scenario findById(Integer id) {
        return entityManager.find(Scenario.class, id);
    }

    @Override
    public List<Scenario> findAll() {
        return entityManager.createQuery("from Scenario").getResultList();
    }

    @Override
    public Scenario update(Scenario scenario) {
        Scenario scenarioToUpdate = findById(scenario.getId());
        if(scenarioToUpdate != null) {
            scenarioToUpdate.setApplicatif(scenario.getApplicatif());
            scenarioToUpdate.setDescription(scenario.getDescription());
            scenarioToUpdate.setFonctions(scenario.getFonctions());
            entityManager.merge(scenarioToUpdate);
            return scenarioToUpdate;
        }
        return null;
    }

    @Override
    public void delete(Scenario scenario) {
        entityManager.getTransaction().begin();
        Scenario managedScenario = findById(scenario.getId());

        if(managedScenario != null) {
            entityManager.remove(managedScenario);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void PurgeScenarios() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("TRUNCATE TABLE scenario CASCADE").executeUpdate();
        entityManager.getTransaction().commit();
    }
}
