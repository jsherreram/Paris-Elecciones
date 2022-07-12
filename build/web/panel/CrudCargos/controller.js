var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/Cargos.html",
        controller: "appController1"
    })
            .when('/Editar/:idCargo', {
                templateUrl: "templates/EditarCargo.html",
                controller: "appController2"
            })
            .when('/Registrar/', {
                templateUrl: "templates/RegistraCargo.html",
                controller: "appController3"
            })
            .otherwise({redirectTo: "/"});
});

app.controller("appController1", ['$scope', 'filterFilter', '$routeParams', 'CargoFactory', function ($scope, filterFilter, $routeParams, CargoFactory) {

        CargoFactory.obtenerCargos(function (data) {
            $scope.cargos = data;
            // create empty search model (object) to trigger $watch on update
            $scope.search = {};
            $scope.resetFilters = function () {
                // needs to be a function or it won't trigger a $watch
                $scope.search = {};
            };
            // pagination controls
            $scope.currentPage = 0;
            $scope.totalItems = $scope.cargos.length;
            $scope.entryLimit = 10; // items per page
            $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
            // $watch search to update pagination
            $scope.$watch('search', function (newVal, oldVal) {
                $scope.filtered = filterFilter($scope.cargos, newVal);
                $scope.totalItems = $scope.filtered.length;
                $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
                $scope.currentPage = 0;
            }, true);
        });
        $scope.atras = function () {
            $window.history.back();
        };
    }]);

app.controller("appController2", ['$scope', 'filterFilter', '$routeParams', 'CargoActualiza', 'CargoFactory', '$window', function ($scope, filterFilter, $routeParams, CargoActualiza, CargoFactory, $window) {
        $scope.idCargo = $routeParams.idCargo;

        CargoFactory.obtenerNivCargo(function (data) {
            $scope.nivelcargo = data;

            CargoFactory.obtenerCargo($scope.idCargo, function (data) {
                $scope.cargo = data;

            });
        });

        $scope.Save = function (reg) {
            var cargo = new CargoActualiza();
            {
                cargo.codigoCargo = reg.codigoCargo;
                cargo.descripcion = reg.descripcion;
                cargo.codigoLogisys = reg.codigoLogisys;
                cargo.nombrecarne = reg.nombrecarne;
                cargo.viaticos = reg.viaticos;
                cargo.funciones = reg.funciones;
                cargo.consalon = reg.consalon;
                cargo.capacitacion = reg.capacitacion;
                cargo.nivel_cargo = reg.nivel_cargo;
                cargo.esSuplente = reg.esSuplente;
                cargo.esicfes = reg.esicfes;
            }
            ;
            cargo.$update();
        };
        $scope.atras = function () {
            $window.history.back();
        };
    }]);

app.controller("appController3", ['$scope', '$location', 'CargoInsert', 'CargoFactory', '$window', function ($scope, $location, CargoInsert, CargoFactory, $window) {

        CargoFactory.obtenerNivCargo(function (data) {
            $scope.nivelcargo = data;
        });
        //$scope.cargo.esSuplente = 0;

        $scope.Insert = function (reg) {
            var cargo = new CargoInsert();
            {
                cargo.codigoCargo = reg.codigoCargo;
                cargo.descripcion = reg.descripcion;
                cargo.codigoLogisys = reg.codigoLogisys;
                cargo.nombrecarne = reg.nombrecarne;
                cargo.viaticos = reg.viaticos;
                cargo.funciones = reg.funciones;
                cargo.consalon = reg.consalon;
                cargo.capacitacion = reg.capacitacion;
                cargo.nivel_cargo = reg.nivel_cargo;
                cargo.esSuplente = reg.esSuplente;
                cargo.esicfes = reg.esicfes;
            }
            ;
            cargo.$insert(function (response) {
                if (response.codigo == '200')
                {
                    $location.path('/Editar/' + cargo.codigoCargo);
                } else {
                    console.log("Error: " + response.descripcion);
                    $scope.error = response.descripcion;
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
