package org.keyin.city;

import org.keyin.airport.Airport;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

//import org.keyin.airport.Airport;


public class City {
    private Long id;
    private String state;
    private int population;
    private String name;
    private List<Airport>airportsInCity;

    public City() {
        airportsInCity = new ArrayList<>();
    }

    public City(Long id, String state, int population, String name) {
        this.id = id;
        this.state = state;
        this.population = population;
        this.name = name;
        airportsInCity = new ArrayList<>();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addAirportsInCity(Airport airport) {
        airportsInCity.add(airport);
    }
    public List<Airport> getAirportsInCity(){return airportsInCity;}
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", name='" + name + '\'' +
                '}';
    }
}
