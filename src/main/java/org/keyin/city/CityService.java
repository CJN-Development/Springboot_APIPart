package org.keyin.city;

import org.keyin.StackControls.Action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CityService {

    private List<City> cityList = new ArrayList<>();
    private Stack<Action> actionStack = new Stack<>();
    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();

    public CityService() {
        City city = new City();
        city.setId(1L);
        city.setName("St. John's");
        city.setState("NL");
        city.setPopulation(150_000);
        cityList.add(city);

        City city2 = new City();
        city2.setId(2L);
        city2.setName("Gander");
        city2.setState("NL");
        city2.setPopulation(50_000);
        cityList.add(city2);
    }

    public List<City> getCityList() {
        return cityList;
    }

    public List<City> searchCities(String searchInput) {
        List<City> searchResults = new ArrayList<>();
        for (City city : cityList) {
            if (city.getName().contains(searchInput) || city.getState().contains(searchInput)) {
                searchResults.add(city);
            }
        }
        return searchResults;
    }

    public List<City> deleteCity(Long id) {
        for (City city : cityList) {
            if (city.getId().equals(id)) {
                Action action = new Action("DELETE", city.getId(), city); // Create an action
                actionStack.push(action); // Push the action onto the stack
                undoStack.push(action); // Push the action onto the undo stack
                redoStack.clear(); // Clear the redo stack
                cityList.remove(city);
                break;
            }
        }
        return cityList;
    }
    public List<City> createCity(City city) {
        cityList.add(city);
        Action action = new Action("CREATE", city.getId(), city); // Create an action
        actionStack.push(action); // Push the action onto the stack
        undoStack.push(action); // Push the action onto the undo stack
        redoStack.clear(); // Clear the redo stack
        return cityList;
    }

    public List<City> updateCity(City updatedCity) {
        for (City city : cityList) {
            if (city.getId().equals(updatedCity.getId())) {
                Action action = new Action("UPDATE", city.getId(), city); // Create an action
                actionStack.push(action); // Push the action onto the stack
                undoStack.push(action); // Push the action onto the undo stack
                redoStack.clear(); // Clear the redo stack
                city.setName(updatedCity.getName());
                city.setState(updatedCity.getState());
                city.setPopulation(updatedCity.getPopulation());
                break;
            }
        }
        return cityList;
    }
    public void undoAction() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            String operation = action.getOperation();
            Long entityId = action.getEntityId();
            Object originalEntity = action.getOriginalEntity();

            // Perform the undo operation based on the operation type
            if (operation.equals("CREATE")) {
                // Undo the create operation
                deleteCity(entityId);
                logActionWithTimestamp("Undo CREATE action");
            } else if (operation.equals("UPDATE")) {
                // Undo the update operation
                updateCity((City) originalEntity);
                logActionWithTimestamp("Undo UPDATE action");
            } else if (operation.equals("DELETE")) {
                // Undo the delete operation
                createCity((City) originalEntity);
                logActionWithTimestamp("Undo DELETE action");
            }

            // Push the undone action to the redo stack
            redoStack.push(action);
        }
    }

    public void redoAction() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();
            String operation = action.getOperation();
            Long entityId = action.getEntityId();
            Object originalEntity = action.getOriginalEntity();

            // Perform the redo operation based on the operation type
            if (operation.equals("CREATE")) {
                // Redo the create operation
                createCity((City) originalEntity);
                logActionWithTimestamp("Redo CREATE action");
            } else if (operation.equals("UPDATE")) {
                // Redo the update operation
                updateCity((City) originalEntity);
                logActionWithTimestamp("Redo UPDATE action");
            } else if (operation.equals("DELETE")) {
                // Redo the delete operation
                deleteCity(entityId);
                logActionWithTimestamp("Redo DELETE action");
            }

            // Push the redone action back to the undo stack
            undoStack.push(action);
        }
    }

    // Helper method to log actions with timestamps
    private void logActionWithTimestamp(String action) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);
        System.out.println(formattedTimestamp + " - " + action);
    }
}