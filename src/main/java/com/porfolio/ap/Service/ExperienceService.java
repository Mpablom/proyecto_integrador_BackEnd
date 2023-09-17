package com.porfolio.ap.Service;

import com.porfolio.ap.Dto.ExperienceDTO;
import com.porfolio.ap.Entity.Experience;

import java.util.List;
import java.util.Optional;

public interface ExperienceService {
    List<Experience> list();
    Optional<Experience> getOne(Long id);
    Optional<Experience> getByNombreE(String nombreE);
    void save(Experience expe);
    void delete(Long id);
    boolean existsById(Long id);
    boolean existsByNombreE(String nombreE);
}
