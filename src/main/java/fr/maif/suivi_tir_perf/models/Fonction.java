package fr.maif.suivi_tir_perf.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
public class Fonction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "fonctions")
    private List<Scenario> scenario;

    public Fonction() {}
    public Fonction(String name) {
        this.name = name;
    }

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

    public List<Scenario> getScenario() {
        return scenario;
    }

    public void setScenario(List<Scenario> scenario) {
        this.scenario = scenario;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Fonction fonction = (Fonction) object;
        return Objects.equals(id, fonction.id) && Objects.equals(name, fonction.name) && Objects.equals(scenario, fonction.scenario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, scenario);
    }
}
