package com.cpsj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CodigoPostal.
 */
@Entity
@Table(name = "codigo_postal")
public class CodigoPostal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre_ciudad")
    private String nombreCiudad;

    @ManyToOne
    @JsonIgnoreProperties("codigopostals")
    private Provincia provincia;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public CodigoPostal codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public CodigoPostal nombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
        return this;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public CodigoPostal provincia(Provincia provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
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
        CodigoPostal codigoPostal = (CodigoPostal) o;
        if (codigoPostal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), codigoPostal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CodigoPostal{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nombreCiudad='" + getNombreCiudad() + "'" +
            "}";
    }
}
