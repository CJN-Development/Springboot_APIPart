package org.keyin.city;


import org.keyin.aircraft.Aircraft;

import java.util.ArrayList;
import java.util.List;
public class CityService {
    private List<City> cityList = new ArrayList<>();

    public CityService() {
        City city = new City();
        city.setId(1L);
        city.setName("St.john's");
        city.setState("NL");
        city.setPopulation(150_000);
        cityList.add(city);

        City city2 = new City();
        city2.setId(1L);
        city2.setName("Gander");
        city2.setState("NL");
        city2.setPopulation(50_000);
        cityList.add(city2);
    }

    public List<City> getAllCities() {
        return cityList;
    }
    public List<City> searchCities(String searchInput){
        List<City> searchResults = new ArrayList<>();
        for (City city : cityList){
            if(city.getName().contains(searchInput) || city.getState().contains(searchInput)){
                searchResults.add(city);
            }
        }
        return searchResults;
    }
}
