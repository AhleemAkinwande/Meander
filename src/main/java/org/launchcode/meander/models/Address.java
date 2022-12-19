package org.launchcode.meander.models;

import javax.persistence.*;

@Entity
public class Address {
    //TODO: fix relationship between Address and Location (location_id is not currently being recorded upon creation of new address)
    @GeneratedValue
    @Id
    private int id;

    private String streetAddress;

    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Address() {}

    public Address(String streetAddress, String zipCode, Location location) {
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public String fullAddressString() {
        return this.streetAddress + ", " + this.zipCode;
    }
}
