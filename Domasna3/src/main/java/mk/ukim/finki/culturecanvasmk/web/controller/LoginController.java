package mk.ukim.finki.culturecanvasmk.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.culturecanvasmk.model.User;
import mk.ukim.finki.culturecanvasmk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {

        if (userService.checkUsernameAndPassword(username, password)) {

            User user = userService.findByUsernameAndPassword(username, password);

            session.setAttribute("username", username);
            session.setAttribute("role", user.getRole());

            model.addAttribute("bodyContent","redirect:/monuments");
            return "master-template";
        } else {
            model.addAttribute("error", "Invalid username or password");

            model.addAttribute("bodyContent","login");
            return "master-template";
        }
    }

    @GetMapping("/logout")
    public String Logout(HttpSession session, Model model){
        session.setAttribute("username", "");
        session.setAttribute("role","");

        model.addAttribute("bodyContent","login");
        return "master-template";
    }

}
