package org.keyin.airport;

import org.keyin.StackControls.RequestStack;
import org.keyin.aircraft.Aircraft;
import org.keyin.passenger.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;

@RestController
@CrossOrigin
public class AirportController {

    private AirportService airportService;

    private Stack<RequestStack> requestStack;

    public AirportController(){
        airportService = new AirportService();
        requestStack = new Stack<>();
    }

    @GetMapping("/airport")
    public List<Airport> getAllAirports() {
        RequestStack request = new RequestStack("GET", "/airport", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: GET /airport at " + request.getTimestamp());
        return airportService.getAllAirports();
    }

    @GetMapping("/airport/airportSearch")
    public List<Airport> search(@RequestParam String searchTerm) {
        RequestStack request = new RequestStack("GET", "/airport/airportSearch", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: GET /airport/airportSearch at " + request.getTimestamp());
        return airportService.searchAirport(searchTerm);
    }
    @DeleteMapping("/airport/deleteAirport")
    public List<Airport> delete(@RequestParam Long id) {
        RequestStack request = new RequestStack("DELETE", "/airport/deleteAirport", LocalDateTime.now());
        requestStack.push(request);
        System.out.println("Logged request: DELETE /airport/deleteAirport at " + request.getTimestamp());
        return airportService.deleteAirport(id);
    }

    @PostMapping("/airport/createAirport")
    public ResponseEntity<String> createAirport(@RequestBody Airport airport) {
        RequestStack request = new RequestStack("POST", "/airport/createAirport", LocalDateTime.now());
        requestStack.push(request);

        try {
            if (!airportService.existsAirport(airport)){
                airportService.createAirport(airport);
                System.out.println("Logged request: POST /airport/createAirport at " + request.getTimestamp());
            } else {
                System.out.println("Logged request FAILED: POST /airport/createAirport at " + request.getTimestamp());
                return new ResponseEntity<>("Failed to create Airport, Airport already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Airport created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create Airport", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/airport/undo")
    public ResponseEntity<String> undoAction() {
        try {
            airportService.undoAction();
            return new ResponseEntity<>("Undo action successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to undo action", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/airport/redo")
    public ResponseEntity<String> redoAction() {
        try {
            airportService.redoAction(); // Implement the redoAction() method in the CityService class
            return new ResponseEntity<>("Redo action successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to redo action", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/airport/updateAirport/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody Airport updatedAirport) {
        try {
            List<Airport> airportList = airportService.getAllAirports();
            for (Airport airport : airportList) {
                if (airport.getId().equals(id)) {
                    airport.setName(updatedAirport.getName());
                    airport.setCode(updatedAirport.getCode());

                    return new ResponseEntity<>("Airport updated successfully", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Airport not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update Airport", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/airport/addPassenger/{id}")
    public ResponseEntity<String> createAddPassenger(@PathVariable Long id, @RequestBody Passenger passengers){
        List<Airport> optionalAirport = airportService.searchById(id);
        for (Airport airport : optionalAirport){
            if (airport.getId().equals(id)){
                airport.addPassengerList(passengers);
            } else {
                throw new Error("Passenger with ID not found");
            }
        }
        return new ResponseEntity<>("Passenger Added To List", HttpStatus.OK);
    }

    @GetMapping("/airport/getPassengers/{id}")
    public List<Passenger> getPassengerList(@PathVariable Long id){
        List<Airport> tempAirport = airportService.searchById(id);

        for (Airport airport : tempAirport){
            if (airport.getId().equals(id)){
                return airport.getPassengersList();
            }
        }
        throw new RuntimeException("Passenger not found");
    }

    @GetMapping("/airport/getAirportActions")
    public List<String> getAirportActions() {
        List<String> actionList = airportService.getAllAirportsActions();
        System.out.println(actionList);
        return actionList;
    }
}
