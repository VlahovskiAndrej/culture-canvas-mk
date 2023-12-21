package mk.ukim.finki.culturecanvasmk.service.Impl;

import mk.ukim.finki.culturecanvasmk.model.DistanceCalculator;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Review;
import mk.ukim.finki.culturecanvasmk.model.exceptions.MonumentNotFoundException;
import mk.ukim.finki.culturecanvasmk.repository.jpa.MonumentRepository;
import mk.ukim.finki.culturecanvasmk.service.MonumentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonumentServiceImpl implements MonumentService {
    private final MonumentRepository monumentRepository;

    public MonumentServiceImpl(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
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
        }
        else{   //EDIT
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
        Monument monument = monumentRepository.findById(monumentId).orElseThrow(()->new MonumentNotFoundException(monumentId));
        monumentRepository.addReviewToMonument(monument.getId(),review);
    }

    @Override
    public List<Review> listAllReviewsForMonument(Long id) {
        Monument monument = monumentRepository.findById(id).orElseThrow(()->new MonumentNotFoundException(id));
        return monument.getReviews();
    }
}

