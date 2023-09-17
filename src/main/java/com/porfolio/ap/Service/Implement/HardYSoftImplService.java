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

}
