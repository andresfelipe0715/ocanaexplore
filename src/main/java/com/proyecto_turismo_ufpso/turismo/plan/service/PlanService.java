package com.proyecto_turismo_ufpso.turismo.plan.service;

import com.proyecto_turismo_ufpso.turismo.plan.dto.PlanDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlanService {

    List<PlanDto> getAllPlan();

    Optional<PlanDto> getPlanId(UUID planId);

    PlanDto savePlan(PlanDto planDto);

    PlanDto updatePlan(UUID planId, PlanDto planDto);

    Boolean deletePlan(UUID planId);

    PlanDto getPlanByUser(UUID userId);


}
