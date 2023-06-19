package org.keyin.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
public class CityController {
    private CityService cityService;

    public CityController(){
        cityService = new CityService();
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
    @GetMapping("/cities/citysearch")
    public List<City> search(@RequestParam String searchInput){
       return cityService.searchCities(searchInput);
    }

    @DeleteMapping("/cities/deletecity")
    public List<City> delete(@RequestParam String searchInput){
        return cityService.searchCities(searchInput);
    }




//    @GetMapping("/cities/citizen_search")
//    public List<City> getAllCities(@RequestParam String citizenName) {
//        return (List<City>) repo.findByCitizens_Name(citizenName);
//    }
//
//    @PostMapping("/city")
//    public void createCity(@RequestBody City city) {
//        repo.save(city);
//    }
//
//    @PutMapping("/city/{id}")
//    public void updateCity(@PathVariable String id, @RequestBody City city, HttpServletResponse response) {
//        Optional<City> returnValue = repo.findById(Long.parseLong(id));
//        City cityToUpdate;
//
//        if (returnValue.isPresent()) {
//            cityToUpdate = returnValue.get();
//
//            cityToUpdate.setName(city.getName());
//
//            repo.save(cityToUpdate);
//        } else {
//            try {
//                response.sendError(404, "City with id: " + city.getId() + " not found.");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

}

