package fr.maif.suivi_tir_perf.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PanSI {

    @Id
    private Integer id;

    private String version;

    @ManyToMany(mappedBy = "panSI")
    private List<Applicatif> applicatif;
}
