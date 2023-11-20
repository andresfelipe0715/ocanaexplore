package com.proyecto_turismo_ufpso.turismo.plan.entity;

import com.proyecto_turismo_ufpso.turismo.planDetail.entity.PlanDetail;
import com.proyecto_turismo_ufpso.turismo.user.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "plan", schema= "turismo")
public class Plan {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="plan_id")
    private UUID planId;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "fk_user_id", nullable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
    private User user;

    @OneToMany(mappedBy = "plan", cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<PlanDetail> planDetails;


    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
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

    public List<PlanDetail> getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(List<PlanDetail> planDetails) {
        this.planDetails = planDetails;
    }
}
