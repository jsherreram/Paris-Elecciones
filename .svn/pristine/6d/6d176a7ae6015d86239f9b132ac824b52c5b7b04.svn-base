var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert', 'cgNotify']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/:idEvento/:idDivipol", {
        templateUrl: "templates/asignarSalon.html",
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

function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

app.controller("appControllerUbicacion", ['$scope', '$routeParams', 'SeguimientoFactory', 'Nombramiento', 'Empleado', 'sweet', '$window', 'Sesion', '$interval', 'notify', function seguimientoController($scope, $routeParams, SeguimientoFactory, Nombramiento, Empleado, sweet, $window, Sesion, $interval, notify) {

        var idPrueba = 0;
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;

            $scope.sitio = SeguimientoFactory.getById($routeParams.idDivipol, function () {

                $scope.parametros = SeguimientoFactory.getParametrosEncuesta($routeParams.idEvento, function (data1) {});

                $scope.idSitio = $routeParams.idSitio;
                SeguimientoFactory.listarUbicacion(idPrueba, $scope.sitio.codigoSitio, function (data) {
                    $scope.ubicaciones = data;
                    for (m = 0; m < $scope.ubicaciones.length; m++)
                    {
                        if ($scope.ubicaciones[m].estado.codigoEstado == '0' || $scope.ubicaciones[m].estado.codigoEstado == '1' || $scope.ubicaciones[m].estado.codigoEstado == '3')
                        {
                            $scope.ubicaciones[m].display = "";
                            $scope.ubicaciones[m].display2 = "none";

                        } else
                        {
                            $scope.ubicaciones[m].display = 'none';
                            $scope.ubicaciones[m].display2 = '';
                        }

                    }

                });
            });
        });
        $scope.atras = function () {
            $window.history.back();
        };

        $scope.asignar = function (id) {
            var nombramiento = new Nombramiento($scope.ubicaciones[id]);
            nombramiento.$updateSalon(function (response) {
                if (response.codigo == '200') {
                    sweet.show('Felicidades', "Sal\u00F3n asignado", "success");

                } else {
                    sweet.show('Oopss', response.descripcion, "error");
                }

            });
        };

    }]);
