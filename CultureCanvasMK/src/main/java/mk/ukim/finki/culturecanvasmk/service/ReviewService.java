package mk.ukim.finki.culturecanvasmk.service;

import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Review;
import mk.ukim.finki.culturecanvasmk.model.exceptions.MonumentNotFoundException;

import java.util.List;

public interface ReviewService {

    Review save(Review review);

    void deleteById(Long reviewId);
}
