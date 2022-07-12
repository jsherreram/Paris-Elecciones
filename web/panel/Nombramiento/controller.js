
var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
	$routeProvider.when("/", {
		templateUrl : "templates/Evento.html",
		controller : "appController"
	})
	.when('/Nacional/:idEvento', {
            templateUrl : "templates/Nacional.jsp",
            controller : "appController"
        })
	.when('/Departamental/:idEvento/:idDepartamento', {
            templateUrl : "templates/Departamental.html",
            controller : "appController"
        })
	.when('/Municipal/:idEvento/:idDepartamento/:idMunicipio', {
            templateUrl : "templates/Municipal.html",
            controller : "appController"
        })
	.when('/Zonal/:idEvento/:idDepartamento/:idMunicipio/:idCargo', {
            templateUrl : "templates/Zonal.jsp",
            controller : "appController"
        })
	.when('/Puesto/:idEvento/:idDepartamento/:idMunicipio/:idCargo/:idZona/:nombreZona', {
            templateUrl : "templates/Puesto.jsp",
            controller : "appController"
        })
	.when('/Ubicacion/:idEvento/:idDepartamento/:idMunicipio/:idCargo/:idZona/:nombreZona/:idPuesto/:nombrePuesto/', {
            templateUrl : "templates/Ubicacion.jsp",
            controller : "appControllerUbicacion"
        })
	.when('/Persona/:idEvento/:idDepartamento/:idMunicipio/:idCargo/:idZona/:nombreZona/:idPuesto/:nombrePuesto/:idUbicacion', {
            templateUrl : "templates/Persona.html",
            controller : "personaAsignadaController"
        })
	.when('/carne/:idDpto/:idMpio/:idEvento', {
		templateUrl : "templates/Carne.jsp",
		controller : "appControllerCarne"
        })
	.when('/Persona/:id', {
            templateUrl : "templates/Persona.html",
            controller : "personaAsignadaController"
        })
        .otherwise({ redirectTo : "/"});
});

app.controller("appControllerCarne", ['$scope', '$routeParams', function ($scope, $routeParams){
      $scope.idDpto =  $routeParams.idDpto;
      $scope.idMpio = $routeParams.idMpio;
      $scope.idEvento = $routeParams.idEvento;
}]);

app.controller("appController", ['$scope', '$routeParams', '$location','SeguimientoFactory', function seguimientoController($scope, $routeParams, $location, SeguimientoFactory){

    $scope.getEventos = function(){
        SeguimientoFactory.getEventos(function (data) {
            $scope.evento = [];
            $scope.evento.push(data[0]);
	});
    };
    
    
    $scope.listarDepartamentos = function(usuario){
        SeguimientoFactory.listarDepartamentos($routeParams.idEvento, usuario , function (data) {
            $scope.departamento = data;
	});

        SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
            $scope.evento = data;
	});
    };
    
    $scope.listarMunicipios = function(){
        SeguimientoFactory.listarMunicipios($routeParams.idEvento,$routeParams.idDepartamento, function (data) {
            $scope.municipio = data;
	});

        SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
            $scope.evento = data;
	});

        SeguimientoFactory.getDepartamento($routeParams.idDepartamento, function (data) {
            $scope.departamento = data;
	});
    };
    
    $scope.listarCargosMpio = function(){
        SeguimientoFactory.listarCargosMunicipio($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio, function (data) {
            $scope.cargos = data;
	});

        SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
            $scope.evento = data;
	});

        SeguimientoFactory.getDepartamento($routeParams.idDepartamento, function (data) {
            $scope.departamento = data;
	});
        
        SeguimientoFactory.getMunicipio($routeParams.idDepartamento,$routeParams.idMunicipio, function (data) {
            $scope.municipio = data;
	});
        
    };
  
    $scope.listarCargosZona = function(){
        SeguimientoFactory.listarCargosZona($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.idCargo, function (data) {
            $scope.zonas = data;
	});

        SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
            $scope.evento = data;
	});

        SeguimientoFactory.getDepartamento($routeParams.idDepartamento, function (data) {
            $scope.departamento = data;
	});
        
        SeguimientoFactory.getMunicipio($routeParams.idDepartamento,$routeParams.idMunicipio, function (data) {
            $scope.municipio = data;
	});
        
        SeguimientoFactory.getCargo($routeParams.idCargo, function (data) {
            $scope.cargo = data;
	});
    };
    
    $scope.listarPuesto = function(){
        SeguimientoFactory.listarPuesto($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.idCargo,$routeParams.idZona, function (data) {
            $scope.puestos = data;
	});

        SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
            $scope.evento = data;
	});

        SeguimientoFactory.getDepartamento($routeParams.idDepartamento, function (data) {
            $scope.departamento = data;
	});
        
        SeguimientoFactory.getMunicipio($routeParams.idDepartamento,$routeParams.idMunicipio, function (data) {
            $scope.municipio = data;
	});
        
        SeguimientoFactory.getCargo($routeParams.idCargo, function (data) {
            $scope.cargo = data;
	});

        $scope.zonaCodigo = $routeParams.idZona;
        $scope.zonaNombre = $routeParams.nombreZona;
        
    };

    $scope.listarUbicacion = function(){
        SeguimientoFactory.listarUbicacion($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.idCargo,$routeParams.idZona,$routeParams.idPuesto,function (data) {
            $scope.ubicaciones = data;
	});

        SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
            $scope.evento = data;
	});

        SeguimientoFactory.getDepartamento($routeParams.idDepartamento, function (data) {
            $scope.departamento = data;
	});
        
        SeguimientoFactory.getMunicipio($routeParams.idDepartamento,$routeParams.idMunicipio, function (data) {
            $scope.municipio = data;
	});
        
        SeguimientoFactory.getCargo($routeParams.idCargo, function (data) {
            $scope.cargo = data;
	});

        $scope.zonaCodigo = $routeParams.idZona;
        $scope.zonaNombre = $routeParams.nombreZona;
        
        $scope.puestoCodigo = $routeParams.idPuesto;
        $scope.puestoNombre = $routeParams.nombrePuesto;
    };

    $scope.listarPersona = function(){
        SeguimientoFactory.listarPersona($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.idCargo,$routeParams.idZona,$routeParams.idPuesto,$routeParams.idUbicacion,function (data) {
            $scope.persona = data;
	});

        $scope.zonaNombre = $routeParams.nombreZona;
        $scope.puestoNombre = $routeParams.nombrePuesto;
    };
    
    
}]);



