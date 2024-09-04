package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;


@Entity
public class Applicatif {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;

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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Applicatif other = (Applicatif) obj;
        return Objects.equals(id, other.id);
    }

}
