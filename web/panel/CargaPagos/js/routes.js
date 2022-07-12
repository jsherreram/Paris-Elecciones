(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/cargaPagoViaticos.html",
            controller: "CargaPagoViaticos as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
