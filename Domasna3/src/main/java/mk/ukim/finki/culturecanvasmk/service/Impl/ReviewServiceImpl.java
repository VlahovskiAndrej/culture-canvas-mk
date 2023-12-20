package mk.ukim.finki.culturecanvasmk.service.Impl;


import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Review;
import mk.ukim.finki.culturecanvasmk.model.exceptions.MonumentNotFoundException;
import mk.ukim.finki.culturecanvasmk.repository.jpa.MonumentRepository;
import mk.ukim.finki.culturecanvasmk.repository.jpa.ReviewRepository;
import mk.ukim.finki.culturecanvasmk.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MonumentRepository monumentRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MonumentRepository monumentRepository) {
        this.reviewRepository = reviewRepository;
        this.monumentRepository = monumentRepository;
    }

    @Override
    public void addReviewToMonument(Review review, Long monumentId) {
        Monument monument = monumentRepository.findById(monumentId).orElseThrow(()->new MonumentNotFoundException(monumentId));
        monumentRepository.addReviewToMonument(monument,review);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAllByMonumentId(Long monumentId) {
        return reviewRepository.findAllByMonumentId(monumentId);
    }

    //ONLY ADMIN ALLOWED TO DELETE
    @Override
    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
