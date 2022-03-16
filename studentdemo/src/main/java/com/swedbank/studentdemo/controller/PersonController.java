package com.swedbank.studentdemo.controller;

import com.swedbank.studentdemo.model.api.CreatePersonRequest;
import com.swedbank.studentdemo.model.api.PersonResponse;
import com.swedbank.studentdemo.model.domain.Person;
import com.swedbank.studentdemo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/persons")
@Tag(name = "Person Controller", description = "Experience service to fetch person data")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @Operation(summary = "Fetch persons data from database")
    @ApiResponse(
            responseCode = "200",
            description = "A list of person objects",
            content = @Content(schema = @Schema(implementation = PersonResponse.class)))
    public List<PersonResponse> fetchPersons(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return personService.fetchPersons(firstName, lastName).stream()
                .map(p -> new PersonResponse(p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhone()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Fetch person data from database using id")
    @ApiResponse(
            responseCode = "200",
            description = "A person objects",
            content = @Content(schema = @Schema(implementation = PersonResponse.class)))
    public PersonResponse fetchPerson(@PathVariable Long id) {
        Optional<Person> person = personService.fetchPerson(id);
        if (person.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person not found with the requested id");
        }
        return new PersonResponse(person.get().getFirstName(), person.get().getLastName(), person.get().getEmail(),
                person.get().getPhone());
    }

    @PostMapping
    @Operation(summary = "Create new person in database")
    @ApiResponse(
            responseCode = "201",
            description = "New person successfully created in database",
            content = @Content(schema = @Schema(implementation = Long.class)))
    public Long createPerson(@Validated @RequestBody CreatePersonRequest request) {
        Person person = personService.createPerson(request);
        return person.getId();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete person from database")
    public void deleteUser(@PathVariable Long id) {
        personService.deletePerson(id);
    }

}
