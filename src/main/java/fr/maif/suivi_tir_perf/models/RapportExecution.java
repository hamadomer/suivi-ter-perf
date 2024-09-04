package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

import java.util.Objects;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCallsNumber() {
        return callsNumber;
    }

    public void setCallsNumber(Integer callsNumber) {
        this.callsNumber = callsNumber;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
        RapportExecution that = (RapportExecution) object;
        return Objects.equals(id, that.id) && Objects.equals(callsNumber, that.callsNumber) && Objects.equals(successRate, that.successRate) && Objects.equals(errors, that.errors) && Objects.equals(duration, that.duration) && Objects.equals(tirPerf, that.tirPerf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, callsNumber, successRate, errors, duration, tirPerf);
    }
}
