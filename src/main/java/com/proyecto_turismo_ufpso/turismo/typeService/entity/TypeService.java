package com.proyecto_turismo_ufpso.turismo.typeService.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Table(name = "type_service", schema = "turismo")
@Entity
public class TypeService {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "type_service_id")
    private UUID typeId;

    @Column(name = "type_service_name", nullable = false, length = 40)
    private String typeName;

    @Column(name = "description", nullable = false, length = 80)
    private String description;

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
