package com.porfolio.ap.Service;

import com.porfolio.ap.Dto.PersonDTO;
import com.porfolio.ap.Entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> list();
    Optional<Person> getOne(Long id);
    Optional<Person> getByNombre(String nombre);
    void save(Person person);
    void delete(Long id);
    boolean existsById(Long id);
    boolean existsByNombre(String nombre);
}

