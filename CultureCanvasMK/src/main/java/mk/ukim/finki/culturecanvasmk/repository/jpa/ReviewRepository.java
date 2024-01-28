package mk.ukim.finki.culturecanvasmk.repository.jpa;

import mk.ukim.finki.culturecanvasmk.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    void deleteById(Long reviewId);
}
