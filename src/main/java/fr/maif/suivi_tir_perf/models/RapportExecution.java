package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

@Entity
public class RapportExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer callsNumber;

    private Integer successRate;

    private String errors;

    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "tirPerf_id")
    private TirPerf tirPerf;

    public RapportExecution() {}
}
