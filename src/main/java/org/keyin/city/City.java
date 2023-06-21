package org.keyin.city;

import javax.persistence.*;

import java.util.List;

//import org.keyin.airport.Airport;

@Entity
public class City {

    @Id
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1, initialValue = 2)
    @GeneratedValue(generator = "city_sequence")
    private Long id;
    private String state;
    private int population;
    private String name;

    public City() {
    }

    public City(Long id, String state, int population, String name) {
        this.id = id;
        this.state = state;
        this.population = population;
        this.name = name;
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
