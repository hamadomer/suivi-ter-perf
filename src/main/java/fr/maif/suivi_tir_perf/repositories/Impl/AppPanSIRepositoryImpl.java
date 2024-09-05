package fr.maif.suivi_tir_perf.repositories.Impl;

import fr.maif.suivi_tir_perf.models.AppPanSI;
import fr.maif.suivi_tir_perf.repositories.AppPanSIRepoistory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class AppPanSIRepositoryImpl implements AppPanSIRepoistory {
    private EntityManager entityManager;

    public AppPanSIRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public AppPanSI create(AppPanSI appPanSI) {
        if (appPanSI != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(appPanSI.getApplicatif());
            entityManager.persist(appPanSI.getPanSI());
            entityManager.persist(appPanSI);
            entityManager.getTransaction().commit();
        }
        return appPanSI;
    }

    @Override
    public AppPanSI update(AppPanSI appPanSI) {
        AppPanSI appPanSIToUpdate = findById(appPanSI.getId());
        if (appPanSIToUpdate != null) {
            entityManager.getTransaction().begin();
            appPanSIToUpdate.setApplicatif(appPanSI.getApplicatif());
            appPanSIToUpdate.setPanId(appPanSI.getPanSI());
            appPanSIToUpdate.setVersion(appPanSI.getVersion());
            entityManager.merge(appPanSIToUpdate);
            entityManager.getTransaction().commit();
        }
        return appPanSIToUpdate;
    }


    @Override
    public AppPanSI findById(AppPanSI.Id id) {
        return entityManager.find(AppPanSI.class, id);
    }

    @Override
    public List<AppPanSI> findAll() {
        TypedQuery<AppPanSI> query = entityManager.createQuery("SELECT a FROM AppPanSI a", AppPanSI.class);
        return query.getResultList();
    }

    @Override
    public void delete(AppPanSI appPanSI) {
        entityManager.getTransaction().begin();
        entityManager.remove(appPanSI);
        entityManager.getTransaction().commit();
    }

    @Override
    public void PurgeAppPanSI() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("TRUNCATE TABLE apppansi CASCADE").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM pansi WHERE id NOT IN (SELECT pansi_id FROM apppansi)").executeUpdate();
        entityManager.getTransaction().commit();
    }
}