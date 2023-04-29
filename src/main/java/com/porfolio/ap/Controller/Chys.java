package com.porfolio.ap.Controller;

import com.porfolio.ap.Dto.dtoHys;
import com.porfolio.ap.Entity.Hys;
import com.porfolio.ap.Security.Controller.Mensaje;
import com.porfolio.ap.Service.Shys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"https://frontapp-64aae.web.app","https://frontapp-64aae.firebaseapp.com","http://localhost:4200"})
@RequestMapping("/skill")
public class Chys {
    @Autowired Shys shys;

    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list(){
        List<Hys> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id){
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Hys hys = shys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys){
        if(StringUtils.isBlank(dtohys.getName()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(shys.existsByName(dtohys.getName()))
            return new ResponseEntity(new Mensaje("Esa Skill existe"), HttpStatus.BAD_REQUEST);

        Hys hys= new Hys(dtohys.getName(), dtohys.getPorcentaje());
        shys.save(hys);

        return new ResponseEntity(new Mensaje("Skill Agregada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys){
        //valida si existe el id
        if(!shys.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        //comptara nombre de experiencia
        if(shys.existsByName(dtohys.getName()) && shys.getByName(dtohys.getName()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Esa Skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if(StringUtils.isBlank(dtohys.getName())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Hys hys= shys.getOne(id).get();
        hys.setName(dtohys.getName());
        hys.setPorcentaje((dtohys.getPorcentaje()));

        shys.save(hys);

        return new ResponseEntity(new Mensaje("Skill Actualizada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //valida si existe el id
        if(!shys.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }

        shys.delete(id);

        return new ResponseEntity(new Mensaje("Skill Eliminadad"), HttpStatus.OK);
    }
}
