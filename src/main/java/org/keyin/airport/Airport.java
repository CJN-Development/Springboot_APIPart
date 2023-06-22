package org.keyin.airport;

import org.keyin.passenger.Passenger;

import java.util.ArrayList;
import java.util.List;

public class Airport {

    private Long id;
    private String name;
    private String code;

    private List<Passenger> passengersList;

    public Airport(){passengersList = new ArrayList<>();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Passenger> getPassengersList() {
        return passengersList;
    }

    public void addPassengerList(Passenger passenger) {
        passengersList.add(passenger);
    }

    public void setPassengersList(List<Passenger> passengersList) {
        this.passengersList = passengersList;
    }
}
