package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class PanSI {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    public PanSI() {}

}
