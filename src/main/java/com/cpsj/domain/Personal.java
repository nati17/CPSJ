package com.cpsj.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Personal.
 */
@Entity
@Table(name = "personal")
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_personal")
    private String nombrePersonal;

    @Column(name = "apellido_personal")
    private String apellidoPersonal;

    @Column(name = "direccion_personal")
    private String direccionPersonal;

    @Column(name = "telefono_personal")
    private String telefonoPersonal;

    @Column(name = "email_personal")
    private String emailPersonal;

    @Column(name = "cargo_personal")
    private String cargoPersonal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public Personal nombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
        return this;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public String getApellidoPersonal() {
        return apellidoPersonal;
    }

    public Personal apellidoPersonal(String apellidoPersonal) {
        this.apellidoPersonal = apellidoPersonal;
        return this;
    }

    public void setApellidoPersonal(String apellidoPersonal) {
        this.apellidoPersonal = apellidoPersonal;
    }

    public String getDireccionPersonal() {
        return direccionPersonal;
    }

    public Personal direccionPersonal(String direccionPersonal) {
        this.direccionPersonal = direccionPersonal;
        return this;
    }

    public void setDireccionPersonal(String direccionPersonal) {
        this.direccionPersonal = direccionPersonal;
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public Personal telefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
        return this;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public Personal emailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
        return this;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public String getCargoPersonal() {
        return cargoPersonal;
    }

    public Personal cargoPersonal(String cargoPersonal) {
        this.cargoPersonal = cargoPersonal;
        return this;
    }

    public void setCargoPersonal(String cargoPersonal) {
        this.cargoPersonal = cargoPersonal;
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
        Personal personal = (Personal) o;
        if (personal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Personal{" +
            "id=" + getId() +
            ", nombrePersonal='" + getNombrePersonal() + "'" +
            ", apellidoPersonal='" + getApellidoPersonal() + "'" +
            ", direccionPersonal='" + getDireccionPersonal() + "'" +
            ", telefonoPersonal='" + getTelefonoPersonal() + "'" +
            ", emailPersonal='" + getEmailPersonal() + "'" +
            ", cargoPersonal='" + getCargoPersonal() + "'" +
            "}";
    }
}
