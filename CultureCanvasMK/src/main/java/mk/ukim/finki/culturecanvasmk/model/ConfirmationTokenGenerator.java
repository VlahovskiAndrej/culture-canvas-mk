package mk.ukim.finki.culturecanvasmk.model;

import java.util.UUID;

public class ConfirmationTokenGenerator {

    // TODO: CHANGE BASE URL AFTER HOSTING
    public static final String BASE_URL = "https://culturecanvas-abbbfc3fc458.herokuapp.com/login?token=";

    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
