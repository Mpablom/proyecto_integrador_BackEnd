package com.porfolio.ap.Controller;

import com.porfolio.ap.Dto.EducationDTO;
import com.porfolio.ap.Entity.Education;
import com.porfolio.ap.Security.Controller.Mensaje;
import com.porfolio.ap.Service.EducationService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = {"https://frontapp-64aae.web.app","https://frontapp-64aae.firebaseapp.com","http://localhost:4200"})
public class EducationController {
    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Education>> list(){
        List<Education> list = educationService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if(!educationService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Education education = educationService.getOne(id).get();
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!educationService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        educationService.delete(id);
        return new ResponseEntity<>(new Mensaje("Education Eliminada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducationDTO educationDTO){
        if (StringUtils.isBlank(educationDTO.getNombreE())){
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio y no puede estar vacío."), HttpStatus.BAD_REQUEST);
        }
        if (educationService.existsByNombreE(educationDTO.getNombreE())){
            return new ResponseEntity<>(new Mensaje("El nombre ya existe."), HttpStatus.BAD_REQUEST);
        }
        Education education = new Education(educationDTO.getNombreE(), educationDTO.getDescripcionE());
        educationService.save(education);
        
        return new ResponseEntity<>(new Mensaje("Educación creada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody EducationDTO educationDTO){
        if(!educationService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("No existe el ID."), HttpStatus.BAD_REQUEST);
        }
        if(educationService.existsByNombreE(educationDTO.getNombreE()) && educationService.getByNombreE(educationDTO.getNombreE()).get().getId() != id){
            return new ResponseEntity<>(new Mensaje("Ese Nombre ya existe."), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(educationDTO.getNombreE())){
            return new ResponseEntity<>(new Mensaje("El campo no puede estar vacío."), HttpStatus.BAD_REQUEST);
        }

        Education education = educationService.getOne(id).get();
        BeanUtils.copyProperties(educationDTO, education);
        educationService.save(education);
        
        return new ResponseEntity<>(new Mensaje("Educación actualizada"), HttpStatus.OK);
    }
    
}
