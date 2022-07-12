var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/Nombramiento.html",
        controller: "appController"
    })
            .when('/Nombramiento/:idEvento/:codigoSitio', {
                templateUrl: "templates/Ubicacion.jsp",
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

app.controller("appController", ['$scope', 'SeguimientoFactory', function encuestaController($scope, SeguimientoFactory) {

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
                $scope.eventos= data;
                $scope.evento=data[0].codigoEvento;
                
            });
        };

    }]);


app.controller("appControllerUbicacion", ['$scope', 'SeguimientoFactory', 'PersonaAsignada', '$routeParams', '$window', function seguimientoController($scope, SeguimientoFactory, PersonaAsignada, $routeParams, $window) {

        $scope.idSitio = "";
        var usuario = leerCookie("APP-TOKEN");

        $scope.listar = function () {
            $scope.idSitio = $routeParams.codigoSitio;
            var idEvento = $routeParams.idEvento;
            $("#modalLoading").modal('show');

            SeguimientoFactory.getSitio($scope.idSitio, function (data) {
                $scope.puestoNombre = data.nombreSitio;
                $scope.direccion=data.direccionSitio;
            });

            SeguimientoFactory.listarUbicacion(idEvento, $scope.idSitio, function (data) {
                $scope.ubicaciones = data;
                $("#modalLoading").modal('hide');
            });
            
            SeguimientoFactory.getEvento(idEvento, function(data){
                $scope.evento=data;
            });
        };

        $scope.volver = function () {
            $window.history.back();
        }

        $scope.asignar = function (id) {
            var personaAsignada = new PersonaAsignada($scope.ubicaciones[id]);

            if (!isNaN(parseFloat(personaAsignada.empleado.nrodoc)) && isFinite(personaAsignada.empleado.nrodoc))
            {
                var idPersonaAsignada = personaAsignada.id;

                if (personaAsignada.empleado.nrodoc == 0) {
                    personaAsignada.estado.codigoEstado = 0;
                } else
                {
                    personaAsignada.estado.codigoEstado = 1;
                }

                var estadoActual = "";
                SeguimientoFactory.getEstadoActual(personaAsignada.empleado.nrodoc, formulario.global.idPruebaActual, personaAsignada.cargo.codigoCargo, function (data) {
                    estadoActual = data;

                    if (1 === 2)
                    {
                        alert('Esta identificación no tiene una aceptación de convocatoria para este cargo');
                    } else
                    {
                        personaAsignada.$update(function (response) {

                            if (response.codigo == '200')
                            {
                                SeguimientoFactory.getPersonaAsignada(idPersonaAsignada, function (data) {
                                    $scope.persona = data;

                                    $scope.ubicaciones[id] = $scope.persona;

                                    if ($scope.ubicaciones[id].estado.codigoEstado == '0' || $scope.ubicaciones[id].estado.codigoEstado == '1' || $scope.ubicaciones[id].estado.codigoEstado == '3')
                                    {
                                        $scope.ubicaciones[id].display = "";
                                        $scope.ubicaciones[id].display2 = "none";

                                    } else
                                    {
                                        $scope.ubicaciones[id].display = 'none';
                                        $scope.ubicaciones[id].display2 = '';
                                    }
                                });
                            } else
                            {
                                alert(response.descripcion);
                            }
                            ;
                        });
                    }

                });
            } else
            {
                alert('Valor no permitido');
            }
        };

    }]);


