package org.keyin.passenger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PassengerController {

    private PassengerService passengerService;

    public PassengerController() {
        passengerService = new PassengerService();
    }



    @GetMapping("/passenger")
    public List<Passenger> getAllPassenger() {
        return passengerService.getAllPassenger();
    }

    @GetMapping("/passenger/searchPassenger")
    public List<Passenger> search(@RequestParam String searchTerm) {
        return passengerService.searchPassenger(searchTerm);
    }
    @DeleteMapping("/passenger/deletepassenger")
    public List<Passenger> delete(@RequestParam Long id) {
        return passengerService.deletePassenger(id);
    }

    @PostMapping("/passenger/createPassenger")
    public void createPassenger(@RequestBody Passenger passenger) {
        passengerService.createPassenger(passenger);
    }

    @PutMapping("/passenger/updatePassenger/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody Passenger updatedPassenger) {
        try {
            List<Passenger> passengerList = passengerService.getAllPassenger();
            for (Passenger passenger : passengerList) {
                if (passenger.getId().equals(id)) {
                    passenger.setFirstName(updatedPassenger.getFirstName());
                    passenger.setLastName(updatedPassenger.getLastName());
                    passenger.setPhoNum(updatedPassenger.getPhoNum());


                    return new ResponseEntity<>("Passenger updated successfully", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Passenger not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update passenger", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
