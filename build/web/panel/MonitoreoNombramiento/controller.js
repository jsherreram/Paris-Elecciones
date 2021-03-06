var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
    
	$routeProvider.when("/Consulta/:tipoReporte",{ 
            templateUrl : "templates/SeleccionarEvento.html",
            controller : "appControllersSeleccionarEvento"
        })
	.when('/ConsolidadoCargos/:tipoReporte/:tipoConsulta/:idEvento/:idPrueba',{
            templateUrl : "templates/ConsolidadoCargos.jsp",
            controller : "appController"
        })
	.when('/ConsolidadoCargos/:tipoReporte/:tipoConsulta/:idEvento/:idDepartamento/:idPrueba', {
            templateUrl : "templates/ConsolidadoCargos.jsp",
            controller : "appController"
        })
	.when('/Nacional/:tipoReporte/:tipoConsulta/:idEvento/:codigoCargo/:idPrueba',{
            templateUrl : "templates/ConsolidadoCargos.jsp", 
            controller : "appController"
        })
	.when('/Nacional/:tipoReporte/:tipoConsulta/:idPrueba',{
            templateUrl : "templates/ConsolidadoCargos.jsp", 
            controller : "appController"
        })
	.when('/Departamental/:tipoReporte/:tipoConsulta/:idEvento/:codigoCargo/:idDepartamento/:idPrueba', {
            templateUrl : "templates/ConsolidadoCargos.jsp",
            controller : "appController"
        })
	.when('/Departamental/:tipoReporte/:tipoConsulta/:idDepartamento/:idPrueba', {
            templateUrl : "templates/ConsolidadoCargos.jsp",
            controller : "appController"
        })
	.when('/Puesto/:tipoReporte/:tipoConsulta/:idEvento/:codigoCargo/:idDepartamento/:idMunicipio/:idPrueba', {
            templateUrl : "templates/ConsolidadoCargos.jsp",
            controller : "appController"
        })
	.when('/Ubicacion/:tipoReporte/:tipoConsulta/:idEvento/:codigoCargo/:idDepartamento/:idMunicipio/:idPuesto/:idPrueba', {
            templateUrl : "templates/Ubicacion.jsp",
            controller : "appController"
        })
	.when('/Ubicacion/:tipoReporte/:tipoConsulta/:idDepartamento/:idPrueba', {
            templateUrl : "templates/Ubicacion.jsp",
            controller : "appController"
        })
        .otherwise({ redirectTo : "/"});
});


app.controller("appControllersSeleccionarEvento", ['$scope', '$routeParams', '$location', 'EventoFactory', '$window', function seguimientoController($scope, $routeParams, $location, EventoFactory, $window) {

        var idPrueba = 0;
        
        //tipo reporte N=NOMBRAMIENTO, A=ASISTENCIA, S=SITIO    
        $scope.tipoReporte = $routeParams.tipoReporte;
       
        EventoFactory.obtenerPruebas(
                function(data) {
                    $scope.pruebas = data;
                });

        $scope.pruebaSelected = function() {
            idPrueba = $scope.pruebaSelect[0];
            if ($scope.tipoReporte != 'S'){
                EventoFactory.obtenerEventos(
                        idPrueba,                    
                        function(data) {
                            $scope.eventos = data;
                            $scope.somethingHere=data.length>0?data[0].codigoEvento:-1;
                        });
                }
        };

        $scope.validarForm = function() {
            if (idPrueba == 0) {
                alert('Por favor seleccione una prueba');
                return false;
            }
            
            if($scope.tipoReporte!='S'){
                if($scope.somethingHere==-1){
                    alert('Por favor seleccione un evento');
                    return false;
                }
            }
            return true;
        }
}]);


