package mk.ukim.finki.culturecanvasmk.model.exceptions;

public class NoReviewFoundException extends RuntimeException{
    public NoReviewFoundException(Long id) {
        super(String.format("Review with id %d couldn't be found",id));
    }
}
