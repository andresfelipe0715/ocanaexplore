package com.proyecto_turismo_ufpso.turismo.person.repository;

import com.proyecto_turismo_ufpso.turismo.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository <Person, UUID> {

    Boolean existsByDocument(long document);
    Person findByDocument(long document);
    Person  findByFirstName(String firstName);

    List<Person> findByActivo(boolean activo);

}