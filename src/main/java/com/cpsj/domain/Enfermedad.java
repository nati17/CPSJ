package com.cpsj.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.cpsj.domain.enumeration.EnfermedadesEnum;

/**
 * A Enfermedad.
 */
@Entity
@Table(name = "enfermedad")
public class Enfermedad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "valor", nullable = false)
    private EnfermedadesEnum valor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnfermedadesEnum getValor() {
        return valor;
    }

    public Enfermedad valor(EnfermedadesEnum valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(EnfermedadesEnum valor) {
        this.valor = valor;
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
        Enfermedad enfermedad = (Enfermedad) o;
        if (enfermedad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enfermedad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Enfermedad{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
