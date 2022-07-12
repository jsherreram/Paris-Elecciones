var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);
app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación x
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/buscarFirmaPersonal.html",
        controller: "appControllerBuscarPersonas",
    })
            .otherwise({redirectTo: "/"});
});

app.controller("appControllerBuscarPersonas", ['$scope', '$routeParams', 'BuscarFirmas', function seguimientoController($scope, $routeParams, BuscarFirmas) {

        idprueba = 0;
       

        $scope.persona = "";
        BuscarFirmas.getPersonaSesion(function (data) {
            $scope.persona = data;
             BuscarFirmas.obtenerPruebas( $scope.persona.idEmpleado,
                function (data) {
                    $scope.pruebas = data;
                     $scope.buscar($scope.pruebas[0].idprueba);
                });
                
        });
      
        $scope.buscar = function (idPrueba) {
            BuscarFirmas.buscarPersonasFirmas(idPrueba, $scope.persona.idEmpleado,
                    function (data) {
                        $scope.personas = data;

                    });
        };
        
         $scope.enrolar = function (idEmpleado) {

           openPopPupEnrolar(idEmpleado);
        };


    }]);


