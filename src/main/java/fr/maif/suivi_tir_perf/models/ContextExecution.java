package fr.maif.suivi_tir_perf.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ContextExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pansiId")
    private PanSI panSI;

    private String environment;

    private String complementaryInformation;

    @ManyToOne
    @JoinColumn(name = "tirPerfId")
    private TirPerf tirPerf;

    public ContextExecution() {}

    public ContextExecution(String environment, String complementaryInformation) {
        this.environment = environment;
        this.complementaryInformation = complementaryInformation;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId () {
        return this.id;
    }


    public PanSI getPanSI() {
        return panSI;
    }

    public void setPanSI(PanSI panSI) {
        this.panSI = panSI;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getComplementaryInformation() {
        return complementaryInformation;
    }

    public void setComplementaryInformation(String complementaryInformation) {
        this.complementaryInformation = complementaryInformation;
    }

    public TirPerf getTirPerf() {
        return tirPerf;
    }

    public void setTirPerf(TirPerf tirPerf) {
        this.tirPerf = tirPerf;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ContextExecution that = (ContextExecution) object;
        return Objects.equals(id, that.id) && Objects.equals(panSI, that.panSI) && Objects.equals(environment, that.environment) && Objects.equals(complementaryInformation, that.complementaryInformation) && Objects.equals(tirPerf, that.tirPerf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, panSI, environment, complementaryInformation, tirPerf);
    }
}
