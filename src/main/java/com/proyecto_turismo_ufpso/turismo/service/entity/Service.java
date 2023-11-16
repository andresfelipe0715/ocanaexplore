package com.proyecto_turismo_ufpso.turismo.service.entity;

import com.proyecto_turismo_ufpso.turismo.typeService.entity.TypeService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "service", schema= "turismo")
public class Service {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="service_id")
    private UUID serviceId;

    @Column(name = "fk_type_service_id")
    private UUID typeId;
    @Column(name = "service_name", nullable = false, length = 50)
    private String serviceName;

    @Column(name = "description", nullable = false, length = 80)
    private String description;

    @Column(name = "service_hour", nullable = false)
    private String serviceHour;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "service_img")
    private String serviceImg;

    @Column(name = "typeName")
    private String typeName;

    @Column(name = "rating")
    private String rating;

    @Column(name = "price_trans")
    private Double priceTrans;

    @Column(name = "trip_amount")
    private Integer tripAmount;

    @Column(name = "room")
    private Double room;

    @Column(name = "double_room")
    private Double doubleRoom;

    @Column(name = "night_amount")
    private Integer nightAmount ;

    @Column(name = "food_price")
    private Double foodPrice;

    @Column(name = "entrance_fee ")
    private Double entranceFee ;

    @Column(name = "personal_guide")
    private Double personalGuide ;

    @ManyToOne
    @JoinColumn(name = "fk_type_service_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
    private TypeService typeService;

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public UUID getTypeId() {
        return typeId;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
}
