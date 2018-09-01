package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Digitalizacion.
 */
@Entity
@Table(name = "digitalizacion")
public class Digitalizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_digitalizacion")
    private String idDigitalizacion;

    @Column(name = "descripcion_digitalizacion")
    private String descripcionDigitalizacion;

    @Column(name = "elemento_digitalizacion")
    private String elementoDigitalizacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdDigitalizacion() {
        return idDigitalizacion;
    }

    public Digitalizacion idDigitalizacion(String idDigitalizacion) {
        this.idDigitalizacion = idDigitalizacion;
        return this;
    }

    public void setIdDigitalizacion(String idDigitalizacion) {
        this.idDigitalizacion = idDigitalizacion;
    }

    public String getDescripcionDigitalizacion() {
        return descripcionDigitalizacion;
    }

    public Digitalizacion descripcionDigitalizacion(String descripcionDigitalizacion) {
        this.descripcionDigitalizacion = descripcionDigitalizacion;
        return this;
    }

    public void setDescripcionDigitalizacion(String descripcionDigitalizacion) {
        this.descripcionDigitalizacion = descripcionDigitalizacion;
    }

    public String getElementoDigitalizacion() {
        return elementoDigitalizacion;
    }

    public Digitalizacion elementoDigitalizacion(String elementoDigitalizacion) {
        this.elementoDigitalizacion = elementoDigitalizacion;
        return this;
    }

    public void setElementoDigitalizacion(String elementoDigitalizacion) {
        this.elementoDigitalizacion = elementoDigitalizacion;
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
        Digitalizacion digitalizacion = (Digitalizacion) o;
        if (digitalizacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), digitalizacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Digitalizacion{" +
            "id=" + getId() +
            ", idDigitalizacion='" + getIdDigitalizacion() + "'" +
            ", descripcionDigitalizacion='" + getDescripcionDigitalizacion() + "'" +
            ", elementoDigitalizacion='" + getElementoDigitalizacion() + "'" +
            "}";
    }
}
