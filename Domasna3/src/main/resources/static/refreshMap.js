$(document).ready(function() {
    $('#filterForm').submit(function(event) {
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
                // Update the map based on the response
                // Clear previous markers
                if (response.monuments) {
                    // Clear previous markers
                    map.eachLayer(function(layer) {
                        if (layer instanceof L.Marker) {
                            map.removeLayer(layer);
                        }
                    });

                    // Add new markers based on the updated data from the response
                    response.monuments.forEach(function(monument) {
                        var marker = L.marker([parseFloat(monument.latitude), parseFloat(monument.longitude)]).addTo(map);
                        var popupContent = "<b>Name:</b> " + monument.nameMk + "<br>";

                        if (monument.nameEn) {
                            popupContent += "<b>Name (English):</b> " + monument.nameEn + "<br>";
                        }
                        if (monument.region) {
                            popupContent += "<b>Region:</b> " + monument.region + "<br>";
                        }
                        if (monument.city) {
                            popupContent += "<b>City:</b> " + monument.city + "<br>";
                        }
                        if (monument.municipality) {
                            popupContent += "<b>Municipality:</b> " + monument.municipality + "<br>";
                        }
                        if (monument.postcode) {
                            popupContent += "<b>Postcode:</b> " + monument.postcode + "<br>";
                        }
                        if (monument.suburb) {
                            popupContent += "<b>Suburb:</b> " + monument.suburb + "<br>";
                        }
                        if (monument.address) {
                            popupContent += "<b>Address:</b> " + monument.address;
                        }

                        marker.bindPopup(popupContent);
                    });
                }

                if (response.latitude !== null && response.longitude !== null) {
                    var redIcon = L.icon({
                        iconUrl: 'https://clipground.com/images/google-maps-marker-transparent-png-10.png', // URL to the red marker image
                        iconSize: [25, 41], // size of the icon
                        iconAnchor: [12, 41], // point of the icon which will correspond to marker's location
                        popupAnchor: [1, -34] // point from which the popup should open relative to the iconAnchor
                    });
                    var marker = L.marker([response.latitude, response.longitude], {icon: redIcon}).addTo(map);
                }
            },
            error: function(error) {
                // Handle error
                console.log(error);
            }
        });
    });
});