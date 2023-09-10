package com.porfolio.ap.Dto;

import com.porfolio.ap.Entity.Experience;

import javax.validation.constraints.NotBlank;


public class ExperienceDTO {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    
    //Constructor

    public ExperienceDTO() {
    }

    public ExperienceDTO(Experience experience) {
        this.nombreE = experience.getNombreE();
        this.descripcionE = experience.getDescripcionE();
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }
    
}
