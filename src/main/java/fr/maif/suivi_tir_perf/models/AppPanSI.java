package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class AppPanSI {
    @Id
    private String version;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private Applicatif appId;

    @ManyToOne
    @JoinColumn(name = "pansi_id")
        private PanSI panId;
}
