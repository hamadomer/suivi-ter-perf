package fr.maif.suivi_tir_perf.repositories.Impl;

import fr.maif.suivi_tir_perf.models.Fonction;
import fr.maif.suivi_tir_perf.repositories.FonctionRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FonctionRepositoryImpl implements FonctionRepository {

    private final EntityManager entityManager;

    public FonctionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Fonction getFonctionById(int id) {
        return entityManager.find(Fonction.class, id);
    }

    @Override
    public Fonction getFonctionByName(String name) {
        return entityManager.find(Fonction.class, name);
    }

    @Override
    public List<Fonction> getAllFonctions() {
        return entityManager.createQuery("from Fonction", Fonction.class).getResultList();
    }

    @Override
    public Fonction createFonction(Fonction fonction) {
        if(fonction.getId() == null) { // check if it has id, means already in DB
            entityManager.getTransaction().begin();
            entityManager.persist(fonction);
            entityManager.getTransaction().commit();
        }
        return fonction;
    }

    @Override
    public Fonction updateFonction(Fonction fonction) {
        Fonction fonctionToUpdate = getFonctionById(fonction.getId());
        if(fonctionToUpdate != null) {
            fonctionToUpdate.setName(fonction.getName());
            fonction.setScenario(fonction.getScenario());
            entityManager.merge(fonctionToUpdate);
            return fonctionToUpdate;
        } else {
            return null; // TODO: need to make an Global Ex catcher
        }
    }

    @Override
    public void deleteFonction(Fonction fonction) {
        entityManager.remove(fonction);
    }
}
