package com.porfolio.ap.Security.Service;

import com.porfolio.ap.Security.Entity.Rol;
import com.porfolio.ap.Security.Enums.RolName;
import com.porfolio.ap.Security.Repository.RolRepository;
import javax.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolName(RolName rolName){
        return rolRepository.findByRolName(rolName);

    }
    public void save(Rol rol){
        rolRepository.save(rol);
    }

}
