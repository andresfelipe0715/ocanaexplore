package com.proyecto_turismo_ufpso.turismo.service.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDto {
    @JsonProperty(value = "serviceId", access = JsonProperty.Access.READ_ONLY)
    private UUID serviceId;

    @JsonProperty(value = "fk_type_service_id")
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
    @Size(max = 80,message = "El campo serviceImg debe tener menos de 80 carater")
    private Double priceTrans;

    @JsonProperty(value = "tripAmount")
    @NotEmpty
    @Size(max = 80,message = "El campo serviceImg debe tener menos de 80 carater")
    private Integer tripAmount;


    @JsonProperty(value = "room")
    @NotEmpty
    @Size(max = 80,message = "El campo room debe tener menos de 80 carater")
    private Double room;

    @JsonProperty(value = "doubleRoom")
    @NotEmpty
    @Size(max = 80,message = "El campo room debe tener menos de 80 carater")
    private Double doubleRoom;

    @JsonProperty(value = "nightAmount")
    @NotEmpty
    @Size(max = 80,message = "El campo nightAmount debe tener menos de 80 carater")
    private Integer nightAmount;


    @JsonProperty(value = "foodPrice")
    @NotEmpty
    @Size(max = 80,message = "El campo foodPrice debe tener menos de 80 carater")
    private Double foodPrice;

    @JsonProperty(value = "entranceFee")
    @NotEmpty
    @Size(max = 80,message = "El campo entranceFee debe tener menos de 80 carater")
    private Double entranceFee;


    @JsonProperty(value = "personalGuide")
    @NotEmpty
    @Size(max = 80,message = "El campo personalGuide debe tener menos de 80 carater")
    private Double personalGuide;

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
