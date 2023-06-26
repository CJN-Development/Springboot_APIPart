package org.keyin.aircraft;

import org.keyin.airport.Airport;
import org.keyin.city.City;

import java.util.ArrayList;
import java.util.List;

public class Aircraft {

    private Long id;
    private String tailNumber;
    private String type;
    private String brand;
    private String model;

    private List<Airport> allowedAirports;

    public Aircraft(Long id, String tailNumber, String type, String brand, String model) {
        this.id = id;
        this.tailNumber = tailNumber;
        this.type = type;
        this.brand = brand;
        this.model = model;
    }

    public Aircraft(){
        allowedAirports = new ArrayList<>();
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void addAllowedAirport(Airport airport) {
        allowedAirports.add(airport);
    }

    public void removeAllowedAirport(Airport airport) {
        allowedAirports.remove(airport);
    }

    public List<Airport> getAllowedAirports() {
        return allowedAirports;
    }
}
