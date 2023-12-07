package mk.ukim.finki.culturecanvasmk.model;

public class DistanceCalculator {
    // Radius of the Earth in kilometers
    private static final double EARTH_RADIUS_KM = 6371.0;

    // Method to calculate distance between two points given their latitude and longitude
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate differences between latitudes and longitudes
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Haversine formula to calculate distance
        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate distance in kilometers
        return EARTH_RADIUS_KM * c;
    }
}
