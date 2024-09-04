package fr.maif.suivi_tir_perf.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class TirPerf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "creation_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    public TirPerf() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TirPerf tirPerf = (TirPerf) object;
        return Objects.equals(id, tirPerf.id) && Objects.equals(date, tirPerf.date) && Objects.equals(scenario, tirPerf.scenario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, scenario);
    }
}
