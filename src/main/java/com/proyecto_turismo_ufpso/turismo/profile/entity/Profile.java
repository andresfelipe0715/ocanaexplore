package com.proyecto_turismo_ufpso.turismo.profile.entity;

import com.proyecto_turismo_ufpso.turismo.user.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profile", schema = "turismo")
public class Profile {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "profile_id")
    private UUID id;

    @Column(name = "profile_name", nullable = false, length = 50)
    private String profileName;

    @OneToMany(mappedBy = "profile")
    private List<User> users = new ArrayList<>();

    public Profile() {
    }

    public Profile(String profileName) {
        this.profileName = profileName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}