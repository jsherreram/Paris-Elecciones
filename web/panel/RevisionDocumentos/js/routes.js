(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/buscarPersonas.html",
            controller: "buscarPersonas as controller"
        }).when("/validar/:id", {
            templateUrl:"templates/validacionPersona.html",
            controller:"validarPersona as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
