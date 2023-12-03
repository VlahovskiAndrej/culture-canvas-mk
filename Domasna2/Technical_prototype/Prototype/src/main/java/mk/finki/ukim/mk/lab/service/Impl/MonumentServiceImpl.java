package mk.finki.ukim.mk.lab.service.Impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHandler;
import mk.finki.ukim.mk.lab.model.Monument;
import mk.finki.ukim.mk.lab.repository.MonumentRepository;
import mk.finki.ukim.mk.lab.service.MonumentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonumentServiceImpl implements MonumentService {
    MonumentRepository monumentRepository;

    public MonumentServiceImpl(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }


    @Override
    public List<Monument> listAllPlaces() {
        return monumentRepository.findAllPlaces();
    }

    @Override
    public List<Monument> searchByName(String name) {
        return monumentRepository.searchByName(name);
    }

    @Override
    public List<Monument> filterByCity(String city) {
        return monumentRepository.cityFilter(city);
    }

    @Override
    public Monument searchById(long id) {
        return monumentRepository.searchById(id);
    }
}
