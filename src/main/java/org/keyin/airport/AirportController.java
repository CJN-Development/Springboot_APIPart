package org.keyin.airport;

import org.keyin.aircraft.Aircraft;
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
}
