package com.proyecto_turismo_ufpso.turismo.planDetail.repository;

import com.proyecto_turismo_ufpso.turismo.planDetail.entity.PlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PlanDetailRepository extends JpaRepository<PlanDetail, UUID> {

    List<PlanDetail> getByPlanId (UUID planId);

    @Query(value = "select sum(pd.subtotal) from turismo.plan_detail pd where pd.fk_plan_id = :planId", nativeQuery = true)
    Double sumSubtotalByPlanId(UUID planId);
}
