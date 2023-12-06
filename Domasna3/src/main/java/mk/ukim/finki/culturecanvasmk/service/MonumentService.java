package mk.ukim.finki.culturecanvasmk.service;

import mk.ukim.finki.culturecanvasmk.model.Monument;

import java.util.List;
import java.util.Optional;

public interface MonumentService {

    List<Monument> listAllPlaces();

    List<Monument> searchByName(String name);

    List<Monument> filterByCity(String city);

    Optional<Monument> searchById(long id);

}


