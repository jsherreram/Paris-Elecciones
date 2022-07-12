var app = angular.module("app", ['ngRoute', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

app.config(function($routeProvider){
    $routeProvider.when('/aportante', {
        templateUrl : "templates/aportante.html",
        controller : "AportanteController"
    })
    .when('/cotizante', {
        templateUrl : "templates/tipos-formulario.html",
        controller : "tiposFormularioController"
    });
});

app.controller("formularioController", ['$scope', '$routeParams', 'FormularioFactory', function formularioController($scope, $routeParams, FormularioFactory){	
        FormularioFactory.query($routeParams.id, function (data) {
            $scope.formularios = data;
	})
}]);

app.controller("AportanteController", ['$scope', '$routeParams', 'CatalogoAportanteFactory', function AportanteController($scope, $routeParams, CatalogoAportanteFactory) {        
    $scope.model = {documento: undefined};
    $scope.busqueda = false;
    $scope.onclickBuscar = function() {
        $scope.busqueda = true;
        if($scope.model !== undefined) {            
            CatalogoAportanteFactory.query($scope.model.documento, function (data) {
                $scope.catalogo = data;            
            });
        }
        else {
            $scope.catalogo = [];
        }
    };
    
    $scope.onclickFiltroSemana = function() {
        var fecha = calcularFechaSemana();
        ProduccionFactory.query(fecha.fechaInicial, fecha.fechaFinal, function (data) {
            $scope.filtro = 2;
            $scope.reporte = data;
            $scope.fechaInicial = fecha.fechaInicial;
            $scope.fechaFinal = fecha.fechaFinal;
        });
    };
    
    $scope.onclickFiltroMes = function() {
        var fecha = calcularFechaMes();
        ProduccionFactory.query(fecha.fechaInicial, fecha.fechaFinal, function (data) {
            $scope.filtro = 3;
            $scope.reporte = data;
            $scope.fechaInicial = fecha.fechaInicial;
            $scope.fechaFinal = fecha.fechaFinal;
        });
    };
    
    $scope.onclickFiltroAnio = function() {
        var fecha = calcularFechaAnio();
        ProduccionFactory.query(fecha.fechaInicial, fecha.fechaFinal, function (data) {
            $scope.filtro = 4;
            $scope.reporte = data;
            $scope.fechaInicial = fecha.fechaInicial;
            $scope.fechaFinal = fecha.fechaFinal;
        });
    };        
}]);