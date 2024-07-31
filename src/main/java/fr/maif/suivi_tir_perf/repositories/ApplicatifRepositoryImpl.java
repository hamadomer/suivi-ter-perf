package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.Applicatif;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ApplicatifRepositoryImpl implements ApplicatifRepository {

    private EntityManager entityManager;

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
            entityManager.persist(applicatif);
        } else {
            entityManager.merge(applicatif);
        }
        return applicatif;
    }

    @Override
    public Applicatif updateApplicatif(Applicatif applicatif, int id) {
        Applicatif applicatifToUpdate = getApplicatifById(id);
        if (applicatifToUpdate != null) {
            applicatifToUpdate.setName(applicatif.getName());
            applicatifToUpdate.setVersion(applicatif.getVersion());
            applicatifToUpdate.setPanSI(applicatif.getPanSI());
            applicatifToUpdate.setFonction(applicatif.getFonction());
            entityManager.merge(applicatifToUpdate);
            return  applicatifToUpdate;
        }
        return null;
    }

    @Override
    public void deleteApplicatif(int id) {

    }
}
