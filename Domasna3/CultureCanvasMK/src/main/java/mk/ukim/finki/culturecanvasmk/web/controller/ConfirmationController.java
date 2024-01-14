package mk.ukim.finki.culturecanvasmk.web.controller;

import mk.ukim.finki.culturecanvasmk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirmation")
public class ConfirmationController {

    @Autowired
    private UserService userService;

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
        // Validate the token and register the user
        if (userService.confirmRegistration(token)) {
            return ResponseEntity.ok("User registration confirmed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }
}