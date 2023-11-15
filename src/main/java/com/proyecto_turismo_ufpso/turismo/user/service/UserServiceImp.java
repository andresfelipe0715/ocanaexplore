package com.proyecto_turismo_ufpso.turismo.user.service;

import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.InternalServerException;
import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.person.dto.UserCreateDto;
import com.proyecto_turismo_ufpso.turismo.person.entity.Person;
import com.proyecto_turismo_ufpso.turismo.profile.entity.Profile;
import com.proyecto_turismo_ufpso.turismo.profile.repository.ProfileRepository;
import com.proyecto_turismo_ufpso.turismo.user.dto.UserDto;
import com.proyecto_turismo_ufpso.turismo.user.entity.User;
import com.proyecto_turismo_ufpso.turismo.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProfileRepository profileRepository;



    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        try {
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        } catch (Exception exception) {
            throw new InternalServerException("Error al intentar agregar el usuario", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void createUser2(Person person, UserCreateDto userRequest) {


        User user = modelMapper.map(userRequest, User.class);
        user.setPersonId(person.getPersonId());
        user.setPerson(person);
        Optional<Profile>optionalProfile=profileRepository.findByProfileName("user");
        optionalProfile.ifPresent(user::setProfile);
        optionalProfile.ifPresent(profile -> user.setProfileName(profile.getProfileName()));
        userRepository.save(user);
    }



    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        // Obtén el usuario existente por ID
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Verifica si el nuevo nombre de usuario ya está en uso por otro usuario
            String newUserName = userDto.getUserName();
            if (newUserName != null && !newUserName.equals(user.getUserName())) {
                User existingUserWithSameName = userRepository.findByUserName(newUserName);
                if (existingUserWithSameName != null) {
                    throw new MessageGeneric("El nombre de usuario ya está en uso", "400", HttpStatus.BAD_REQUEST);
                }
            }

            // Verifica si el nuevo nombre de perfil existe
            String newProfileName = userDto.getProfileName();
            if (newProfileName != null) {
                Optional<Profile> optionalProfile = profileRepository.findByProfileName(newProfileName);
                if (!optionalProfile.isPresent()) {
                    throw new MessageGeneric("El nombre de perfil especificado no existe", "400", HttpStatus.BAD_REQUEST);
                }
                // Actualiza el perfil del usuario solo si el perfil existe
                user.setProfileName(newProfileName); // Actualiza el nombre del perfil
                user.setProfile(optionalProfile.get()); // Actualiza la referencia del perfil
            }

            // Actualiza los campos necesarios
            if (newUserName != null) {
                user.setUserName(newUserName);
            }

            // Actualiza la contraseña si se proporciona una nueva contraseña
            if (userDto.getUserPassword() != null) {
                user.setUserPassword(userDto.getUserPassword());
            }

            // Guarda el usuario actualizado
            user = userRepository.save(user); // Guarda la actualización

            // Carga el perfil nuevamente para ver los cambios reflejados
            user.getProfile().getId(); // Esto carga el perfil nuevamente

            return modelMapper.map(user, UserDto.class); // Mapea el usuario actualizado
        } else {
            throw new MessageGeneric("Usuario no encontrado", "404", HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public void cambiarEstadoUser(UUID userId, boolean nuevoEstado) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));

        user.setActivo(nuevoEstado);
        userRepository.save(user);

    }

    public Optional<UserDto> getUserByName(String userName) {
        User user = userRepository.findByUserName(userName);

        // Si el usuario se encuentra, conviértelo en UserDto y devuélvelo
        if (user != null) {
            UserDto userDto = new UserDto();
            // Copia los atributos relevantes de User a UserDto
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setUserPassword(user.getUserPassword());
            userDto.setPersonId(user.getPersonId());
            userDto.setProfileName(user.getProfileName());

            return Optional.of(userDto);
        } else {
            // Si no se encuentra, devuelve Optional.empty()
            return Optional.empty();
        }
    }
}
