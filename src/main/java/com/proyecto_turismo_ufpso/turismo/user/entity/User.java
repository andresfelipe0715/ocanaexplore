package com.proyecto_turismo_ufpso.turismo.user.entity;

import com.proyecto_turismo_ufpso.turismo.person.entity.Person;
import com.proyecto_turismo_ufpso.turismo.profile.entity.Profile;
import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user" , schema = "turismo")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @NotNull
    @Column(name = "fk_person_id",nullable = false,length = 40)
    private UUID personId;
    @NotNull
    @Column(name = "User_password", nullable = false, length = 50)
    private String userPassword;


    @NotNull
    @Column(name = "Profile_name", nullable = false, length = 50, updatable = true)
    private String profileName;


    @OneToOne
    @JoinColumn(name = "fk_person_id", insertable = false , updatable = false)
    private Person person;


    @ManyToOne
    @JoinColumn(name = "fk_profile_id")
    private Profile profile;



    @Column( name = "activo", nullable = false)
    private Boolean activo = true;




    public User(UUID userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;

    }


    public User() {

    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }



    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getActivo() {
        return activo;
    }


    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
