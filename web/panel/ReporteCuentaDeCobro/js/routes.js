(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/seleccionarArchivo.jsp",
            controller: "generarCuentaCobro as controller"
        })
        .otherwise({redirectTo: "/"});
    });
})();
