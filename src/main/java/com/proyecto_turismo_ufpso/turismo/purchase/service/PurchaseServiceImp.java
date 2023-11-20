package com.proyecto_turismo_ufpso.turismo.purchase.service;


import com.proyecto_turismo_ufpso.turismo.Enum.PurchaseStatusEnum;
import com.proyecto_turismo_ufpso.turismo.Exception.exceptions.MessageGeneric;
import com.proyecto_turismo_ufpso.turismo.plan.entity.Plan;
import com.proyecto_turismo_ufpso.turismo.plan.repository.PlanRepository;
import com.proyecto_turismo_ufpso.turismo.planDetail.entity.PlanDetail;
import com.proyecto_turismo_ufpso.turismo.planDetail.repository.PlanDetailRepository;
import com.proyecto_turismo_ufpso.turismo.purchase.dto.PurchaseDto;
import com.proyecto_turismo_ufpso.turismo.purchase.entity.Purchase;
import com.proyecto_turismo_ufpso.turismo.purchase.repository.PurchaseRepository;
import com.proyecto_turismo_ufpso.turismo.service.repository.ServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImp implements PurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanDetailRepository planDetailRepository;

    @Override
    public Optional<List<PurchaseDto>> getAllPurchase() {
        return Optional.of(purchaseRepository.findAll().stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseDto.class))
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<PurchaseDto> getPurchaseById(UUID purchaseId) {
        Purchase purchase = purchaseRepository.findByPurchaseId(purchaseId);
        if (purchase != null) {
            return Optional.of(modelMapper.map(purchase, PurchaseDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PurchaseDto> getPurchaseBySerialNumber(String serialNumber) {
        Purchase purchase = purchaseRepository.findBySerialNumber(serialNumber);
        if (purchase != null) {
            return Optional.of(modelMapper.map(purchase, PurchaseDto.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PurchaseDto createPurchase(PurchaseDto purchaseDto) {
        // Mapea el DTO a una entidad Purchase
        Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);

        // Genera un número de serie único
        String uniqueSerialNumber = SerialNumberGenerator.generateUniqueSerialNumber();
        purchase.setSerialNumber(uniqueSerialNumber);

        // Establece la fecha actual
        purchase.setDate(LocalDateTime.now());

        // Busca el carrito correspondiente por su ID directamente en el método findById
        Plan plan = planRepository.findById(purchaseDto.getPlanId())
                .orElseThrow(() -> new MessageGeneric("Carrito no encontrado", "404", HttpStatus.NOT_FOUND));

        if (purchaseRepository.existsByPlanAndStatus(plan, PurchaseStatusEnum.PENDING)){
            throw new MessageGeneric("Ya existe una compra pendiente para este carrito", "400", HttpStatus.BAD_REQUEST);
        }

        // Asigna el valor de subtotal al atributo total de la entidad Purchase
        purchase.setTotal(plan.getTotal());

        // Establece el estado predeterminado (PENDING) si no se proporciona
        if (purchase.getStatus() == null) {
            purchase.setStatus(PurchaseStatusEnum.PENDING);
        }

        // Establece paymentMethod y targetNumber como nulos si no se proporcionan
        if (purchase.getPaymentMethod() == null) {
            purchase.setPaymentMethod(null);
        }

        if (purchase.getTargetNumber() == null) {
            purchase.setTargetNumber(null);
        }

        Purchase createdPurchase = purchaseRepository.save(purchase);

        // Mapea la entidad creada de nuevo a un DTO y devuélvelo
        return modelMapper.map(createdPurchase, PurchaseDto.class);
    }

    @Override
    public ResponseEntity<String> updatePurchaseStatus(UUID purchaseId, Map<String, String> statusUpdate) {
        PurchaseStatusEnum statusEnum = PurchaseStatusEnum.getValueOf(statusUpdate.get("status"));

        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        if (Objects.equals(PurchaseStatusEnum.PAID, statusEnum)) {
            List<PlanDetail> planDetails = purchase.getPlan().getPlanDetails();
            List<com.proyecto_turismo_ufpso.turismo.service.entity.Service> services = new ArrayList<>();



            serviceRepository.saveAll(services);

            if(!planDetails.isEmpty()){
                planDetailRepository.deleteAll(planDetails);
            }

        }

        if (Objects.equals(PurchaseStatusEnum.PAID, statusEnum) || Objects.equals(PurchaseStatusEnum.CANCELED, statusEnum)) {
            // Actualizar el subtotal del plan a 0
            purchase.getPlan().setTotal(0.0);
        }

        purchase.setStatus(statusEnum);

        // Actualiza paymentMethod si se proporciona en statusUpdate
        if (statusUpdate.containsKey("paymentMethod")) {
            purchase.setPaymentMethod(statusUpdate.get("paymentMethod"));
        }

        // Actualiza targetNumber si se proporciona en statusUpdate
        if (statusUpdate.containsKey("targetNumber")) {
            String targetNumberStr = statusUpdate.get("targetNumber");
            if (targetNumberStr != null && !targetNumberStr.isEmpty()) {
                try {
                    Long targetNumber = Long.parseLong(targetNumberStr);
                    purchase.setTargetNumber(targetNumber);
                } catch (NumberFormatException e) {
                    // Manejo de error si no se puede convertir a Long
                }
            }
        }

        purchaseRepository.save(purchase);

        return ResponseEntity.ok().build();
    }

    @Override
    public List<PurchaseDto> getPurchaseByPlanId(UUID planId) {
        Plan plan = new Plan();
        plan.setPlanId(planId);

        // Llamar al método Plan del repositorio con el objeto Cart
        return purchaseRepository.getByPlan(plan).stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deletePurchase(UUID purchaseId) {
        if(purchaseRepository.findById(purchaseId).isPresent()){
            purchaseRepository.deleteById(purchaseId);
            return true;
        }
        return false;
    }
}
