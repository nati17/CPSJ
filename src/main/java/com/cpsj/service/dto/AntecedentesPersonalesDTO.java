package com.cpsj.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AntecedentesPersonales entity.
 */
public class AntecedentesPersonalesDTO implements Serializable {

    private Long id;

    private Boolean tabaco;

    private Boolean tecafe;

    private Set<EnfermedadDTO> enfermedades = new HashSet<>();

    private Set<AlergiaDTO> alergias = new HashSet<>();

    private Set<IntoleranciaDTO> intolerancias = new HashSet<>();

    private Set<RegimenDTO> regimenes = new HashSet<>();

    private Set<BebidaDTO> bebidas = new HashSet<>();

    private Set<EjercicioDTO> ejercicios = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<EnfermedadDTO> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(Set<EnfermedadDTO> enfermedads) {
        this.enfermedades = enfermedads;
    }

    public Set<AlergiaDTO> getAlergias() {
        return alergias;
    }

    public void setAlergias(Set<AlergiaDTO> alergias) {
        this.alergias = alergias;
    }

    public Set<IntoleranciaDTO> getIntolerancias() {
        return intolerancias;
    }

    public void setIntolerancias(Set<IntoleranciaDTO> intolerancias) {
        this.intolerancias = intolerancias;
    }

    public Set<RegimenDTO> getRegimenes() {
        return regimenes;
    }

    public void setRegimenes(Set<RegimenDTO> regimen) {
        this.regimenes = regimen;
    }

    public Set<BebidaDTO> getBebidas() {
        return bebidas;
    }

    public void setBebidas(Set<BebidaDTO> bebidas) {
        this.bebidas = bebidas;
    }

    public Set<EjercicioDTO> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Set<EjercicioDTO> ejercicios) {
        this.ejercicios = ejercicios;
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
            ", tabaco='" + isTabaco() + "'" +
            ", tecafe='" + isTecafe() + "'" +
            "}";
    }
}
