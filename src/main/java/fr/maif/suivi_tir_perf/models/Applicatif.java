package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

import java.util.List;


@Entity
public class Applicatif {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;

//    @ManyToMany
//    @JoinTable(
//        name = "Applicatif_PanSI",
//        joinColumns = {
//                @JoinColumn(name = "applicatif_id")
//        },
//            inverseJoinColumns = {
//                @JoinColumn(name = "pan_si")
//            }
//    )
//    private List<PanSI> panSI;

    public Applicatif() {}

    // The least amount of information an applicatif needs to have to be saved
    public Applicatif(String name) {
        this.name = name;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

}
