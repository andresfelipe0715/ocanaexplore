package com.proyecto_turismo_ufpso.turismo.offer.entity;


import com.proyecto_turismo_ufpso.turismo.planDetail.entity.PlanDetail;
import com.proyecto_turismo_ufpso.turismo.user.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @Column(name = "fk_user_id", nullable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
    private User user;


}
