package com.porfolio.ap.Repository;

import com.porfolio.ap.Entity.Experience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long>{
    public Optional<Experience> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}
