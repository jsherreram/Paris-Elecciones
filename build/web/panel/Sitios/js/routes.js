(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/gestionarSitios.html",
            controller: "ConsultarSitios as controller"
        }).when('/RegistrarSitio', {
            templateUrl: "templates/registrarSitio.html",
            controller: "RegistrarSitio as controller"
        }).when('/EditarSitio/:idSitio', {
            templateUrl: "templates/registrarSitio.html",
            controller: "RegistrarSitio as controller"
        }).when('/CargarSitios', {
            templateUrl: "templates/cargarSitiosMasivos.jsp",
            controller: "CargarArchivoSitios as controller"
        }).when('/CargarPdsSitio', {
            templateUrl: "templates/cargarPDSSitiosMasivos.jsp",
            controller: "CargarArchivoPDSSitios as controller"
        }).when('/CambiarCargo/:id',{
            templateUrl:"templates/cambiarCargo.html",
            controller:"CambiarCargo as controller"
        }).when('/GeolocalizarSitios/',{
            templateUrl:"templates/geolocalizarSitios.html",
            controller:"GeolocalizarSitios as controller"
        }
        ).otherwise({redirectTo: "/"});
    });
})();
