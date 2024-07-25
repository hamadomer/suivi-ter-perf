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
public class ContextExecution {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pansiId")
    private PanSI panSI;

    private String environment;

    private String complementaryInformation;

    @ManyToOne
    @JoinColumn(name = "tirPerfId")
    private TirPerf tirPerf;
}
