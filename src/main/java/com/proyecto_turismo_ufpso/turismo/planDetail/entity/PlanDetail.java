package com.proyecto_turismo_ufpso.turismo.planDetail.entity;


import com.proyecto_turismo_ufpso.turismo.typeService.entity.TypeService;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "planDetail", schema= "turismo")
public class PlanDetail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="planDetail_id")
    private UUID planDetailId;

    @Column(name = "fk_service_id")
    private UUID serviceId;

    @Column(name = "fk_plan_id")
    private UUID planId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
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


    @Column(name = "subtotal", nullable = false)
    private Double subtotal;



  //  @ManyToOne
  //  @JoinColumn(name = "fk_plan_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
  //  private Plan plan;

    //  @ManyToOne
    //  @JoinColumn(name = "fk_service_id", insertable = false,updatable = false)  //no se pueden insertar ni actualizar nuevas categorias
    //  private Service service;



}
