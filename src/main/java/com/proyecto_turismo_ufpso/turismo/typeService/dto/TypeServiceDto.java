package com.proyecto_turismo_ufpso.turismo.typeService.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeServiceDto {
    @JsonProperty(value = "typeId", access = JsonProperty.Access.READ_ONLY)
    private UUID typeId;

    @JsonProperty(value = "typeName")
    @NotNull(message = "El campo typName no puede ser null")
    @NotEmpty
    @Size(max = 40,message = "El campo typeName debe tener menos de 40 carater")
    private String typeName;

    @JsonProperty(value = "description")
    @NotNull(message = "El campo description no puede ser null")
    @NotEmpty
    @Size(max = 80,message = "El campo description debe tener menos de 80 carater")
    private String description;

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}