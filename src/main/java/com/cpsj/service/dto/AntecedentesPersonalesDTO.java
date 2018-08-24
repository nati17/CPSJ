package com.cpsj.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.cpsj.domain.enumeration.EnfermedadesEnum;
import com.cpsj.domain.enumeration.AlergiasEnum;
import com.cpsj.domain.enumeration.IntoleranciasEnum;
import com.cpsj.domain.enumeration.RegimenesEnum;
import com.cpsj.domain.enumeration.BebidasEnum;
import com.cpsj.domain.enumeration.EjerciciosEnum;

/**
 * A DTO for the AntecedentesPersonales entity.
 */
public class AntecedentesPersonalesDTO implements Serializable {

    private Long id;

    @NotNull
    private EnfermedadesEnum enfermedad;

    @NotNull
    private AlergiasEnum alergia;

    @NotNull
    private IntoleranciasEnum intolerancia;

    @NotNull
    private RegimenesEnum regimen;

    @NotNull
    private BebidasEnum bebida;

    @NotNull
    private EjerciciosEnum ejercicio;

    private Boolean tabaco;

    private Boolean tecafe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnfermedadesEnum getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(EnfermedadesEnum enfermedad) {
        this.enfermedad = enfermedad;
    }

    public AlergiasEnum getAlergia() {
        return alergia;
    }

    public void setAlergia(AlergiasEnum alergia) {
        this.alergia = alergia;
    }

    public IntoleranciasEnum getIntolerancia() {
        return intolerancia;
    }

    public void setIntolerancia(IntoleranciasEnum intolerancia) {
        this.intolerancia = intolerancia;
    }

    public RegimenesEnum getRegimen() {
        return regimen;
    }

    public void setRegimen(RegimenesEnum regimen) {
        this.regimen = regimen;
    }

    public BebidasEnum getBebida() {
        return bebida;
    }

    public void setBebida(BebidasEnum bebida) {
        this.bebida = bebida;
    }

    public EjerciciosEnum getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(EjerciciosEnum ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Boolean isTabaco() {
        return tabaco;
    }

    public void setTabaco(Boolean tabaco) {
        this.tabaco = tabaco;
    }

    public Boolean isTecafe() {
        return tecafe;
    }

    public void setTecafe(Boolean tecafe) {
        this.tecafe = tecafe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AntecedentesPersonalesDTO antecedentesPersonalesDTO = (AntecedentesPersonalesDTO) o;
        if (antecedentesPersonalesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), antecedentesPersonalesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AntecedentesPersonalesDTO{" +
            "id=" + getId() +
            ", enfermedad='" + getEnfermedad() + "'" +
            ", alergia='" + getAlergia() + "'" +
            ", intolerancia='" + getIntolerancia() + "'" +
            ", regimen='" + getRegimen() + "'" +
            ", bebida='" + getBebida() + "'" +
            ", ejercicio='" + getEjercicio() + "'" +
            ", tabaco='" + isTabaco() + "'" +
            ", tecafe='" + isTecafe() + "'" +
            "}";
    }
}
