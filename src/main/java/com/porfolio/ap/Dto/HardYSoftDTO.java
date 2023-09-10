package com.porfolio.ap.Dto;

import com.porfolio.ap.Entity.HardYSoft;

import javax.validation.constraints.NotBlank;

public class HardYSoftDTO {
    @NotBlank
    private  String name;
    @NotBlank
    private int porcentaje;

    //Constructor

    public HardYSoftDTO() {
    }

    public HardYSoftDTO(HardYSoft hardYSoft) {
        this.name = hardYSoft.getName();
        this.porcentaje = hardYSoft.getPorcentaje();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
