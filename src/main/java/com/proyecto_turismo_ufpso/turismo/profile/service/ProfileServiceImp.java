package com.proyecto_turismo_ufpso.turismo.profile.service;

import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.InternalServerException;
import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.profile.dto.ProfileDto;
import com.proyecto_turismo_ufpso.turismo.profile.entity.Profile;
import com.proyecto_turismo_ufpso.turismo.profile.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImp implements ProfileService{

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream().map(profile -> {
            return modelMapper.map(profile, ProfileDto.class);
        }).collect(Collectors.toList());
    }
    @Override
    public ProfileDto createProfile(String profileName) {
        if (profileRepository.existsByProfileName(profileName)) {
            throw new MessageGeneric("Ya existe un perfil con este nombre: " + profileName, "409", HttpStatus.CONFLICT);
        }

        try {
            Profile profile = new Profile(profileName);
            return modelMapper.map(profileRepository.save(profile), ProfileDto.class);
        } catch (Exception ex) {
            throw new InternalServerException("ERROR al intentar crear el perfil", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProfileDto getProfileById(UUID profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);

        if (optionalProfile.isPresent()) {
            return modelMapper.map(optionalProfile.get(), ProfileDto.class);
        } else {
            throw new MessageGeneric("No se encontró el perfil", "404", HttpStatus.NOT_FOUND);
        }
    }


    @Override//Se arreglo el problema de la variable, desde la entidad
    public ProfileDto updateProfile(UUID profileId, ProfileDto profileDto) {
        return profileRepository.findById(profileId).map(profile -> {
            profile.setProfileName((profileDto.getProfileName() != null)?profileDto.getProfileName():profile.getProfileName());
            return modelMapper.map(profileRepository.save(profile),ProfileDto.class);
        }).orElseThrow(() -> new MessageGeneric("Usuario no encontrado", "404", HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteProfile(UUID profileId) {
        profileRepository.deleteById(profileId);
    }

    @Override
    public boolean profileExists(String profileName) {
        return profileRepository.existsByProfileName(profileName);
    }


}
