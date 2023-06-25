package org.keyin.airport;

import org.keyin.StackControls.Action;
import org.keyin.aircraft.Aircraft;
import org.keyin.city.City;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AirportService {

    private List<Airport> airportList = new ArrayList<>();

    private Stack<Action> actionStack = new Stack<>();
    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();

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

    public void undoAction() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            String operation = action.getOperation();
            Long entityId = action.getEntityId();
            Object originalEntity = action.getOriginalEntity();

            if (operation.equals("CREATE")) {
                deleteAirport(entityId);
                logActionWithTimestamp("Undo CREATE action");
            } else if (operation.equals("UPDATE")) {

                for (Airport airport : airportList) {
                    if (airport.getId().equals(entityId)) {
                        Airport clonedAirport = cloneAirport(airport);
                        clonedAirport.setName(((Airport) originalEntity).getName());
                        clonedAirport.setCode(((Airport) originalEntity).getCode());


                        airportList.remove(airport);
                        airportList.add(clonedAirport);
                        break;
                    }
                }
                logActionWithTimestamp("Undo UPDATE action");
            } else if (operation.equals("DELETE")) {
                createAirport((Airport) originalEntity);
                logActionWithTimestamp("Undo DELETE action");
            }

            redoStack.push(action);
        }
    }

    public void redoAction() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();

            if (action.getOperation().equals("CREATE")) {
                createAirport((Airport) action.getOriginalEntity());
                logActionWithTimestamp("Redo CREATE action");
            } else if (action.getOperation().equals("UPDATE")) {
                Airport updatedAirport = (Airport) action.getOriginalEntity();

                for (Airport airport : airportList) {
                    if (airport.getId().equals(updatedAirport.getId())) {
                        airport.setName(updatedAirport.getName());
                        airport.setCode(updatedAirport.getCode());
                        break;
                    }
                }

                logActionWithTimestamp("Redo UPDATE action");
            } else if (action.getOperation().equals("DELETE")) {
                deleteAirport(action.getEntityId());
                logActionWithTimestamp("Redo DELETE action");
            }

            undoStack.push(action);
        }
    }

    private Airport cloneAirport(Airport airport) {
        return new Airport(airport.getId(), airport.getName(), airport.getCode());
    }

    // method to log actions with timestamps
    private void logActionWithTimestamp(String action) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:s");
        String formattedTimestamp = timestamp.format(formatter);
        System.out.println(formattedTimestamp + " - " + action);
    }
}
