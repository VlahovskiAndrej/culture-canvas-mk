package mk.ukim.finki.culturecanvasmk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Monument {
//    name,nameEn,region,city,municipality,postcode,suburb,lat,lon,address,,,,,,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameMk;
    private String nameEn;
    private String region;
    private String city;
    private String municipality;
    private String postcode;
    private String suburb;
    private String longitude;
    private String latitude;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;



    public Monument(String nameMk, String nameEn, String region, String city, String municipality, String postcode, String suburb, String latitude, String longitude, String address) {
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
        this.reviews=new ArrayList<>();
    }

    public Monument() {

    }

    public double getRating()
    {
        if(!reviews.isEmpty())
            return this.getReviews().stream().mapToInt(Review::getScore).average().getAsDouble();
        return 0.0;
    }

}
