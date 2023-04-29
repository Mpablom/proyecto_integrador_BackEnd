package com.porfolio.ap.Entity;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Hys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 1, max = 50,message = "no cumple con la longitud")
    private  String name;
    @NotNull
    @Size(min = 1, max = 50,message = "no cumple con la longitud")
    private int porcentaje;

    public Hys() {
    }

    public Hys(String name, int porcentaje) {
        this.name = name;
        this.porcentaje = porcentaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
