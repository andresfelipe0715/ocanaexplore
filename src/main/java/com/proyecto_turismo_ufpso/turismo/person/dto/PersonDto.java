package com.proyecto_turismo_ufpso.turismo.person.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {

    @JsonProperty(value ="personId", access = JsonProperty.Access.READ_ONLY)
    private UUID personId;

    @JsonProperty(value = "first_name")
    @NotNull(message = "El campo nombrePersona no puede ser null")
    @Size(max = 100,message = "El nombre no debe sobrepasar los 100 caracteres")
    private String firstName;

    @JsonProperty(value = "last_name")
    @NotNull(message = "El campo apellidoPersona no puede ser null")
    @Size(max = 100,message = "El apellido no debe sobrepasar los 100 caracteres")
    private String lastName;

    @JsonProperty(value = "email")
    @NotNull(message = "El campo email no puede ser null")
    @Size(max = 100,message = "El Email no debe sobrepasar los 100 caracteres")
    private String email;

    @JsonProperty(value = "address")
    @Size(max = 150,message = "El Email no debe sobrepasar los 150 caracteres")
    private String address;

    @JsonProperty(value = "phone")
    @Size(max = 15,message = "El celular no debe sobrepasar los 15 caracteres")
    @Pattern(regexp = "\\d+",message = "El Celular solo se admite numeros")
    private String phone;

    @JsonProperty(value = "document")
    @NotNull(message = "El campo document no puede ser null")
    @Size(max = 10,message = "El documento no debe sobrepasar los 10 caracteres")
    @Pattern(regexp = "\\d+",message = "El NIT solo se admite numeros")
    private Long document;

    @JsonProperty(value = "user")
    private UserCreateDto user;

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public UserCreateDto getUser() {
        return user;
    }

    public void setUser(UserCreateDto user) {
        this.user = user;
    }
}
