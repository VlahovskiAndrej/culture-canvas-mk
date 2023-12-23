package mk.ukim.finki.culturecanvasmk.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Review;
import mk.ukim.finki.culturecanvasmk.service.MonumentService;
import mk.ukim.finki.culturecanvasmk.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public String addReview(@PathVariable Long monumentId, @RequestParam String review_description, @RequestParam int score,HttpSession session)
    {
        if (!Objects.equals((String) session.getAttribute("role"), "ADMIN") && !Objects.equals((String) session.getAttribute("role"), "USER") )
            return "redirect:/monuments";
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
    @PostMapping("/delete/{monumentId}")
    public String deleteReview(@PathVariable Long monumentId, @RequestParam Long review_id, HttpSession session)
    {
        if (!Objects.equals((String) session.getAttribute("role"), "ADMIN"))
            return "redirect:/monuments";

        Monument monument = monumentService.findById(monumentId);
        monumentService.deleteReviewById(monumentId,review_id);

        return "redirect:/review/show/{monumentId}";
    }
}
