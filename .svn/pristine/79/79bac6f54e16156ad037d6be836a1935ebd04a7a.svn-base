(function () {
    angular.module("app").config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/consultarMediosPago.html",
            controller: "ConsultarMediosPagoController as controller"
        }).when("/registrarMedioPago/",{
            templateUrl: "templates/registrarMedioPago.html",
            controller: "RegistrarMedioPagoController as controller"
        }).when("/actualizarMedioPago/:idmedio_pago",{
            templateUrl: "templates/registrarMedioPago.html",
            controller: "ActualizarMedioPagoController as controller"
        }).when("/cargarArchivo/:idmedio_pago/:prueba",{
            templateUrl: "templates/cargarArchivo.jsp",
            controller: "CargarArchivoController as controller"
        }).when("/coberturaMunicipio/",{
            templateUrl: "templates/coberturaMunicipio.html",
            controller: "CoberturaMunicipioController as controller"
        }).when("/statusCargue/:idmedio_pago",{
            templateUrl: "templates/StatusCargue.html",
            controller: "AppControllerStatus as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
