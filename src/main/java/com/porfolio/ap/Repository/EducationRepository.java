package com.porfolio.ap.Repository;

import com.porfolio.ap.Entity.Education;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long>{
    public Optional<Education> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}
