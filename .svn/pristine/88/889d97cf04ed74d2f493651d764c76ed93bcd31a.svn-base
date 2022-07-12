
var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
	$routeProvider.when("/", {
            templateUrl : "templates/inicio.html",
            controller : "appController"
	})

        .when('/Menu/:idMenu', {
		templateUrl : "templates/inicio.html",
		controller : "appController"
        })

        .otherwise({ redirectTo : "/"});
});


app.controller("appController", ['$scope','$routeParams', function encuestaController($scope, $routeParams){
        
               
        
}]);


