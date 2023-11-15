package com.proyecto_turismo_ufpso.turismo.typeService.service;

import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.InternalServerException;
import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.typeService.dto.TypeServiceDto;
import com.proyecto_turismo_ufpso.turismo.typeService.entity.TypeService;
import com.proyecto_turismo_ufpso.turismo.typeService.repository.TypeServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return Optional.ofNullable(typeServiceRepository.findById(typeId).map(typeService ->
                modelMapper.map(typeService, TypeServiceDto.class)).orElseThrow(() ->
                new MessageGeneric("No se encontro la categoria que esta solicitando", "404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public TypeServiceDto saveType(TypeServiceDto typeServiceDto) {

        TypeService typeService = modelMapper.map(typeServiceDto, TypeService.class);

        if(typeServiceRepository.existsByTypeName(typeService.getTypeName())){
            throw new InternalServerException("ERROR AL REGISTRAR EL TYPO DE SERVICIO", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            return modelMapper.map(typeServiceRepository.save(typeService), TypeServiceDto.class);
        }catch (Exception ex) {
            throw new InternalServerException("ERROR al intentar Registrar el tipo de servicio", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TypeServiceDto updateType(UUID typeId, TypeServiceDto typeServiceDto) {

        return typeServiceRepository.findById(typeId).map(typeService -> {
            typeService.setTypeName((typeServiceDto.getTypeName() != null) ? typeServiceDto.getTypeName() : typeService.getTypeName());
            typeService.setDescription((typeServiceDto.getDescription() != null) ? typeServiceDto.getDescription() : typeService.getDescription());
            return modelMapper.map(typeServiceRepository.save(typeService), TypeServiceDto.class);
        }).orElseThrow(() -> new MessageGeneric("No se encontro la categoria", "404", HttpStatus.NOT_FOUND));
    }
}
