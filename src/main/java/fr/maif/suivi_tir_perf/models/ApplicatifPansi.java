package fr.maif.suivi_tir_perf.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ApplicatifPansi {

    @Id
    private String version;

    @ManyToOne
    private Applicatif applicatifId;

    @ManyToOne
    private PanSI pansiId;

    public ApplicatifPansi() {

    }


}
