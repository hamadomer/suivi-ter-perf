package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class PanSI {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    public PanSI() {}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PanSI panSI = (PanSI) object;
        return Objects.equals(id, panSI.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
