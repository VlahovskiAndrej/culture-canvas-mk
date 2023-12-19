package mk.ukim.finki.culturecanvasmk.web.controller;


import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.MonumentResponse;
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

    @GetMapping
    public String getMonuments(Model model) {

        List<Monument> monuments = monumentService.listAllPlaces();
        model.addAttribute("monuments", monuments);

        model.addAttribute("bodyContent", "listMonuments");
        return "master-template";
    }


    @PostMapping
    String searchBook(@RequestParam(required = false) String searchMonuments, String city, Model model) {

        List<Monument> monuments = monumentService.searchByName(searchMonuments);
        if (!Objects.equals(city, "All"))
            monuments = monumentService.filterByCity(city);

        model.addAttribute("monuments", monuments);
        model.addAttribute("bodyContent", "listMonuments");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getMonuments(@PathVariable long id, Model model) {

        Optional<Monument> monument = monumentService.searchById(id);
        if (monument.isEmpty())
            throw new MonumentNotFoundException(id);
        model.addAttribute("monument", monument.get());
        model.addAttribute("bodyContent", "monumentDetails");
        return "master-template";
    }

    @GetMapping("/map")
    public String getMap(Model model) {
        model.addAttribute("monuments", monumentService.listAllPlaces());
        model.addAttribute("bodyContent", "osm");
        return "master-template";
    }

    @PostMapping("/map")
    @ResponseBody
    public MonumentResponse updateMap(@RequestParam(required = false) Double distance,
                                      @RequestParam(required = false) Double latitude,
                                      @RequestParam(required = false) Double longitude) {

        MonumentResponse monumentResponse = new MonumentResponse();
        monumentResponse.setMonuments(monumentService.filterByDistance(latitude, longitude, distance));
        monumentResponse.setLatitude(latitude);
        monumentResponse.setLongitude(longitude);
        return monumentResponse;
    }

    @PostMapping("/findRoute")
    @ResponseBody
    public MonumentResponse findRoute(@RequestParam(required = false) Double latitude1,
                                      @RequestParam(required = false) Double longitude1,
                                      @RequestParam(required = false) Double selectedLat,
                                      @RequestParam(required = false) Double selectedLng) {

        MonumentResponse monumentResponse = new MonumentResponse();
        monumentResponse.setLatitude(latitude1);
        monumentResponse.setLongitude(longitude1);
        monumentResponse.setSelectedLat(selectedLat);
        monumentResponse.setSelectedLng(selectedLng);
        return monumentResponse;
    }
}
