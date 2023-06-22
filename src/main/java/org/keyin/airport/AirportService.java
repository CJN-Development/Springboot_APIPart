package org.keyin.airport;

import org.keyin.aircraft.Aircraft;

import java.util.ArrayList;
import java.util.List;

public class AirportService {

    private List<Airport> airportList = new ArrayList<>();

    public AirportService(){
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Pearson International");
        airport.setCode("YYZ");

        airportList.add(airport);

        Airport airport2 = new Airport();
        airport2.setId(2L);
        airport2.setName("St.John's International");
        airport2.setCode("YYT");

        airportList.add(airport2);
    }

    public List<Airport> getAllAirports(){return airportList;}

    public List<Airport> searchAirport(String searchTerm){
        List<Airport> searchResults = new ArrayList<>();
        for (Airport airport : airportList){
            if (airport.getName().contains(searchTerm) || airport.getCode().contains(searchTerm)){
                searchResults.add(airport);
            }
        }
        return searchResults;
    }

    public List<Airport> searchById(Long id){
        List<Airport> idSearchResult = new ArrayList<>();
        for(Airport airport: airportList){
            if(airport.getId().equals(id)){
                idSearchResult.add(airport);
            }


        }
        return idSearchResult;
    }

    public List<Airport> deleteAirport(Long id){
        for (Airport airport: airportList){
            if (airport.getId().equals(id)) {
                airportList.remove(airport);
            }
        }
        return airportList;
    }

    public void createAirport(Airport airport){
        airportList.add(airport);
    }
}
