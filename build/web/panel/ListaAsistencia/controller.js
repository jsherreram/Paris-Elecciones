
var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/seleccionarArchivo.jsp",
        controller: "appControllerUbicacion"
    })

            .otherwise({redirectTo: "/"});
});

function leerCookie(nombre) {
    var lista = document.cookie.split(";");
    for (i in lista) {
        var busca = lista[i].search(nombre);
        if (busca > -1) {
            micookie = lista[i]
        }
    }
    var igual = micookie.indexOf("=");
    var valor = micookie.substring(igual + 1);
    return valor;
}


app.controller("appControllerUbicacion", ['$scope', '$routeParams', '$location', 'SeguimientoFactory', function seguimientoController($scope, $routeParams, $location, SeguimientoFactory) {

        var idEvento = 0;

        $scope.listarSitios = function () {
            SeguimientoFactory.getEmpleadoPruebaSesion(function (data) {
                $scope.idprueba = data.pruebaActual;
                $scope.usuario = data.nrodoc;

                SeguimientoFactory.getSitios($scope.idprueba, $scope.usuario, function (data) {
                    $scope.sitios = data;
                });
            });
        };

        $scope.listarEventos = function () {

            SeguimientoFactory.getEventos($scope.sitio.id, function (data) {
                $scope.eventos = data;
                $scope.evento = data[0].codigoEvento;

            });
        };


    }]);


