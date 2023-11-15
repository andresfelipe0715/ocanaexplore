package com.proyecto_turismo_ufpso.turismo.typeService.service;

import com.proyecto_turismo_ufpso.turismo.typeService.dto.TypeServiceDto;
import com.proyecto_turismo_ufpso.turismo.typeService.repository.TypeServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TypeServiceServiceImp implements TypeServiceService {

    @Autowired
    private TypeServiceRepository typeServiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TypeServiceDto> getAllTypesServices() {
        return typeServiceRepository.findAll().stream().map(typeService -> {
            return modelMapper.map(typeService, TypeServiceDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<TypeServiceDto> getTypeId(UUID typeId) {
        return Optional.empty();
    }

    @Override
    public TypeServiceDto saveType(TypeServiceDto typeServiceDto) {
        return null;
    }

    @Override
    public TypeServiceDto updateType(UUID typeId, TypeServiceDto typeServiceDto) {
        return null;
    }
}
