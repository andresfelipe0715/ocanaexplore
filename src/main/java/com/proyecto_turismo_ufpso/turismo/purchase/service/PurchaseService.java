package com.proyecto_turismo_ufpso.turismo.purchase.service;

import com.proyecto_turismo_ufpso.turismo.purchase.dto.PurchaseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface PurchaseService {


    Optional<List<PurchaseDto>> getAllPurchase();

    Optional<PurchaseDto> getPurchaseById(UUID purchaseId);

    Optional<PurchaseDto> getPurchaseBySerialNumber(String serialNumber);

    PurchaseDto createPurchase(PurchaseDto purchaseDto);

    ResponseEntity<String> updatePurchaseStatus(UUID purchaseId, Map<String, String> statusUpdate);

    List<PurchaseDto> getPurchaseByPlanId(UUID planId);

    Boolean deletePurchase(UUID purchaseId);


}
