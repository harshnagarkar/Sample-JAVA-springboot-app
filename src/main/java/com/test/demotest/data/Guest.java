package com.test.demotest.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GUEST")
public class Guest {
//     CREATE TABLE GUEST(
//   GUEST_ID BIGSERIAL PRIMARY KEY,
//   FIRST_NAME VARCHAR(64),
//   LAST_NAME VARCHAR(64),
//   EMAIL_ADDRESS VARCHAR(64),
//   ADDRESS VARCHAR(64),
//   COUNTRY VARCHAR(32),
//   STATE VARCHAR(12),
// //   PHONE_NUMBER VARCHAR(24)
// );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Guest_ID")
    private  long id;

    @Column(name="FIRST_NAME")
    private String first_name;

    @Column(name="LAST_NAME")
    private String last_name;

    @Column(name="EMAIL_ADDRESS")
    private String email_addres;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="COUNTRY")
    private String country;

    @Column(name="STATE")
    private String state;

    @Column(name="PHONE_NUMBER")
    private String phone_number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_addres() {
        return email_addres;
    }

    public void setEmail_addres(String email_addres) {
        this.email_addres = email_addres;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Guest [address=" + address + ", country=" + country + ", email_addres=" + email_addres + ", first_name="
                + first_name + ", id=" + id + ", last_name=" + last_name + ", phone_number=" + phone_number + ", state="
                + state + "]";
    }
}
