package org.keyin.airport;

import org.keyin.aircraft.Aircraft;
import org.keyin.passenger.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AirportController {

    private AirportService airportService;

    public AirportController(){airportService = new AirportService();}

    @GetMapping("/airport")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/airport/airportSearch")
    public List<Airport> search(@RequestParam String searchTerm) {
        return airportService.searchAirport(searchTerm);
    }
    @DeleteMapping("/airport/deleteAirport")
    public List<Airport> delete(@RequestParam Long id) {
        return airportService.deleteAirport(id);
    }

    @PostMapping("/airport/createAirport")
    public void createAirport(@RequestBody Airport airport) {
        airportService.createAirport(airport);
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

    @PostMapping("/cities/redo")
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
}
