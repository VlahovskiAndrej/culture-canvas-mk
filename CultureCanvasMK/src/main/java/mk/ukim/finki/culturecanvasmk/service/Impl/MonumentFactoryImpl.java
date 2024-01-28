package mk.ukim.finki.culturecanvasmk.service.Impl;

import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.service.MonumentFactory;
import org.springframework.stereotype.Service;

@Service
public class MonumentFactoryImpl implements MonumentFactory {
    @Override
    public Monument createMonument(String nameMk, String nameEn, String city, String region, String municipality, String suburb, String longitude, String latitude, String address, String imageUrl) {
        return new Monument(nameMk, nameEn, region, city, municipality, "1000", suburb, longitude, latitude, address, imageUrl);
    }
}
