package mk.ukim.finki.culturecanvasmk.web.controller;

import mk.ukim.finki.culturecanvasmk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("bodyContent","register");
        return "master-template";
    }


    @PostMapping
    public String registerUser(String firstName, String lastName, String username, String password, Model model) {
        if (userService.registerUser(firstName, lastName, username, password)){

            model.addAttribute("bodyContent","redirect:/login");
            return "master-template";
        }

        model.addAttribute("bodyContent","register");
        return "master-template";
    }
}
