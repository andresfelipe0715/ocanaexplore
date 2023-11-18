package com.proyecto_turismo_ufpso.turismo.planDetail.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PlanDetailDto {

    @JsonProperty(value = "planDetail", access = JsonProperty.Access.READ_ONLY)
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
    @NotNull
    @NotEmpty
    private Date date;

    @JsonProperty(value = "tripAmount")
    @NotEmpty
    private Integer tripAmount;

    @JsonProperty(value = "roomAmount")
    @NotEmpty
    private Integer roomAmount;

    @JsonProperty(value = "doubleRoomAmount")
    @NotEmpty
    private Integer doubleRoomAmount;

    @JsonProperty(value = "night_amount")
    @NotEmpty
    private Integer night_amount;

    @JsonProperty(value = "foodAmount")
    @NotEmpty
    private Double foodAmount;

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

    public Integer getNight_amount() {
        return night_amount;
    }

    public void setNight_amount(Integer night_amount) {
        this.night_amount = night_amount;
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
}
