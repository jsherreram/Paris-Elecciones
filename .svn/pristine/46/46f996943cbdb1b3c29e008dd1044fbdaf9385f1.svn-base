(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/Cargar/:idDivipol", {
            templateUrl: "templates/cargarFotosSitio.html",
            controller: "CargarFotosSitio as controller"
        }).when("/Cargar/:idDivipol/:folder/:idEvento", {
            templateUrl: "templates/cargarFotosSitio.html",
            controller: "CargarFotosSitio as controller"
        }).when("/Visualizar", {
            templateUrl: "templates/visualizarFotos.html",
            controller: "VisualizarFotosSitio as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
