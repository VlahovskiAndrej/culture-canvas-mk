package mk.ukim.finki.culturecanvasmk.repository;


import mk.ukim.finki.culturecanvasmk.bootstrap.DataHandler;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryMonumentRepository {
    public List<Monument> findAllPlaces() {
        return new ArrayList<>(DataHandler.monumentList);
    }

    public List<Monument> searchByName(String name) {
        return DataHandler.monumentList.stream().filter(m -> m.getNameMk().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Monument> cityFilter(String city) {
        return DataHandler.monumentList.stream().filter(m -> m.getCity().toLowerCase().equals(city.toLowerCase())).collect(Collectors.toList());
    }

    public Monument searchById(long id) {
        return DataHandler.monumentList.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }
}
