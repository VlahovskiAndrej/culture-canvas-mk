$(document).ready(function() {
    var userLoc;
    var routeControl;
    $('#findRoute').submit(function(event) {
        // Prevent the default form submission
        event.preventDefault();

        // Get form data
        var formData = $(this).serialize();

        // Make an AJAX request
        $.ajax({
            type: 'POST',
            url: $(this).attr('action'),
            data: formData,
            success: function(response) {

                if (response.latitude !== null && response.longitude !== null) {
                    var redIcon = L.icon({
                        iconUrl: 'https://clipground.com/images/google-maps-marker-transparent-png-10.png', // URL to the red marker image
                        iconSize: [25, 41], // size of the icon
                        iconAnchor: [12, 41], // point of the icon which will correspond to marker's location
                        popupAnchor: [1, -34] // point from which the popup should open relative to the iconAnchor
                    });
                    userLoc = L.marker([response.latitude, response.longitude], {icon: redIcon}).addTo(map);
                }
                var selectedLat = response.selectedLat
                var selectedLng = response.selectedLng;

                if(selectedLat === null || selectedLng === null){
                    return
                }

                var userLocation = L.latLng(response.latitude, response.longitude)
                var clickedLocation = L.latLng(selectedLat, selectedLng)

                if (routeControl) {
                    // Remove the existing route if it exists
                    map.removeControl(routeControl);
                }


                routeControl = L.Routing.control({
                    waypoints: [
                        userLocation, // User's location
                        clickedLocation // Clicked marker's location
                    ],
                    show: true,
                    addWaypoints: false,
                    autoRoute: true,
                    routeWhileDragging: false,
                    draggableWaypoints: false,
                    useZoomParameter: false,
                    showAlternatives: true
                }).addTo(map);

            },
            error: function(error) {
                // Handle error
                console.log(error);
            }
        });
    });
});
