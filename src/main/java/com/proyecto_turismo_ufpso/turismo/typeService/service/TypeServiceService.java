package com.proyecto_turismo_ufpso.turismo.typeService.service;

import com.proyecto_turismo_ufpso.turismo.typeService.dto.TypeServiceDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TypeServiceService {

    List<TypeServiceDto> getAllTypesServices();

    Optional<TypeServiceDto> getTypeId(UUID typeId);

    TypeServiceDto saveType(TypeServiceDto typeServiceDto);

    TypeServiceDto updateType(UUID typeId, TypeServiceDto typeServiceDto);

}
