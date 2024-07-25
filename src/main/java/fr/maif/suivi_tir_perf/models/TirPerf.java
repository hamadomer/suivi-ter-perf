package fr.maif.suivi_tir_perf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TirPerf {

    @Id
    private Integer id;

    @Column(name = "creation_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;
}
