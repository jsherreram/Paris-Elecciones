var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

// Hacemos el ruteo de nuestra aplicación.
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/Evento.html",
        controller: "appController1"
    })
            .when('/Editar/:codigoEvento', {
                templateUrl: "templates/EditarEvento.html",
                controller: "appController2"
            })
            .when('/Registrar/', {
                templateUrl: "templates/RegistraEvento.html",
                controller: "appController3"
            })
            .otherwise({redirectTo: "/"});
});

app.controller("appController1", ['$scope', 'filterFilter', '$routeParams', 'EventoFactory', 'Empleado', '$window', function ($scope, filterFilter, $routeParams, EventoFactory, Empleado, $window) {
        $scope.idPrueba = $routeParams.idPrueba;

        //Se obtiene el id del evento para enviarlo en la consulta
        Empleado.getEmpleadoPruebaSesion(function (data) {
            $scope.idPrueba = data.pruebaActual;
            EventoFactory.obtenerEventosPorPrueba($scope.idPrueba, function (data) {
                $scope.eventos = data;
                $scope.evento = data[0];
                // create empty search model (object) to trigger $watch on update
                $scope.search = {};
                $scope.resetFilters = function () {
                    // needs to be a function or it won't trigger a $watch
                    $scope.search = {};
                };
                // pagination controls
                $scope.currentPage = 0;
                $scope.totalItems = $scope.eventos.length;
                $scope.entryLimit = 10; // items per page
                $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
                // $watch search to update pagination
                $scope.$watch('search', function (newVal, oldVal) {
                    $scope.filtered = filterFilter($scope.eventos, newVal);
                    $scope.totalItems = $scope.filtered.length;
                    $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
                    $scope.currentPage = 0;
                }, true);
            });
        });
        $scope.atras = function () {
            $window.history.back();
        };
    }]);

app.controller("appController2", ['$scope', 'filterFilter', '$routeParams', 'EventoActualiza', 'EventoFactory', '$window', 'sweet', function ($scope, filterFilter, $routeParams, EventoActualiza, EventoFactory, $window, sweet) {
        $scope.codigoEvento = $routeParams.codigoEvento;

        EventoFactory.obtenerEvento($scope.codigoEvento, function (data) {
            $scope.evento = data[0];

            var fecha = new Date($scope.evento.fecha);
            $scope.evento.fecha = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1) + '-' + pad(fecha.getDate());
            var fecha = new Date($scope.evento.fecha_final);
            $scope.evento.fecha_final = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1) + '-' + pad(fecha.getDate());
        });

        $scope.Save = function (reg) {
            var evento = new EventoActualiza();
            {
                evento.codigoEvento = reg.codigoEvento;
                evento.nombre = reg.nombre;
                evento.tipoSesion = reg.tipoSesion;
                evento.fecha = Date.parse($('#fecha').val());
                evento.hora_inicial = $('#hora_inicial').val();
                evento.fecha_final = Date.parse($('#fecha_final').val());
                evento.hora_final = $('#hora_final').val();
                evento.esEleccion = reg.esEleccion;
            }
            ;
            evento.$update({}, function (response) {
                if (response.codigo === '200') {
                    sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                } else {
                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                }
            });
        };
        $scope.atras = function () {
            $window.history.back();
        };
        function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }
        ;
    }]);

app.controller("appController3", ['$scope', '$location', '$routeParams', 'EventoInsert', 'EventoFactory', '$window', 'sweet', function ($scope, $location, $routeParams, EventoInsert, EventoFactory, $window, sweet) {
        $scope.idPrueba = 48;
        var evento = 999999999;

        $scope.Insert = function (reg) {
            var texto = "";
            if (reg.nombre === null || reg.nombre === '' || reg.nombre === undefined) {
                texto = texto + "El evento debe tener un Nombre.\n";
            }
            if (reg.tipoSesion === null || reg.tipoSesion === '' || reg.tipoSesion === undefined) {
                texto = texto + "Debe indicar el Tipo de Sesi\u00F3n.\n";
            }
            if (Date.parse($('#fecha').val()) === null || Date.parse($('#fecha').val()) === '') {
                texto = texto + "Debe indicar la Fecha de Inicio.\n";
            }
            if ($('#hora_inicial').val() === null || $('#hora_inicial').val() === '') {
                texto = texto + "Debe indicar la Hora de Inicio.\n";
            }
            if (Date.parse($('#fecha_final').val()) === null || Date.parse($('#fecha_final').val()) === '') {
                texto = texto + "Debe indicar la Fecha de Finalizaci\u00F3n.\n";
            }
            if ($('#hora_final').val() === null || $('#hora_final').val() === '') {
                texto = texto + "Debe indicar la Hora de Finalizaci\u00F3n.\n";
            }
            if (reg.esEleccion === null || reg.esEleccion === '' || reg.esEleccion === undefined) {
                texto = texto + "Debe indicar si es o no Elecci\u00F3n.\n";
            }
            if (texto !== '') {
                sweet.show({
                    title: 'Oopss!',
                    text: 'Resolver los Siguiente(s)\n' + texto,
                    type: 'warning',
                    showCancelButton: false,
                    confirmButtonColor: '#3e6bcc',
                    cancelButtonText: 'Cancelar',
                    confirmButtonText: 'OK, he entendido',
                    closeOnConfirm: false
                });
            } else {
                var evento = new EventoInsert();
                {
                    evento.codigoEvento = reg.codigoEvento;
                    evento.idprueba = $scope.idPrueba;
                    evento.nombre = reg.nombre;
                    evento.esCapacitacion = reg.esCapacitacion;
                    evento.codigoLogisys = '0';
                    evento.tipoSesion = reg.tipoSesion;
                    evento.fecha = Date.parse($('#fecha').val());
                    evento.hora_inicial = $('#hora_inicial').val();
                    evento.fecha_final = Date.parse($('#fecha_final').val());
                    evento.hora_final = $('#hora_final').val();
                    evento.tomaAsistencia = reg.tomaAsistencia;
                    evento.esPenitenciaria = reg.esPenitenciaria;
                    evento.activo = reg.activo;
                    evento.fecha_actualiza = reg.fecha_actualiza;
                    evento.abrirsesion = reg.abrirsesion;
                    evento.coddepartamento = '99';
                    evento.cantidadcapacitados = reg.cantidadcapacitados;
                    evento.esEleccion = reg.esEleccion;
                    evento.requiereAsistenciaCtaCobro = reg.requiereAsistenciaCtaCobro;
                    evento.codigoeventoPadre = reg.codigoeventoPadre;
                }
                ;
                evento.$insert(function (response) {
                    if (response.codigo === '200')
                    {
                        sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                        $location.path('/Editar/' + response.id);
                    } else {
                        console.log("Error: " + response.descripcion);
                        $scope.error = response.descripcion;
                        sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                    }
                    ;
                });
            }
        };
        $scope.atras = function () {
            $window.history.back();
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
