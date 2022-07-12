(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/listarMonitoreoSitios.jsp",
            controller: "ControllerMonitoreoSitio as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
