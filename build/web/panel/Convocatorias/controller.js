var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/ConvocatoriasDisponibles.html",
        controller: "ConvocatoriaController as controller"
    })
    .when('/ConvocatoriaDetalle/:idprueba', {
        templateUrl: "templates/ConvocatoriaDetalle.html",
        controller: "ConvocatoriaController as controller"
    })
    $routeProvider.when("/Convocatorias/:idEmpleado", {
        templateUrl: "templates/ConvocatoriasDisponibles.html",
        controller: "ConvocatoriaController as controller"
    })
    .when('/ConvocatoriaDetalle/:idprueba/:idEmpleado', {
        templateUrl: "templates/ConvocatoriaDetalle.html",
        controller: "ConvocatoriaController as controller"
    })
    
    .otherwise({redirectTo: "/"});
});

app.controller("ConvocatoriaController", ['$scope', '$routeParams', '$location', 'ConvocatoriaFactory', 'Convocatoria','$window' ,function ($scope, $routeParams, $location, ConvocatoriaFactory, Convocatoria, $window) {

        this.eventos = [];
        this.eventoSeleccionado = $routeParams.idprueba;
        this.evento = [];
        var self = this;
        
        
        if($routeParams.idEmpleado === undefined ){
            ConvocatoriaFactory.listarxPersona(function (data) {
                listar(data);
            });
            ConvocatoriaFactory.getPersonaSesion(function (data) {
                $scope.idPersona = data.idEmpleado;
            });
            
        }else{
            ConvocatoriaFactory.listarXIdEmpleado($routeParams.idEmpleado, function (data) {
                listar(data);
            });
            $scope.idPersona = $routeParams.idEmpleado;
        }
        
        function listar(data) {
            self.eventos = data;
            console.log(self.eventos);
            $scope.eventos = self.eventos;
            if (self.eventoSeleccionado !== undefined) {                
                var i = 0;

                for (i = 0; i < self.eventos.length; i++) {                    
                    if (self.eventos[i].codigoPrueba == self.eventoSeleccionado) {
                        self.evento = self.eventos[i];
                        console.log("entra");
                    }
                }                
            }
        }

        $scope.guardar = function (guarda) {
            if (guarda == 1)
            {
                if(this.fecha1 == undefined || this.fecha2 == undefined){
                    alert("Debe diligenciar ambas fechas de disponibilidad.");
                    return;
                }

                var date = this.fecha1;
                var vfecha1 = date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2);
                console.log(vfecha1);
                var date1 = this.fecha2;
                var vfecha2 = date1.getFullYear() + "-" + ("0" + (date1.getMonth() + 1)).slice(-2) + "-" + ("0" + date1.getDate()).slice(-2);
                console.log(vfecha2);
                if(vfecha1 > vfecha2){
                    alert("Fecha inicial debe ser menor a la final.");
                    return;
                }
                var convocatoria = new Convocatoria({
                    fechaDisponibilidadInicial: vfecha1,
                    fechaDisponibilidadFinal: vfecha2,
                    disponibilidadViaje: this.d1,
                    disponibilidadRural: this.d2,
                    disponibilidadUrbana: this.d3,
                    trabajoActual: this.d4,
                    disponibilidadCapacitacion: this.d5,
                    observaciones: this.observaciones,
                    idPrueba: self.eventoSeleccionado,
                    estado: 7,
                    idUsuario: this.idPersona
                });
                convocatoria.$update(function (response) {
                    if (response.codigo == '200')
                    {
                        console.log("ok");
                        $window.history.back();
                    }
                    else
                    {
                        $scope.error = response.descripcion;
                    }
                    ;
                });
            }
            if (guarda == 2)
            {
                var convocatoria = new Convocatoria({
                    fechaDisponibilidadInicial: 'null',
                    fechaDisponibilidadFinal: 'null',
                    disponibilidadViaje: 0,
                    disponibilidadRural: 0,
                    disponibilidadUrbana: 0,
                    trabajoActual: 0,
                    disponibilidadCapacitacion: 0,
                    observaciones: 0,
                    idPrueba: self.eventoSeleccionado,
                    estado: 6,
                    idUsuario: this.idPersona
                });
                convocatoria.$update(function (response) {
                    if (response.codigo == '200')
                    {
                        console.log("ok");
                        $window.history.back();
                    }
                    else
                    {
                        $scope.error = response.descripcion;
                    }
                    ;
                });
            }
        }; 

        $scope.volver = function(){
            $window.history.back();
        };

    }]);

app.controller("ConvocatoriaDetalleController", ['$scope', '$routeParams', '$location', 'ConvocatoriaFactory', function ($scope, $routeParams, $location, ConvocatoriaDetalleController) {
        this.eventoSeleccionado = $routeParams.idprueba;

    }]);