var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/Prueba.html",
        controller: "appController1"
    })
            .when('/Editar/:idPrueba', {
                templateUrl: "templates/EditarPrueba.html",
                controller: "appController2"
            })
            .when('/Registrar/', {
                templateUrl: "templates/RegistraPrueba.html",
                controller: "appController3"
            })
            .otherwise({redirectTo: "/"});
});

app.controller("appController1", ['$scope', 'filterFilter', '$routeParams', 'PruebaFactory', function ($scope, filterFilter, $routeParams, PruebaFactory) {

        PruebaFactory.obtenerPruebas(function (data) {
            $scope.pruebas = data;
            // create empty search model (object) to trigger $watch on update
            $scope.search = {};
            $scope.resetFilters = function () {
                // needs to be a function or it won't trigger a $watch
                $scope.search = {};
            };
            // pagination controls
            $scope.currentPage = 0;
            $scope.totalItems = $scope.pruebas.length;
            $scope.entryLimit = 10; // items per page
            $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
            // $watch search to update pagination
            $scope.$watch('search', function (newVal, oldVal) {
                $scope.filtered = filterFilter($scope.pruebas, newVal);
                $scope.totalItems = $scope.filtered.length;
                $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
                $scope.currentPage = 0;
            }, true);
        });
        $scope.atras = function () {
            $window.history.back();
        };
    }]);

app.controller("appController2", ['$scope', 'filterFilter', '$routeParams', 'PruebaActualiza', 'PruebaFactory', '$window', 'sweet', function ($scope, filterFilter, $routeParams, PruebaActualiza, PruebaFactory, $window, sweet) {
        $scope.idPrueba = $routeParams.idPrueba;

        PruebaFactory.obtenerTipPruebaEsp(function (data) {
            $scope.tipopruebaesp = data;

            PruebaFactory.obtenerEstPrueba(function (data) {
                $scope.estadoprueba = data;

                PruebaFactory.obtenerPrueba($scope.idPrueba, function (data) {
                    $scope.prueba = data[0];

                    var fecha = new Date($scope.prueba.fechaaplicacion);
                    $scope.prueba.fechaaplicacion = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1) + '-' + pad(fecha.getDate());
                    var fecha = new Date($scope.prueba.fecha_final_aplicacion);
                    $scope.prueba.fecha_final_aplicacion = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1) + '-' + pad(fecha.getDate());

                    var fecha = new Date($scope.prueba.fecha_inicio_convocatoria);
                    $scope.prueba.fecha_inicio_convocatoria = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1) + '-' + pad(fecha.getDate());
                    var fecha = new Date($scope.prueba.fecha_final_convocatoria);
                    $scope.prueba.fecha_final_convocatoria = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1) + '-' + pad(fecha.getDate());
                });
            });
        });
        $scope.Save = function (reg) {
            var prueba = new PruebaActualiza();
            {
                prueba.idprueba = reg.idprueba;
                prueba.nombre = reg.nombre;
                prueba.tprueba = reg.tprueba;
                prueba.codigoEstadoPrueba = reg.codigoEstadoPrueba;
                prueba.estadoprueba = reg.estadoprueba;
                prueba.descripcionestprueba = reg.descripcionestprueba;
                prueba.dias = reg.dias;
                prueba.vtiger_campo_estado = reg.vtiger_campo_estado;
                prueba.vtiger_campo_texto = reg.vtiger_campo_texto;
                prueba.vtiger_campo_cargo = reg.vtiger_campo_cargo;
                prueba.tipoPrueba = reg.tipoPrueba;
                prueba.fechaaplicacion = Date.parse($('#fechaaplicacion').val());
                prueba.tnombreprueba = reg.tnombreprueba;
                prueba.tpruebadescripcion = reg.tpruebadescripcion;
                prueba.fecha_final_aplicacion = Date.parse($('#fecha_final_aplicacion').val());
                prueba.fecha_final_convocatoria = Date.parse($('#fecha_final_convocatoria').val());
                prueba.fecha_inicio_convocatoria = Date.parse($('#fecha_inicio_convocatoria').val());
                prueba.vtiger_campo_nodo = reg.vtiger_campo_nodo;
                prueba.vtiger_campo_municipio = reg.vtiger_campo_municipio;
                prueba.idEstadoPrueba = reg.idEstadoPrueba;
                prueba.texto_convocatoria = reg.texto_convocatoria;
            }
            ;
            prueba.$update({}, function (response) {
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

app.controller("appController3", ['$scope', '$location', 'PruebaInsert', 'PruebaFactory', '$window', 'sweet', function ($scope, $location, PruebaInsert, PruebaFactory, $window, sweet) {
        $scope.idPrueba = 0;
        var prueba = 999999999;

        PruebaFactory.obtenerTipPruebaEsp(function (data) {
            $scope.tipopruebaesp = data;
        });

        PruebaFactory.obtenerEstPrueba(function (data) {
            $scope.estadoprueba = data;
        });

        PruebaFactory.obtenerPrueba(prueba, function (data) {
            $scope.prueba = data[0];
        });

        $scope.Insert = function (reg) {
            var prueba = new PruebaInsert();
            {
                prueba.idprueba = reg.idprueba;
                prueba.nombre = reg.nombre;
                prueba.tprueba = reg.tprueba;
                prueba.codigoEstadoPrueba = reg.codigoEstadoPrueba;
                prueba.estadoprueba = reg.estadoprueba;
                prueba.descripcionestprueba = reg.descripcionestprueba;
                prueba.dias = reg.dias;
                prueba.vtiger_campo_estado = reg.vtiger_campo_estado;
                prueba.vtiger_campo_texto = reg.vtiger_campo_texto;
                prueba.vtiger_campo_cargo = reg.vtiger_campo_cargo;
                prueba.tipoPrueba = reg.tipoPrueba;
                prueba.fechaaplicacion = Date.parse($('#fechaaplicacion').val());
                prueba.tnombreprueba = reg.tnombreprueba;
                prueba.tpruebadescripcion = reg.tpruebadescripcion,
                prueba.fecha_final_aplicacion = Date.parse($('#fecha_final_aplicacion').val());
                prueba.fecha_final_convocatoria = Date.parse($('#fecha_final_convocatoria').val());
                prueba.fecha_inicio_convocatoria = Date.parse($('#fecha_inicio_convocatoria').val());
                prueba.vtiger_campo_nodo = reg.vtiger_campo_nodo;
                prueba.vtiger_campo_municipio = reg.vtiger_campo_municipio;
                prueba.idEstadoPrueba = reg.idEstadoPrueba;
                prueba.texto_convocatoria = reg.texto_convocatoria;
            }
            ;
            prueba.$insert(function (response) {
                if (response.codigo == '200')
                {
                    sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                    $location.path('/Editar/' + response.id);
                } else {
                    console.log("Error: " + response.descripcion);
                    $scope.error = response.descripcion;
                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                }
                ;
            })
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
