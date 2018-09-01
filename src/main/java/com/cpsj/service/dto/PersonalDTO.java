package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Personal entity.
 */
public class PersonalDTO implements Serializable {

    private Long id;

    private String nombrePersonal;

    private String apellidoPersonal;

    private String direccionPersonal;

    private String telefonoPersonal;

    private String emailPersonal;

    private String cargoPersonal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public String getApellidoPersonal() {
        return apellidoPersonal;
    }

    public void setApellidoPersonal(String apellidoPersonal) {
        this.apellidoPersonal = apellidoPersonal;
    }

    public String getDireccionPersonal() {
        return direccionPersonal;
    }

    public void setDireccionPersonal(String direccionPersonal) {
        this.direccionPersonal = direccionPersonal;
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public String getCargoPersonal() {
        return cargoPersonal;
    }

    public void setCargoPersonal(String cargoPersonal) {
        this.cargoPersonal = cargoPersonal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonalDTO personalDTO = (PersonalDTO) o;
        if (personalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonalDTO{" +
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
