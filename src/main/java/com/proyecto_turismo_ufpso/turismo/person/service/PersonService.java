package com.proyecto_turismo_ufpso.turismo.person.service;


import com.proyecto_turismo_ufpso.turismo.person.dto.PersonDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PersonService {

    static List<PersonDto> getAllPersona() {
        return null;
    }

    List<PersonDto> getAllPerson();

    Optional<PersonDto> getPersonId(UUID personId);
    Optional<PersonDto> getDocument (Long document);

    Optional<PersonDto> getFirstName (String firstName);

    PersonDto savePerson(PersonDto personDto);

    PersonDto updatePerson(UUID personId, PersonDto personDto);


    void cambiarEstadoPerson(UUID personId, boolean activo);


}
