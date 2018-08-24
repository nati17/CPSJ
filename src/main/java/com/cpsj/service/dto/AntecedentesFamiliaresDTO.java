package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AntecedentesFamiliares entity.
 */
public class AntecedentesFamiliaresDTO implements Serializable {

    private Long id;

    private Boolean vivoAF;

    private String causaMuerteAF;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isVivoAF() {
        return vivoAF;
    }

    public void setVivoAF(Boolean vivoAF) {
        this.vivoAF = vivoAF;
    }

    public String getCausaMuerteAF() {
        return causaMuerteAF;
    }

    public void setCausaMuerteAF(String causaMuerteAF) {
        this.causaMuerteAF = causaMuerteAF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AntecedentesFamiliaresDTO antecedentesFamiliaresDTO = (AntecedentesFamiliaresDTO) o;
        if (antecedentesFamiliaresDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), antecedentesFamiliaresDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AntecedentesFamiliaresDTO{" +
            "id=" + getId() +
            ", vivoAF='" + isVivoAF() + "'" +
            ", causaMuerteAF='" + getCausaMuerteAF() + "'" +
            "}";
    }
}
