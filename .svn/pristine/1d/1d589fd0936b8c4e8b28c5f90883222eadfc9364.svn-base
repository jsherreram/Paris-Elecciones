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

app.controller("appController", ['$scope','AplicacionFactory', function ($scope, AplicacionFactory){
            AplicacionFactory.obtenerPruebas(function (data) {
                        $scope.pruebas = data;        
        });
}]);

app.controller("appControllerStatus", ['$scope', 'AplicacionFactory', '$interval', function ($scope, AplicacionFactory, $interval){
        //consultar los datos a la entrada
            AplicacionFactory.listarStatus(function (data) {
                        $scope.status = data;        
        });
        //refrescar cada 10 segundos
        var timer=$interval(function(){
            AplicacionFactory.listarStatus(function (data) {
                        $scope.status = data;        
            });
        },5000);
}]);