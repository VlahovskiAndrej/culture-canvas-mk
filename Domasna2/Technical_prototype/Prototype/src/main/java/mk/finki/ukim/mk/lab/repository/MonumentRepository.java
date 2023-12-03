package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHandler;
import mk.finki.ukim.mk.lab.model.Monument;
import mk.finki.ukim.mk.lab.model.filters.Pipe;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MonumentRepository {
    public List<Monument> findAllPlaces(){
        return new ArrayList<>(DataHandler.monumentList);
    }

    public List<Monument> searchByName(String name){
        return DataHandler.monumentList.stream().filter(m -> m.getNameMk().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Monument> cityFilter(String city){
        return DataHandler.monumentList.stream().filter(m -> m.getCity().toLowerCase().equals(city.toLowerCase())).collect(Collectors.toList());
    }

    public Monument searchById(long id){
        return DataHandler.monumentList.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }
}
