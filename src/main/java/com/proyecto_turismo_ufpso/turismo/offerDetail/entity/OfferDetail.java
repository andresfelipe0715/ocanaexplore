package com.proyecto_turismo_ufpso.turismo.offerDetail.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto_turismo_ufpso.turismo.offer.entity.Offer;
import com.proyecto_turismo_ufpso.turismo.plan.entity.Plan;
import com.proyecto_turismo_ufpso.turismo.service.entity.Service;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "offer_detail", schema= "turismo")
public class OfferDetail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="offer_detail_id")
    private UUID offerDetailId;

    @Column(name = "fk_service_id")
    private UUID serviceId;

    @Column(name = "fk_offer_id")
    private UUID offerId;

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
    @JoinColumn(name = "fk_offer_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "fk_service_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
    private Service service;

    public UUID getOfferDetailId() {
        return offerDetailId;
    }

    public void setOfferDetailId(UUID offerDetailId) {
        this.offerDetailId = offerDetailId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public UUID getOfferId() {
        return offerId;
    }

    public void setOfferId(UUID offerId) {
        this.offerId = offerId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
