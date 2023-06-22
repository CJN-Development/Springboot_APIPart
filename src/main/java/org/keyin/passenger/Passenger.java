package org.keyin.passenger;

import org.keyin.aircraft.Aircraft;

import java.util.ArrayList;
import java.util.List;


public class Passenger {

    private Long id;

    private String firstName;

    private String lastName;

    private int phoNum;

    private List<Aircraft> aircraftsList;

    public Passenger(){aircraftsList = new ArrayList<>();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getPhoNum() {
        return phoNum;
    }

    public void setPhoNum(int phoNum) {
        this.phoNum = phoNum;
    }

    public List<Aircraft> getAircraftList() {
        return aircraftsList;
    }

    public void addAircraftList(Aircraft aircraft) {
        aircraftsList.add(aircraft);
    }

    public void setAircraftList(List<Aircraft> aircraftsList) {
        this.aircraftsList = aircraftsList;
    }
}

