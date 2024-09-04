package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class
Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "applicatif_id")
    private Applicatif applicatif;

    @ManyToMany
    @JoinTable(
            name = "scenario_fonction",
            joinColumns = {
                    @JoinColumn(name = "scenario_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "fonction_id")
            }
    )
    private List<Fonction> fonctions;

    public Scenario() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Applicatif getApplicatif() {
        return applicatif;
    }

    public void setApplicatif(Applicatif applicatif) {
        this.applicatif = applicatif;
    }

    public List<Fonction> getFonctions() {
        return fonctions;
    }

    public void setFonctions(List<Fonction> fonctions) {
        this.fonctions = fonctions;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Scenario scenario = (Scenario) object;
        return Objects.equals(id, scenario.id) && Objects.equals(description, scenario.description) && Objects.equals(applicatif, scenario.applicatif) && Objects.equals(fonctions, scenario.fonctions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, applicatif, fonctions);
    }
}
