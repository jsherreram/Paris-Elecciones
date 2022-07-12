var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
	$routeProvider.when("/", {
            templateUrl : "templates/seleccionarArchivo.jsp",
            controller : "appControllerSitios"
	})
        
        .when('/Nombramiento/:idDivipol/:idPrueba', {
		templateUrl : "templates/Ubicacion.jsp",
		controller : "appControllerUbicacion"
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

function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

app.controller("appControllerUbicacion", ['$scope', '$routeParams', 'SeguimientoFactory','Nombramiento', function seguimientoController($scope, $routeParams, SeguimientoFactory, Nombramiento){
        
        $scope.idDivipol = $routeParams.idDivipol;
        $scope.idPrueba = $routeParams.idPrueba;
        
        $scope.listar = function(){
              $("#modalLoading").modal('show');
            
            SeguimientoFactory.getSitio($scope.idDivipol, function (data) {
               $scope.puestoNombre = data.nombreSitio;
               $scope.idSitio = data.codigoSitio;
            });
            
            SeguimientoFactory.listarUbicacion($scope.idPrueba, $scope.idDivipol ,function (data) {
                
               $scope.ubicaciones = data;
                
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
                
                  $("#modalLoading").modal('hide');
            });

        };
        
        $scope.asignar = function(id){
            var nombramiento = new Nombramiento($scope.ubicaciones[id]);
            nombramiento.idPrueba = $scope.idPrueba;
        
            if(!isNaN(parseFloat(nombramiento.empleado.nrodoc)) && isFinite(nombramiento.empleado.nrodoc))
                {
                    var idPersonaAsignada =  nombramiento.id; 

                    if(nombramiento.empleado.nrodoc ==0){
                        nombramiento.estado.codigoEstado = 0;
                    }else
                        {
                           nombramiento.estado.codigoEstado = 1;
                        }

                    
                    SeguimientoFactory.getEstadoActual(nombramiento.empleado.nrodoc, $scope.idPrueba, nombramiento.cargo.codigoCargo, function (data) {
                        var EstadoPersonaPrueba = data;
                        
                        //if (nombramiento.estado.codigoEstado === 1 && EstadoPersonaPrueba.idestpersona !== 7)
                        //{
                        //    alert('ESTA IDENTIFICACION NO TIENE UNA ACEPTACION DE CONVOCATORIA PARA ESTE CARGO');
                        //}else                        
                        if (1===2)
                        {
                            alert('Esta identificación no tiene una aceptación de convocatoria para este cargo');
                        }else                        
{
                        
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
                        }
                    });    
                    
                }else
                    {
                        alert('Valor no permitido');
                    }
        };        
        
}]);



app.controller("appControllerSitios", ['$scope','$routeParams', '$location','SeguimientoFactory',function seguimientoController($scope, $routeParams, $location, SeguimientoFactory){
            
        SeguimientoFactory.getEmpleadoPruebaSesion(function (data) {
            $scope.idprueba = data.pruebaActual;
            $scope.usuario = data.nrodoc;
                
            SeguimientoFactory.getSitios($scope.idprueba, $scope.usuario, function (data) {
                $scope.sitios = data;
            });
            SeguimientoFactory.getSitiosSinCierre($scope.idprueba, $scope.usuario, function (data) {
                $scope.sitiossincierre = data;
            });
                
        });

}]);