package com.proyecto_turismo_ufpso.turismo.offer.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto_turismo_ufpso.turismo.offerDetail.entity.OfferDetail;
import com.proyecto_turismo_ufpso.turismo.planDetail.entity.PlanDetail;
import com.proyecto_turismo_ufpso.turismo.user.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "offer", schema= "turismo")
public class Offer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="offer_id")
    private UUID offerId;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "finish_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date finishDate;

    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "total_discount", nullable = false)
    private Double totalDiscount;
    @Column(name = "fk_user_id", nullable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
    private User user;

    @OneToMany(mappedBy = "offer", cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<OfferDetail> offerDetails;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public UUID getOfferId() {
        return offerId;
    }

    public void setOfferId(UUID offerId) {
        this.offerId = offerId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OfferDetail> getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(List<OfferDetail> offerDetails) {
        this.offerDetails = offerDetails;
    }
}
