package com.proyecto_turismo_ufpso.turismo.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @JsonProperty(value ="userId", access = JsonProperty.Access.READ_ONLY)
    private UUID userId;

    @NotNull(message = "El campo username no puede ser null")
    @Size(max = 100, message = "El username no debe sobrepasar los 100 caracteres")
    @JsonProperty(value = "username")
    private String userName;

    @NotNull(message = "El campo password no puede ser null")
    @Size(max = 100, message = "El password no debe sobrepasar los 100 caracteres")
    @JsonProperty(value = "password")
    private String userPassword;

    @JsonProperty(value = "personId")
    private UUID personId;

    @JsonProperty(value = "fk_profile_id")
    private UUID profileId;

    @JsonProperty(value = "profileName")
    private String profileName;




    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

