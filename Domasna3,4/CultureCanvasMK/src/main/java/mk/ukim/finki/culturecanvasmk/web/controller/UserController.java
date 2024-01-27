package mk.ukim.finki.culturecanvasmk.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsersPage(HttpSession session, Model model){

/*        if (!Objects.equals((String) session.getAttribute("role"), "ADMIN"))
            return "redirect:/home";*/

        model.addAttribute("users", userService.listUsers());

        model.addAttribute("bodyContent", "listUsers");
        return "master-template";
    }

    @PostMapping("/delete/{userId}")
    public String deleteReview(@PathVariable Long userId, HttpSession session)
    {
/*        if (!Objects.equals((String) session.getAttribute("role"), "ADMIN"))
            return "redirect:/monuments";*/

        userService.deleteById(userId);

        return "redirect:/users";
    }

}
