package mk.ukim.finki.culturecanvasmk.service.Impl;

import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.repository.InMemoryMonumentRepository;
import mk.ukim.finki.culturecanvasmk.repository.jpa.MonumentRepository;
import mk.ukim.finki.culturecanvasmk.service.MonumentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonumentServiceImpl implements MonumentService {
    MonumentRepository monumentRepository;

    public MonumentServiceImpl(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }


    @Override
    public List<Monument> listAllPlaces() {
        return monumentRepository.findAll();
    }

    @Override
    public List<Monument> searchByName(String name) {
        return monumentRepository.findAllByNameMkContainingIgnoreCaseOrNameEnContainingIgnoreCase(name,name);
    }

    @Override
    public List<Monument> filterByCity(String city) {
        return monumentRepository.findAllByCityIgnoreCase(city);
    }

    @Override
    public Optional<Monument> searchById(long id) {
        return monumentRepository.findById(id);
    }
}
