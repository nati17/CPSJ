package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Calendario.
 */
@Entity
@Table(name = "calendario")
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_calendario")
    private String idCalendario;

    @Column(name = "anio_calendario")
    private String anioCalendario;

    @Column(name = "mes_calendario")
    private String mesCalendario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCalendario() {
        return idCalendario;
    }

    public Calendario idCalendario(String idCalendario) {
        this.idCalendario = idCalendario;
        return this;
    }

    public void setIdCalendario(String idCalendario) {
        this.idCalendario = idCalendario;
    }

    public String getAnioCalendario() {
        return anioCalendario;
    }

    public Calendario anioCalendario(String anioCalendario) {
        this.anioCalendario = anioCalendario;
        return this;
    }

    public void setAnioCalendario(String anioCalendario) {
        this.anioCalendario = anioCalendario;
    }

    public String getMesCalendario() {
        return mesCalendario;
    }

    public Calendario mesCalendario(String mesCalendario) {
        this.mesCalendario = mesCalendario;
        return this;
    }

    public void setMesCalendario(String mesCalendario) {
        this.mesCalendario = mesCalendario;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Calendario calendario = (Calendario) o;
        if (calendario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), calendario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Calendario{" +
            "id=" + getId() +
            ", idCalendario='" + getIdCalendario() + "'" +
            ", anioCalendario='" + getAnioCalendario() + "'" +
            ", mesCalendario='" + getMesCalendario() + "'" +
            "}";
    }
}
