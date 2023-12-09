package mk.ukim.finki.culturecanvasmk.web.controller;


import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.exceptions.MonumentNotFoundException;
import mk.ukim.finki.culturecanvasmk.service.InsertDataService;
import mk.ukim.finki.culturecanvasmk.service.MonumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
//@RestController
@RequestMapping("/monuments")
public class MonumentController {

    private final MonumentService monumentService;

    public MonumentController(MonumentService monumentService) {
        this.monumentService = monumentService;
    }

//    @GetMapping
//    public ResponseEntity<String> getMonuments(Model model) {
//
//        List<String[]> monuments = monumentService.listAllPlaces();
//        model.addAttribute("monuments", monuments);
//
//        return new ResponseEntity<String>(monuments, HttpStatus.OK);
//    }


    @GetMapping
    public String getMonuments(Model model) {

        List<Monument> monuments = monumentService.listAllPlaces();
        model.addAttribute("monuments", monuments);

        return "listMonuments";
    }


    @PostMapping
    String searchBook(@RequestParam(required = false) String searchMonuments, String city, Model model) {

        List<Monument> monuments = monumentService.searchByName(searchMonuments);
        if (!Objects.equals(city, "All"))
            monuments = monumentService.filterByCity(city);

        model.addAttribute("monuments", monuments);
        return "listMonuments";
    }

    @GetMapping("/{id}")
    public String getMonuments(@PathVariable long id, Model model) {

        Optional<Monument> monument = monumentService.searchById(id);
        if (monument.isEmpty())
            throw new MonumentNotFoundException(id);
        model.addAttribute("monument", monument.get());

        return "monumentDetails";
    }

    @GetMapping("/map")
    public String getMap(Model model) {
        model.addAttribute("monuments", monumentService.listAllPlaces());
        return "osm";
    }

    @PostMapping("/map")
    public String getFilteredMap(@RequestParam(required = false) Double distance,
                                 @RequestParam(required = false) Double latitude,
                                 @RequestParam(required = false) Double longitude,
                                 Model model) {
        model.addAttribute("monuments", monumentService.filterByDistance(latitude, longitude, distance));
        model.addAttribute("userLatitude", latitude);
        model.addAttribute("userLongitude", longitude);
        return "osm";
    }

}
