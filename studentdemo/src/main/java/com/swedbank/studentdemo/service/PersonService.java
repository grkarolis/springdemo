package com.swedbank.studentdemo.service;

import com.swedbank.studentdemo.model.api.CreatePersonRequest;
import com.swedbank.studentdemo.model.domain.Person;
import com.swedbank.studentdemo.repository.PersonRepository;
import com.swedbank.studentdemo.util.BarFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final BarFormatter barFormatter;

    @Autowired
    public PersonService(PersonRepository personRepository, BarFormatter barFormatter) {
        this.personRepository = personRepository;
        this.barFormatter = barFormatter;
    }

    public List<Person> fetchPersons(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return personRepository.findAllByLastNameAndFirstName(lastName, firstName);
        } else if (firstName != null) {
            return personRepository.findAllByFirstName(firstName);
        } else if (lastName != null){
            return personRepository.findAllByLastName(lastName);
        }
        return personRepository.findAll();
    }

    public Optional<Person> fetchPerson(Long id) {
        return personRepository.findById(id);
    }

    public Person createPerson(CreatePersonRequest request) {
        Person person = Person.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
