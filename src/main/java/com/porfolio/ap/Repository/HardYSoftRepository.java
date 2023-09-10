package com.porfolio.ap.Repository;

import com.porfolio.ap.Entity.HardYSoft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HardYSoftRepository extends JpaRepository<HardYSoft, Long > {
    public Optional<HardYSoft> findByName(String name);
    public boolean existsByName(String name);
}
