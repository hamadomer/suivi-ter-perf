package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

import java.util.List;


@Entity
public class Applicatif {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String version;

    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;

    @ManyToMany
    @JoinTable(
        name = "Applicatif_PanSI",
        joinColumns = {
                @JoinColumn(name = "applicatif_id")
        },
            inverseJoinColumns = {
                @JoinColumn(name = "pan_si")
            }
    )
    private List<PanSI> panSI;

    public Applicatif() {}
}
