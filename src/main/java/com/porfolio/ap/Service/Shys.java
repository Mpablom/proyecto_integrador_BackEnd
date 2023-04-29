package com.porfolio.ap.Service;

import com.porfolio.ap.Entity.Hys;
import com.porfolio.ap.Repository.Rhys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Shys {
    @Autowired
    Rhys rhys;

    public List<Hys> list(){
        return rhys.findAll();
    }

    public Optional<Hys> getOne(int id){
        return rhys.findById(id);
    }

    public Optional<Hys> getByName(String name){
        return rhys.findByName(name);
    }

    public void save(Hys skill){
        rhys.save(skill);
    }

    public void delete(int id){
        rhys.deleteById(id);
    }

    public boolean existsById(int id){
        return rhys.existsById(id);
    }

    public boolean existsByName(String name){
        return rhys.existsByName(name);
    }
}
