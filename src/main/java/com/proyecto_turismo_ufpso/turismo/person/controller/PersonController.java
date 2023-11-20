package com.proyecto_turismo_ufpso.turismo.person.controller;

import com.proyecto_turismo_ufpso.turismo.person.dto.PersonDto;
import com.proyecto_turismo_ufpso.turismo.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> persons = personService.getAllPerson();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> findById(@PathVariable UUID personId) {
        Optional<PersonDto> person = personService.getPersonId(personId);
        return person.map(personDto -> new ResponseEntity<>(personDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("document/{document}")
    public ResponseEntity<PersonDto> findByDocument(@PathVariable Long document) {
        Optional<PersonDto> person = personService.getDocument(document);
        return person.map(personDto -> new ResponseEntity<>(personDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("nombre/{firstName}")
    public ResponseEntity<PersonDto> findByFirstName(@PathVariable String firstName) {
        Optional<PersonDto> person = personService.getFirstName(firstName);
        return person.map(personDto -> new ResponseEntity<>(personDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @PostMapping("/save")
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto) {
        return new ResponseEntity<>(personService.savePerson(personDto), HttpStatus.CREATED);
    }


    @PutMapping("/update/{personId}")//Se actualizo el post por un put por buenas practicas.
    public ResponseEntity<PersonDto> updatePerson(@PathVariable UUID personId, @RequestBody PersonDto personDto) {
        PersonDto updatedPerson = personService.updatePerson(personId, personDto);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }


    @PatchMapping("state/{personId}")
    public ResponseEntity<String> cambiarEstadoPerson(
            @PathVariable UUID personId,
            @RequestParam boolean nuevoEstado){
        personService.cambiarEstadoPerson(personId,nuevoEstado);
        return ResponseEntity.ok("estado de persona cambiado exitosammente");

    }

}
