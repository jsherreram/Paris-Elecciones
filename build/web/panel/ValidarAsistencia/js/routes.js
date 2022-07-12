(function () {
    angular.module('app').config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/listaSitiosAsistencia.html",
            controller: "ListarSitiosAsistenciaController as controller"
        }).when("/validar/:codigoSitio/:idEvento", {
            templateUrl:"templates/validaListaAsistencia.jsp",
            controller:"ValidarAsistenciaController as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
