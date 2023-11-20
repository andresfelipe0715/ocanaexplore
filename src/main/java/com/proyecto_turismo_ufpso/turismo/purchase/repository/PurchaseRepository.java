package com.proyecto_turismo_ufpso.turismo.purchase.repository;

import com.proyecto_turismo_ufpso.turismo.Enum.PurchaseStatusEnum;
import com.proyecto_turismo_ufpso.turismo.offer.entity.Offer;
import com.proyecto_turismo_ufpso.turismo.plan.entity.Plan;
import com.proyecto_turismo_ufpso.turismo.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

    List<Purchase> findAll();

    Purchase findByPurchaseId(UUID purchaseId);

    Purchase findBySerialNumber(String serialNumber);

    List<Purchase> findByStatus(PurchaseStatusEnum status);

    List<Purchase> getByPlan(Plan plan);

    boolean existsByPlanAndStatus(Plan plan, PurchaseStatusEnum status);


}