app.controller("personaAsignadaController",['$scope','$routeParams', '$location','PersonaAsignada','SeguimientoFactory', function($scope, $routeParams, $location, PersonaAsignada, SeguimientoFactory){

        $scope.listarPersona = function(){
            SeguimientoFactory.getPersonaAsignada($routeParams.id, function (data) {
                $scope.persona = data;
            });

            $scope.zonaNombre = $routeParams.nombreZona;
            $scope.puestoNombre = $routeParams.nombrePuesto;
        };

        $scope.asignar = function(){
            var personaAsignada = new PersonaAsignada($scope.persona);
            
            personaAsignada.$update(function (response){

                if (response.codigo == '200')
                    {
                        $location.path('/Ubicacion/'+$scope.persona.evento.codigoEvento+'/'+$scope.persona.municipio.departamento.codigo+'/'+$scope.persona.municipio.codigoMunicipio+'/'+$scope.persona.cargo.codigoCargo+'/'+$scope.persona.zona+'/'+$scope.zonaNombre+'/'+$scope.persona.puesto+'/'+$scope.puestoNombre);
                    }
                    else
                        {
                            $scope.error = response.descripcion;
                        };
            });
        };        
    
    
}]);




app.controller("appControllerUbicacion", ['$scope', '$routeParams', '$location','SeguimientoFactory','Nombramiento', function seguimientoController($scope, $routeParams, $location, SeguimientoFactory, Nombramiento){
        
        $scope.listar = function(){
            
            SeguimientoFactory.listarUbicacion($routeParams.idEvento,$routeParams.idDepartamento,$routeParams.idMunicipio,$routeParams.idCargo,$routeParams.idZona,$routeParams.idPuesto,function (data) {
                
                $scope.ubicaciones = data;

                /*if(data.nombramiento.length > 1){
                    $scope.ubicaciones = data.nombramiento;
                }else
                    {
                        $scope.ubicaciones = [];
                        $scope.ubicaciones.push(data.nombramiento);
                    }
                */
                
               for(m= 0; m < $scope.ubicaciones.length; m++)
                {
                    if($scope.ubicaciones[m].estado.codigoEstado=='0' || $scope.ubicaciones[m].estado.codigoEstado=='1' || $scope.ubicaciones[m].estado.codigoEstado=='3')
                    {
                        $scope.ubicaciones[m].display = "";
                        $scope.ubicaciones[m].display2 = "none";
                        
                    }else
                        {
                            $scope.ubicaciones[m].display = 'none';
                            $scope.ubicaciones[m].display2 = '';
                        }
                        
                }
                
            });

            SeguimientoFactory.getEvento($routeParams.idEvento, function (data) {
                $scope.evento = data;
            });

            SeguimientoFactory.getDepartamento($routeParams.idDepartamento, function (data) {
                $scope.departamento = data;
            });

            SeguimientoFactory.getMunicipio($routeParams.idDepartamento,$routeParams.idMunicipio, function (data) {
                $scope.municipio = data;
            });

            SeguimientoFactory.getCargo($routeParams.idCargo, function (data) {
                $scope.cargo = data;
            });

            $scope.zonaCodigo = $routeParams.idZona;
            $scope.zonaNombre = $routeParams.nombreZona;

            $scope.puestoCodigo = $routeParams.idPuesto;
            $scope.puestoNombre = $routeParams.nombrePuesto;
            
        };
        
        $scope.asignar = function(id){
            var nombramiento = new Nombramiento($scope.ubicaciones[id]);
            
            if(!isNaN(parseFloat(nombramiento.empleado.nrodoc)) && isFinite(nombramiento.empleado.nrodoc))
                {
                    var idPersonaAsignada =  nombramiento.id; 

                    if(nombramiento.empleado.nrodoc ==0){
                        nombramiento.estado.codigoEstado = 0;
                    }else
                        {
                           nombramiento.estado.codigoEstado = 1;
                        }

                    nombramiento.$update(function (response){

                        if (response.codigo == '200')
                            {
                                SeguimientoFactory.getPersonaAsignada(idPersonaAsignada, function (data) {
                                    $scope.persona = data;

                                    $scope.ubicaciones[id] = $scope.persona;
                                    
                                    if($scope.ubicaciones[id].estado.codigoEstado=='0' || $scope.ubicaciones[id].estado.codigoEstado=='1' || $scope.ubicaciones[id].estado.codigoEstado=='3')
                                    {
                                        $scope.ubicaciones[id].display = "";
                                        $scope.ubicaciones[id].display2 = "none";

                                    }else
                                        {
                                            $scope.ubicaciones[id].display = 'none';
                                            $scope.ubicaciones[id].display2 = '';
                                        }
                                });
                            }
                            else
                                {
                                    alert(response.descripcion);
                                };
                    });
                }else
                    {
                        alert('Valor no permitido');
                    }
        };        
        
}]);


