
var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/Asistencia.html",
        controller: "appController"
    })
            .when('/Asistencia/:idEvento/:codigoSitio', {
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


app.controller("appController", ['$scope', 'EncuestaFactory', '$routeParams', 'Encuesta', function encuestaController($scope, EncuestaFactory, $routeParams, Encuesta) {

        var idEvento = 0;

        $scope.listarSitios = function () {
            EncuestaFactory.getEmpleadoPruebaSesion(function (data) {
                $scope.idprueba = data.pruebaActual;
                $scope.usuario = data.nrodoc;

                EncuestaFactory.getSitios($scope.idprueba, $scope.usuario, function (data) {
                    $scope.sitios = data;
                });
            });
        };

        $scope.listarEventos = function () {
            EncuestaFactory.getEventos($scope.sitio.id, function (data) {
                $scope.eventos = data;
                $scope.evento = data[0].codigoEvento;

            });
        };


    }]);


app.controller("appControllerUbicacion", ['$scope', '$routeParams', '$location', 'SeguimientoFactory', 'PersonaAsignada', '$window', function seguimientoController($scope, $routeParams, $location, SeguimientoFactory, PersonaAsignada, $window) {

        $scope.listar = function () {
            var idEvento = $routeParams.idEvento;
            $scope.codigoSitio = $routeParams.codigoSitio;
            $scope.idEvento=idEvento;

            SeguimientoFactory.getSitio($scope.codigoSitio, function (data) {
                $scope.puestoNombre = data.nombreSitio;
            });


            $scope.displayColummAsistieron = '';
            $scope.displayColummNoAsistieron = '';


            SeguimientoFactory.listarUbicacion(idEvento, $scope.codigoSitio, function (data) {
                $scope.ubicaciones = data;

                for (m = 0; m < $scope.ubicaciones.length; m++)
                {
                    if ($scope.ubicaciones[m].usuario != "''")
                    {
                        $scope.ubicaciones[m].display = "";
                        $scope.ubicaciones[m].display2 = "none";

                    } else
                    {
                        $scope.ubicaciones[m].display = 'none';
                        $scope.ubicaciones[m].display2 = '';
                    }

                    for (n = 0; n < $scope.estados.length; n++)
                    {
                        if ($scope.estados[n].codigoEstado == $scope.ubicaciones[m].estado.codigoEstado)
                        {
                            $scope.ubicaciones[m].estado = $scope.estados[n];
                        }
                    }
                }
            });
        };

        $scope.asignar = function (id) {
            var personaAsignada = new PersonaAsignada($scope.ubicaciones[id]);

            var usuario = leerCookie("APP-TOKEN");
            personaAsignada.usuario = usuario;

            if (!isNaN(parseFloat(personaAsignada.empleado.nrodoc)) && isFinite(personaAsignada.empleado.nrodoc))
            {
                var idPersonaAsignada = personaAsignada.id;

                personaAsignada.$update(function (response) {

                    if (response.codigo == '200')
                    {
                        SeguimientoFactory.getPersonaAsignada(idPersonaAsignada, function (data) {
                            $scope.persona = data;
                            $scope.ubicaciones[id] = $scope.persona;


                            if ($scope.ubicaciones[id].usuario != "''")
                            {
                                $scope.ubicaciones[id].display = "";
                                $scope.ubicaciones[id].display2 = "none";

                            } else
                            {
                                $scope.ubicaciones[id].display = 'none';
                                $scope.ubicaciones[id].display2 = '';
                            }

                            for (n = 0; n < $scope.estados.length; n++)
                            {
                                if ($scope.estados[n].codigoEstado == $scope.ubicaciones[id].estado.codigoEstado)
                                {
                                    $scope.ubicaciones[id].estado = $scope.estados[n];
                                }
                            }

                        });
                    } else
                    {
                        alert(response.descripcion);
                    }
                    ;
                });
            } else
            {
                alert('Valor no permitido');
            }
        };
        
        $scope.volver=function(){
            $window.history.back();
        }

    }]);



