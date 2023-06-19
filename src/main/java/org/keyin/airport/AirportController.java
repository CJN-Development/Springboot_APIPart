package org.keyin.airport;

import org.keyin.aircraft.Aircraft;
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

}
