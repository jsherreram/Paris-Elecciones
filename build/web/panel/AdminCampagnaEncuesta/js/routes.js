 (function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/gestionCampagnaEncuesta.html",
            controller: "AdminCampagnaEncuesta as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
