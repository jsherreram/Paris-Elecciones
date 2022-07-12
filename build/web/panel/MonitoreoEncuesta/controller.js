var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
    
	$routeProvider.when("/", 
        { 
            templateUrl : "templates/SeleccionarEvento.html",
            controller : "appControllersSeleccionarEvento"
        })
        .otherwise({ redirectTo : "/"});
});


app.controller("appControllersSeleccionarEvento", ['$scope', '$routeParams', '$location', 'EventoFactory', '$window', function seguimientoController($scope, $routeParams, $location, EventoFactory, $window) {

        var idPrueba = 0;
        
        //tipo reporte N=NOMBRAMIENTO, A=ASISTENCIA
        $scope.tipoReporte = $routeParams.tipoReporte;

       
        EventoFactory.obtenerPruebas(
                function(data) {
                    $scope.pruebas = data;
                   
                });

        $scope.pruebaSelected = function() {
            idPrueba = $scope.pruebaSelect[0];
            EventoFactory.obtenerEventos(
                    idPrueba,                    
                    function(data) {
                        $scope.eventos = data;
                        $scope.somethingHere=data.length>0?data[0].codigoEvento:-1;

                    });
        };

        $scope.validarForm = function() {
            
            if (idPrueba == 0) {
                alert('Por favor seleccione una prueba');
                return false;
            }
            
            if($scope.somethingHere==-1){
                alert('Por favor seleccione un evento');
                return false;
            }

            return true;
        }
}]);

