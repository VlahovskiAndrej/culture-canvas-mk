window.onload=function () {
    if ("geolocation" in navigator) {
        // Get current position
        navigator.geolocation.getCurrentPosition(function (position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            // Use latitude and longitude values
            console.log("Latitude: " + latitude + ", Longitude: " + longitude);
            document.getElementById("latitude").value = latitude;
            document.getElementById("longitude").value = longitude;

            // Here, you can perform further actions with the obtained coordinates
            // For example, you can make an AJAX request to send the coordinates to the server
            // or display the location on a map.
        }, function (error) {
            // Handle error cases
            switch (error.code) {
                case error.PERMISSION_DENIED:
                    console.log("User denied the request for geolocation.");
                    break;
                case error.POSITION_UNAVAILABLE:
                    console.log("Location information is unavailable.");
                    break;
                case error.TIMEOUT:
                    console.log("The request to get user location timed out.");
                    break;
                case error.UNKNOWN_ERROR:
                    console.log("An unknown error occurred.");
                    break;
            }
        });
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}