package com.proyecto_turismo_ufpso.turismo.offerDetail.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDetailDto {


    @JsonProperty(value = "offerDetailId")
    private UUID offerDetailId;

    @JsonProperty(value = "offerId")
    @NotEmpty
    @NotNull
    private UUID offerId;

    @JsonProperty(value = "serviceId")
    @NotEmpty
    @NotNull
    private UUID serviceId;


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

    @JsonProperty(value = "night_amount")
    @NotEmpty
    private Integer night_amount;

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

    public UUID getOfferDetailId() {
        return offerDetailId;
    }

    public void setOfferDetailId(UUID offerDetailId) {
        this.offerDetailId = offerDetailId;
    }

    public UUID getOfferId() {
        return offerId;
    }

    public void setOfferId(UUID offerId) {
        this.offerId = offerId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
