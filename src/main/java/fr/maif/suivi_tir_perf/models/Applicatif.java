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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public List<PanSI> getPanSI() {
        return panSI;
    }

    public void setPanSI(List<PanSI> panSI) {
        this.panSI = panSI;
    }
}
