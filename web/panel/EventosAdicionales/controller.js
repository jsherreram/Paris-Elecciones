var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

// Hacemos el ruteo de nuestra aplicación.
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/EventoAdicional.html",
        controller: "appController1"
    })
            .otherwise({redirectTo: "/"});
});

app.controller("appController1", ['$scope', '$location', 'EventoAdicionalFactory', 'EventoAdicionalInsert', '$window', 'sweet', function ($scope, $location, EventoAdicionalFactory, EventoAdicionalInsert, $window, sweet) {

        $scope.toListAll = function () {
            EventoAdicionalFactory.getEmpleadoPruebaSesion(function (data) {
                $scope.idprueba = data.pruebaActual;
                EventoAdicionalFactory.getSitios($scope.idprueba, function (data) {
                    $scope.sitios = data;
                    $scope.sitio = data[0].divipol;
                });
                EventoAdicionalFactory.getEventos($scope.idprueba, function (data) {
                    $scope.eventos = data;
                    $scope.evento = data[0].codigoEvento;
                });
                EventoAdicionalFactory.getCargos(function (data) {
                    $scope.cargos = data;
                    $scope.cargo = data[0].codigoCargo;
                });
            });
        };

        $scope.Insert = function (reg) {
            EventoAdicionalFactory.getEmpleadoPruebaSesion(function (dataPrueba) {
                $scope.idPrueba = dataPrueba.pruebaActual;

                $scope.idSitio = $scope.detalleCargoPuestoEvento.divipol;
                EventoAdicionalFactory.getSitio($scope.idSitio, function (dataSitio) {
                    $scope.idDivipol = dataSitio.iddivipol;
                    $scope.codigoDepartamento = dataSitio.codigoDepartamento;
                    $scope.codigoMunicipio = dataSitio.codigoMunicipio;
                    $scope.codigoZona = dataSitio.codigoZona;
                    $scope.codigoPuesto = dataSitio.codigoSitio;

                    $scope.idCargo = $scope.detalleCargoPuestoEvento.cargo;
                    EventoAdicionalFactory.getCargo($scope.idCargo, function (dataCargo) {
                        $scope.codigoCargo = dataCargo.codigoCargo;

                        $scope.idEvento = $scope.detalleCargoPuestoEvento.evento;
                        EventoAdicionalFactory.getEvento($scope.idEvento, function (dataEvento) {
                            $scope.codigoEvento = dataEvento.codigoEvento;

                            var detalleCargoPuestoEvento = new EventoAdicionalInsert();
                            {
                                detalleCargoPuestoEvento.id = reg.id;
                                detalleCargoPuestoEvento.divipol = dataSitio;
                                var prueba = {idprueba: $scope.idPrueba};
                                detalleCargoPuestoEvento.prueba = prueba;
                                detalleCargoPuestoEvento.cargo = dataCargo;
                                detalleCargoPuestoEvento.evento = dataEvento;
                                detalleCargoPuestoEvento.ubicacion = reg.ubicacion;
                            }
                            ;
                            detalleCargoPuestoEvento.$insert(function (response) {
                                if (response.codigo === '200')
                                {
                                    sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                                    $location.path('/' + response.id);
                                } else {
                                    console.log("Error: " + response.descripcion);
                                    $scope.error = response.descripcion;
                                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                                }
                                ;
                            });
                        });
                    });
                });
            });
        };
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
