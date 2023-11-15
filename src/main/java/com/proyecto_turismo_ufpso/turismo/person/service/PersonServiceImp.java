package com.proyecto_turismo_ufpso.turismo.person.service;

import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.InternalServerException;
import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.person.dto.PersonDto;
import com.proyecto_turismo_ufpso.turismo.user.repository.UserRepository;
import com.proyecto_turismo_ufpso.turismo.person.entity.Person;
import com.proyecto_turismo_ufpso.turismo.person.repository.PersonRepository;


import com.proyecto_turismo_ufpso.turismo.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonServiceImp implements PersonService{

    @Autowired
    private UserService userService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PersonDto> getAllPerson() {
        return personRepository.findAll().stream().map(person -> {
            return modelMapper.map(person, PersonDto.class);
        }).collect(Collectors.toList());
    }


    @Override
    public Optional<PersonDto> getPersonId(UUID personId) {
        return Optional.ofNullable(personRepository.findById(personId).map(person -> {
            return modelMapper.map(person, PersonDto.class);
        }).orElseThrow(() -> {
            String errorMessage = "No se encontro la Persona con ID: " + personId;
            return new MessageGeneric(errorMessage, "404", HttpStatus.NOT_FOUND);
        }));
    }

    @Override
    public Optional<PersonDto> getDocument (Long document) {
        Person person = personRepository.findByDocument(document);
        if (person != null) {
            return Optional.of(modelMapper.map(person, PersonDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PersonDto> getFirstName (String firstName) {
        Person person = personRepository.findByFirstName(firstName);
        if (person != null) {
            return Optional.of(modelMapper.map(person, PersonDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PersonDto savePerson(PersonDto personDto) {
        //Person person = modelMapper.map(personDto, Person.class);
        Person person= Person.create(
                personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getEmail(),
                personDto.getAddress(),
                personDto.getPhone(),
                personDto.getDocument());
        String document = personDto.getDocument().toString(); // Convierte a String
        if (document.length() > 11) {
            throw new MessageGeneric("El número de documento no puede tener más de 11 caracteres.", "411", HttpStatus.LENGTH_REQUIRED);
        }

        if (personRepository.existsByDocument(person.getDocument())) {
            throw new MessageGeneric("Ya existe una persona con ese numero de documento : " + personDto.getDocument(), "409", HttpStatus.CONFLICT);
        }
        if (userRepository.existsByUserName(personDto.getUser().getUserName())) {
            throw new MessageGeneric("Ya existe un usuario con el nombre de usuario: " + personDto.getUser().getUserName(), "409", HttpStatus.CONFLICT);
        }
        try {
            Person person1 = personRepository.save(person);
            userService.createUser2(person1, personDto.getUser());
            return modelMapper.map(personRepository.save(person1), PersonDto.class);

            //throw new InternalServerException("ERROR al intentar crear la persona", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception exception) {
            throw new InternalServerException("Error al intentar crear la persona", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @Override
    public PersonDto updatePerson(UUID personId, PersonDto personDto) {

        return personRepository.findById(personId).map(person -> {
            person.setFirstName((personDto.getFirstName() != null) ? personDto.getFirstName() : person.getFirstName());
            person.setLastName((personDto.getLastName() != null) ? personDto.getLastName() : person.getLastName());
            person.setEmail((personDto.getEmail() != null) ? personDto.getEmail() : person.getEmail());
            person.setAddress((personDto.getAddress() != null) ? personDto.getAddress() : person.getAddress());
            person.setPhone((personDto.getPhone() != null) ? personDto.getPhone() : person.getPhone());
            person.setDocument((personDto.getDocument() != null) ? personDto.getDocument() : person.getDocument());

            return modelMapper.map(personRepository.save(person), PersonDto.class);
        }).orElseThrow(() -> new MessageGeneric("No se encontró la persona", "404", HttpStatus.NOT_FOUND));
    }

    @Override
    public void cambiarEstadoPerson(UUID personId, boolean nuevoEstado) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));

        person.setActivo(nuevoEstado);
        personRepository.save(person);

    }
}
