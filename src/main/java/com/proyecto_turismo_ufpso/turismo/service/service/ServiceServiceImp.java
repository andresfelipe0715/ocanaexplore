package com.proyecto_turismo_ufpso.turismo.service.service;

import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.InternalServerException;
import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.service.dto.ServiceDto;
import com.proyecto_turismo_ufpso.turismo.service.repository.ServiceRepository;
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
public class ServiceServiceImp implements ServiceService{

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TypeServiceRepository typeServiceRepository;

    @Override
    public List<ServiceDto> getAllService() {
        return serviceRepository.findAll().stream().map(service ->
                modelMapper.map(service, ServiceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceDto> getServiceId(UUID serviceId) {
        return Optional.ofNullable(serviceRepository.findById(serviceId).map(service ->
                modelMapper.map(service, ServiceDto.class)).orElseThrow(() ->
                new MessageGeneric("No se encontro el servicio que esta solicitando", "404", HttpStatus.NOT_FOUND)));
    }

    /*@Override
    public ServiceDto saveService(ServiceDto serviceDto) {
        Optional<TypeService> optionalTypeService = typeServiceRepository.findByTypeName(serviceDto.getTypeName());

        if (optionalTypeService.isPresent()){
            TypeService typeService = optionalTypeService.get();

            serviceDto.setTypeId(typeService.getTypeId());

            if (serviceRepository.existsByServiceName(serviceDto.getServiceName())){
                throw new MessageGeneric("Ya existe un servicio con este nombre: ", "409", HttpStatus.CONFLICT);
            }
            try{
                return modelMapper.map(serviceRepository.save(modelMapper.map(serviceDto, com.proyecto_turismo_ufpso.turismo.service.entity.Service.class)), ServiceDto.class);
            }catch (Exception ex) {
                throw new InternalServerException("ERROR al intentar Registrar el servicio", "500", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            // Maneja el caso en que no se encontró la categoría
            throw new MessageGeneric("El tipo de servicio no se encontró", "404", HttpStatus.NOT_FOUND);
        }
    }*/

    @Override
    public ServiceDto saveService(ServiceDto serviceDto) {
        Optional<TypeService> optionalTypeService = typeServiceRepository.findByTypeName(serviceDto.getTypeName());

        if (optionalTypeService.isPresent()) {
            TypeService typeService = optionalTypeService.get();

            serviceDto.setTypeId(typeService.getTypeId());

            /*if (serviceRepository.existsByServiceName(serviceDto.getServiceName())) {
                throw new MessageGeneric("Ya existe un servicio con este nombre: ", "409", HttpStatus.CONFLICT);
            }*/

            try {
                com.proyecto_turismo_ufpso.turismo.service.entity.Service entity =
                        modelMapper.map(serviceDto, com.proyecto_turismo_ufpso.turismo.service.entity.Service.class);

                // Calcular subtotal
                double subtotal = 0;

                if ("Hoteleria".equals(serviceDto.getTypeName())) {
                    double room = serviceDto.getRoom() != null ? serviceDto.getRoom() : 0;
                    double doubleRoom = serviceDto.getDoubleRoom() != null ? serviceDto.getDoubleRoom() : 0;
                    double nightAmount = serviceDto.getNightAmount() != null ? serviceDto.getNightAmount() : 0;
                    double roomAmount = serviceDto.getRoomAmount() != null ? serviceDto.getRoomAmount() : 0;
                    subtotal = (room * nightAmount * roomAmount) + (doubleRoom * nightAmount * roomAmount);
                } else if ("Restaurante".equals(serviceDto.getTypeName())) {
                    double foodPrice = serviceDto.getFoodPrice() != null ? serviceDto.getFoodPrice() : 0;
                    double foodAmount = serviceDto.getFoodAmount() != null ? serviceDto.getFoodAmount() : 0;
                    double priceTrans = serviceDto.getPriceTrans() != null ? serviceDto.getPriceTrans() : 0;
                    double tripAmount = serviceDto.getTripAmount() != null ? serviceDto.getTripAmount() : 0;
                    subtotal = (foodPrice * foodAmount) + (priceTrans * tripAmount);
                } else if ("Sitio turistico".equals(serviceDto.getTypeName())) {
                    double entranceFee = serviceDto.getEntranceFee() != null ? serviceDto.getEntranceFee() : 0;
                    double personalGuide = serviceDto.getPersonalGuide() != null ? serviceDto.getPersonalGuide() : 0;
                    double priceTrans = serviceDto.getPriceTrans() != null ? serviceDto.getPriceTrans() : 0;
                    double tripAmount = serviceDto.getTripAmount() != null ? serviceDto.getTripAmount() : 0;
                    subtotal = entranceFee + personalGuide + (priceTrans * tripAmount);
                }

                entity.setSubtotal(subtotal);

                return modelMapper.map(serviceRepository.save(entity), ServiceDto.class);
            } catch (Exception ex) {
                throw new InternalServerException("ERROR al intentar Registrar el servicio", "500",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            // Maneja el caso en que no se encontró la categoría
            throw new MessageGeneric("El tipo de servicio no se encontró", "404", HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ServiceDto updateService(UUID serviceId, ServiceDto serviceDto) {
        return serviceRepository.findById(serviceId).map(service -> {
            service.setServiceName((serviceDto.getServiceName() != null) ? serviceDto.getServiceName() : service.getServiceName());
            service.setDescription((serviceDto.getDescription() != null) ? serviceDto.getDescription() : service.getDescription());
            service.setServiceHour((serviceDto.getServiceHour() != null) ? serviceDto.getServiceHour() : service.getServiceHour());
            service.setContact((serviceDto.getContact() != null) ? serviceDto.getContact() : service.getContact());
            service.setLocation((serviceDto.getLocation() != null) ? serviceDto.getLocation() : service.getLocation());
            service.setServiceImg((serviceDto.getServiceImg() != null) ? serviceDto.getServiceImg() : service.getServiceImg());
            service.setRating((serviceDto.getRating() != null) ? serviceDto.getRating() : service.getRating());
            service.setPriceTrans((serviceDto.getPriceTrans() != null) ? serviceDto.getPriceTrans() : service.getPriceTrans());
            service.setTripAmount((serviceDto.getTripAmount() != null) ? serviceDto.getTripAmount() : service.getTripAmount());
            service.setRoom((serviceDto.getRoom() != null) ? serviceDto.getRoom() : service.getRoom());
            service.setDoubleRoom((serviceDto.getDoubleRoom() != null) ? serviceDto.getDoubleRoom() : service.getDoubleRoom());
            service.setNightAmount((serviceDto.getNightAmount() != null) ? serviceDto.getNightAmount() : service.getNightAmount());
            service.setFoodPrice((serviceDto.getFoodPrice() != null) ? serviceDto.getFoodPrice() : service.getFoodPrice());
            service.setEntranceFee((serviceDto.getEntranceFee() != null) ? serviceDto.getEntranceFee() : service.getEntranceFee());
            service.setPersonalGuide((serviceDto.getPersonalGuide() != null) ? serviceDto.getPersonalGuide() : service.getPersonalGuide());
            service.setRoomAmount((serviceDto.getRoomAmount() != null) ? serviceDto.getRoomAmount() : service.getRoomAmount());

            if (serviceDto.getServiceName() != null){
                Optional<TypeService> optionalTypeService = typeServiceRepository.findByTypeName(serviceDto.getTypeName());

                if (optionalTypeService.isPresent()){
                    TypeService typeService = optionalTypeService.get();
                    service.setTypeId(typeService.getTypeId());
                    service.setTypeName(typeService.getTypeName());
                }else {
                    throw new MessageGeneric("El tipo de servicio no se encontró", "404", HttpStatus.NOT_FOUND);
                }
            }
            return modelMapper.map(serviceRepository.save(service), ServiceDto.class);
        }).orElseThrow(() -> new MessageGeneric("No se encontró el servicio", "404", HttpStatus.NOT_FOUND));
    }

    @Override
    public Boolean deleteService(UUID serviceId) {
        if(serviceRepository.findById(serviceId).isPresent()){
            serviceRepository.deleteById(serviceId);
            return true;
        }
        return false;
    }

    @Override
    public List<ServiceDto> getServiceByType(UUID typeId) {
        return serviceRepository.getByTypeId(typeId).stream().map(service ->
                modelMapper.map(service, ServiceDto.class)).collect(Collectors.toList());

    }

    @Override
    public Optional<ServiceDto> getServiceName(String serviceName) {
        return Optional.ofNullable(serviceRepository.findByServiceName(serviceName).map(service ->
                modelMapper.map(service, ServiceDto.class)).
                orElseThrow(() -> new MessageGeneric("No se encontro el servicio que esta solicitando", "404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<ServiceDto> getServiceByTypeName(String typeName) {
        List<com.proyecto_turismo_ufpso.turismo.service.entity.Service> services = serviceRepository.findByTypeName(typeName);
        return services.stream().map(service -> modelMapper.map(service, ServiceDto.class)).collect(Collectors.toList());

    }
}
