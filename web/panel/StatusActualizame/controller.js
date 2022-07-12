var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert', 'angularUtils.directives.dirPagination']);


app.config(['$httpProvider', function ($httpProvider) {

        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/StatusActualizame.jsp",
        controller: "appController1"
    });
});

app.controller("appController1", ['$scope', 'Actualizame', 'Empleado','sweet', function ($scope, Actualizame, Empleado,sweet) {
        var idPrueba = 0;
        $scope.search = "";
        $scope.departamentoArray = [];
        $scope.municipioArray = [];
        $scope.estadoArray = [];
        $scope.fechaArray = [];
        $scope.currentPage = 1;
        $scope.pageSize = 20;
        $scope.filas = 0;
        var aux = 0;
        
        $("#modalLoading").modal('show');
        
        Empleado.getEmpleadoPruebaSesion(function (data) {
            idPrueba = data.pruebaActual;
            Actualizame.getPersonaSesion(function (data) {
                $scope.persona = data;
                $scope.nrodoc = $scope.persona.nrodoc;
                Actualizame.ConsultaActualizame($scope.persona.nrodoc,idPrueba, function (data) {
                    $scope.ultima_actualizacion = data;

                $scope.ultima_actualizacion.forEach(function (registro) {
                    aux = aux + 1;
                    if ($scope.departamentoArray.indexOf(registro.nombreDepartamento) === -1) {
                        $scope.departamentoArray.push(registro.nombreDepartamento);
                    }
                    if ($scope.municipioArray.indexOf(registro.nombreMunicipio) === -1) {
                        $scope.municipioArray.push(registro.nombreMunicipio);
                    }
                    if ($scope.estadoArray.indexOf(registro.fechadia) === -1) {
                        $scope.estadoArray.push(registro.fechadia);
                    }
                });
                $("#modalLoading").modal('hide');
                });
            });
        });

        $scope.atras = function () {
            $window.history.back();
        };
    }]);
