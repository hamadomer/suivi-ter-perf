package fr.maif.suivi_tir_perf.repositories;

import fr.maif.suivi_tir_perf.models.Fonction;

import java.util.List;

public interface FonctionRepository {
    Fonction getFonctionById(int id);
    Fonction getFonctionByName(String name);
    List<Fonction> getAllFonctions();
    Fonction createFonction(Fonction fonction);
    Fonction updateFonction(Fonction fonction, int id);
    void deleteFonction(Fonction fonction);
}
