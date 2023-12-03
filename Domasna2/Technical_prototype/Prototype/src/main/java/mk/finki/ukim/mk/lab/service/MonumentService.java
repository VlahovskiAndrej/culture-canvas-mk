package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Monument;

import java.util.List;

public interface MonumentService {

    List<Monument> listAllPlaces();

    List<Monument> searchByName(String name);

    List<Monument> filterByCity(String city);

    Monument searchById(long id);

}


