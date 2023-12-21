package mk.ukim.finki.culturecanvasmk.web.controller;

import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Review;
import mk.ukim.finki.culturecanvasmk.service.MonumentService;
import mk.ukim.finki.culturecanvasmk.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final MonumentService monumentService;

    public ReviewController(ReviewService reviewService, MonumentService monumentService) {
        this.reviewService = reviewService;
        this.monumentService = monumentService;
    }

    @PostMapping("/add/{monumentId}")
    public String addReview(@PathVariable Long monumentId, @RequestParam String review_description, @RequestParam int score)
    {
        Monument monument = monumentService.findById(monumentId);
        Review review = reviewService.save(new Review(score,review_description));
        monumentService.addReviewToMonument(review,monumentId);
        return "redirect:/monuments";
    }

    @GetMapping("/show/{monumentId}")
    public String showReviews(@PathVariable Long monumentId, Model model)
    {
        Monument monument = monumentService.findById(monumentId);
        List<Review> reviews = monumentService.listAllReviewsForMonument(monumentId);
        model.addAttribute("monument",monument);
        model.addAttribute("reviews",reviews);
        model.addAttribute("bodyContent", "listReviews");
        return "master-template";

    }

}
