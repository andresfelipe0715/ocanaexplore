package com.proyecto_turismo_ufpso.turismo.planDetail.service;

import com.proyecto_turismo_ufpso.turismo.planDetail.dto.PlanDetailDto;
import com.proyecto_turismo_ufpso.turismo.service.dto.ServiceDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlanDetailService {

    List<PlanDetailDto> getAllPlanDetails();

    Optional <PlanDetailDto> getPlanDetailId(UUID planDetailId);

    PlanDetailDto savePlanDetail (PlanDetailDto planDetailDto);

    Boolean deletePlanDetail (UUID planDetailId);

    List<PlanDetailDto> getPlanDetailByPlan(UUID planId);

    PlanDetailDto updatePlanDetail(UUID planDetailId, PlanDetailDto planDetailDto);
}
