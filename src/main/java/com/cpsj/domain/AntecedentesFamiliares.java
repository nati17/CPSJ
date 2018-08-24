package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AntecedentesFamiliares.
 */
@Entity
@Table(name = "antecedentes_familiares")
public class AntecedentesFamiliares implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vivo_af")
    private Boolean vivoAF;

    @Column(name = "causa_muerte_af")
    private String causaMuerteAF;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isVivoAF() {
        return vivoAF;
    }

    public AntecedentesFamiliares vivoAF(Boolean vivoAF) {
        this.vivoAF = vivoAF;
        return this;
    }

    public void setVivoAF(Boolean vivoAF) {
        this.vivoAF = vivoAF;
    }

    public String getCausaMuerteAF() {
        return causaMuerteAF;
    }

    public AntecedentesFamiliares causaMuerteAF(String causaMuerteAF) {
        this.causaMuerteAF = causaMuerteAF;
        return this;
    }

    public void setCausaMuerteAF(String causaMuerteAF) {
        this.causaMuerteAF = causaMuerteAF;
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
        AntecedentesFamiliares antecedentesFamiliares = (AntecedentesFamiliares) o;
        if (antecedentesFamiliares.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), antecedentesFamiliares.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AntecedentesFamiliares{" +
            "id=" + getId() +
            ", vivoAF='" + isVivoAF() + "'" +
            ", causaMuerteAF='" + getCausaMuerteAF() + "'" +
            "}";
    }
}
