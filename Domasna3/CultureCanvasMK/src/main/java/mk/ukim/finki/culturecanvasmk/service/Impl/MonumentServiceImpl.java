package mk.ukim.finki.culturecanvasmk.service.Impl;

import mk.ukim.finki.culturecanvasmk.model.DistanceCalculator;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Review;
import mk.ukim.finki.culturecanvasmk.model.exceptions.MonumentNotFoundException;
import mk.ukim.finki.culturecanvasmk.model.exceptions.NoReviewFoundException;
import mk.ukim.finki.culturecanvasmk.repository.jpa.MonumentRepository;
import mk.ukim.finki.culturecanvasmk.repository.jpa.ReviewRepository;
import mk.ukim.finki.culturecanvasmk.service.MonumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonumentServiceImpl implements MonumentService {
    private final MonumentRepository monumentRepository;
    private final ReviewRepository reviewRepository;

    public MonumentServiceImpl(MonumentRepository monumentRepository, ReviewRepository reviewRepository) {
        this.monumentRepository = monumentRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public Monument findById(Long id) {
        return monumentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Monument> listAllPlaces() {
        return monumentRepository.findAll();
    }

    @Override
    public List<Monument> searchByName(String name) {
        return monumentRepository.findAllByNameMkContainingIgnoreCaseOrNameEnContainingIgnoreCase(name, name);
    }

    @Override
    public List<Monument> filterByCity(String city) {
        return monumentRepository.findAllByCityIgnoreCase(city);
    }

    @Override
    public Optional<Monument> searchById(long id) {
        return monumentRepository.findById(id);
    }

    @Override
    public List<Double> getLatitudes() {
        return monumentRepository.findAllLatitudes();
    }

    @Override
    public List<Double> getLongitudes() {
        return monumentRepository.findAllLongitudes();
    }

    @Override
    public List<Monument> filterByDistance(Double latFrom, Double lngFrom, Double distance) {
        if (latFrom == null || lngFrom == null || distance == null)
            return monumentRepository.findAll();
        List<Monument> monuments = monumentRepository.findAll();
        return monuments.stream().filter(monument -> DistanceCalculator.calculateDistance(Double.parseDouble(monument.getLatitude()), Double.parseDouble(monument.getLongitude()), latFrom, lngFrom) <= distance)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        monumentRepository.deleteById(id);
    }

    @Override
    public void saveMonument(String nameMk, String nameEn, String city, String region, String municipality, String suburb, String longitude, String latitude, String address, long id) {

        if (id == 0) {   //CREATE NEW
            monumentRepository.save(new Monument(nameMk, nameEn, region, city, municipality, "1000", suburb, longitude, latitude, address));
        } else {   //EDIT
            Monument monument = monumentRepository.findById(id).orElse(null);

            assert monument != null;
            monument.setNameEn(nameEn);
            monument.setNameMk(nameMk);
            monument.setCity(city);
            monument.setRegion(region);
            monument.setMunicipality(municipality);
            monument.setLongitude(longitude);
            monument.setLatitude(latitude);
            monument.setAddress(address);

            monumentRepository.save(monument);
        }
    }

    @Override
    public void addReviewToMonument(Review review, Long monumentId) {
        Monument monument = monumentRepository.findById(monumentId).orElseThrow(() -> new MonumentNotFoundException(monumentId));
        monumentRepository.addReviewToMonument(monument.getId(), review);
    }

    @Override
    public List<Review> listAllReviewsForMonument(Long id) {
        Monument monument = monumentRepository.findById(id).orElseThrow(() -> new MonumentNotFoundException(id));
        return monument.getReviews();
    }

    @Override
    public void deleteReviewById(Long monument_id, Long review_id) {
        Review review = reviewRepository.findById(review_id).orElseThrow(() -> new NoReviewFoundException(review_id));
        Monument monument = monumentRepository.findById(monument_id).orElseThrow(() -> new MonumentNotFoundException(monument_id));
        monument.getReviews().remove(review);
        reviewRepository.deleteById(review_id);
        monumentRepository.save(monument);
    }

    @Override
    public Page<Monument> listMonumentsPageable(Pageable pageable) {
        return monumentRepository.findAll(pageable);
    }

    @Override
    public Page<Monument> searchByNamePageable(String name, Pageable pageable) {
        return monumentRepository.findAllByNameMkContainingIgnoreCaseOrNameEnContainingIgnoreCase(name, name, pageable);
    }

    @Override
    public Page<Monument> filterByCityPageable(String city, Pageable pageable) {
        return monumentRepository.findAllByCityIgnoreCase(city, pageable);
    }

    @Override
    public Page<Monument> filterByCityAndNamePageable(String city, String nameMk, String nameEn, Pageable pageable) {
        if (!Objects.equals(city, "All") && nameMk != null && nameEn != null && !nameMk.isEmpty() && !nameEn.isEmpty()){
            return monumentRepository.findAllByCityIgnoreCaseAndNameMkContainingIgnoreCaseOrCityIgnoreCaseAndNameEnContainingIgnoreCase(city, nameMk, city, nameEn, pageable);
        }else if(nameMk != null && nameEn != null && !nameMk.isEmpty() && !nameEn.isEmpty()){
            return monumentRepository.findAllByNameMkContainingIgnoreCaseOrNameEnContainingIgnoreCase(nameMk, nameEn, pageable);
        }else if(!Objects.equals(city, "All")){
            return monumentRepository.findAllByCityIgnoreCase(city, pageable);
        }
        return this.listMonumentsPageable(pageable);
    }
}

