package com.porfolio.ap.Controller;

import com.porfolio.ap.Dto.HardYSoftDTO;
import com.porfolio.ap.Entity.HardYSoft;
import com.porfolio.ap.Security.Controller.Mensaje;
import com.porfolio.ap.Service.HardYSoftService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://frontapp-64aae.web.app","https://frontapp-64aae.firebaseapp.com","http://localhost:4200"})
@RequestMapping("/skill")
public class HardYSoftController {
    private final HardYSoftService hardYSoftService;
    @Autowired
    public HardYSoftController(HardYSoftService hardYSoftService) {
        this.hardYSoftService = hardYSoftService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<HardYSoft>> list(){
        List<HardYSoft> list = hardYSoftService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if(!hardYSoftService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe."), HttpStatus.NOT_FOUND);
        HardYSoft hardYSoft = hardYSoftService.getOne(id).get();
        return new ResponseEntity<>(hardYSoft, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HardYSoftDTO hardYSoftDTO){
        if(StringUtils.isBlank(hardYSoftDTO.getName())) {
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."),HttpStatus.BAD_REQUEST);
        }
        if(hardYSoftService.existsByName(hardYSoftDTO.getName())) {
            return new ResponseEntity<>(new Mensaje("Esa Skill existe."),HttpStatus.BAD_REQUEST);
        }

        HardYSoft hardYSoft = new HardYSoft(hardYSoftDTO.getName(), hardYSoftDTO.getPorcentaje());
        hardYSoftService.save(hardYSoft);

        return new ResponseEntity<>(new Mensaje("Skill Agregada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody HardYSoftDTO hardYSoftDTO){
        if (!hardYSoftService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("El id no existe."),HttpStatus.BAD_REQUEST);
        }
        if (hardYSoftService.existsByName(hardYSoftDTO.getName()) && hardYSoftService.getByName(hardYSoftDTO.getName()).get().getId() != id){
            return new ResponseEntity<>(new Mensaje("Esa Skill ya existe."),HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(hardYSoftDTO.getName())){
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."),HttpStatus.BAD_REQUEST);
        }

        HardYSoft hardYSoft = hardYSoftService.getOne(id).get();
        BeanUtils.copyProperties(hardYSoftDTO,hardYSoft);
        hardYSoftService.save(hardYSoft);

        return new ResponseEntity<>(new Mensaje("Skill Actualizada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!hardYSoftService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }

        hardYSoftService.delete(id);

        return new ResponseEntity<>(new Mensaje("Skill Eliminadad"), HttpStatus.OK);
    }
}
