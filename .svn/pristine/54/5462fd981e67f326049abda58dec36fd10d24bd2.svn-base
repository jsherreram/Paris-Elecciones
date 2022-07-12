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

app.controller("appController", ['$scope','SalonesFactory', function ($scope, SalonesFactory){
            SalonesFactory.obtenerPruebas(function (data) {
                        $scope.pruebas = data;        
        });
}]);

app.controller("appControllerStatus", ['$scope', 'SalonesFactory', '$interval', function ($scope, SalonesFactory, $interval){
        //consultar los datos a la entrada
            SalonesFactory.listarStatus(function (data) {
                        $scope.status = data;        
        });
        //refrescar cada 10 segundos
        var timer=$interval(function(){
            SalonesFactory.listarStatus(function (data) {
                        $scope.status = data;        
            });
        },5000);
}]);