package mk.ukim.finki.culturecanvasmk.model.exceptions;

public class MonumentNotFoundException extends RuntimeException {
    public MonumentNotFoundException(Long id) {
        super(String.format("Monument with id %d does not exist", id));
    }
}
