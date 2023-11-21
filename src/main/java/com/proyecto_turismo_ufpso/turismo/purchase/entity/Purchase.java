package com.proyecto_turismo_ufpso.turismo.purchase.entity;

import com.proyecto_turismo_ufpso.turismo.Enum.PurchaseStatusEnum;
import com.proyecto_turismo_ufpso.turismo.offer.entity.Offer;
import com.proyecto_turismo_ufpso.turismo.plan.entity.Plan;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase", schema = "turismo")
public class Purchase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "purchase_Id")
    private UUID purchaseId;


    @Column(name = "serial_number", nullable = false, length = 16)
    private String serialNumber;

    @Column(name = "date", nullable = false, length = 50)
    private LocalDateTime date;

    @Column(name = "payment_method", nullable = true, length = 50)
    private String paymentMethod;

    @Column(name = "target_number", nullable = true, length = 50)
    private Long targetNumber;

    @Column(name = "total", nullable = false)
    private Double total;


    @Column(name = "codigo_qr")
    private String codigoQr;




    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PurchaseStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "fk_plan_id")  //no se pueden insertar ni actualizar nuevas categorias
    private Plan plan;



    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public PurchaseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatusEnum status) {
        this.status = status;
    }
}
