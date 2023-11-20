package com.proyecto_turismo_ufpso.turismo.offer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class OfferDto {

    @JsonProperty(value = "offerId", access = JsonProperty.Access.READ_ONLY)
    private UUID offerId;

    @JsonProperty(value = "userId")
    @NotEmpty
    @NotNull
    private UUID userId;

    @JsonProperty(value = "total")
    @NotEmpty
    @NotNull
    private Double total;

    @JsonProperty(value = "startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    @NotEmpty
    private Date startDate;
    @JsonProperty(value = "finishDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    @NotEmpty
    private Date finishDate;

    @JsonProperty(value = "percentage")
    @NotEmpty
    private Double percentage;

    @JsonProperty(value = "discount")
    @NotEmpty
    private Double discount;

    @JsonProperty(value = "totalDiscount")
    @NotEmpty
    private Double totalDiscount;

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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
