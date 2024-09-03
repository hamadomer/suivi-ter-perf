package fr.maif.suivi_tir_perf.models;


import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AppPanSI {
    
    @Embeddable
    public static class Id implements Serializable{
        private String version;

        @ManyToOne
        @JoinColumn(name = "app_id")
        private Applicatif appId;

        @ManyToOne
        @JoinColumn(name = "pansi_id")
        private PanSI panId;

        @Override
        public int hashCode() {
            return Objects.hash(appId, panId, version);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Id other = (Id) obj;
            return Objects.equals(appId, other.appId) && Objects.equals(panId, other.panId) && Objects.equals(version, other.version);
        }
        
    }
    
    @EmbeddedId
    private Id id = new Id();
    
}
