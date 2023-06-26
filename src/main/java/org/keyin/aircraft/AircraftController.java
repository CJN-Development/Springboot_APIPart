package org.keyin.aircraft;

import org.keyin.StackControls.RequestStack;
import org.keyin.airport.Airport;
import org.keyin.airport.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;

@RestController
@CrossOrigin
public class AircraftController {

    private AircraftService aircraftService;
    private AirportService airportService;

    private Stack<RequestStack> requestStack; // Stack to store the request history


    public AircraftController() {
        aircraftService = new AircraftService();
        airportService = new AirportService();
        requestStack = new Stack<>();

    }


    @GetMapping("/aircraft")
    public List<Aircraft> getAllAircraft() {
        RequestStack request = new RequestStack("GET", "/aircraft", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: GET /aircraft at " + request.getTimestamp());
        return aircraftService.getAllAircraft();
    }

    @GetMapping("/aircraft/aircraftsearch")
    public List<Aircraft> search(@RequestParam String searchTerm) {
        RequestStack request = new RequestStack("GET", "/aircraft/aircraftsearch", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: GET /aircraft/aircraftsearch at " + request.getTimestamp());

        return aircraftService.searchAircraft(searchTerm);
    }
    @DeleteMapping("/aircraft/deleteaircraft")
    public List<Aircraft> delete(@RequestParam Long id) {
        RequestStack request = new RequestStack("DELETE", "/aircraft/deleteaircraft", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: DELETE /aircraft/deleteaircraft at " + request.getTimestamp());
        return aircraftService.deleteAircraft(id);
    }

    @PostMapping("/aircraft/createAircraft")
    public ResponseEntity<String> createAircraft(@RequestBody Aircraft aircraft) {
        RequestStack request = new RequestStack("POST", "/aircraft/createAircraft", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: POST /aircraft/createAircraft at " + request.getTimestamp());
        try {
            aircraftService.createAircraft(aircraft);
            return new ResponseEntity<>("Aircraft created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create Aircraft", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/aircraft/undo")
    public ResponseEntity<String> undoAction() {
        try {
            aircraftService.undoAction();
            return new ResponseEntity<>("Undo action successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to undo action", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/aircraft/redo")
    public ResponseEntity<String> redoAction() {
        try {
            aircraftService.redoAction(); // Implement the redoAction() method in the CityService class
            return new ResponseEntity<>("Redo action successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to redo action", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/aircraft/updateAircraft/{id}")
    public ResponseEntity<String> updateAircraft(@PathVariable Long id, @RequestBody Aircraft updatedAircraft) {
        RequestStack request = new RequestStack("PUT", "/aircraft/updateAircraft/" + id, LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: PUT /aircraft/updateAircraft/" + id + " at " + request.getTimestamp());
        try {
            aircraftService.updateAircraft(updatedAircraft);
            return new ResponseEntity<>("Aircraft updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update update Aircraft", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/aircraft/addAllowed/{id}")
    public ResponseEntity<String> createAddAllowedAirport(@PathVariable Long id, @RequestBody Airport airport) {
        List<Aircraft> optionalAircraft = aircraftService.searchById(id);
        for(Aircraft aircraft: optionalAircraft)
            if (aircraft.getId().equals(id)) {
            aircraft.addAllowedAirport(airport);
        } else {
            throw new Error("Aircraft with ID now found");
        }
        return new ResponseEntity<>("Airport Added To Allowed List", HttpStatus.OK);
    }

    @GetMapping("/aircraft/getAllowed/{id}")
    public List<Airport> getAllowedList(@PathVariable Long id){
        List<Aircraft> tempAircraft = aircraftService.searchById(id);

        for(Aircraft aircraft: tempAircraft)
            if(aircraft.getId().equals(id)){
                return aircraft.getAllowedAirports();
            }

        throw new RuntimeException("Aircraft not found");


    }

    @DeleteMapping("/aircraft/deleteAllowedAirport/{id}/{airportId}")
    public ResponseEntity<String> deleteAllowedAirport(@PathVariable Long id, @PathVariable("airportId") Long iD){
        List<Aircraft> tempAircraft = aircraftService.searchById(id);

        for(Aircraft aircraft : tempAircraft)
            if(aircraft.getId().equals(id)){
                List<Airport> listAllowedAirport = aircraft.getAllowedAirports();
                if(listAllowedAirport.isEmpty()){
                    return new ResponseEntity<>(" Allowed List Of Aircraft Is Empty ", HttpStatus.OK);
                }
                for(Airport airport : listAllowedAirport)
                    if(airport.getId().equals(iD)){
                        listAllowedAirport.remove(airport);
                        break;




                    }
            }
        return new ResponseEntity<>("Airport Deleted From Allowed List Of Aircraft With ID ", HttpStatus.OK);



    }

    @GetMapping("/aircraft/getAircraftActions")
    public List<String> getAircraftActions(){
        List<String> actionList = aircraftService.getAllAircraftActions();


        System.out.println(actionList);

        return  actionList;


    }



}


