package com.proyecto_turismo_ufpso.turismo.planDetail.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto_turismo_ufpso.turismo.plan.entity.Plan;
import com.proyecto_turismo_ufpso.turismo.service.entity.Service;
import com.proyecto_turismo_ufpso.turismo.typeService.entity.TypeService;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "plan_detail", schema= "turismo")
public class PlanDetail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="plan_detail_id")
    private UUID planDetailId;

    @Column(name = "fk_service_id")
    private UUID serviceId;

    @Column(name = "fk_plan_id")
    private UUID planId;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date date;

    @Column(name = "trip_amount")
    private Integer tripAmount;

    @Column(name = "room_amount")
    private Integer roomAmount;

    @Column(name = "doubleRoom_amount")
    private Integer doubleRoomAmount;

    @Column(name = "night_amount")
    private Integer nightAmount ;

    @Column(name = "food_amount")
    private Double foodAmount;

    @Column(name = "time")
    private String time;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;


     @ManyToOne
     @JoinColumn(name = "fk_plan_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
     private Plan plan;

    @ManyToOne
    @JoinColumn(name = "fk_service_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
    private Service service;

    public UUID getPlanDetailId() {
        return planDetailId;
    }

    public void setPlanDetailId(UUID planDetailId) {
        this.planDetailId = planDetailId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTripAmount() {
        return tripAmount;
    }

    public void setTripAmount(Integer tripAmount) {
        this.tripAmount = tripAmount;
    }

    public Integer getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(Integer roomAmount) {
        this.roomAmount = roomAmount;
    }

    public Integer getDoubleRoomAmount() {
        return doubleRoomAmount;
    }

    public void setDoubleRoomAmount(Integer doubleRoomAmount) {
        this.doubleRoomAmount = doubleRoomAmount;
    }

    public Integer getNightAmount() {
        return nightAmount;
    }

    public void setNightAmount(Integer nightAmount) {
        this.nightAmount = nightAmount;
    }

    public Double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(Double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
