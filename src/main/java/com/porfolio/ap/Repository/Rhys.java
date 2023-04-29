package com.porfolio.ap.Repository;

import com.porfolio.ap.Entity.Hys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Rhys extends JpaRepository<Hys, Integer > {
    Optional<Hys> findByName(String name);
    public boolean existsByName(String name);
}
