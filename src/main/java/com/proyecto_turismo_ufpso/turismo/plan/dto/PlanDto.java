package com.proyecto_turismo_ufpso.turismo.plan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PlanDto {

    @JsonProperty(value = "planId", access = JsonProperty.Access.READ_ONLY)
    private UUID planId;

    @JsonProperty(value = "userId")
    @NotEmpty
    @NotNull
    private UUID userId;

    @JsonProperty(value = "total")
    @NotEmpty
    @NotNull
    private Double total;

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
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
