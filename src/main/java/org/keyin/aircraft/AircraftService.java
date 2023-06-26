package org.keyin.aircraft;

import org.keyin.StackControls.Action;
import org.keyin.airport.Airport;
import org.keyin.city.City;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AircraftService {

    private List<Aircraft> aircraftList = new ArrayList<>();

    private Stack<Action> actionStack = new Stack<>();
    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();
    private Map<Long, Aircraft> updatedAircrafts = new HashMap<>();

    private List<String> aircraftActions = new ArrayList<>();


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




//    Get All Aircraft  GET
    public List<Aircraft> getAllAircraft() {
        return aircraftList;
    }

    public List<String> getAllAircraftActions(){
        return aircraftActions;
    }



//    Search Aircraft By Search Term GET
    public List<Aircraft> searchAircraft(String searchTerm) {
        List<Aircraft> searchResults = new ArrayList<>();
        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getBrand().contains(searchTerm) || aircraft.getModel().contains(searchTerm)) {
                searchResults.add(aircraft);
            }
        }
        return searchResults;
    }

//    Search By Id
    public List<Aircraft> searchById(Long id){
        List<Aircraft> idSearchResult = new ArrayList<>();
        for(Aircraft aircraft: aircraftList){
            if(aircraft.getId().equals(id)){
                idSearchResult.add(aircraft);
            }


        }
        return idSearchResult;
    }
//    Delete Aircraft  DELETE
    public List<Aircraft> deleteAircraft(Long id) {

        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getId().equals(id)) {
                Action action = new Action("DELETE", aircraft.getId(), aircraft); // Create an action
                actionStack.push(action); // Push the action onto the stack
                undoStack.push(action); // Push the action onto the undo stack
                redoStack.clear(); // Clear the redo stack
                aircraftList.remove(aircraft);
                aircraftActions.add( logActionWithTimestamp("Deleted Aircraft action"));
                break;

            }
        }
        return aircraftList;
    }

//    Create Aircraft POST
    public List<Aircraft> createAircraft(Aircraft aircraft){
        aircraftList.add(aircraft);
        Action action = new Action("CREATE", aircraft.getId(), aircraft); // Create an action
        actionStack.push(action);
        undoStack.push(action);
        redoStack.clear();

        if(existsAircraft(aircraft)){
            aircraftActions.add(logActionWithTimestamp("Created Aircraft"));

        } else {
            aircraftActions.add(logActionWithTimestamp("Created Aircraft Failed"));

        }

        return  aircraftList;
    }

    public List<Airport>getAllowedList(Aircraft aircraft){
       return aircraft.getAllowedAirports();
    }

    public boolean existsAircraft(Aircraft aircraft) {
        for(Aircraft aircraft1: aircraftList){
            if(aircraft1.getId().equals(aircraft.getId())){
                return true;
            }
        } return  false;
    }

    public List<Aircraft> updateAircraft(Aircraft updatedAircraft) {
        Aircraft orginalAircraft = null;
        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getId().equals(updatedAircraft.getId())) {
                orginalAircraft = cloneAircraft(aircraft);
                break;
            }
        }

        if (orginalAircraft != null) {
            Action action = new Action("UPDATE", updatedAircraft.getId(), orginalAircraft); // Create an action with the original city
            actionStack.push(action); // Push the action onto the stack
            undoStack.push(action); // Push the action onto the undo stack
            redoStack.clear(); // Clear the redo stack

            // Update the city with the new values
            for (Aircraft aircraft : aircraftList) {
                if (aircraft.getId().equals(updatedAircraft.getId())) {
                    aircraft.setBrand(updatedAircraft.getBrand());
                    aircraft.setModel(updatedAircraft.getModel());
                    aircraft.setTailNumber(updatedAircraft.getTailNumber());
                    aircraft.setType(updatedAircraft.getType());

                    updatedAircrafts.put(updatedAircraft.getId(), cloneAircraft(aircraft)); // Store the updated city
                    aircraftActions.add( logActionWithTimestamp("Aircraft Updated"));
                    break;
                }
            }
        }

        return aircraftList;
    }

    public void undoAction() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            String operation = action.getOperation();
            Long entityId = action.getEntityId();
            Object originalEntity = action.getOriginalEntity();

            if (operation.equals("CREATE")) {
                deleteAircraft(entityId);
               aircraftActions.add( logActionWithTimestamp("Undo CREATE action"));
            } else if (operation.equals("UPDATE")) {

                for (Aircraft aircraft : aircraftList) {
                    if (aircraft.getId().equals(entityId)) {
                        Aircraft clonedAircraft = cloneAircraft(aircraft);
                        clonedAircraft.setTailNumber(((Aircraft) originalEntity).getTailNumber());
                        clonedAircraft.setType(((Aircraft) originalEntity).getType());
                        clonedAircraft.setModel(((Aircraft) originalEntity).getModel());
                        clonedAircraft.setBrand(((Aircraft) originalEntity).getBrand());

                        // Replace the city with the cloned version
                        aircraftList.remove(aircraft);
                        aircraftList.add(clonedAircraft);
                        break;
                    }
                }
                aircraftActions.add(logActionWithTimestamp("Undo UPDATE action"));
            } else if (operation.equals("DELETE")) {
                createAircraft((Aircraft) originalEntity);
                 aircraftActions.add(logActionWithTimestamp("Undo DELETE action"));
            }

            redoStack.push(action); // Store the original action in the redo stack
        }
    }

    public void redoAction() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop(); // Retrieve the original action from redo stack

            if (action.getOperation().equals("CREATE")) {
                createAircraft((Aircraft) action.getOriginalEntity());
                aircraftActions.add(logActionWithTimestamp("Redo CREATE action"));
            } else if (action.getOperation().equals("UPDATE")) {
                Aircraft updatedAircraft = updatedAircrafts.get(action.getEntityId()); // Get the updated city from the stored map

                // Find the city in the list
                for (Aircraft aircraft : aircraftList) {
                    if (aircraft.getId().equals(updatedAircraft.getId())) {
                        aircraft.setModel(updatedAircraft.getModel());
                        aircraft.setType(updatedAircraft.getType());
                        aircraft.setBrand(aircraft.getBrand());
                        aircraft.setTailNumber(aircraft.getTailNumber());
                        break;
                    }
                }

                aircraftActions.add(logActionWithTimestamp("Redo UPDATE action"));
            } else if (action.getOperation().equals("DELETE")) {
                deleteAircraft(action.getEntityId());
                aircraftActions.add(logActionWithTimestamp("Redo DELETE action"));
            }

            undoStack.push(action); // Store the original action in the undo stack
        }
    }

    private Aircraft cloneAircraft(Aircraft aircraft) {
        return new Aircraft(aircraft.getId(), aircraft.getTailNumber(), aircraft.getType(), aircraft.getBrand(), aircraft.getModel());
    }

    // method to log actions with timestamps
    private String logActionWithTimestamp(String action) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:s");
        String formattedTimestamp = timestamp.format(formatter);
        System.out.println(formattedTimestamp + " - " + action);
        return  action;
    }
}
