package com.proyecto_turismo_ufpso.turismo.purchase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto_turismo_ufpso.turismo.Enum.PurchaseStatusEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;

public class PurchaseDto {


    @JsonProperty(value = "purchaseId", access = JsonProperty.Access.READ_ONLY)
    private UUID purchaseId;

    @JsonProperty(value = "serialNumber")
    @NotNull(message = "El campo número de serie no puede ser null")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "El campo serialNumber solo se admite números y letras mayúsculas")
    @NotEmpty
    private String serialNumber;


    @JsonProperty(value = "date")
    @NotNull(message = "El campo date no puede ser null")
    @Pattern(regexp = "\\d+", message = "El campo serialNumber solo se admite numeros")
    @NotEmpty
    private LocalDateTime date;

    @JsonProperty(value = "paymentMethod")
    @NotEmpty
    private String paymentMethod;

    @JsonProperty(value = "targetNumber")
    @NotEmpty
    private Long targetNumber;

    @JsonProperty(value = "total")
    @NotNull(message = "El campo total no puede ser null")
    @NotEmpty
    private String total;

    @JsonProperty(value = "status")
    @NotNull(message = "El campo state no puede ser null")
    private PurchaseStatusEnum status;

    @JsonProperty(value = "planId")
    @NotNull(message = "El campo state no puede ser null")
    private UUID planId;

    @JsonProperty(value = "offerId")
    @NotNull(message = "El campo state no puede ser null")
    private UUID offerId;


    public UUID getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(UUID purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(Long targetNumber) {
        this.targetNumber = targetNumber;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public PurchaseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatusEnum status) {
        this.status = status;
    }

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
    }

    public UUID getOfferId() {
        return offerId;
    }

    public void setOfferId(UUID offerId) {
        this.offerId = offerId;
    }
}
