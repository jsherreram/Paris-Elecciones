(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/nombramientoCargo.html",
            controller: "NombramientoCargo as controller"
        }).when("/busquedaPersonas/:idSitio/:codDpto/:codMun/:codZona/:sal/:nivel/:idNom/:page/:cargo", {
            templateUrl: "templates/busquedaPersonas.html",
            controller: "BusquedaPersonas as controller"
        }).when("/:codDpto/:codMun/:codZona/:sal/:nivel/:page/:cargo", {
            templateUrl: "templates/nombramientoCargo.html",
            controller: "NombramientoCargo as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
