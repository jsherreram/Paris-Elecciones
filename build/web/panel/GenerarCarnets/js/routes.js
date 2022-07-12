(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/seleccionarArchivo.jsp",
            controller: "generarCarnet as controller"
        })
        .otherwise({redirectTo: "/"});
    });
})();
