package org.keyin.aircraft;

import java.util.ArrayList;
import java.util.List;

public class AircraftService {

    private List<Aircraft> aircraftList = new ArrayList<>();

    public AircraftService() {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setBrand("Boeing");
        aircraft.setModel("737");
        aircraft.setTailNumber("AF-1234");

        aircraftList.add(aircraft);

        Aircraft aircraft2 = new Aircraft();
        aircraft2.setId(2L);
        aircraft2.setBrand("Reet2");
        aircraft2.setModel("7373");
        aircraft2.setTailNumber("AF-12344444");

        aircraftList.add(aircraft2);
    }




    public List<Aircraft> getAllAircraft() {
        return aircraftList;
    }

    public List<Aircraft> searchAircraft(String searchTerm) {
        List<Aircraft> searchResults = new ArrayList<>();
        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getBrand().contains(searchTerm) || aircraft.getModel().contains(searchTerm)) {
                searchResults.add(aircraft);
            }
        }
        return searchResults;
    }

//    public List<Aircraft> deleteAircraft(String searchTerm) {
//        List<Aircraft> searchResults = new ArrayList<>();
//        for (Aircraft aircraft : aircraftList) {
//            if (aircraft.getBrand().contains(searchTerm) || aircraft.getModel().contains(searchTerm)) {
//                searchResults.add(aircraft);
//            }
//        }
//        return searchResults;
//    }
}
