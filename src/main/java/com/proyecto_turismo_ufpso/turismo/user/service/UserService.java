package com.proyecto_turismo_ufpso.turismo.user.service;

import com.proyecto_turismo_ufpso.turismo.person.dto.UserCreateDto;
import com.proyecto_turismo_ufpso.turismo.person.entity.Person;
import com.proyecto_turismo_ufpso.turismo.user.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(UUID userId);

    UserDto createUser(UserDto userDto);

    void createUser2(Person person, UserCreateDto user);

    UserDto updateUser(UUID userId, UserDto userDto);

    void cambiarEstadoUser(UUID userId, boolean nuevoEstado);

    Optional<UserDto> getUserByName(String userName);
}
