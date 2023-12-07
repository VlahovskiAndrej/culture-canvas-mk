package mk.ukim.finki.culturecanvasmk.service.Impl;

import mk.ukim.finki.culturecanvasmk.model.DistanceCalculator;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.repository.InMemoryMonumentRepository;
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


}
