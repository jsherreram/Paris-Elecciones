
var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
	$routeProvider.when("/", {
            templateUrl : "templates/Ubicacion.html",
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


app.controller("appControllerUbicacion", ['$scope', '$routeParams', '$location','SeguimientoFactory','PersonaAsignada', function seguimientoController($scope, $routeParams, $location, SeguimientoFactory, PersonaAsignada){
        
        $scope.nrodoc = 0;
        
        $scope.listar = function(){
            
            nrodoc = $scope.nrodoc;
            
            $scope.displayColummAsistieron = '';
            $scope.displayColummNoAsistieron = '';
            
            if($routeParams.idCargo == '1')
                {
                    $scope.displayColummAsistieron = '';
                    $scope.displayColummNoAsistieron = '';
                }else
                    {
                        $scope.displayColummAsistieron = 'none';
                        $scope.displayColummNoAsistieron = 'none';
                    }
            
            
            $scope.estados = [
                {"codigoEstado":"1","descripcion":"PROGRAMADO"},
                {"codigoEstado":"2","descripcion":"PARA AUDITORIA"},
                {"codigoEstado":"3","descripcion":"INCONSISTENTE"}
            ];
            
            SeguimientoFactory.listarUbicacion(nrodoc,function (data) {
                $scope.ubicaciones = data;
                
                if(data.personaAsignada.length > 1){
                    $scope.ubicaciones = data.personaAsignada;
                }else
                    {
                        $scope.ubicaciones = [];
                        $scope.ubicaciones.push(data.personaAsignada);
                    }
                
             
               for(m= 0; m < $scope.ubicaciones.length; m++)
                {
                    if ( $scope.ubicaciones[m].usuario !="''")
                    {
                        $scope.ubicaciones[m].display = "";
                        $scope.ubicaciones[m].display2 = "none";
                        
                    }else
                        {
                            $scope.ubicaciones[m].display = 'none';
                            $scope.ubicaciones[m].display2 = '';
                        }

                    
                    for(n= 0; n < $scope.estados.length; n++)
                    {
                        if($scope.estados[n].codigoEstado == $scope.ubicaciones[m].estado.codigoEstado)
                        {
                            $scope.ubicaciones[m].estado = $scope.estados[n];
                        }
                    }
                    
                    if($scope.ubicaciones[m].asistio == "true")
                        {
                            $scope.ubicaciones[m].asistio = true;
                        }else
                        {
                            $scope.ubicaciones[m].asistio = false;
                        }
                }                
                
            });
            
        };
        
}]);


