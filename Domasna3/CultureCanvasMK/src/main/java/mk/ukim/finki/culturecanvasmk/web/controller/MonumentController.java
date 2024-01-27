package mk.ukim.finki.culturecanvasmk.web.controller;


import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.MonumentResponse;
import mk.ukim.finki.culturecanvasmk.model.exceptions.MonumentNotFoundException;
import mk.ukim.finki.culturecanvasmk.service.MonumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String getMonuments(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size,
                               @RequestParam(required = false) String searchMonuments,
                               @RequestParam(defaultValue = "All") String city,
                               Model model, HttpSession session) {

        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);

        Page<Monument> monumentsPage;
        if (Objects.equals(city, "null") || city.isEmpty()) city = "All";

        if (searchMonuments == null && Objects.equals(city, "All")) {
            monumentsPage = monumentService.listMonumentsPageable(PageRequest.of(page, size));
        } else {
            monumentsPage = monumentService.filterByCityAndNamePageable(city, searchMonuments, searchMonuments, PageRequest.of(page, size));
            model.addAttribute("searchName", searchMonuments);
            model.addAttribute("city", city);
        }

        model.addAttribute("monuments", monumentsPage.getContent());
        model.addAttribute("totalPages", monumentsPage.getTotalPages());
        model.addAttribute("currentPage", page);

        model.addAttribute("bodyContent", "listMonuments");
        return "master-template";
    }

//    @PostMapping
//    String searchBook(@RequestParam(required = false) String searchMonuments, String city, Model model) {
//
//        List<Monument> monuments = monumentService.searchByName(searchMonuments);
//        if (!Objects.equals(city, "All"))
//            monuments = monumentService.filterByCity(city);
//
//        model.addAttribute("monuments", monuments);
//        model.addAttribute("bodyContent", "listMonuments");
//        return "master-template";
//    }
//    @PostMapping
//    String searchBook(@RequestParam(required = false) String searchMonuments,
//                      String city,
//                      @RequestParam(defaultValue = "0") int page,
//                      @RequestParam(defaultValue = "15") int size,
//                      Model model) {
//
//        Page<Monument> monumentsPage;
//
//        if (searchMonuments != null) {
//            monumentsPage = monumentService.searchByNamePageable(searchMonuments, PageRequest.of(page, size));
//        } else {
//            monumentsPage = monumentService.listMonumentsPageable(PageRequest.of(page, size));
//        }
//
//        if (!Objects.equals(city, "All"))
//            monumentsPage = monumentService.filterByCityPageable(city, PageRequest.of(page, size));
//
//        model.addAttribute("monuments", monumentsPage.getContent());
//        model.addAttribute("totalPages", monumentsPage.getTotalPages());
//        model.addAttribute("currentPage", page);
//
//        model.addAttribute("bodyContent", "listMonuments");
//        return "master-template";
//    }

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

    @GetMapping("/{id}/delete")
    public String deleteMonument(@PathVariable long id, HttpSession session, Model model) {
        monumentService.deleteById(id);
        model.addAttribute("bodyContent", "redirect:/monuments");
        return "master-template";
    }

    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable long id, HttpSession session, Model model) {
        model.addAttribute("monument", monumentService.findById(id));

        model.addAttribute("bodyContent", "addMonument");
        return "master-template";
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
                               Model model) {
        monumentService.saveMonument(nameMk, nameEn, city, region, municipality, suburb, longitude, latitude, address, Long.parseLong(id));

        return "redirect:/monuments";
    }

    @GetMapping("/add")
    public String getAddPage(HttpSession session, Model model) {
        model.addAttribute("bodyContent", "addMonument");
        return "master-template";
    }

    @PostMapping("/add_review/{monumentId}")
    public String reviewBook(@PathVariable Long monumentId, Model model) {
        Monument monument = monumentService.findById(monumentId);
        model.addAttribute("monument", monument);
        model.addAttribute("bodyContent", "monument-review");
        return "master-template";
    }

}
