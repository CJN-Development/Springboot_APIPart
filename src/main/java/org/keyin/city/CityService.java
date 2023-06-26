package org.keyin.city;

import org.keyin.StackControls.Action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CityService {

    private List<City> cityList = new ArrayList<>();
    private Stack<Action> actionStack = new Stack<>();
    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();
    private Map<Long, City> updatedCities = new HashMap<>();


    public CityService() {
        City city1 = new City(1L,"NL",150_000,"St. John's");
        City city2 = new City(2L,"ON",2_900_000,"Toronto");


        cityList.add(city1);
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
        City originalCity = null;
        for (City city : cityList) {
            if (city.getId().equals(updatedCity.getId())) {
                originalCity = cloneCity(city); // Create a clone of the original city
                break;
            }
        }

        if (originalCity != null) {
            Action action = new Action("UPDATE", updatedCity.getId(), originalCity); // Create an action with the original city
            actionStack.push(action); // Push the action onto the stack
            undoStack.push(action); // Push the action onto the undo stack
            redoStack.clear(); // Clear the redo stack

            // Update the city with the new values
            for (City city : cityList) {
                if (city.getId().equals(updatedCity.getId())) {
                    city.setName(updatedCity.getName());
                    city.setState(updatedCity.getState());
                    city.setPopulation(updatedCity.getPopulation());
                    updatedCities.put(updatedCity.getId(), cloneCity(city)); // Store the updated city
                    break;
                }
            }
        }

        return cityList;
    }
    public List<City> searchCityById(Long id){
        List<City> idCitySearchResult = new ArrayList<>();
        for(City city: cityList){
            if(city.getId().equals(id)){
                idCitySearchResult.add(city);
            }
        }
        return idCitySearchResult;
    }



    /** even tried using a new stack specific to these actions to store the updated city before the
     * undo reverts back to the original created state before updating and that didn't work */

    // second attempt at undo redo updated functionality
    public void undoAction() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            String operation = action.getOperation();
            Long entityId = action.getEntityId();
            Object originalEntity = action.getOriginalEntity();

            if (operation.equals("CREATE")) {
                deleteCity(entityId);
                logActionWithTimestamp("Undo CREATE action");
            } else if (operation.equals("UPDATE")) {
                // Find the city in the list
                for (City city : cityList) {
                    if (city.getId().equals(entityId)) {
                        City clonedCity = cloneCity(city); // Clone the updated city
                        clonedCity.setName(((City) originalEntity).getName());
                        clonedCity.setState(((City) originalEntity).getState());
                        clonedCity.setPopulation(((City) originalEntity).getPopulation());

                        // Replace the city with the cloned version
                        cityList.remove(city);
                        cityList.add(clonedCity);
                        break;
                    }
                }
                logActionWithTimestamp("Undo UPDATE action");
            } else if (operation.equals("DELETE")) {
                createCity((City) originalEntity);
                logActionWithTimestamp("Undo DELETE action");
            }

            redoStack.push(action); // Store the original action in the redo stack
        }
    }

    public void redoAction() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop(); // Retrieve the original action from redo stack

            if (action.getOperation().equals("CREATE")) {
                createCity((City) action.getOriginalEntity());
                logActionWithTimestamp("Redo CREATE action");
            } else if (action.getOperation().equals("UPDATE")) {
                City updatedCity = updatedCities.get(action.getEntityId()); // Get the updated city from the stored map

                // Find the city in the list
                for (City city : cityList) {
                    if (city.getId().equals(updatedCity.getId())) {
                        city.setName(updatedCity.getName());
                        city.setState(updatedCity.getState());
                        city.setPopulation(updatedCity.getPopulation());
                        break;
                    }
                }

                logActionWithTimestamp("Redo UPDATE action");
            } else if (action.getOperation().equals("DELETE")) {
                deleteCity(action.getEntityId());
                logActionWithTimestamp("Redo DELETE action");
            }

            undoStack.push(action); // Store the original action in the undo stack
        }
    }

    private City cloneCity(City city) {
        return new City(city.getId(), city.getState(), city.getPopulation(), city.getName());
    }

    // method to log actions with timestamps
    private void logActionWithTimestamp(String action) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:s");
        String formattedTimestamp = timestamp.format(formatter);
        System.out.println(formattedTimestamp + " - " + action);
    }
    public boolean existsCity(City city) {
        for (City existingCity : cityList) {
            if (existingCity.getId().equals(city.getId())) {
                return true;
            }
        }
        return false;
    }
}


