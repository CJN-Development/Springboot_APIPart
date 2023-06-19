package org.keyin.city;

import java.util.ArrayList;
import java.util.List;

public class CityService {
    public List<City> getCityList() {
        return cityList;
    }

    private List<City> cityList = new ArrayList<>();

    public CityService() {
        City city = new City();
        city.setId(1L);
        city.setName("St. John's");
        city.setState("NL");
        city.setPopulation(150_000);
        cityList.add(city);

        City city2 = new City();
        city2.setId(2L);
        city2.setName("Gander");
        city2.setState("NL");
        city2.setPopulation(50_000);
        cityList.add(city2);
    }

    public List<City> getAllCities() {
        return cityList;
    }

    public List<City> searchCities(String searchInput) {
        List<City> searchResults = new ArrayList<>();
        for (City city : cityList) {
            if (city.getName().contains(searchInput) || city.getState().contains(searchInput)) {
                searchResults.add(city);
            }
        }
        return searchResults;
    }

    public List<City> deleteCity(Long id) {
        for (City city : cityList) {
            if (city.getId().equals(id)) {
                cityList.remove(city);
                break;
            }
        }
        return cityList;
    }

    public List<City> createCity(City city) {
        cityList.add(city);
        return cityList;
    }

    public List<City> updateCity(City updatedCity) {
        for (City city : cityList) {
            if (city.getId().equals(updatedCity.getId())) {
                city.setName(updatedCity.getName());
                city.setState(updatedCity.getState());
                city.setPopulation(updatedCity.getPopulation());
                break;
            }
        }
        return cityList;
    }
}
