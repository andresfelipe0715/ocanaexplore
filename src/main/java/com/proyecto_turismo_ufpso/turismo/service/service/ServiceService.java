package com.proyecto_turismo_ufpso.turismo.service.service;

import com.proyecto_turismo_ufpso.turismo.service.dto.ServiceDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceService {

    List<ServiceDto> getAllService();

    Optional<ServiceDto> getServiceId(UUID serviceId);

    ServiceDto saveService(ServiceDto serviceDto);

    ServiceDto updateService(UUID serviceId, ServiceDto serviceDto);

    Boolean deleteService(UUID serviceId);

    List<ServiceDto> getServiceByType (UUID typeId);

    Optional<ServiceDto> getServiceName(String serviceName);

    List<ServiceDto> getServiceByTypeName(String typeName);


}
