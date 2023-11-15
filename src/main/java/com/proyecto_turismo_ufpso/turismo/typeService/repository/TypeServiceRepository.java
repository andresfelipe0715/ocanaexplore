package com.proyecto_turismo_ufpso.turismo.typeService.repository;

import com.proyecto_turismo_ufpso.turismo.typeService.entity.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TypeServiceRepository extends JpaRepository<TypeService, UUID> {

}
