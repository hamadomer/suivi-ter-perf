package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.Applicatif;
import fr.maif.suivi_tir_perf.models.Fonction;

import java.util.List;

public interface ApplicatifRepository {
    Applicatif getApplicatifById(int id);
    Applicatif getApplicatifByName(String name);
    List<Applicatif> getAllApplicatifs();
    Applicatif createApplicatif(Applicatif applicatif);
    Applicatif updateApplicatif(Applicatif applicatif, int id);

    void deleteApplicatif(int id);
}
