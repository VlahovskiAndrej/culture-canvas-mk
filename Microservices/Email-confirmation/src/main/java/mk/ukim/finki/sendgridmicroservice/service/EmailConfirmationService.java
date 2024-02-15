package mk.ukim.finki.sendgridmicroservice.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailConfirmationService {

    @Value("${sendgrid.api-key}")
    private String sendGridApiKey;

    public void sendConfirmationEmail(String toEmail, String confirmationLink) {
        Email from = new Email("culturecanvasmk@gmail.com");
        Email to = new Email(toEmail);
        String subject = "Confirm Your Registration";
        String confirm = "<a href=" + confirmationLink + ">Confirm Your Account</a>";
        Content content = new Content("text/html", "<p>Hello,</p>" +
                "<p>Thank you for registering with CultureCanvasMK.</p>" +
                "<img style='width:200px; height:100px;' src='https://images-ext-2.discordapp.net/external/Y3hBq5WtIKemvAZcbDTR44Osk7JJUphQxPdmDuUnnC4/https/i.ibb.co/N6k98ZN/cc-final-r.png?format=webp&quality=lossless'/>" +
                "<p>Please click the following link to confirm your registration: </p>" + confirm
                + "<p>This link will expire in 48 hours</p>"
                + "<p>If you didn't request this, please ignore this email. Your account will not be confirmed.</p>");

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}