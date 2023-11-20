package com.proyecto_turismo_ufpso.turismo.purchase.controller;


import com.proyecto_turismo_ufpso.turismo.purchase.dto.PurchaseDto;
import com.proyecto_turismo_ufpso.turismo.purchase.service.PurchaseService;
import com.proyecto_turismo_ufpso.turismo.purchase.service.SerialNumberGenerator;
import com.proyecto_turismo_ufpso.turismo.service.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseDto>> getAll() {
        Optional<List<PurchaseDto>> purchases = purchaseService.getAllPurchase();

        if (purchases.isPresent()) {
            return new ResponseEntity<>(purchases.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Otra respuesta HTTP apropiada si no se encuentran compras.
        }
    }

    @PostMapping("/createPurchase")
    public ResponseEntity<PurchaseDto> createPurchase(@RequestBody PurchaseDto purchaseDto) {
        String uniqueSerialNumber = SerialNumberGenerator.generateUniqueSerialNumber();
        purchaseDto.setSerialNumber(uniqueSerialNumber);
        PurchaseDto createdPurchase = purchaseService.createPurchase(purchaseDto);
        return new ResponseEntity<>(createdPurchase, HttpStatus.CREATED);
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<PurchaseDto> findByPurchaseId(@PathVariable UUID purchaseId) {
        Optional<PurchaseDto> purchase = purchaseService.getPurchaseById(purchaseId);
        if (purchase.isPresent()) {
            return new ResponseEntity<>(purchase.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/purchaseBySerialNumber/{serialNumber}")
    public ResponseEntity<PurchaseDto> getPurchaseBySerialNumber(@PathVariable String serialNumber) {
        Optional<PurchaseDto> purchase = purchaseService.getPurchaseBySerialNumber(serialNumber);
        if (purchase.isPresent()) {
            return new ResponseEntity<>(purchase.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/update/{purchaseId}")
    public ResponseEntity<String> updatePurchaseStatus(
            @PathVariable UUID purchaseId,
            @RequestBody Map<String, String> statusUpdate) {
        return purchaseService.updatePurchaseStatus(purchaseId, statusUpdate);
    }

    @GetMapping("/purchaseByCart/{cartId}")
    public ResponseEntity<List<PurchaseDto>> getPurchaseByCart(@PathVariable("planId") UUID planId){
        List<PurchaseDto> purchases= purchaseService.getPurchaseByPlanId(planId);
        if (!purchases.isEmpty()){
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{purchaseId}")
    public ResponseEntity deletePurchase (@PathVariable UUID purchaseId){
        if(purchaseService.deletePurchase(purchaseId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
