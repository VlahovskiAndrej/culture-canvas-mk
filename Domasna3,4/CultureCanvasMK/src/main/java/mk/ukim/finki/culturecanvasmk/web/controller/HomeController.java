package mk.ukim.finki.culturecanvasmk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/home", "/"})
public class HomeController {

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        model.addAttribute("bodyContent", "about");
        return "master-template";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        model.addAttribute("bodyContent", "contact");
        return "master-template";
    }
    @PostMapping("/contact")
    public String sendEmail(Model model) {
        model.addAttribute("bodyContent", "contact");
        return "master-template";
    }
}
