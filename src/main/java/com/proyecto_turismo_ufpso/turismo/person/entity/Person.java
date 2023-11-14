package com.proyecto_turismo_ufpso.turismo.person.entity;
import com.proyecto_turismo_ufpso.turismo.user.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "person", schema= "turismo")
public class Person {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="person_id")
    private UUID personId;

    @Column(name="first_name",nullable = false, length = 100)
    private String firstName;
    @Column(name="last_name",nullable = false, length = 100)
    private String lastName;

    @Column(name="email",nullable = false, length = 100)
    private String email;

    @Column(name="address",nullable = false, length = 150)
    private String address;

    @Column(name="phone_number",nullable = false, length = 15)
    private String phone;

    @Column(name="document",nullable = false, length = 20, unique = true)
    private Long document;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private User user;

    @Column(nullable = true)
    private boolean activo = true;


    public Person() {
    }

    public Person(String firstName, String lastName, String email, String address, String phone, Long document) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.document = document;

    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    public  static  Person create(String firstName, String lastName, String email, String address, String phone, long document){
        return  new Person( firstName, lastName, email,  address, phone,  document);
    }
    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }
}