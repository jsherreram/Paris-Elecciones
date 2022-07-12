(function () {
    angular.module("app").config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/consultarPersona.html",
            controller: "ConsultarPersonaPersonalCallController as controller"
        }).when("/gestionar/:idEmpleado", {
            templateUrl: "templates/gestionarPersona.html",
            controller: "GestionarPersonaPersonalCallController as controller"
        }).when("/usuario/:idEmpleado", {
            templateUrl: "templates/usuario.html",
            controller: "UsuarioPersonalCall as controller"
        }).when("/estado-prueba/:idPrueba",{
            templateUrl: "templates/cambioEstadoPersonaPrueba.html",
            controller: "CambioEstadoPerCall as controller"
        }).otherwise({redirectTo: "/"});
    });
})();

