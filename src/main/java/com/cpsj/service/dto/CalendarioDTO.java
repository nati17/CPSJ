package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Calendario entity.
 */
public class CalendarioDTO implements Serializable {

    private Long id;

    private String idCalendario;

    private String anioCalendario;

    private String mesCalendario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(String idCalendario) {
        this.idCalendario = idCalendario;
    }

    public String getAnioCalendario() {
        return anioCalendario;
    }

    public void setAnioCalendario(String anioCalendario) {
        this.anioCalendario = anioCalendario;
    }

    public String getMesCalendario() {
        return mesCalendario;
    }

    public void setMesCalendario(String mesCalendario) {
        this.mesCalendario = mesCalendario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CalendarioDTO calendarioDTO = (CalendarioDTO) o;
        if (calendarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), calendarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CalendarioDTO{" +
            "id=" + getId() +
            ", idCalendario='" + getIdCalendario() + "'" +
            ", anioCalendario='" + getAnioCalendario() + "'" +
            ", mesCalendario='" + getMesCalendario() + "'" +
            "}";
    }
}
