package com.porfolio.ap.Repository;

import com.porfolio.ap.Entity.Person;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    public Optional<Person> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
