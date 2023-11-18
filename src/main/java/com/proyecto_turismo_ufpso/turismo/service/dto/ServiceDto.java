package com.proyecto_turismo_ufpso.turismo.service.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDto {
    @JsonProperty(value = "serviceId", access = JsonProperty.Access.READ_ONLY)
    @NotEmpty
    @NotNull
    private UUID serviceId;

    @JsonProperty(value = "typeId")
    @NotEmpty
    @NotNull
    private UUID typeId;

    @JsonProperty(value = "serviceName")
    @NotNull(message = "El campo serviceName no puede ser null")
    @NotEmpty
    @Size(max = 40,message = "El campo serviceName debe tener menos de 40 carater")
    private String serviceName;

    @JsonProperty(value = "description")
    @NotNull(message = "El campo description no puede ser null")
    @NotEmpty
    @Size(max = 80,message = "El campo description debe tener menos de 80 carater")
    private String description;


    /*@JsonProperty(value = "date")
    @NotNull(message = "El campo date no puede ser null")
    @NotEmpty
    private LocalDate date;*/

    @JsonProperty(value = "serviceHour")
    @NotNull(message = "El campo serviceHour no puede ser null")
    @NotEmpty
    @Size(max = 80,message = "El campo serviceHour debe tener menos de 80 carater")
    private String serviceHour;


    @JsonProperty(value = "contact")
    @NotNull(message = "El campo contact no puede ser null")
    @NotEmpty
    @Size(max = 80,message = "El campo contact debe tener menos de 80 carater")
    private String contact;

    @JsonProperty(value = "location")
    @NotNull(message = "El campo location no puede ser null")
    @NotEmpty
    @Size(max = 80,message = "El campo location debe tener menos de 80 carater")
    private String location;

    @JsonProperty(value = "typeName")
    @NotEmpty
    private String TypeName;

    @JsonProperty(value = "serviceImg")
    @NotEmpty
    @Size(max = 80,message = "El campo serviceImg debe tener menos de 80 carater")
    private String serviceImg;

    @JsonProperty(value = "rating")
    @NotEmpty
    @Size(max = 80,message = "El campo serviceImg debe tener menos de 80 carater")
    private String rating;

    @JsonProperty(value = "priceTrans")
    @NotEmpty
    private Double priceTrans;

    @JsonProperty(value = "tripAmount")
    @NotEmpty
    private Integer tripAmount;


    @JsonProperty(value = "room")
    @NotEmpty
    private Double room;

    @JsonProperty(value = "doubleRoom")
    @NotEmpty
    private Double doubleRoom;

    @JsonProperty(value = "nightAmount")
    @NotEmpty
    private Integer nightAmount;

    @JsonProperty(value = "roomAmount")
    @NotEmpty
    private Integer roomAmount;

    @JsonProperty(value = "foodPrice")
    @NotEmpty
    private Double foodPrice;

    @JsonProperty(value = "entranceFee")
    @NotEmpty
    private Double entranceFee;


    @JsonProperty(value = "personalGuide")
    @NotEmpty
    private Double personalGuide;

    @JsonProperty(value = "subtotal")
    @NotEmpty
    @NotNull
    private Double subtotal;

    @JsonProperty(value = "foodAmount")
    @NotEmpty
    private Double foodAmount;

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return TypeName;
    }


    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceHour() {
        return serviceHour;
    }

    public void setServiceHour(String serviceHour) {
        this.serviceHour = serviceHour;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(String serviceImg) {
        this.serviceImg = serviceImg;
    }

    public Double getPriceTrans() {
        return priceTrans;
    }

    public void setPriceTrans(Double priceTrans) {
        this.priceTrans = priceTrans;
    }

    public Integer getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(Integer roomAmount) {
        this.roomAmount = roomAmount;
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

    public Integer getTripAmount() {
        return tripAmount;
    }

    public void setTripAmount(Integer tripAmount) {
        this.tripAmount = tripAmount;
    }

    public Double getRoom() {
        return room;
    }

    public void setRoom(Double room) {
        this.room = room;
    }

    public Double getDoubleRoom() {
        return doubleRoom;
    }

    public void setDoubleRoom(Double doubleRoom) {
        this.doubleRoom = doubleRoom;
    }

    public Integer getNightAmount() {
        return nightAmount;
    }

    public void setNightAmount(Integer nightAmount) {
        this.nightAmount = nightAmount;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Double getEntranceFee() {
        return entranceFee;
    }

    public void setEntranceFee(Double entranceFee) {
        this.entranceFee = entranceFee;
    }

    public Double getPersonalGuide() {
        return personalGuide;
    }

    public void setPersonalGuide(Double personalGuide) {
        this.personalGuide = personalGuide;
    }

}
