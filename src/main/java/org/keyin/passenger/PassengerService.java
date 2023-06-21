package org.keyin.passenger;


import org.keyin.passenger.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerService {
    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    private List<Passenger> passengerList = new ArrayList<>();

    public PassengerService() {
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setPhoNum(1231234);
        passengerList.add(passenger);

        Passenger passenger2 = new Passenger();
        passenger2.setId(2L);
        passenger2.setFirstName("Steph");
        passenger2.setLastName("Short");
        passenger2.setPhoNum(5675678);
        passengerList.add(passenger2);
    }

    public List<Passenger> getAllPassenger() {
        return passengerList;
    }

    public List<Passenger> searchPassenger(String searchInput) {
        List<Passenger> searchResults = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            if (passenger.getFirstName().contains(searchInput) || passenger.getLastName().contains(searchInput)) {
                searchResults.add(passenger);
            }
        }
        return searchResults;
    }

    public List<Passenger> deletePassenger(Long id) {
        for (Passenger passenger : passengerList) {
            if (passenger.getId().equals(id)) {
                passengerList.remove(passenger);
                break;
            }
        }
        return passengerList;
    }

    public List<Passenger> createPassenger(Passenger passenger) {
        passengerList.add(passenger);
        return passengerList;
    }

    public List<Passenger> updatePassenger(Passenger updatedPassenger) {
        for (Passenger passenger : passengerList) {
            if (passenger.getId().equals(updatedPassenger.getId())) {
                passenger.setFirstName(updatedPassenger.getFirstName());
                passenger.setLastName(updatedPassenger.getLastName());
                passenger.setPhoNum(updatedPassenger.getPhoNum());
                break;
            }
        }
        return passengerList;
    }
}
