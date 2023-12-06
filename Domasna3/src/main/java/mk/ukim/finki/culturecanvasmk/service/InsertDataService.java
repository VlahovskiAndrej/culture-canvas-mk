package mk.ukim.finki.culturecanvasmk.service;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.culturecanvasmk.bootstrap.DataHandler;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.repository.jpa.MonumentRepository;
import org.springframework.stereotype.Service;

@Service
public class InsertDataService {
    private final MonumentRepository monumentRepository;

    public InsertDataService(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }

    @PostConstruct
    public void addDataFromCsv(){
        if(monumentRepository.count()==0)
            monumentRepository.saveAll(DataHandler.monumentList);
    }
}
