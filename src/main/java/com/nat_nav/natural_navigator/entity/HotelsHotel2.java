package com.nat_nav.natural_navigator.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "hotels", schema = "public", catalog = "natural_navigator_bd")
public class HotelsHotel2 {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "region")
    private String region;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "house")
    private String house;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "phone_num")
    private String phoneNum;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "web_site")
    private String webSite;
    
    @Column(name = "family")
    private int family;
    
    @Column(name = "children")
    private int children;
    
    @Column(name = "the_youth")
    private int theYouth;
    
    @Column(name = "old_friends")
    private int oldFriends;
    
    @Column(name = "comfort")
    private int comfort;
    
    @Column(name = "distance")
    private int distance;
    
    @Column(name = "price")
    private int price;
    
    @Column(name = "activity")
    private int activity;
    
    @Column(name = "safety")
    private int safety;
    
    @Column(name = "active_recreation_on_the_water")
    private int activeRecreationOnTheWater;
    
    @Column(name = "fishing")
    private int fishing;
    
    @Column(name = "football")
    private int football;
    
    @Column(name = "volleyball")
    private int volleyball;
    
    @Column(name = "table_tennis")
    private int tableTennis;
    
    @Column(name = "tennis")
    private int tennis;
    
    @Column(name = "cycling")
    private int cycling;
    
    @Column(name = "distance_from_kazan")
    private int distanceFromKazan;
    
    @Column(name = "cost_of_stay_per_day")
    private int costOfStayPerDay;
    
    @Column(name = "short_description")
    private String shortDescription;
    
    @Column(name = "main_feature")
    private String mainFeature;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getTheYouth() {
        return theYouth;
    }

    public void setTheYouth(int theYouth) {
        this.theYouth = theYouth;
    }

    public int getOldFriends() {
        return oldFriends;
    }

    public void setOldFriends(int oldFriends) {
        this.oldFriends = oldFriends;
    }

    public int getComfort() {
        return comfort;
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getSafety() {
        return safety;
    }

    public void setSafety(int safety) {
        this.safety = safety;
    }

    public int getActiveRecreationOnTheWater() {
        return activeRecreationOnTheWater;
    }

    public void setActiveRecreationOnTheWater(int activeRecreationOnTheWater) {
        this.activeRecreationOnTheWater = activeRecreationOnTheWater;
    }

    public int getFishing() {
        return fishing;
    }

    public void setFishing(int fishing) {
        this.fishing = fishing;
    }

    public int getFootball() {
        return football;
    }

    public void setFootball(int football) {
        this.football = football;
    }

    public int getVolleyball() {
        return volleyball;
    }

    public void setVolleyball(int volleyball) {
        this.volleyball = volleyball;
    }

    public int getTableTennis() {
        return tableTennis;
    }

    public void setTableTennis(int tableTennis) {
        this.tableTennis = tableTennis;
    }

    public int getTennis() {
        return tennis;
    }

    public void setTennis(int tennis) {
        this.tennis = tennis;
    }

    public int getCycling() {
        return cycling;
    }

    public void setCycling(int cycling) {
        this.cycling = cycling;
    }

    public int getDistanceFromKazan() {
        return distanceFromKazan;
    }

    public void setDistanceFromKazan(int distanceFromKazan) {
        this.distanceFromKazan = distanceFromKazan;
    }

    public int getCostOfStayPerDay() {
        return costOfStayPerDay;
    }

    public void setCostOfStayPerDay(int costOfStayPerDay) {
        this.costOfStayPerDay = costOfStayPerDay;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getMainFeature() {
        return mainFeature;
    }

    public void setMainFeature(String mainFeature) {
        this.mainFeature = mainFeature;
    }




}
