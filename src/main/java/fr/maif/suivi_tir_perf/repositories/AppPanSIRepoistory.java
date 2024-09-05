package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.AppPanSI;

import java.util.List;

public interface AppPanSIRepoistory {

    AppPanSI create(AppPanSI appPanSI);
    AppPanSI update(AppPanSI appPanSI);
    AppPanSI findById(AppPanSI.Id id);
    List<AppPanSI> findAll();
    void delete(AppPanSI appPanSI);
    void PurgeAppPanSI();
}
