package com.porfolio.ap.Interface;

import com.porfolio.ap.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //Traer persona
    public List<Persona> getPersona();
    //Guardar persona
    public void savePersona(Persona persona);
    //Eliminar persona
    public void deletePersona(Long id);
    //Buscar por id
    public Persona findPersona(Long id);
}
