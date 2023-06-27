package org.keyin.passenger;

import org.keyin.aircraft.Aircraft;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


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

    @PutMapping("/passenger/addAircraft/{id}")
    public ResponseEntity<String> createAddAircraft(@PathVariable Long id, @RequestBody Aircraft aircraft){
        List<Passenger> optionalPassenger = passengerService.searchById(id);
        for (Passenger passenger : optionalPassenger){
            if (passenger.getId().equals(id)){
                passenger.addAircraftList(aircraft);
            } else {
                throw new Error("Aircraft with ID not found");
            }
        }
        return new ResponseEntity<>("Aircraft Added To List", HttpStatus.OK);
    }

    @GetMapping("/passenger/getAircrafts/{id}")
    public List<Aircraft> getPassengerList(@PathVariable Long id){
        List<Passenger> tempPassenger = passengerService.searchById(id);

        for (Passenger passenger : tempPassenger){
            if (passenger.getId().equals(id)){
                return passenger.getAircraftList();
            }
        }
        throw new RuntimeException("Aircraft not found");
    }

    @PostMapping("/passenger/undo")
    public ResponseEntity<String> undoAction() {
        try {
            passengerService.undoAction();
            return new ResponseEntity<>("Undo action successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to undo action", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/passenger/redo")
    public ResponseEntity<String> redoAction() {
        try {
            passengerService.redoAction();
            return new ResponseEntity<>("Redo action successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to redo action", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/passenger/getPassengerActions")
    public List<String> getAircraftActions(){
        List<String> actionList = passengerService.getAllPassengerActions();


        System.out.println(actionList);

        return  actionList;

    }
}
