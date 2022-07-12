var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert', 'angularUtils.directives.dirPagination']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/:idPrueba", {
        templateUrl: "templates/Evento.html",
        controller: "appController"
    }).when("/", {
        templateUrl: "templates/Evento.html",
        controller: "appController"
    })
            .when('/Editar/:codigoEvento', {
                templateUrl: "templates/EditarEvento.html",
                controller: "appController2"
            })
            .when('/Registrar/:idPrueba/:codigoEvento', {
                templateUrl: "templates/RegistraEvento.html",
                controller: "appController3"
            })
            .otherwise({redirectTo: "/"});
});

app.controller("appController", ['$scope', 'filterFilter', '$routeParams', 'EventoFactory', '$location', '$window', function ($scope, filterFilter, $routeParams, EventoFactory, $location, $window) {
        $scope.idPrueba = $routeParams.idPrueba;
        var idpruebaUSer = 0;

        $scope.currentPage = 1;
        $scope.pageSize = 10;
        EventoFactory.getEmpleadoPruebaSesion(function (data) {
            idpruebaUSer = data.pruebaActual;
            $scope.usuario = data.nrodoc;
            if ($scope.idPrueba == 0 || $scope.idPrueba === undefined) {
                $scope.idPrueba = idpruebaUSer;
            }
            EventoFactory.obtenerEventosDepartamento($scope.idPrueba, $scope.usuario, function (data) {
                $scope.eventosdepartamento = data;
                $scope.eventodepartamento = data[0];
                // create empty search model (object) to trigger $watch on update

            });
        });


        $scope.nombramiento = function (codevento, iddivipol) {

            EventoFactory.getSitio(iddivipol, function (data) {

                var ruta = $window.location.origin + "/Paris/panel/NombramientoSitio/main.jsp#/Nombramiento/" + codevento + "/" + data.codigoSitio
                $window.open(ruta);
            });
        }


    }]);

app.controller("appController2", ['$scope', 'filterFilter', '$routeParams', 'EventoFactory', 'EventoActualiza', 'CapacitacionEvento', 'sweet', '$window', function ($scope, filterFilter, $routeParams, EventoFactory, EventoActualiza, CapacitacionEvento, sweet, $window) {
        $scope.codigoEvento = $routeParams.codigoEvento;
        $scope.idPrueba = 0;
        $scope.usuario = 0;
        
        EventoFactory.obtenerCargos(function (data) {
            $scope.cargos = data;
            EventoFactory.obtenerEvento($scope.codigoEvento, function (data) {
                $scope.evento = data[0];
                var fecha = new Date($scope.evento.fecha);
                $scope.evento.fecha = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1) + '-' + pad(fecha.getDate());
                var fecha = new Date($scope.evento.fecha_final);
                $scope.evento.fecha_final = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1) + '-' + pad(fecha.getDate());
            });
        });

        EventoFactory.getEmpleadoPruebaSesion(function (data) {
            $scope.usuario = data.nrodoc;
            $scope.idPrueba = data.pruebaActual;

            EventoFactory.obtenerdepartamentouser($scope.idPrueba, $scope.usuario, function (data) {
                $scope.lstdepartamentos = data;
            });
            EventoFactory.obtenerSitiosuser($scope.idPrueba, $scope.usuario, function (data) {
                $scope.lstsitios = data;
            });
        });



        $scope.Save = function (reg) {
            var texto = "";
            if (reg.nombre == null || reg.nombre == '') {
                texto = "La Capacitaci\u00F3n debe tener un sal\u00F3n.\n";
            }
            if (reg.coddepartamento == null) {
                texto = texto + "Debe Indicar Depatamento.\n";
            }
            if (reg.iddivipol == null || reg.iddivipol == 0) {
                texto = texto + "Debe Indicar Sitio.\n";
            }
            if (reg.codcargo == null) {
                texto = texto + "Debe Indicar Cargo.\n";
            }
            if (reg.cantidadcapacitados == null) {
                texto = texto + "Debe Indicar Cantidad.\n";
            } else {
                if (validarSiNumerol(reg.cantidadcapacitados)) {
                    texto = texto + "Valor de Cantidad no es N\u00FAmerico.\n";
                }
            }
            if (texto != '') {
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
                var evento = new EventoActualiza();
                {
                    evento.codigoEvento = reg.codigoEvento;
                    evento.nombre = reg.nombre;
                    evento.esCapacitacion = reg.esCapacitacion;
                    evento.codigoLogisys = reg.codigoLogisys;
                    evento.tipoSesion = reg.tipoSesion;
                    evento.fecha = Date.parse($('#fechapicker').val());
                    evento.hora_inicial = $('#horaInicial').val();
                    evento.fecha_final = Date.parse($('#fechafinalpicker').val());
                    evento.hora_final = $('#horaFinal').val();
                    evento.activo = reg.activo;
                    evento.tomaAsistencia = reg.tomaAsistencia;
                    evento.esPenitenciaria = reg.esPenitenciaria;
                    evento.coddepartamento = reg.coddepartamento;
                    evento.iddivipol = reg.iddivipol;
                    evento.codcargo = reg.codcargo;
                    evento.cantidadcapacitados = reg.cantidadcapacitados;
                }
                ;
                evento.$update(function (response) {
                    if (response.codigo === '200') {
                        var capacitacion = new CapacitacionEvento();
                        {
                            capacitacion.idprueba = reg.idprueba;
                            capacitacion.codigoEvento = reg.codigoEvento;
                            capacitacion.iddivipol = reg.iddivipol;
                            capacitacion.codcargo = reg.codcargo;
                            capacitacion.cantidadcapacitados = reg.cantidadcapacitados;
                            capacitacion.$update(function (response) {
                                if (response.codigo === '200') {
                                    sweet.show('Felicidades', 'Actualizaci\u00F3n Realizada', "success");
                                } else {
                                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                                }
                            });
                        }
                    } else {
                        sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                    }
                    ;

                });

            }
        };
        $scope.atras = function () {
            $window.history.back();
        };
        $scope.validarSiNumero = function (numero) {
            if (!/^([0-9])*$/.test(numero)) {
                alert("El valor " + numero + " no es un n\u00FAmero");
            }
        };
        validarSiNumerol = function (numero) {
            if (!/^([0-9])*$/.test(numero)) {
                return true;
            } else {
                return false;
            }
        };
        function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }
    }]);

