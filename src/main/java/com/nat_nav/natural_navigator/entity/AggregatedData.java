package com.nat_nav.natural_navigator.entity;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

import java.util.Objects;


public class AggregatedData {
    public int id_user;
    public int id_hotel;
    public long total_rating;

    public AggregatedData(){

    }

    public AggregatedData(long id_user, long id_hotel, long total_rating) {
        this.id_user = (int) id_user;
        this.id_hotel = (int) id_hotel;
        this.total_rating = total_rating;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = (int) id_user;
    }

    public long getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(long id_hotel) {
        this.id_hotel = (int) id_hotel;
    }

    public long getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(long total_rating) {
        this.total_rating = total_rating;
    }
    // getters and setters
}