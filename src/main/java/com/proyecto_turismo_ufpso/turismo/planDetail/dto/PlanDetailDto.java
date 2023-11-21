package com.proyecto_turismo_ufpso.turismo.planDetail.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PlanDetailDto {

    @JsonProperty(value = "planDetailId")
    private UUID planDetailId;

    @JsonProperty(value = "planId")
    @NotEmpty
    @NotNull
    private UUID planId;

    @JsonProperty(value = "serviceId")
    @NotEmpty
    @NotNull
    private UUID serviceId;

    @JsonProperty(value = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    @NotEmpty
    private Date date;

    @JsonProperty(value = "time")
    @NotEmpty
    private String time;

    @JsonProperty(value = "tripAmount")
    @NotEmpty
    private Integer tripAmount;

    @JsonProperty(value = "roomAmount")
    @NotEmpty
    private Integer roomAmount;

    @JsonProperty(value = "doubleRoomAmount")
    @NotEmpty
    private Integer doubleRoomAmount;

    @JsonProperty(value = "nightAmount")
    @NotEmpty
    private Integer nightAmount;

    @JsonProperty(value = "foodAmount")
    @NotEmpty
    private Double foodAmount;

    @JsonProperty(value = "serviceName")
    @NotEmpty
    private String serviceName;

    @JsonProperty(value = "serviceImg")
    @NotNull(message = "La imagen no debe ser nulo")
    @NotEmpty
    private String serviceImg;

    @JsonProperty(value = "subtotal")
    @NotEmpty
    private Double subtotal;



    public UUID getPlanDetailId() {
        return planDetailId;
    }

    public void setPlanDetailId(UUID planDetailId) {
        this.planDetailId = planDetailId;
    }

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
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


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(String serviceImg) {
        this.serviceImg = serviceImg;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
