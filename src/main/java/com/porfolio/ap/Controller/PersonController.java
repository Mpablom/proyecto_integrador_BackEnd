package com.porfolio.ap.Controller;

import com.porfolio.ap.Dto.PersonDTO;
import com.porfolio.ap.Entity.Person;
import com.porfolio.ap.Security.Controller.Mensaje;
import java.util.List;

import com.porfolio.ap.Service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"https://frontapp-64aae.web.app","https://frontapp-64aae.firebaseapp.com","http://localhost:4200"})
@RequestMapping("/persona")
public class PersonController {
    @Autowired
    private PersonService personService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Person>> list(){
        List<Person> list = personService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") Long id){
        if(!personService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Person person = personService.getOne(id).get();
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody PersonDTO personDTO){
        String validationResult = personService.validatePerson(id, personDTO);
        if (validationResult != null) {
            return new ResponseEntity<>(new Mensaje(validationResult), HttpStatus.BAD_REQUEST);
        }
            Person person = personService.getOne(id).get();
            BeanUtils.copyProperties(personDTO, person);
            personService.save(person);
        return new ResponseEntity(new Mensaje("Person actualizada"), HttpStatus.OK);
    }
    
}
