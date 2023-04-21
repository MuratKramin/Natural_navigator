package com.nat_nav.natural_navigator.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Hotels")
public class Hotel {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;

    @Column(name="country")
    private String country;
    @Column(name="region")
    private String region;

    @Column(name="city")
    private String city;
    @Column(name="street")
    private String street;
    @Column(name="house")
    private String house;
    @Column(name="description")
    private String description;
    @Column(name="phone_num")
    private String phone_num;
    @Column(name="email")
    private String email;
    @Column(name="web_site")
    private String web_site;

    @JsonManagedReference
    @OneToMany(mappedBy = "hotel_pic")
    private List<Photo> photoList;

    public Hotel(){

    }

    public Hotel(int id, String name, String country, String region, String city, String street, String house, String description, String phone_num, String email, String web_site, List<Photo> photoList) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.description = description;
        this.phone_num = phone_num;
        this.email = email;
        this.web_site = web_site;
        this.photoList = photoList;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", description='" + description + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", email='" + email + '\'' +
                ", web_site='" + web_site + '\'' +
                ", photoList=" + photoList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb_site() {
        return web_site;
    }

    public void setWeb_site(String web_site) {
        this.web_site = web_site;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public List<Photo> getPhotofromSecond(){return photoList.subList(1,photoList.size());}
}
