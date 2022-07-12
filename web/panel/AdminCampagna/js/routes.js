(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/gestionCampagna.html",
            controller: "AdministrarCampagna as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
