package fr.maif.suivi_tir_perf.models;

import jakarta.persistence.*;
import java.time.LocalDate;

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
}
