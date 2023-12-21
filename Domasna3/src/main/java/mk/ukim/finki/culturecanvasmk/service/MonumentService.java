package mk.ukim.finki.culturecanvasmk.service;

import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Review;

import java.util.List;
import java.util.Optional;

public interface MonumentService {

    Monument findById(Long id);

    List<Monument> listAllPlaces();

    List<Monument> searchByName(String name);

    List<Monument> filterByCity(String city);

    Optional<Monument> searchById(long id);

    List<Double> getLatitudes();

    List<Double> getLongitudes();

    List<Monument> filterByDistance(Double latFrom, Double lngFrom, Double distance);

    void deleteById(Long id);
    void saveMonument(String nameMk, String nameEn, String city, String region, String municipality, String suburb, String longitude, String latitude, String address, long id);

    void addReviewToMonument(Review review, Long monumentId);

    List<Review> listAllReviewsForMonument(Long id);

}


