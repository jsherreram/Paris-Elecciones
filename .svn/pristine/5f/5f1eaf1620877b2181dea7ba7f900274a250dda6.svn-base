var app = angular.module("app", ['ngRoute', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

app.config(function($routeProvider){
    $routeProvider.when("/", {
        templateUrl : "templates/indice-reportes.html",
    })
    .when('/produccion', {
        templateUrl : "templates/produccion.html",
        controller : "produccionController"
    })
    .when('/tipos-formulario', {
        templateUrl : "templates/tipos-formulario.html",
        controller : "tiposFormularioController"
    })
    .when('/estados', {
        templateUrl : "templates/estados.html",
    })
    .when('/formulario/:id', {
        templateUrl : "templates/formulario.html",
        controller : "formularioController"
    })
    .otherwise({ redirectTo : "/"})
})

app.controller("formularioController", ['$scope', '$routeParams', 'FormularioFactory', function formularioController($scope, $routeParams, FormularioFactory){
	FormularioFactory.query($routeParams.id, function (data) {
            $scope.formularios = data;
	})
}]);

app.controller("tiposFormularioController", ['$scope', '$routeParams', 'TiposFormularioFactory', function tiposFormularioController($scope, $routeParams, TiposFormularioFactory) {    
    
    $scope.onclickFiltroHoy = function() {
        var fecha = calcularFechaHoy();
        TiposFormularioFactory.query(fecha.fechaInicial, fecha.fechaFinal, function (data) {
            $scope.filtro = 1;
            $scope.reporte = data;
            $scope.fechaInicial = fecha.fechaInicial;
            $scope.fechaFinal = fecha.fechaFinal;
            
        });
    };
    
    $scope.onclickFiltroSemana = function() {
        var fecha = calcularFechaSemana();
        TiposFormularioFactory.query(fecha.fechaInicial, fecha.fechaFinal, function (data) {
            $scope.filtro = 2;
            $scope.reporte = data;
            $scope.fechaInicial = fecha.fechaInicial;
            $scope.fechaFinal = fecha.fechaFinal;
        });
    };
    
    $scope.onclickFiltroMes = function() {
        var fecha = calcularFechaMes();
        TiposFormularioFactory.query(fecha.fechaInicial, fecha.fechaFinal, function (data) {
            $scope.filtro = 3;
            $scope.reporte = data;
            $scope.fechaInicial = fecha.fechaInicial;
            $scope.fechaFinal = fecha.fechaFinal;
        });
    };
    
    $scope.onclickFiltroAnio = function() {
        var fecha = calcularFechaAnio();
        TiposFormularioFactory.query(fecha.fechaInicial, fecha.fechaFinal, function (data) {
            $scope.filtro = 4;
            $scope.reporte = data;
            $scope.fechaInicial = fecha.fechaInicial;
            $scope.fechaFinal = fecha.fechaFinal;
        });
    };
    // Por defecto se cargan las estadisticas del dia
    $scope.onclickFiltroHoy();
    
}]);

app.controller("produccionController", ['$scope', '$routeParams', 'ProduccionFactory', function produccionController($scope, $routeParams, ProduccionFactory) {    
    
    $scope.onclickFiltroHoy = function() {
        var fecha = calcularFechaHoy();
        ProduccionFactory.query(fecha.fechaInicial, fecha.fechaFinal, function (data) {
            $scope.filtro = 1;
            $scope.reporte = data;
            $scope.fechaInicial = fecha.fechaInicial;
            $scope.fechaFinal = fecha.fechaFinal;
        });
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
    // Por defecto se cargan las estadisticas del dia
    $scope.onclickFiltroHoy();
    
}]);