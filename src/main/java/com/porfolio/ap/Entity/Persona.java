package com.porfolio.ap.Entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 50,message = "no cumple con la longitud")
    private String nombre;
    @NotNull
    @Size(min = 1, max = 50,message = "no cumple con la longitud")
    private String apellido;
    @Size(min = 1, max = 50,message = "no cumple con la longitud")
    private String img;
}
