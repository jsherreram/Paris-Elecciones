var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider){
    $routeProvider.when("/:idPrueba", {
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

app.controller("appController", ['$scope', 'filterFilter', '$routeParams', 'EventoFactory', function ($scope, filterFilter, $routeParams, EventoFactory) {
        $scope.idPrueba =   $routeParams.idPrueba;

        EventoFactory.obtenerEventos($scope.idPrueba,function (data) {
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
            $scope.entryLimit = 18; // items per page
            $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
            // $watch search to update pagination
            $scope.$watch('search', function (newVal, oldVal) {
                $scope.filtered = filterFilter($scope.eventos, newVal);
                $scope.totalItems = $scope.filtered.length;
                $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
                $scope.currentPage = 0;
            }, true);
        });
        $scope.atras = function () {
            $window.history.back();
        };
}]);

app.controller("appController2",['$scope', 'filterFilter', '$routeParams', 'EventoFactory', 'EventoActualiza','$window', function ($scope, filterFilter, $routeParams, EventoFactory, EventoActualiza, $window) {
        $scope.codigoEvento          = $routeParams.codigoEvento;
        EventoFactory.obtenerTipSesion(function (data) {
            $scope.tipoSesion = data;
            EventoFactory.obtenerEvento($scope.codigoEvento,function (data) {
            $scope.evento = data[0];
            
            var fecha = new Date($scope.evento.fecha);
            $scope.evento.fecha = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1 ) + '-' + pad(fecha.getDate());
            var fecha = new Date($scope.evento.fecha_final);
            $scope.evento.fecha_final = fecha.getFullYear() + '-' + pad(fecha.getMonth() + 1 ) + '-' + pad(fecha.getDate());
            });
        });
        $scope.Save = function (reg) {
            var evento = new EventoActualiza();
            {   evento.codigoEvento  = reg.codigoEvento;
                evento.nombre        = reg.nombre;
                evento.esCapacitacion= reg.esCapacitacion;
                evento.codigoLogisys = reg.codigoLogisys;
                evento.tipoSesion    = reg.tipoSesion;
                evento.fecha         = Date.parse($('#fechapicker').val());
                evento.hora_inicial  = $('#horaInicial').val();
                evento.fecha_final   = Date.parse($('#fechafinalpicker').val());
                evento.hora_final    = $('#horaFinal').val();
                evento.activo        = reg.activo;
                evento.tomaAsistencia= reg.tomaAsistencia;
                evento.esPenitenciaria=reg.esPenitenciaria;
            };
             evento.$update();
        };
        $scope.atras = function () {
            $window.history.back();
        };
        function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }
}]);

app.controller("appController3",['$scope', '$location', '$routeParams', 'EventoFactory', 'EventoInsert','$window', function ($scope, $location, $routeParams, EventoFactory, EventoInsert, $window) {
        $scope.idPrueba = $routeParams.idPrueba;
        var codigoEvento= $routeParams.codigoEvento;
        EventoFactory.obtenerTipSesion(function (data) {
            $scope.tipoSesion = data;

            EventoFactory.obtenerEvento(codigoEvento,function (data) {
            $scope.evento = data[0];
            $scope.evento.activo = 1;
            $scope.tomaAsistencia= 0;
            $scope.esPenitenciaria=0;
            });
        });
        $scope.Save = function (reg) {
            var evento = new EventoInsert();{
                //alert(Date.parse($('#fechapicker').val()));
                //alert(Date.parse($('#fechafinalpicker').val()));
                evento.idprueba      = $scope.idPrueba;
                evento.nombre        = reg.nombre;
                evento.esCapacitacion= reg.esCapacitacion;
                evento.codigoLogisys = reg.codigoLogisys;
                evento.tipoSesion    = reg.tipoSesion;
                evento.fecha         = Date.parse($('#fechapicker').val());
                evento.hora_inicial  = $('#horaInicial').val();
                evento.fecha_final   = Date.parse($('#fechafinalpicker').val());
                evento.hora_final    = $('#horaFinal').val();
                evento.activo        = 1;
                evento.tomaAsistencia= reg.tomaAsistencia;
                evento.esPenitenciaria=reg.esPenitenciaria;
            };
             evento.$insert(function (response) {
                if (response.codigo == '200')
                {  //$location.path(formulario.global.angularBase+'/ProgramarEventos/main.jsp#/Editar/' + response.id);
                    $location.path('/Editar/' + response.id );
                }
                else{
                    console.log("Error: "+response.descripcion);
                    $scope.error = response.descripcion;
                };
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
        }return [];
    };
});