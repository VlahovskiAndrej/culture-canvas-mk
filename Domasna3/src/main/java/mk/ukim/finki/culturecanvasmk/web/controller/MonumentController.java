package mk.ukim.finki.culturecanvasmk.web.controller;


import jakarta.servlet.http.HttpSession;
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

    @GetMapping
    public String getMonuments(Model model, HttpSession session) {

        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);

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
    public String getFilteredMap(@RequestParam(required = false) Double distance,
                                 @RequestParam(required = false) Double latitude,
                                 @RequestParam(required = false) Double longitude,
                                 Model model) {
        model.addAttribute("monuments", monumentService.filterByDistance(latitude, longitude, distance));
        model.addAttribute("userLatitude", latitude);
        model.addAttribute("userLongitude", longitude);
        model.addAttribute("bodyContent", "osm");
        return "master-template";
    }

    @GetMapping("/{id}/delete")
    public String deleteMonument(@PathVariable long id,HttpSession session, Model model){

        if (session.getAttribute("role") != "ADMIN")
            return "redirect:/monuments";

        monumentService.deleteById(id);
        return "redirect:/monuments";
    }

    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable long id, HttpSession session, Model model){

        if (!Objects.equals((String) session.getAttribute("role"), "ADMIN"))
            return "redirect:/monuments";

        model.addAttribute("monument", monumentService.findById(id));
        return "addMonument";
    }

    @PostMapping("/save")
    public String saveMonument(String nameMk,
                               String nameEn,
                               String city,
                               String region,
                               String municipality,
                               String suburb,
                               String longitude,
                               String latitude,
                               String address,
                               String id,
                               Model model){
        monumentService.saveMonument(nameMk, nameEn, city, region, municipality, suburb, longitude, latitude, address, Long.parseLong(id));
        return "redirect:/monuments";
    }

    @GetMapping("/add")
    public String getAddPage(HttpSession session, Model model){

        if (!Objects.equals((String) session.getAttribute("role"), "ADMIN"))
            return "redirect:/monuments";

        return "addMonument";
    }

}
