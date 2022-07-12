var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
	$routeProvider.when("/", {
		templateUrl : "templates/formularios.html",
		controller : "appController"
	})
	.when('/formulario/:id', {
            templateUrl : "templates/formulario.html",
            controller : "formularioController"
        })
        .when('/produccion', {
            templateUrl : "templates/produccion.html",
            controller : "produccionController"
        })
	.otherwise({ redirectTo : "/"})
});

app.controller("appController", ['$scope', 'TipoFormaFactory', function tipoFormaController($scope, TipoFormaFactory){
        $scope.formEngine = formulario.global.formEngine;
        TipoFormaFactory.listar(function (data) {
            $scope.formularios = data;
	});                                        
}]);

app.controller("formularioController", ['$scope', '$routeParams', '$modal', 'TipoFormaFactory', 'FormularioFactory', function formularioController($scope, $routeParams, $modal, TipoFormaFactory, FormularioFactory){
	//$scope.formEngine = formulario.global.formEngine;
        //$scope.app = formulario.global.app;
        $scope.global = formulario.global;
        var localVars = {
            selectFormId: -1,
            tipoForma: -1,
            scope: $scope
        };
        TipoFormaFactory.obtener($routeParams.id, function (data) {
            $scope.tipoForma = data.form;
            localVars.tipoForma = data.form;
	});                                        
        FormularioFactory.query($routeParams.id, function (data) {
		$scope.formularios = data;
	});
                                                
        $scope.onclickSuscribir = function (formId) {            
            localVars.selectFormId = formId;
            localVars.scope = $scope;
            var modalInstance = $modal.open({
                templateUrl: 'modal/modal_suscribir.html',
                controller: ModalSuscribirController,
                windowClass: "hmodal-info"
            });
        };
        
        function ModalSuscribirController($scope, $modalInstance) {
            $scope.afiliadoAcepta = false;
            $scope.asesorAcepta = false;
            $scope.aceptarError = false;
            $scope.messageError = "";
            $scope.ok = function () {
                if($scope.afiliadoAcepta && $scope.asesorAcepta) {
                    console.log(localVars.selectFormId);                    
                    FormularioFactory.suscribir(localVars.selectFormId, function (data) {
                        if(data.status === "success") {
                            FormularioFactory.query($routeParams.id, function (data) {                                
                                localVars.scope.formularios = data;
                                $modalInstance.close();
                            });
                        }
                        else {
                            $scope.aceptarError = true;
                            $scope.messageError = "Se ha generado un error por favor intente mas tarde.";
                        }
                    });                                        
                }
                else {
                    $scope.aceptarError = true;
                    $scope.messageError = "Debe confirmar ambos items.";
                }                                
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        };

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

function ModalInstanceCtrl ($scope, $modalInstance) {

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};
