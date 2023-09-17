package com.porfolio.ap.Service.Implement;

import com.porfolio.ap.Dto.PersonDTO;
import com.porfolio.ap.Entity.Person;
import com.porfolio.ap.Repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.porfolio.ap.Service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonImplService implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonImplService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> list(){
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getOne(Long id){
        return personRepository.findById(id);
    }

    @Override
    public Optional<Person> getByNombre(String nombre){
        return personRepository.findByNombre(nombre);
    }

    @Override
    public void save(Person person){
        personRepository.save(person);
    }

    @Override
    public void delete(Long id){
        personRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return personRepository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre){
        return personRepository.existsByNombre(nombre);
    }

}
