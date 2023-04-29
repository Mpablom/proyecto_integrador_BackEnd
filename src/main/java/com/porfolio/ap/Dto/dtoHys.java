package com.porfolio.ap.Dto;

import javax.validation.constraints.NotBlank;

public class dtoHys {
    @NotBlank
    private  String name;
    @NotBlank
    private int porcentaje;

    public dtoHys() {
    }

    public dtoHys(String name, int porcentaje) {
        this.name = name;
        this.porcentaje = porcentaje;
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
