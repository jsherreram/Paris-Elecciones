var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
    
	$routeProvider.when("/Consulta/:tipoReporte", 
        { 
            templateUrl : "templates/SeleccionarEvento.html",
            controller : "appControllersSeleccionarEvento"
        })
	.when('/ConsolidadoCargosNodo/:tipoReporte/:tipoConsulta/:idEvento',{
            templateUrl : "templates/ConsolidadoCargos.jsp",
            controller : "appController"
        })
	.when('/Nacional/:tipoReporte/:tipoConsulta/:idEvento/:codigoCargo',{
            templateUrl : "templates/ConsolidadoCargos.jsp", 
            controller : "appController"
        })
	.when('/Departamental/:tipoReporte/:tipoConsulta/:idEvento/:codigoCargo', {
            templateUrl : "templates/ConsolidadoCargos.jsp",
            controller : "appController"
        })
	.when('/Puesto/:tipoReporte/:tipoConsulta/:idEvento/:codigoCargo/:idDepartamento/:idMunicipio', {
            templateUrl : "templates/ConsolidadoCargos.jsp",
            controller : "appController"
        })
	.when('/Ubicacion/:tipoReporte/:tipoConsulta/:idEvento/:codigoCargo/:idDepartamento/:idMunicipio/:idPuesto', {
            templateUrl : "templates/Ubicacion.jsp",
            controller : "appController"
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


app.controller("appControllersSeleccionarEvento", ['$scope', '$routeParams', '$location', 'EventoFactory', '$window', function seguimientoController($scope, $routeParams, $location, EventoFactory, $window) {

        var idPrueba = 0;
        
        //tipo reporte N=NOMBRAMIENTO, A=ASISTENCIA
        $scope.tipoReporte = $routeParams.tipoReporte;

       
        EventoFactory.obtenerPruebas(
                function(data) {
                    $scope.pruebas = data;
                   
                });

        $scope.pruebaSelected = function() {
            idPrueba = $scope.pruebaSelect[0];
            EventoFactory.obtenerEventos(
                    idPrueba,                    
                    function(data) {
                        $scope.eventos = data;
                        $scope.somethingHere=data.length>0?data[0].codigoEvento:-1;

                    });
        };

        $scope.validarForm = function() {
            
            if (idPrueba == 0) {
                alert('Por favor seleccione una prueba');
                return false;
            }
            
            if($scope.somethingHere==-1){
                alert('Por favor seleccione un evento');
                return false;
            }

            return true;
        }
}]);


app.controller("appController", ['$scope', '$routeParams', '$location','SeguimientoFactory','Empleado', function seguimientoController($scope, $routeParams, $location, SeguimientoFactory, Empleado){
    
    var idPrueba = 0;
    if($routeParams.idEvento !== undefined)
    {    
        SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
            $scope.evento = data;
	});
    }   
    
    if($routeParams.tipoReporte ==="N" || $routeParams.tipoReporte ==="A")
    {
        if($routeParams.codigoCargo !== undefined)
        {    
            SeguimientoFactory.getCargo($routeParams.codigoCargo, function (data) {
                $scope.cargo = data;
            });
        }
    }

    if($routeParams.idDepartamento !== undefined)
    {    
        SeguimientoFactory.getDepartamento($routeParams.idDepartamento, function (data) {
            $scope.departamento = data;
            
        
	});
    }   

    if($routeParams.idMunicipio !== undefined)
    {    
        SeguimientoFactory.getMunicipio($routeParams.idDepartamento,$routeParams.idMunicipio, function (data) {
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
        
        //tipo reporte N=NOMBRAMIENTO, A=ASISTENCIA
        $scope.tipoReporte = $routeParams.tipoReporte;
        
        //Consulta X cargos
        if (tipoConsulta === "1")
        {
            SeguimientoFactory.nombramientoCargosNodo($routeParams.idEvento, function (data) {
                $scope.registros = data;
                if($scope.tipoReporte ==="N" || $scope.tipoReporte ==="NT")
                {
                    $scope.titulo = "Nombramiento Nodo";
                }else{
                    $scope.titulo = "Asistencia Nodo";
                }
//                $scope.url_regresar = "#/Consulta/"+$routeParams.tipoReporte;
                
                $scope.url_detalle = "#/Departamental/"+$routeParams.tipoReporte+"/3/"+$routeParams.idEvento+"/";
            });
            
            
            Empleado.getEmpleadoPruebaSesion(function (data) {
            idPrueba = data.pruebaActual;
                //Recuperar el departamento al que tiene permisos el usuario
                var usuario = leerCookie("APP-TOKEN");
                SeguimientoFactory.getUsuarioDepartamento(usuario,idPrueba, function (data) {
                    $scope.dptoUsuario = data[0].codigo;
                        SeguimientoFactory.getDepartamento($scope.dptoUsuario, function (data) {
                            $scope.departamento = data;
                        });
                });
            });
            
        }

        //Consulta Cargo X Nodos
        if (tipoConsulta === "2")
        {
            SeguimientoFactory.listarDepartamentos($routeParams.idEvento, $routeParams.codigoCargo, function (data) {
                $scope.registros = data;
                if($scope.tipoReporte ==="N" || $scope.tipoReporte ==="NT")
                {
                    $scope.titulo = "Nombramiento por Nodo";
                }else{
                    $scope.titulo = "Asistencia por Nodo";
                }
                
                if($routeParams.tipoReporte == "N" || $routeParams.tipoReporte == "A")
                {
                    $scope.url_regresar = "#/ConsolidadoCargos/"+$routeParams.tipoReporte+"/1/"+$routeParams.idEvento;
                }else
                {
//                    $scope.url_regresar = "#/Consulta/"+$routeParams.tipoReporte;
                }
                
                $scope.url_detalle = "#/Departamental/"+$routeParams.tipoReporte+"/3/"+$routeParams.idEvento+"/"+ $routeParams.codigoCargo +"/";
            });
        }

        //Consulta Cargo X Nodos x Municipios
        if (tipoConsulta === "3")
        {
            Empleado.getEmpleadoPruebaSesion(function (data) {
            idPrueba = data.pruebaActual;
                //Recuperar el departamento al que tiene permisos el usuario
                var usuario = leerCookie("APP-TOKEN");
                SeguimientoFactory.getUsuarioDepartamento(usuario,idPrueba, function (data) {
                    $scope.dptoUsuario = data[0].codigo;

                        SeguimientoFactory.getDepartamento($scope.dptoUsuario, function (data) {
                            $scope.departamento = data;
                        });
                        SeguimientoFactory.listarMunicipios($routeParams.idEvento, $scope.dptoUsuario, $routeParams.codigoCargo, function (data) {
                            $scope.registros = data;
                            if($scope.tipoReporte ==="N" || $scope.tipoReporte ==="NT")
                            {
                                $scope.titulo = "Nombramiento por Municipio";
                            }else{
                                $scope.titulo = "Asistencia por Municipio";
                            }

                            if($routeParams.tipoReporte == "N" || $routeParams.tipoReporte == "A")
                            {
                                $scope.url_regresar = "#/ConsolidadoCargosNodo/"+$routeParams.tipoReporte+"/1/"+$routeParams.idEvento;
                            }else
                            {
//                                $scope.url_regresar = "#/Consulta/"+$routeParams.tipoReporte;
                            }
                            $scope.url_detalle = "#/Puesto/"+$routeParams.tipoReporte+"/4/"+$routeParams.idEvento+"/"+ $routeParams.codigoCargo +"/"+$scope.dptoUsuario+"/";
                        });
                });
            });
        }

        //Consulta Cargo X Nodos x Municipios x puestos
        if (tipoConsulta === "4")
        {
            SeguimientoFactory.listarPuesto($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.codigoCargo,function (data) {
                $scope.registros = data;
                if($scope.tipoReporte ==="N" || $scope.tipoReporte ==="NT")
                {
                    $scope.titulo = "Nombramiento por Puesto";
                }else{
                    $scope.titulo = "Asistencia por Puesto";
                }
                $scope.url_regresar = "#/Departamental/"+$routeParams.tipoReporte+"/3/"+$routeParams.idEvento+"/"+ $routeParams.codigoCargo;
                $scope.url_detalle = "#/Ubicacion/"+$routeParams.tipoReporte+"/5/"+$routeParams.idEvento+"/"+ $routeParams.codigoCargo +"/"+$routeParams.idDepartamento+"/"+$routeParams.idMunicipio+"/";
            });
        }
        
        //Consulta detallado
        if (tipoConsulta === "5")
        {
            SeguimientoFactory.listarUbicacion($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.codigoCargo,$routeParams.idPuesto,function (data) {
                $scope.registros = data;
                $scope.url_regresar = "#/Puesto/"+$routeParams.tipoReporte+"/4/"+$routeParams.idEvento+"/"+ $routeParams.codigoCargo +"/"+$routeParams.idDepartamento+"/"+$routeParams.idMunicipio;
                $scope.url_detalle = "";
            });
        }
        
    };
    
    
}]);


