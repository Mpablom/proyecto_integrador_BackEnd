package com.porfolio.ap.Controller;

import com.porfolio.ap.Dto.ExperienceDTO;
import com.porfolio.ap.Entity.Experience;
import com.porfolio.ap.Security.Controller.Mensaje;
import com.porfolio.ap.Service.ExperienceService;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = {"https://frontapp-64aae.web.app","https://frontapp-64aae.firebaseapp.com","http://localhost:4200"})
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experience>> list(){
        List<Experience> list = experienceService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") Long id){
        if(!experienceService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experience experience = experienceService.getOne(id).get();
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienceDTO experienceDTO){
        String validationResult = experienceService.validateExperienceName(experienceDTO);
        if (validationResult != null){
            return new ResponseEntity<>(new Mensaje(validationResult),HttpStatus.BAD_REQUEST);
        }
        Experience experience = new Experience(experienceDTO.getNombreE(), experienceDTO.getDescripcionE());
        experienceService.save(experience);

        return new ResponseEntity<>(new Mensaje("Experience Agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ExperienceDTO experienceDTO){
        String validationResult = experienceService.validateExperience(id, experienceDTO);
        if (validationResult != null) {
            return new ResponseEntity<>(new Mensaje(validationResult), HttpStatus.BAD_REQUEST);
        }
        Experience experience = experienceService.getOne(id).get();
        BeanUtils.copyProperties(experienceDTO, experience);
        experienceService.save(experience);
        
        return new ResponseEntity<>(new Mensaje("Experience Actualizada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!experienceService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        experienceService.delete(id);
        
        return new ResponseEntity<>(new Mensaje("Experience Eliminada"), HttpStatus.OK);
    }
}
