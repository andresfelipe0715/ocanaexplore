package com.proyecto_turismo_ufpso.turismo.profile.service;
import com.proyecto_turismo_ufpso.turismo.profile.dto.ProfileDto;

import java.util.List;
import java.util.UUID;

public interface ProfileService {
    List<ProfileDto> getAllProfiles();

    ProfileDto getProfileById(UUID profileId);

    ProfileDto updateProfile(UUID profileId, ProfileDto profileDto);

    void deleteProfile(UUID profileId);

    ProfileDto createProfile(String profileName);

    boolean profileExists (String profileName);
}
