package mk.ukim.finki.sendgridmicroservice.web;

import mk.ukim.finki.sendgridmicroservice.model.ConfirmationRequest;
import mk.ukim.finki.sendgridmicroservice.service.EmailConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/confirmation")
public class ConfirmationController {

    @Autowired
    private EmailConfirmationService emailConfirmationService;

    @PostMapping("/send-confirmation")
    public ResponseEntity<String> sendConfirmation(@RequestBody ConfirmationRequest confirmationRequest) {

        emailConfirmationService.sendConfirmationEmail(confirmationRequest.getEmail(), confirmationRequest.getConfirmationLink());

        return ResponseEntity.ok("Confirmation email sent successfully.");
    }
}
