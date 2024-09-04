package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.TirPerf;

import java.util.List;

public interface TirPerfRepository {
    TirPerf create(TirPerf tirPerf);
    TirPerf update(TirPerf tirPerf);
    TirPerf findById(Integer id);
    List<TirPerf> findAll();
    void delete(TirPerf tirPerf);
    void PurgeTirperfs();
}
