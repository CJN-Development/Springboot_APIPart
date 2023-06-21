package org.keyin.aircraft;

import org.keyin.airport.Airport;
import org.keyin.airport.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {

    private AircraftService aircraftService;
    private AirportService airportService;

    public AircraftController() {
        aircraftService = new AircraftService();
        airportService = new AirportService();

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



}


