package org.launchcode.meander.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private int id;

    private String city;

    private String state;

    private String country;

    @OneToMany(mappedBy="location", cascade = CascadeType.ALL)
    private final List<Post> posts = new ArrayList<>();

    public Location(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Location() { }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityAndStateName() {
        if(this.state!= null) {
            return this.city + ", " + this.state;
        } else {
            return this.city + ", " + this.country;
        }
    }
}
