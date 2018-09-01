package com.cpsj.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.cpsj.domain.enumeration.BebidasEnum;

/**
 * A Bebida.
 */
@Entity
@Table(name = "bebida")
public class Bebida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "valor", nullable = false)
    private BebidasEnum valor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BebidasEnum getValor() {
        return valor;
    }

    public Bebida valor(BebidasEnum valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(BebidasEnum valor) {
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
        Bebida bebida = (Bebida) o;
        if (bebida.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bebida.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bebida{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
