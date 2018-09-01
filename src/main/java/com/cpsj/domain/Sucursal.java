package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Sucursal.
 */
@Entity
@Table(name = "sucursal")
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_sucursal")
    private String nombreSucursal;

    @Column(name = "numero_sucursal")
    private Integer numeroSucursal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public Sucursal nombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
        return this;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Integer getNumeroSucursal() {
        return numeroSucursal;
    }

    public Sucursal numeroSucursal(Integer numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
        return this;
    }

    public void setNumeroSucursal(Integer numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
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
        Sucursal sucursal = (Sucursal) o;
        if (sucursal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sucursal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sucursal{" +
            "id=" + getId() +
            ", nombreSucursal='" + getNombreSucursal() + "'" +
            ", numeroSucursal=" + getNumeroSucursal() +
            "}";
    }
}
