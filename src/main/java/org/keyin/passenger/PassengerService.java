package org.keyin.passenger;

import org.keyin.StackControls.Action;
import org.keyin.airport.Airport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PassengerService {
    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    private List<Passenger> passengerList = new ArrayList<>();

    private List<String> passengerActions = new ArrayList<>();

    private Stack<Action> actionStack = new Stack<>();
    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();

    public PassengerService() {
//        Passenger passenger = new Passenger();
//        passenger.setId(1L);
//        passenger.setFirstName("John");
//        passenger.setLastName("Doe");
//        passenger.setPhoNum(1231234);
//        passengerList.add(passenger);
//
//        Passenger passenger2 = new Passenger();
//        passenger2.setId(2L);
//        passenger2.setFirstName("Steph");
//        passenger2.setLastName("Short");
//        passenger2.setPhoNum(5675678);
//        passengerList.add(passenger2);
    }

    public List<Passenger> getAllPassenger() {
        return passengerList;
    }

    public List<String> getAllPassengerActions(){
        return passengerActions;
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

    public List<Passenger> searchById(Long id){
        List<Passenger> idSearchResults = new ArrayList<>();
        for(Passenger passenger: passengerList){
            if(passenger.getId().equals(id)){
                idSearchResults.add(passenger);
            }


        }
        return idSearchResults;
    }

    public List<Passenger> deletePassenger(Long id) {
        for (Passenger passenger : passengerList) {
            if (passenger.getId().equals(id)) {
                Action action = new Action("DELETE", passenger.getId(), passenger);
                actionStack.push(action);
                undoStack.push(action);
                redoStack.clear();
                passengerList.remove(passenger);
                passengerActions.add(logActionWithTimestamp("Deleted Passenger action"));
                break;
            }
        }
        return passengerList;
    }

    public List<Passenger> createPassenger(Passenger passenger) {
        passengerList.add(passenger);
        Action action = new Action("CREATE", passenger.getId(), passenger);
        actionStack.push(action);
        undoStack.push(action);
        redoStack.clear();
        if (existsPassenger(passenger)){
            passengerActions.add(logActionWithTimestamp("Created Passenger"));
        } else {
            passengerActions.add(logActionWithTimestamp("Created Passenger Failed"));
        }
        return passengerList;
    }

    public boolean existsPassenger(Passenger passenger){
        for (Passenger passenger1 : passengerList){
            if (passenger1.getId().equals(passenger.getId())){
                return true;
            }
        }
        return false;
    }

    public List<Passenger> updatePassenger(Passenger updatedPassenger) {
        Passenger originalPassenger = null;
        for (Passenger passenger : passengerList){
            if (passenger.getId().equals(updatedPassenger.getId())){
                originalPassenger = clonePassenger(passenger);
                break;
            }
        }
        if (originalPassenger != null) {
            Action action = new Action("UPDATE", updatedPassenger.getId(), originalPassenger);
            actionStack.push(action);
            undoStack.push(action);
            redoStack.clear();

        for (Passenger passenger : passengerList) {
            if (passenger.getId().equals(updatedPassenger.getId())) {
                passenger.setFirstName(updatedPassenger.getFirstName());
                passenger.setLastName(updatedPassenger.getLastName());
                passenger.setPhoNum(updatedPassenger.getPhoNum());
                break;
            }
            }
        }
        return passengerList;
    }

    public void undoAction() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            String operation = action.getOperation();
            Long entityId = action.getEntityId();
            Object originalEntity = action.getOriginalEntity();

            if (operation.equals("CREATE")) {
                deletePassenger(entityId);
                logActionWithTimestamp("Undo CREATE action");
            } else if (operation.equals("UPDATE")) {

                for (Passenger passenger : passengerList) {
                    if (passenger.getId().equals(entityId)) {
                        Passenger clonedPassenger = clonePassenger(passenger);
                        clonedPassenger.setFirstName(((Passenger) originalEntity).getFirstName());
                        clonedPassenger.setPhoNum(((Passenger) originalEntity).getPhoNum());

                        passengerList.remove(passenger);
                        passengerList.add(clonedPassenger);
                        break;
                    }
                }
                logActionWithTimestamp("Undo UPDATE action");
            } else if (operation.equals("DELETE")) {
                createPassenger((Passenger) originalEntity);
                logActionWithTimestamp("Undo DELETE action");
            }

            redoStack.push(action);
        }
    }

    public void redoAction() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();

            if (action.getOperation().equals("CREATE")) {
                createPassenger((Passenger) action.getOriginalEntity());
                logActionWithTimestamp("Redo CREATE action");
            } else if (action.getOperation().equals("UPDATE")) {
                Passenger updatedPassenger = (Passenger) action.getOriginalEntity();

                for (Passenger passenger : passengerList) {
                    if (passenger.getId().equals(updatedPassenger.getId())) {
                        passenger.setFirstName(updatedPassenger.getFirstName());
                        passenger.setPhoNum(updatedPassenger.getPhoNum());
                        break;
                    }
                }

                logActionWithTimestamp("Redo UPDATE action");
            } else if (action.getOperation().equals("DELETE")) {
                deletePassenger(action.getEntityId());
                logActionWithTimestamp("Redo DELETE action");
            }

            undoStack.push(action);
        }
    }

    private Passenger clonePassenger(Passenger passenger) {
        return new Passenger(passenger.getId(), passenger.getFirstName(), passenger.getPhoNum());
    }

    private String logActionWithTimestamp(String action) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:s");
        String formattedTimestamp = timestamp.format(formatter);
        System.out.println(formattedTimestamp + " - " + action);
        return action;
    }
}
