package org.keyin.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/aircraft/updateAircraft/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody Aircraft updatedAircraft) {
        try {
            List<Aircraft> aircraftList = aircraftService.getAllAircraft();
            for (Aircraft aircraft : aircraftList) {
                if (aircraft.getId().equals(id)) {
                    aircraft.setModel(updatedAircraft.getModel());
                    aircraft.setBrand(updatedAircraft.getBrand());
                    aircraft.setTailNumber(updatedAircraft.getTailNumber());
                    aircraft.setType(updatedAircraft.getType());

                    return new ResponseEntity<>("City updated successfully", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Aircraft not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update city", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

