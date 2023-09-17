package com.porfolio.ap.Service;

import com.porfolio.ap.Dto.EducationDTO;
import com.porfolio.ap.Entity.Education;

import java.util.List;
import java.util.Optional;


public interface EducationService {
    List<Education> list();
    Optional<Education> getOne(Long id);
    Optional<Education> getByNombreE(String nombreE);
    void save(Education education);
    void delete(Long id);
    boolean existsById(Long id);
    boolean existsByNombreE(String nombreE);
}