app.controller("appController", ['$scope', '$routeParams', '$location','SeguimientoFactory', '$window', function seguimientoController($scope, $routeParams, $location, SeguimientoFactory, $window){
    
    $scope.idPrueba=0;
    var prueba=$routeParams.idPrueba;

    prueba  =   prueba.toString().replace("[", "", "gi");
    prueba  =   prueba.toString().replace("]", "", "gi");
    $scope.idPrueba = prueba.toString().replace('"','', "gi");

    if($routeParams.idEvento !== undefined)
    {   SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
            $scope.evento = data;
	});
    }   
    
    if($routeParams.tipoReporte ==="N" || $routeParams.tipoReporte ==="A")
    {    if($routeParams.codigoCargo !== undefined)
        {   SeguimientoFactory.getCargo($routeParams.codigoCargo, function (data) {
                $scope.cargo = data;
            });
        }
    }

    if($routeParams.idDepartamento !== undefined)
    {   SeguimientoFactory.getDepartamento($routeParams.idDepartamento, function (data) {
            $scope.departamento = data;
	});
    }   

    if($routeParams.idMunicipio !== undefined)
    {   SeguimientoFactory.getMunicipio($routeParams.idDepartamento,$routeParams.idMunicipio, function (data) {
            $scope.municipio = data;
	});
    }   
    
    $scope.listar = function() {
        
        var tipoConsulta = $routeParams.tipoConsulta;
        $scope.tipoConsulta = $routeParams.tipoConsulta;
        $scope.codigoPuesto = $routeParams.idPuesto;
        
        $scope.url_regresar = "";
        $scope.url_detalle = "";
        $scope.titulo = "";
        $scope.nodo = "";
        
        //tipo reporte N=NOMBRAMIENTO, A=ASISTENCIA, S=SITIO
        $scope.tipoReporte = $routeParams.tipoReporte;
        
        //Consulta X cargos
        if (tipoConsulta === "1")
        {   if($scope.tipoReporte!="CA"){
                SeguimientoFactory.listarConsolidadoNacional($routeParams.idEvento, function (data) {
                    $scope.registros = data;
                    if($scope.tipoReporte ==="N" || $scope.tipoReporte ==="NT")
                    {      $scope.titulo = "Nombramiento Nacional";
                    }else{ $scope.titulo = "Asistencia Nacional";
                    }
                });
                $scope.url_detalle = "#/Nacional/"+$routeParams.tipoReporte+"/2/"+$routeParams.idEvento+"/";
            }if($scope.tipoReporte === "CA"){
                SeguimientoFactory.capacitacionCargosNacionalDpto($routeParams.idEvento,$routeParams.idDepartamento,prueba, function (data) {
                    $scope.registros = data;
                    $scope.titulo = "Seguimiento Capacitaciones, Departamento";
                    $scope.url_regresar = "#/Nacional/"+$routeParams.tipoReporte+"/2/"+$routeParams.idEvento+"/TODOS/"+$scope.idPrueba;

                });
                /*$scope.url_detalle = "#/Nacional/"+$routeParams.tipoReporte+"/2/"+$routeParams.idEvento+"/";*/
            }
        }

        //Consulta Cargo X Nodos
        if (tipoConsulta === "2")
        {  $scope.url_detalle = "#/Departamental/"+$routeParams.tipoReporte+"/3/"+$routeParams.idEvento+"/"+$routeParams.codigoCargo+"/";
            if($scope.tipoReporte!="CA"){
                SeguimientoFactory.listarDepartamentos($routeParams.idEvento, $routeParams.codigoCargo, function (data) {
                    $scope.registros = data;
                    if($scope.tipoReporte ==="N" || $scope.tipoReporte ==="NT")
                    {       $scope.titulo = "Nombramiento por Departamento";
                    }else{  $scope.titulo = "Asistencia por Departamento";
                    }
                    if( $routeParams.tipoReporte == "N" || $routeParams.tipoReporte == "A") {
                    $scope.url_regresar = "#/ConsolidadoCargos/"+$routeParams.tipoReporte+"/1/"+$routeParams.idEvento+"/"+$scope.idPrueba;
                    }
                });
            }if($scope.tipoReporte === "CA"){
                SeguimientoFactory.listarDepartamentosCapacitacion($routeParams.idPrueba, $routeParams.codigoCargo, function (data) {
                    $scope.registros    = data;
                    $scope.titulo       = "Seguimiento Capacitaciones por Departamentos";
                    $scope.url_detalle = "#/ConsolidadoCargos/"+$routeParams.tipoReporte+"/1/"+$routeParams.idEvento+"/";
                });
            }
        }

        //Consulta Cargo X Nodos x Municipios
        if (tipoConsulta === "3")
        {
            $scope.url_regresar = "#/Nacional/"+$routeParams.tipoReporte+"/2/"+$routeParams.idEvento+"/"+$routeParams.codigoCargo+"/"+$scope.idPrueba;
            $scope.url_detalle  = "#/Puesto/"+$routeParams.tipoReporte+"/4/"+$routeParams.idEvento+"/"+ $routeParams.codigoCargo+"/"+$routeParams.idDepartamento+"/";
            if($scope.tipoReporte!="S"){
                SeguimientoFactory.listarMunicipios($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.codigoCargo, function (data) {
                    $scope.registros = data;
                    if($scope.tipoReporte ==="N" || $scope.tipoReporte ==="NT")
                    {     $scope.titulo = "Nombramiento por Municipio";
                    }else{$scope.titulo = "Asistencia por Municipio";
                    }
                });
            }else{
                SeguimientoFactory.listarSitiosAll($routeParams.idDepartamento,$scope.idPrueba, function (data) {
                    $scope.registros= data;
                    $scope.titulo   = "Confirmación de Sitios, por Departamento";
                });
                $scope.url_regresar = "#/Nacional/"+$routeParams.tipoReporte+"/2/"+$scope.idPrueba;
                $scope.url_detalle  = "";
            }
        }

        //Consulta Cargo X Nodos x Municipios x puestos
        if (tipoConsulta === "4")
        {
            if($scope.tipoReporte!="S"){
                SeguimientoFactory.listarPuesto($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.codigoCargo,function (data) {
                    $scope.registros = data;
                    if($scope.tipoReporte ==="N" || $scope.tipoReporte ==="NT")
                    {   $scope.titulo = "Nombramiento por Puesto";
                    }else{
                        $scope.titulo = "Asistencia por Puesto";
                    }
                    $scope.url_detalle = "#/Ubicacion/"+$routeParams.tipoReporte+"/5/"+$routeParams.idEvento+"/"+$routeParams.codigoCargo+"/"+$routeParams.idDepartamento+"/"+$routeParams.idMunicipio+"/";
                });
            }else{
                SeguimientoFactory.listarPuestoSitios($scope.idPrueba, function (data) {
                    $scope.registros = data;
                    $scope.titulo = "Sitios";
                    $scope.url_detalle = "";
                });
            }
            $scope.url_regresar= "#/Departamental/"+$routeParams.tipoReporte+"/3/"+$routeParams.idEvento+"/"+$routeParams.codigoCargo+"/"+$routeParams.idDepartamento+"/"+$scope.idPrueba;
        }
        
        //Consulta detallado
        if (tipoConsulta === "5")
        {
            SeguimientoFactory.listarUbicacion($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.codigoCargo,$routeParams.idPuesto,function (data) {
                $scope.registros = data;
                $scope.url_regresar = "#/Puesto/"+$routeParams.tipoReporte+"/4/"+$routeParams.idEvento+"/"+ $routeParams.codigoCargo +"/"+$routeParams.idDepartamento+"/"+$routeParams.idMunicipio+"/"+$scope.idPrueba;
                $scope.url_detalle = "";
            });
        }
    };
}]);


