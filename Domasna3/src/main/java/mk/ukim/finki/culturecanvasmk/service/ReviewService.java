package mk.ukim.finki.culturecanvasmk.service;

import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Review;
import mk.ukim.finki.culturecanvasmk.model.exceptions.MonumentNotFoundException;

import java.util.List;

public interface ReviewService {

    void addReviewToMonument(Review review, Long monumentId);

    Review save(Review review);

    List<Review> findAllByMonumentId(Long monumentId);

    void deleteById(Long reviewId);
}
