package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class PanSI {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String version;

    @ManyToMany(mappedBy = "panSI")
    private List<Applicatif> applicatif;

    public PanSI() {}

    public PanSI(Integer id, String version, List<Applicatif> applicatif) {
        this.id = id;
        this.version = version;
        this.applicatif = applicatif;
    }
}
