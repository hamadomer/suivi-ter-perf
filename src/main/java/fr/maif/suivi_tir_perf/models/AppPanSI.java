package fr.maif.suivi_tir_perf.models;


import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

@Entity
public class AppPanSI {
    
    @Embeddable
    public static class Id implements Serializable{
        private String version;

        @ManyToOne(cascade = CascadeType.ALL)
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

        public Applicatif getAppId() {
            return appId;
        }

        protected void setAppId(Applicatif appId) {
            this.appId = appId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public PanSI getPanId() {
            return panId;
        }

        public void setPanId(PanSI panId) {
            this.panId = panId;
        }
    }
    
    @EmbeddedId
    private Id id = new Id();

    public Id getId() {
        return this.id;
    }

    public PanSI getPanSI() {
        return id.getPanId();
    }

    public Applicatif getApplicatif() {
        return  id.getAppId();
    }

    public String getVersion() {
        return id.getVersion();
    }

    public void setApplicatif(Applicatif applicatif) {
        id.setAppId(applicatif);
    }

    public void setPanId(PanSI panId) {
        id.setPanId(panId);
    }

    public void setVersion(String version) {
        id.setVersion(version);
    }
}
