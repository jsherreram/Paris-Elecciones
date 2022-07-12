var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert', 'angularUtils.directives.dirPagination']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/Personal.html",
        controller: "appController"
    })
    .otherwise({redirectTo: "/"});
});

app.controller("appController", ['$scope', 'filterFilter', '$routeParams', 'Empleado', 'SeguimientoCapacitacion', '$location', '$window', function ($scope, filterFilter, $routeParams, Empleado, SeguimientoCapacitacion, $location, $window) {
        $scope.currentPage = 1;
        $scope.pageSize = 10;
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            $scope.idPrueba = data.pruebaActual;
            $scope.usuario = data.nrodoc;
            SeguimientoCapacitacion.getEstadoCapacitacion($scope.idPrueba,$scope.usuario,function (data) {
                    $scope.registros = data;
            });
        });
    }]);

app.filter('startFrom', function () {
    return function (input, start) {
        if (input) {
            start = +start;
            return input.slice(start);
        }
        return [];
    };
});

