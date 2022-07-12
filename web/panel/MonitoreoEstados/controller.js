
var app = angular.module("app", ['ngRoute', 'ui.bootstrap','hSweetAlert', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){

    $routeProvider.when("/", {
		templateUrl : "templates/SeleccionarEvento.html",
		controller : "appControllersSeleccionarEvento"
    })

    $routeProvider.when("/MonitoreoEventoNacional/:idPrueba", {
		templateUrl : "templates/GestionNacional.html",
		controller : "appControllerNacional"
    })
        
    $routeProvider.when("/MonitoreoEvento/:idPrueba", {
		templateUrl : "templates/GestionInicial.html",
		controller : "appController as controller"
    })

    .otherwise({ redirectTo : "/"});
});

app.controller("appController", ['$scope', '$routeParams', '$location','nodosFactory', function seguimientoController($scope, $routeParams, $location, nodosFactory){
    this.nodos = [];
    this.nodosEstado = [];
    var self = this;
    nodosFactory.listarNodos(function(data){
        self.nodos = data;        
    })
    $scope.idPrueba = $routeParams.idPrueba;
    
    $scope.nodoEscogido = function(){       
        var substr = "";
        nodosFactory.listarEstado($scope.idPrueba,$scope.nodoEscogidoSelect,function(data){
            self.nodosEstado = data;                   
            for(i = 0; i < self.nodosEstado.length; i++){
                substr = (self.nodosEstado[i].asignada * 100 / self.nodosEstado[i].total)+ "";
                self.nodosEstado[i].porcentajeAsignacion = substr.substring(0,5) + "%";
            }
        })
    }
}]);

app.controller("appControllerNacional", ['$scope', '$routeParams', '$location','nodosFactory', 'Campagna','sweet', function seguimientoController($scope, $routeParams, $location, nodosFactory, Campagna, sweet){
    
    $scope.idPrueba = $routeParams.idPrueba;
    var substr = "";
    nodosFactory.listarSeguimientoEstado($scope.idPrueba,function(data){
        $scope.nodosEstado = data;                   
        for(i = 0; i < $scope.nodosEstado.length; i++){
            substr = ($scope.nodosEstado[i].asignada * 100 / $scope.nodosEstado[i].total)+ "";
            $scope.nodosEstado[i].porcentajeAsignacion = substr.substring(0,5) + "%";
        }
    });
    
     $scope.insertarACampagna = function () {
         
        Campagna.insertarDatosACampagna({idPrueba:$scope.idPrueba, idCampagna: $scope.campagna.idcampagna}, function (response) {
            if (response.codigo === '200') {
                sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
            } else {
                sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
            }

        });                 
         
     };
    
    
}]);

    
app.controller("appControllersSeleccionarEvento", ['$scope', '$routeParams', '$location', 'EventoFactory', '$window', function seguimientoController($scope, $routeParams, $location, EventoFactory, $window) {

        var idPrueba = 0;
        
        $scope.tipoReporte = "MonitoreoEventoNacional";

       
        EventoFactory.obtenerPruebas(
                function(data) {
                    $scope.pruebas = data;
                   
                });

        $scope.pruebaSelected = function() {
            idPrueba = $scope.pruebaSelect[0];
        };

        $scope.validarForm = function() {
            
            if (idPrueba == 0) {
                alert('Por favor seleccione una prueba');
                return false;
            }
            
            return true;
        }
}]);