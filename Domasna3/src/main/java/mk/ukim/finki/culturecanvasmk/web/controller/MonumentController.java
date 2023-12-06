package mk.ukim.finki.culturecanvasmk.web.controller;


import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.exceptions.MonumentNotFoundException;
import mk.ukim.finki.culturecanvasmk.service.InsertDataService;
import mk.ukim.finki.culturecanvasmk.service.MonumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

}
