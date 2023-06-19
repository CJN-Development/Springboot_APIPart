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

   public Long getId(){
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

}
