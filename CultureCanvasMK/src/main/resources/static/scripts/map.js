var map = L.map('map').setView([41.6086, 21.7453], 8);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

var monuments = [[${monuments}]];

var userLat = parseFloat(document.getElementById("latitude").value);
var userLng = parseFloat(document.getElementById("longitude").value);
if (!isNaN(userLat) && !isNaN(userLng)) {
    var redIcon = L.icon({
        iconUrl: 'https://clipground.com/images/google-maps-marker-transparent-png-10.png', // URL to the red marker image
        iconSize: [25, 41], // size of the icon
        iconAnchor: [12, 41], // point of the icon which will correspond to marker's location
        popupAnchor: [1, -34] // point from which the popup should open relative to the iconAnchor
    });
    var userLoc = L.marker([userLat, userLng], {icon: redIcon}).addTo(map);
}

monuments.forEach(function (monument) {
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

    marker.on('click', function (e) {
        document.getElementById('selectedLat').value = e.latlng.lat;
        document.getElementById('selectedLng').value = e.latlng.lng;
    });
})