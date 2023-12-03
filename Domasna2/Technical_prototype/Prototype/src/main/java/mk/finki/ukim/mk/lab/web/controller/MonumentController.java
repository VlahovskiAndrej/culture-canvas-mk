package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Monument;
import mk.finki.ukim.mk.lab.service.MonumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
//@RestController
@RequestMapping("/monuments")
public class MonumentController {

    MonumentService monumentService;

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
//    }  r



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

        Monument monument = monumentService.searchById(id);
        model.addAttribute("monument", monument);

        return "monumentDetails";
    }

}
