package com.proyecto_turismo_ufpso.turismo.profile.dto;


import java.util.UUID;

public class ProfileDto {

    private UUID profileId;
    private String profileName;

    // Constructor, getters y setters
    public ProfileDto(){

    }
    public ProfileDto(UUID id, String profileName) {
        this.profileId = profileId;
        this.profileName = profileName;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