app.controller("appController3", ['$scope', '$location', '$routeParams', 'EventoFactory', 'EventoInsert', 'CapacitacionEvento', 'sweet', '$window', function ($scope, $location, $routeParams, EventoFactory, EventoInsert, CapacitacionEvento, sweet, $window) {
        $scope.idPrueba = $routeParams.idPrueba;
        var codigoEvento = $routeParams.codigoEvento;
        var idpruebaUSer = 0;

        EventoFactory.getEmpleadoPruebaSesion(function (data) {
            idpruebaUSer = data.pruebaActual;
            $scope.usuario = data.nrodoc;
            if ($scope.idPrueba == 0) {
                $scope.idPrueba = idpruebaUSer;
            }
            EventoFactory.obtenerdepartamentouser($scope.idPrueba, $scope.usuario, function (data) {
                $scope.lstdepartamentos = data;
            });
            EventoFactory.obtenerSitiosuser($scope.idPrueba, $scope.usuario, function (data) {
                $scope.lstsitios = data;
            });
        });
        EventoFactory.obtenerCargos(function (data) {
            $scope.cargos = data;
        });

        $scope.Save = function (reg) {
            var texto = "";
            if (reg.nombre == null) {
                texto = "La Capacitaci\u00F3n debe tener un sal\u00F3n.\n";
            }
            if (reg.coddepartamento == null) {
                texto = texto + "Debe Indicar Depatamento.\n";
            }
            if (reg.iddivipol == null) {
                texto = texto + "Debe Indicar Sitio.\n";
            }
            if (reg.codcargo == null) {
                texto = texto + "Debe Indicar Cargo.\n";
            }
            if (reg.cantidadcapacitados == null) {
                texto = texto + "Debe Indicar Cantidad.\n";
            } else {
                if (validarSiNumerol(reg.cantidadcapacitados)) {
                    texto = texto + "Valor de Cantidad no es N\u00FAmerico.\n";
                }
            }

            if (texto != '') {
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
                    evento.idprueba = $scope.idPrueba;
                    evento.nombre = reg.nombre;
                    evento.esCapacitacion = 1;
                    evento.codigoLogisys = '';
                    evento.tipoSesion = 'CAPACITACION';
                    evento.fecha = Date.parse($('#fechapicker').val());
                    evento.hora_inicial = $('#horaInicial').val();
                    evento.fecha_final = Date.parse($('#fechafinalpicker').val());
                    evento.hora_final = $('#horaFinal').val();
                    evento.activo = 1;
                    evento.tomaAsistencia = 1;
                    evento.esPenitenciaria = 0;
                    evento.coddepartamento = reg.coddepartamento;
                    evento.iddivipol = reg.iddivipol;
                    evento.codcargo = reg.codcargo;
                    evento.cantidadcapacitados = reg.cantidadcapacitados;
                }
                ;
                evento.$insert(function (response) {
                    if (response.codigo == '200') {
                        var capacitacion = new CapacitacionEvento();
                        {
                            capacitacion.idprueba = $scope.idPrueba;
                            capacitacion.codigoEvento = response.id;
                            capacitacion.iddivipol = reg.iddivipol;
                            capacitacion.codcargo = reg.codcargo;
                            capacitacion.cantidadcapacitados = reg.cantidadcapacitados;
                            capacitacion.$update(function (response) {
                                if (response.codigo === '200') {
                                    sweet.show('Felicidades', 'Informaci\u00F3n guardada', "success");
                                } else {
                                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                                }
                            });
                        }
                        ;
                        $window.history.back();
                    } else {
                        sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                        $scope.error = response.descripcion;
                    }
                    ;
                })
            }
        };
        $scope.atras = function () {
            $window.history.back();
        };
        $scope.validarSiNumero = function (numero) {
            if (!/^([0-9])*$/.test(numero)) {
                alert("El valor " + numero + " no es un n\u00FAmero");
            }
        };
        validarSiNumerol = function (numero) {
            if (!/^([0-9])*$/.test(numero)) {
                return true;
            } else {
                return false;
            }
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

