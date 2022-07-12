(function () {
    angular.module("app").config(function ($routeProvider) {
        $routeProvider.when("/", {
            templateUrl: "templates/consultarEmpleados.html",
            controller: "ConsultarEmpleadosController as controller"
        }).when("/actualizarEmpleado/:idEmpleado",{
            templateUrl: "templates/actualizarEmpleado.html",
            controller: "ActualizarEmpleadoController as controller"
        }).when("/asignarRol",{
            templateUrl: "templates/asignarRoles.html",
            controller: "ActualizarEmpleadoController as controller"
        }).otherwise({redirectTo: "/"});
    });
})();
