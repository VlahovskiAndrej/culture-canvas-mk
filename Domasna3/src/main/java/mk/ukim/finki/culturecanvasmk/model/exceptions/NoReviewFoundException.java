package mk.ukim.finki.culturecanvasmk.model.exceptions;

public class NoReviewFoundException extends RuntimeException{
    public NoReviewFoundException(Long id) {
        super(String.format("Review with id %id couldn't be found",id));
    }
}
