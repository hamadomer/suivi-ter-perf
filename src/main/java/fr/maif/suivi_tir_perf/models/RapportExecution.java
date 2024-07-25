package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RapportExecution {

    @Id
    private Integer id;

    private Integer callsNumber;

    private Integer successRate;

    private String errors;

    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "tirPerf_id")
    private TirPerf tirPerf;
}
