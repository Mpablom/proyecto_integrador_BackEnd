package com.porfolio.ap.Service.Implement;

import com.porfolio.ap.Dto.HardYSoftDTO;
import com.porfolio.ap.Entity.HardYSoft;
import com.porfolio.ap.Repository.HardYSoftRepository;
import com.porfolio.ap.Service.HardYSoftService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HardYSoftImplService implements HardYSoftService {

    private final HardYSoftRepository hardYSoftRepository;

    @Autowired
    public HardYSoftImplService(HardYSoftRepository hardYSoftRepository) {
        this.hardYSoftRepository = hardYSoftRepository;
    }

    @Override
    public List<HardYSoft> list(){
        return hardYSoftRepository.findAll();
    }

    @Override
    public Optional<HardYSoft> getOne(Long id){
        return hardYSoftRepository.findById(id);
    }

    @Override
    public Optional<HardYSoft> getByName(String name){
        return hardYSoftRepository.findByName(name);
    }

    @Override
    public void save(HardYSoft skill){
        hardYSoftRepository.save(skill);
    }

    @Override
    public void delete(Long id){
        hardYSoftRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return hardYSoftRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name){
        return hardYSoftRepository.existsByName(name);
    }

    @Override
    public String validateHardYSoft(Long id, HardYSoftDTO hardYSoftDTO){
        if (!existsById(id)){
            return "El id no existe";
        }
        if (existsByName(hardYSoftDTO.getName()) && getByName(hardYSoftDTO.getName()).get().getId() != id){
            return "El skill ya existe";
        }
        if (StringUtils.isBlank(hardYSoftDTO.getName())){
            return "El nombre es obligatorio";
        }
        return null;
    }

    @Override
    public String validateHardYSoftName(HardYSoftDTO hardYSoftDTO){
        if(StringUtils.isBlank(hardYSoftDTO.getName())) {
            return "El nombre es obligatorio";
        }
        if(existsByName(hardYSoftDTO.getName())) {
            return "Esa Skill existe";
        }
        return null;
    }
}
