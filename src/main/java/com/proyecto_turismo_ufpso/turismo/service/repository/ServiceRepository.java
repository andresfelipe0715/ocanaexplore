package com.proyecto_turismo_ufpso.turismo.service.repository;

import com.proyecto_turismo_ufpso.turismo.service.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {

    Boolean existsByServiceName (String serviceName);

    Optional<Service> findByServiceName (String serviceName);
    List<Service> getByTypeId(UUID typeId);

    List<Service> findByTypeName(String typeName);

}
