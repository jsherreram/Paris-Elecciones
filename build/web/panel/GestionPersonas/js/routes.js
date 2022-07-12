(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/gestionarPersonas.html",
            controller: "GestionarPersona as controller"
        }).when('/RegistrarPersona', {
            templateUrl: "templates/registrarPersonaBasico.html",
            controller: "RegistrarPersona as controller"
        }).when('/EditarBasico/:idPersona', {
            templateUrl: "templates/registrarPersonaBasico.html",
            controller: "RegistrarPersona as controller"
        }).when('/EditarDetallado/:idPersona', {
            templateUrl: "templates/editarPersonaDetallado.html",
            controller: "EditarPersonaDetallado as controller"
        }).when('/EditarDetallado', {
            templateUrl: "templates/editarPersonaDetallado.html",
            controller: "EditarPersonaDetallado as controller"
        }).when('/seleccionarArchivo/:idDpto/:idPersona/:idEmpleado', {
            templateUrl: "templates/seleccionarArchivo.jsp",
            controller: "AdjuntarArchivos as controller"
        }).when('/Geolocalizar/',{
            templateUrl:"templates/geolocalizar.html",
            controller:"Geolocalizar as controller"
        }).when('/verHistorial/:idEmpleado/:tipoDoc', {
            templateUrl: "templates/historicoArchivo.html",
            controller: "verHistorial as controller"
        }).when('/VerDetallado/:idPersona', {
            templateUrl: "templates/verPersonaDetallado.html",
            controller: "verPersonaDetallado as controller"
        }).when('/SubirArchivoPersonas', {
            templateUrl: "templates/cargarPersonasMasivo.jsp",
            controller: "CargarArchivoPersonas as controller"
        }).when('/VerHistorialLaboral/:id',{
            templateUrl:"templates/verHistorialLaboralPersona.html",
            controller:"verHistorialLaboral as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
