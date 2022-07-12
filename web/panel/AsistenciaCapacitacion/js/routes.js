(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/listarSitios.jsp",
            controller: "ControllerListarSitios as controller"
        }).when("/ver/:idDivipol/:idEvento", {
            templateUrl: "templates/asistenciaCapacitacion.html",
            controller: "AdministrarAsistencia as controller"
        }).when("/AsignaSuplente/:id", {
            templateUrl: "templates/asignaSuplente.html",
            controller: "AsignarSuplente as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
