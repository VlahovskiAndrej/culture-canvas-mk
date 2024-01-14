package mk.ukim.finki.culturecanvasmk.model;

import lombok.Data;

@Data
public class ConfirmationRequest {
    private String email;
    private String confirmationLink;

    public ConfirmationRequest(String email, String confirmationLink) {
        this.email = email;
        this.confirmationLink = confirmationLink;
    }
}
