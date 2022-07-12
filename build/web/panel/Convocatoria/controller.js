var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

app.config(function($routeProvider){
	$routeProvider.when("/Cargar", {
		templateUrl : "templates/seleccionarArchivo.jsp",
		controller : "appController"
	})
	.when('/', {
            templateUrl : "templates/StatusCargue.html",
            controller : "appControllerStatus"
        })
        
	.otherwise({ redirectTo : "/"});
});

app.controller("appController", ['$scope','ConvocatoriaFactory', function ($scope, ConvocatoriaFactory){
        
        
         ConvocatoriaFactory.listarPruebasEnConvocatoria(function (data) {
                    $scope.pruebas = data;        
        })
}]);

app.controller("appControllerStatus", ['$scope', 'ConvocatoriaFactory', '$interval', function ($scope, ConvocatoriaFactory, $interval){

        //consultar los datos a la entrada
        ConvocatoriaFactory.listarStatus(function (data) {
                    $scope.status = data;        
        });

        
        //refrescar cada 10 segundos
        var timer=$interval(function(){
            ConvocatoriaFactory.listarStatus(function (data) {
                        $scope.status = data;        
            });
        },5000);
        
}]);

