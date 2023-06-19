package org.keyin.city;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CityController {
    private CityService cityService;

    public CityController() {
        cityService = new CityService();
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getCityList();
    }

    @GetMapping("/cities/citysearch")
    public List<City> searchCities(@RequestParam String searchInput) {
        return cityService.searchCities(searchInput);
    }

    @DeleteMapping("/cities/deletecity")
    public List<City> deleteCity(@RequestParam Long id) {
        cityService.deleteCity(id);
        return cityService.getCityList();
    }

    @PostMapping("/cities/createCity")
    public ResponseEntity<String> createCity(@RequestBody City city) {
        try {
            cityService.getCityList().add(city);
            return new ResponseEntity<>("City created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create city", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cities/updateCity/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        try {
            List<City> cityList = cityService.getCityList();
            for (City city : cityList) {
                if (city.getId().equals(id)) {
                    city.setName(updatedCity.getName());
                    city.setState(updatedCity.getState());
                    city.setPopulation(updatedCity.getPopulation());
                    return new ResponseEntity<>("City updated successfully", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update city", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
