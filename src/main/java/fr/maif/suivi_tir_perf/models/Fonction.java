package fr.maif.suivi_tir_perf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fonction {

    @Id
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "fonctions")
    private List<Scenario> scenario;
}
