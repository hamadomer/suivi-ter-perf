package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.Fonction;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FonctionRepositoryImpl implements FonctionRepository{

    private EntityManager entityManager;

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
            entityManager.persist(fonction);
        } else {
            entityManager.merge(fonction); // Update the DB with the new values
        }
        return fonction;
    }

    @Override
    public Fonction updateFonction(Fonction fonction, int id) {
        Fonction fonctionToUpdate = getFonctionById(id);
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
