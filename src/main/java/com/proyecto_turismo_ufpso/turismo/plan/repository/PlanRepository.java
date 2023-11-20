package com.proyecto_turismo_ufpso.turismo.plan.repository;

import com.proyecto_turismo_ufpso.turismo.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {
    Optional<Plan> findByUserId(UUID userId);
}
