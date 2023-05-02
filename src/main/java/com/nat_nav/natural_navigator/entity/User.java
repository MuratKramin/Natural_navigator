package com.nat_nav.natural_navigator.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "public", catalog = "natural_navigator_bd")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "otchestvo")
    private String otchestvo;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone_num")
    private String phoneNum;
    @Basic
    @Column(name = "password")
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user_rev")
    private List<ResidenceHistory> residenceHistoryList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String firstName, String lastName, String otchestvo, String email, String phoneNum, String password, List<ResidenceHistory> residenceHistoryList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otchestvo = otchestvo;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
        this.residenceHistoryList = residenceHistoryList;
    }
    public User(){

    }

    public List<ResidenceHistory> getResidenceHistoryList() {
        return residenceHistoryList;
    }

    public void setResidenceHistoryList(List<ResidenceHistory> residenceHistoryList) {
        this.residenceHistoryList = residenceHistoryList;
    }
}
