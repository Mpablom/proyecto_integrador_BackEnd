package com.porfolio.ap.Service.Implement;

import com.porfolio.ap.Dto.EducationDTO;
import com.porfolio.ap.Entity.Education;
import com.porfolio.ap.Repository.EducationRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.porfolio.ap.Service.EducationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EducationImplService implements EducationService {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationImplService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public List<Education> list(){
        return educationRepository.findAll();
    }

    @Override
    public Optional<Education> getOne(Long id){
        return educationRepository.findById(id);
    }

    @Override
    public Optional<Education> getByNombreE(String nombreE){
        return educationRepository.findByNombreE(nombreE);
    }

    @Override
    public void save(Education education){
        educationRepository.save(education);
    }

    @Override
    public void delete(Long id){
        educationRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return educationRepository.existsById(id);
    }

    @Override
    public boolean existsByNombreE(String nombreE){
        return educationRepository.existsByNombreE(nombreE);
    }


}
