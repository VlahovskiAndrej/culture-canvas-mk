package mk.ukim.finki.culturecanvasmk.web.controller;

import mk.ukim.finki.culturecanvasmk.model.ConfirmationRequest;
import mk.ukim.finki.culturecanvasmk.model.ConfirmationTokenGenerator;
import mk.ukim.finki.culturecanvasmk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("bodyContent","register");
        return "masterTemplate";
    }

    @PostMapping
    public String registerUser(String firstName, String lastName, String username, String email, String password, Model model) {
        String token = ConfirmationTokenGenerator.generateToken();
        if (userService.registerUser(firstName, lastName, username, email, password, token)){
            ConfirmationRequest confirmationRequest = new ConfirmationRequest(email,
                    ConfirmationTokenGenerator.BASE_URL + token);

            // TODO: CHANGE URL AFTER HOSTING
            restTemplate.postForEntity("https://service-culturecanvas-2fb0fafc5950.herokuapp.com/confirmation/send-confirmation", confirmationRequest, String.class);

            model.addAttribute("bodyContent","confirmRegistration");
            return "masterTemplate";
        }

        model.addAttribute("bodyContent","register");
        model.addAttribute("hasError", true);
        model.addAttribute("error", "The username already exists");
        return "masterTemplate";
    }
}
