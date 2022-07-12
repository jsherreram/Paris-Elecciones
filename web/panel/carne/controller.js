var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
	$routeProvider.when("/", {
		templateUrl : "templates/seleccionarArchivo.jsp",
		controller : "appControllerFiles"
	})
       
	.otherwise({ redirectTo : "/"});
});

function leerCookie(nombre) {
    var lista = document.cookie.split(";");
    for (i in lista) {
        var busca = lista[i].search(nombre);
        if (busca > -1) {micookie=lista[i]}
        }
    var igual = micookie.indexOf("=");
    var valor = micookie.substring(igual+1);
    return valor;
}

app.controller("appControllerFiles", ['$scope', '$routeParams', function ($scope, $routeParams){
      //$scope.idPersona =  $routeParams.idPersona;
      //$scope.idDpto = $routeParams.idDpto;
      //$scope.idEmpleado = $routeParams.idEmpleado;
}]);


