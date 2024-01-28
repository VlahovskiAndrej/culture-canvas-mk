package mk.ukim.finki.culturecanvasmk.model;

import java.util.UUID;

public class ConfirmationTokenGenerator {

    // TODO: CHANGE BASE URL AFTER HOSTING
    public static final String BASE_URL = "http://localhost:8081/login?token=";

    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
