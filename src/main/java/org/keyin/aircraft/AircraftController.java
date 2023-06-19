package org.keyin.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {

    private AircraftService aircraftService;

    public AircraftController() {
        aircraftService = new AircraftService();
    }


    @GetMapping("/aircraft")
    public List<Aircraft> getAllAircraft() {
        return aircraftService.getAllAircraft();
    }

    @GetMapping("/aircraft/aircraftsearch")
    public List<Aircraft> search(@RequestParam String searchTerm) {
        return aircraftService.searchAircraft(searchTerm);
    }
    @DeleteMapping("/aircraft/deleteaircraft")
    public List<Aircraft> delete(@RequestParam Long id) {
        return aircraftService.deleteAircraft(id);
    }

    @PostMapping("/aircraft/createAircraft")
    public void createAircraft(@RequestBody Aircraft aircraft) {
        aircraftService.createAircraft(aircraft);
    }

}
