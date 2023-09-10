package com.porfolio.ap.Service.Implement;

import com.porfolio.ap.Dto.ExperienceDTO;
import com.porfolio.ap.Entity.Experience;
import com.porfolio.ap.Repository.ExperienceRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.porfolio.ap.Service.ExperienceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienceImplService implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceImplService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<Experience> list(){
        return experienceRepository.findAll();
    }

    @Override
    public Optional<Experience> getOne(Long id){
        return experienceRepository.findById(id);
    }

    @Override
    public Optional<Experience> getByNombreE(String nombreE){
        return experienceRepository.findByNombreE(nombreE);
    }

    @Override
    public void save(Experience expe){
        experienceRepository.save(expe);
    }

    @Override
    public void delete(Long id){
        experienceRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return experienceRepository.existsById(id);
    }

    @Override
    public boolean existsByNombreE(String nombreE){
        return experienceRepository.existsByNombreE(nombreE);
    }

    @Override
    public String validateExperience(Long id, ExperienceDTO experienceDTO) {
        if(!existsById(id)){
            return "El id no existe";
        }
        if(existsByNombreE(experienceDTO.getNombreE()) && getByNombreE(experienceDTO.getNombreE()).get().getId() != id){
            return "Esa experiencia ya existe";
        }
        if(StringUtils.isBlank(experienceDTO.getNombreE())){
            return "El nombre es obligatorio";
        }
        return null;
    }

    public String validateExperienceName(ExperienceDTO experienceDTO){
        if(StringUtils.isBlank(experienceDTO.getNombreE())) {
            return "El nombre es obligatorio";
        }
        if(existsByNombreE(experienceDTO.getNombreE())) {
            return "Esa experiencia existe";
        }
        return null;
    }
}
