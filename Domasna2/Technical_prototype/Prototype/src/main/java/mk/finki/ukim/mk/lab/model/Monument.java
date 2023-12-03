package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Monument {
//    name,nameEn,region,city,municipality,postcode,suburb,lon,lat,address,,,,,,


    long id;
    String nameMk;
    String nameEn;
    String region;
    String city;
    String municipality;
    String postcode;
    String suburb;
    String longitude;
    String latitude;
    String address;


    public Monument(String nameMk, String nameEn, String region, String city, String municipality, String postcode, String suburb, String longitude, String latitude, String address) {
        this.id = (long)(Math.random() * 10000);
        this.nameMk = nameMk;
        this.nameEn = nameEn;
        this.region = region;
        this.city = city;
        this.municipality = municipality;
        this.postcode = postcode;
        this.suburb = suburb;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }
}
