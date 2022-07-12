var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
	$routeProvider.when("/", {
		templateUrl : "templates/capacitacion-new.jsp",
		controller : "appController"
	})
	.otherwise({ redirectTo : "/"});
});

function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

app.controller("appController", ['$scope', '$location', 'Capacitacion','CapacitacionFactory','Empleado', function ($scope, $location, Capacitacion, CapacitacionFactory,Empleado){

        $scope.inicio = function(usuario){

            var idPrueba = 0;
            Empleado.getEmpleadoPruebaSesion(function (data) {
            idPrueba = data.pruebaActual;
                CapacitacionFactory.listarDepartamentosDeUsuario(usuario,idPrueba, function (data){
                    $scope.departamento = data.usuarioDepartamento;

                    $scope.idDepartamento = $scope.departamento.codigo;

                          CapacitacionFactory.listarMunicipios($scope.idDepartamento, function (data) {

                                    if($scope.idDepartamento == "16" || $scope.idDepartamento == "99")
                                        {
                                            $scope.municipios = [];
                                            var municipio = {codigoMunicipio:"001",departamento:{codigo:$scope.idDepartamento,nombre:""},nombre:"BOGOTA D.C."};
                                            $scope.municipios.push(municipio);
                                        }else
                                            {
                                                $scope.municipios = data.municipio;
                                            }

                                    CapacitacionFactory.listarCargos(function (dataCargo){
                                        $scope.cargos = dataCargo.cargo;
                                    });

                          });
                });
            });
        };
        

        $scope.Save = function(){
            
                var date = this.fecha;
                var fechacapacita = date.getFullYear() + ("0" + (date.getMonth() + 1)).slice(-2) + ("0" + date.getDate()).slice(-2)                       
    
                var d = new Date();           
                var h = addZero(d.getHours());
                var m = addZero(d.getMinutes());
                var s = addZero(d.getSeconds())            
                var puesto = h+m+s;
            
                var h1 = this.horaInicial;
                var h = addZero(h1.getHours());
                var m = addZero(h1.getMinutes());
                var horaIni = h+":"+m;

                var h2 = this.horaFinal;
                var h = addZero(h2.getHours());
                var m = addZero(h2.getMinutes());
                var horaFin = h+":"+m;
            
            
               var capacitacion = new Capacitacion({
                     idEvento:1,
                     idDepartamento:this.idDepartamento,
                     idMunicipio:this.municipio.codigoMunicipio,
                     idZona:fechacapacita,
                     idPuesto:puesto,
                     idCargo:this.cargo.codigoCargo,
                     cantidad:this.cantidad,
                     fecha:fechacapacita,
                     horaInicial:horaIni,
                     horaFinal:horaFin,
                     nombreCapacitador:this.nombreCapacitador,
                     salon:this.salon});

                 capacitacion.$save(function (response){
                       if (response.codigo == '200')
                           {
                               alert(response.descripcion);
                               $location.path('/Capacitacion');
                           }
                           else
                               {
                                   $scope.mensaje = response.descripcion;
                               };

                 });
                 
        };

}]);

