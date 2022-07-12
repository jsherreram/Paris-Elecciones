// Creates the gservice factory. This will be the primary means by which we interact with Google Maps
(function () {
    function Maps($rootScope, $q) {


        // Initialize Variables
        // -------------------------------------------------------------

        // Service our factory will return                             
        var map;
        var googleMapService = {};
        googleMapService.clickLat = 0;
        googleMapService.clickLong = 0;

        // Variables we'll use to help us pan to the right spot
        var lastMarker;

        // User Selected Location (initialize to center of America)
        var latitud = 0;
        var longitud = 0;

        /**
         * Permite ubicar un sitio de acuerdo a las coordenadas que recibe com parametro
         * @param {type} latitude
         * @param {type} longitude
         * @param {type} direccion
         * @param {type} municipio
         * @param {type} confirmado
         * @returns {undefined}
         */
        googleMapService.printCoordinates = function (latitude, longitude, direccion, municipio, confirmado) {
            cargarMapa();
            latitud = latitude;
            longitud = longitude;
            var img_confirmado = "<img src='../../images/yes.png' />";
            if (confirmado == 0)
                img_confirmado = "<img src='../../images/no.png' />";

            var content = "<b>Direcci&oacute;n</b>: " + capitalize(direccion) + "" + "<br><b>Municipio</b>: " + capitalize(municipio) + "<br><b>Confirmado</b>: " + img_confirmado

            if (latitude != null)
                initialize(latitude, longitude, content, "red");
            /*else 
             initialize(latitud, longitud, "Sin definir", "Sin definir", confirmado);*/
        };

        /**
         * Permite ubicar y pintar las coordenadas de un sitio a traves de la dirección
         * @param {type} direccion
         * @param {type} municipio
         * @returns {undefined}
         */
        googleMapService.printAddress = function (direccion, municipio) {

            cargarMapa();
            var geocoder = new google.maps.Geocoder();
            var address = direccion + "," + municipio + "," + "Colombia";
            var img_confirmado = "<img src='../../images/no.png' />";

            var content = "<b>Direcci&oacute;n</b>: " + capitalize(direccion) + "" + "<br><b>Municipio</b>: " + capitalize(municipio) + "<br><b>Confirmado</b>: " + img_confirmado

            geocoder.geocode({'address': address}, function (results, status) {

                if (status === google.maps.GeocoderStatus.OK)
                    initialize(results[0].geometry.location.lat(), results[0].geometry.location.lng(), content, "red");
                else {
                    address = municipio + "," + "Colombia";
                    geocoder.geocode({'address': address}, function (results, status) {

                        if (status === google.maps.GeocoderStatus.OK) {
                            content = "<b>Direcci&oacute;n</b>: " + direccion + " (No Localizada). <br><b>Recomendaci&oacute;n</b>: Ubique el sitio de forma manual<br><b>Municipio</b>: " + capitalize(municipio) + "<br><b>Confirmado</b>: " + img_confirmado
                            initialize(results[0].geometry.location.lat(), results[0].geometry.location.lng(), content, "red");
                        } else {
                            alert('Imposible ubicar la dirección seleccionada. Revise sus datos: ' + status);
                        }
                    });
                }
            });
        };

        /**
         * Imprime los empleados que fueron asignados de forma cercana a un sitio
         * @param {type} data, la data de empleados a pintar
         * @returns {undefined}
         */
        googleMapService.printCercanos = function (data, color) {
            var content = "<b>Documento</b>: " + data.nrodoc + "" + "<br><b>Direcci&oacute;n</b>: " + capitalize(data.direccion) + "" + "<br><b>Municipio</b>: " + capitalize(data.municipio.nombre) + "<br><b>Cargo</b>: " + capitalize(data.observacion);
            initialize(data.latitud, data.longitud, content, color);
        };

        /**
         * Obtiene coordenadas a partir de una dirección. Es utilizado por la geolocalización masiva
         * de sitios. Este metodo debe ser reemplazado por la forma en la cual se realizo el masivo con personas
         * @param {type} direccion
         * @param {type} municipio
         * @param {type} timeout
         * @returns {defered.promise}
         */
        googleMapService.getCoordenadas = function (direccion, municipio, timeout) {

            var defered = $q.defer();
            var promise = defered.promise;

            window.setTimeout(function () {

                var geocoder = new google.maps.Geocoder();
                var address = direccion + "," + municipio + "," + "Colombia";
                console.log(address);
                geocoder.geocode({'address': address}, function (results, status) {

                    if (status === google.maps.GeocoderStatus.OK) {
                        latitud = results[0].geometry.location.lat();
                        longitud = results[0].geometry.location.lng();
                        var coor = {"latitud": latitud, "longitud": longitud};
                        defered.resolve(coor);
                    } else {
                        var coor = {"latitud": 0, "longitud": 0};
                        defered.reject(status);
                    }
                });
            }, timeout);
            return promise;
        };

        /**
         * Retorna lalatitud del sitio
         * @returns {Number|latitude|googleMapService.clickLat}
         */
        googleMapService.getLatitud = function () {
            return latitud;
        };

        /**
         * Retornal l longitud del sitio
         * @returns {Number|googleMapService.clickLong|longitude}
         */
        googleMapService.getLongitud = function () {
            return longitud;
        };

        // Private Inner Functions
        // --------------------------------------------------------------

        /**
         * Funcion que s eencarga de pintar el mapa de acuerdo a las coordenadas que recibe como parametro
         * @param {type} latitude
         * @param {type} longitude
         * @param {type} direccion
         * @param {type} municipio
         * @param {type} confirmado
         * @param {type} cercano - Indica si se va a pintar un sitio o un empleado (false), o un empleado cercano (true)
         * @returns {undefined}
         */
        var initialize = function (latitude, longitude, content, color) {



            // Marca por defecto
            var iconMark = "http://maps.google.com/mapfiles/ms/icons/" + color + "-dot.png";

            // Set initial location as a bouncing red marker
            var initialLocation = new google.maps.LatLng(latitude, longitude);
            var marker = new google.maps.Marker({
                position: initialLocation,
                //animation: google.maps.Animation.BOUNCE,
                map: map,
                icon: iconMark
            });


            // Function for moving to a selected location
            //map.panTo(new google.maps.LatLng(latitude, longitude));            

            //+ Permite agregar la vantana de información al momento en que se muestra la marca
            var infowindow = new google.maps.InfoWindow({
                content: content
            });

            if (color == "red") {
                infowindow.open(map, marker);
                map.panTo(marker.position);
                lastMarker = marker;
            }

            marker.addListener('click', function () {
                infowindow.open(map, marker);
            });

            // Clicking on the Map moves the bouncing red marker
            google.maps.event.addListener(map, 'click', function (e) {
                var marker = new google.maps.Marker({
                    position: e.latLng,
                    //animation: google.maps.Animation.BOUNCE,
                    map: map,
                    icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png'
                });

                //+ Permite mostrar el mensaje informativo, indicando que la dirección fue modificada
                infowindow.setContent("Ubicaci&oacute;n Modificada");
                infowindow.open(map, marker);


                // When a new spot is selected, delete the old red bouncing marker
                if (lastMarker) {
                    lastMarker.setMap(null);
                }

                // Create a new red bouncing marker and move to it
                lastMarker = marker;
                //+ Agregar el evento click para mostrar ventana de información a la nueva ubicación seleccionada
                google.maps.event.addListener(lastMarker, "click", function () {
                    infowindow.open(map, this);
                });
                //- Se comenta para evitar que el mapa se mueva cada vez que se hace click en un punto direferente
                //map.panTo(marker.position); 

                // Update Broadcasted Variable (lets the panels know to change their lat, long values)
                googleMapService.clickLat = marker.getPosition().lat();
                googleMapService.clickLong = marker.getPosition().lng();
                latitud = googleMapService.clickLat;
                longitud = googleMapService.clickLong;
                $rootScope.$broadcast("clicked");
            });
        };

        var cargarMapa = function () {
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 15
            });
        };

        var capitalize = function (cadena) {
            cadena = cadena.toLowerCase();
            return cadena.charAt(0).toUpperCase() + cadena.slice(1);
        };

        // Refresh the page upon window load. Use the initial latitude and longitude
        //google.maps.event.addDomListener(window, 'load',initialize(latitud, longitud, "Sin definir", "Sin definir"));

        return googleMapService;
    }
    ;
    angular.module('app.services').factory('Maps', Maps);
})();

