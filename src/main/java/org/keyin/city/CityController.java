package org.keyin.city;


import org.keyin.StackControls.RequestStack;
import org.keyin.airport.Airport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;

@RestController
@CrossOrigin
public class CityController {
    private CityService cityService;
    private Stack<RequestStack> requestStack; // Stack to store the request history

    public CityController() {
        cityService = new CityService();
        requestStack = new Stack<>();
    }

    @PostMapping("/cities/undo")
    public ResponseEntity<String> undoAction() {
        try {
            cityService.undoAction();
            return new ResponseEntity<>("Undo action successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to undo action", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cities/redo")
    public ResponseEntity<String> redoAction() {
        try {
            cityService.redoAction(); // Implement the redoAction() method in the CityService class
            return new ResponseEntity<>("Redo action successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to redo action", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        RequestStack request = new RequestStack("GET", "/cities", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: GET /cities at " + request.getTimestamp());
        return cityService.getCityList();
    }

    @GetMapping("/cities/citysearch")
    public List<City> searchCities(@RequestParam String searchInput) {
        RequestStack request = new RequestStack("GET", "/cities/citysearch", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: GET /cities/citysearch at " + request.getTimestamp());
        return cityService.searchCities(searchInput);
    }

    @DeleteMapping("/cities/deletecity")
    public List<City> deleteCity(@RequestParam Long id) {
        RequestStack request = new RequestStack("DELETE", "/cities/deletecity", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: DELETE /cities/deletecity at " + request.getTimestamp());
        cityService.deleteCity(id);
        return cityService.getCityList();
    }

    @PostMapping("/cities/createCity")
    public ResponseEntity<String> createCity(@RequestBody City city) {
        RequestStack request = new RequestStack("POST", "/cities/createCity", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: POST /cities/createCity at " + request.getTimestamp());

        try {
            if (!cityService.existsCity(city)) {
                cityService.createCity(city);
                System.out.println("Logged request: POST /cities/createCity at " + request.getTimestamp());
                return new ResponseEntity<>("City created successfully", HttpStatus.OK);
            } else {
                System.err.println("Logged request FAILED: POST /cities/createCity at " + request.getTimestamp());
                return new ResponseEntity<>("Failed to create City. City already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create City", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cities/updateCity/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        RequestStack request = new RequestStack("PUT", "/cities/updateCity/" + id, LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: PUT /cities/updateCity/" + id + " at " + request.getTimestamp());
        try {
            cityService.updateCity(updatedCity);
            return new ResponseEntity<>("City updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update city", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/cities/addAirport/{id}")
    public ResponseEntity<String> createAddAirportsInCity(@PathVariable Long id, @RequestBody Airport airport) {
        List<City> optionalCity = cityService.searchCityById(id);
        for(City city: optionalCity)
            if (city.getId().equals(id)) {
                city.addAirportsInCity(airport);
            } else {
                throw new Error("City with ID not found");
            }
        return new ResponseEntity<>("Airport Added To City List", HttpStatus.OK);
    }
    @GetMapping("/cities/getAirport/{id}")
    public List<Airport> getAirportsInCity(@PathVariable Long id){
        List<City> tempCity = cityService.searchCityById(id);

        for(City city: tempCity)
            if(city.getId().equals(id)){
                return city.getAirportsInCity();
            }

        throw new RuntimeException("City not found");


    }
    @GetMapping("/cities/getCityActions")
    public List<String> getCityActions() {
        List<String> actionList = cityService.getAllCityActions();

        System.out.println(actionList);

        return actionList;
    }



}
