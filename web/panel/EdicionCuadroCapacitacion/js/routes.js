(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/aplicacion.html",
            controller: "aplicacion as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
