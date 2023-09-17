package com.porfolio.ap.Service;

import com.porfolio.ap.Dto.HardYSoftDTO;
import com.porfolio.ap.Entity.HardYSoft;

import java.util.List;
import java.util.Optional;

public interface HardYSoftService {
    List<HardYSoft> list();
    Optional<HardYSoft> getOne(Long id);
    Optional<HardYSoft> getByName(String name);
    void save(HardYSoft skill);
    void delete(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
}
