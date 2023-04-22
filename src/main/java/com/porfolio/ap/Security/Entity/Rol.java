package com.porfolio.ap.Security.Entity;

import com.porfolio.ap.Security.Enums.RolName;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;

    public Rol() {
    }

    public Rol(@NotNull RolName rolName){
    this.rolName = rolName;
    }
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public RolName getRolName() {
        return rolName;
    }

    public void setRolName(RolName rolName) {
        this.rolName = rolName;
    }
}
