package mk.ukim.finki.culturecanvasmk.model;

import lombok.Data;

import java.util.List;

@Data
public class MonumentResponse {

    private List<Monument> monuments;
    private Double latitude;
    private Double longitude;
    private Double selectedLat;
    private Double selectedLng;
}
