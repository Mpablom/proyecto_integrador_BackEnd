package com.porfolio.ap.Dto;

import com.porfolio.ap.Entity.Education;

import javax.validation.constraints.NotBlank;


public class EducationDTO {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    
    //constructor

    public EducationDTO() {
    }

    public EducationDTO(Education education) {
        this.nombreE = education.getNombreE();
        this.descripcionE = education.getDescripcionE();
    }
    
    //Getter & Setter

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
