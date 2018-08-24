package com.cpsj.domain;


import javax.persistence.*;
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
 * A AntecedentesPersonales.
 */
@Entity
@Table(name = "antecedentes_personales")
public class AntecedentesPersonales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enfermedad", nullable = false)
    private EnfermedadesEnum enfermedad;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "alergia", nullable = false)
    private AlergiasEnum alergia;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "intolerancia", nullable = false)
    private IntoleranciasEnum intolerancia;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "regimen", nullable = false)
    private RegimenesEnum regimen;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "bebida", nullable = false)
    private BebidasEnum bebida;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ejercicio", nullable = false)
    private EjerciciosEnum ejercicio;

    @Column(name = "tabaco")
    private Boolean tabaco;

    @Column(name = "tecafe")
    private Boolean tecafe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnfermedadesEnum getEnfermedad() {
        return enfermedad;
    }

    public AntecedentesPersonales enfermedad(EnfermedadesEnum enfermedad) {
        this.enfermedad = enfermedad;
        return this;
    }

    public void setEnfermedad(EnfermedadesEnum enfermedad) {
        this.enfermedad = enfermedad;
    }

    public AlergiasEnum getAlergia() {
        return alergia;
    }

    public AntecedentesPersonales alergia(AlergiasEnum alergia) {
        this.alergia = alergia;
        return this;
    }

    public void setAlergia(AlergiasEnum alergia) {
        this.alergia = alergia;
    }

    public IntoleranciasEnum getIntolerancia() {
        return intolerancia;
    }

    public AntecedentesPersonales intolerancia(IntoleranciasEnum intolerancia) {
        this.intolerancia = intolerancia;
        return this;
    }

    public void setIntolerancia(IntoleranciasEnum intolerancia) {
        this.intolerancia = intolerancia;
    }

    public RegimenesEnum getRegimen() {
        return regimen;
    }

    public AntecedentesPersonales regimen(RegimenesEnum regimen) {
        this.regimen = regimen;
        return this;
    }

    public void setRegimen(RegimenesEnum regimen) {
        this.regimen = regimen;
    }

    public BebidasEnum getBebida() {
        return bebida;
    }

    public AntecedentesPersonales bebida(BebidasEnum bebida) {
        this.bebida = bebida;
        return this;
    }

    public void setBebida(BebidasEnum bebida) {
        this.bebida = bebida;
    }

    public EjerciciosEnum getEjercicio() {
        return ejercicio;
    }

    public AntecedentesPersonales ejercicio(EjerciciosEnum ejercicio) {
        this.ejercicio = ejercicio;
        return this;
    }

    public void setEjercicio(EjerciciosEnum ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Boolean isTabaco() {
        return tabaco;
    }

    public AntecedentesPersonales tabaco(Boolean tabaco) {
        this.tabaco = tabaco;
        return this;
    }

    public void setTabaco(Boolean tabaco) {
        this.tabaco = tabaco;
    }

    public Boolean isTecafe() {
        return tecafe;
    }

    public AntecedentesPersonales tecafe(Boolean tecafe) {
        this.tecafe = tecafe;
        return this;
    }

    public void setTecafe(Boolean tecafe) {
        this.tecafe = tecafe;
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
        AntecedentesPersonales antecedentesPersonales = (AntecedentesPersonales) o;
        if (antecedentesPersonales.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), antecedentesPersonales.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AntecedentesPersonales{" +
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
