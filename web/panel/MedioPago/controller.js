var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);
app.config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider) {
    $routeProvider.when("/:idEmpleado/:idPrueba/:codCargo", {
        templateUrl: "templates/SeleccionMedioPago.html",
        controller: "appController",
    })
    .when('/', {
        templateUrl: "templates/SeleccionMedioPago.html",
        controller: "appController"
    })
    .otherwise({redirectTo: "/"});
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

app.controller("appController", ['$scope', '$routeParams', '$location', 'MedioPagoFactory', 'EmpleadoPrueba', '$window', function seguimientoController($scope, $routeParams, $location, MedioPagoFactory, EmpleadoPrueba, $window) {

        // se espera que lleguen tres parametros

        var idEmp = $routeParams.idEmpleado;
        var idPru = $routeParams.idPrueba;
        var codCar = $routeParams.codCargo;

        if($routeParams.idEmpleado === undefined)
        {
            //esta opción solo aplica para los cordinadores de sitio codigocargo 2, y en la pruba actual
            MedioPagoFactory.getPersonaSesion(function (data) {
                idEmp = data.idEmpleado;
                idPru = formulario.global.idPruebaActual;
                codCar = 2;
                cargarMedioPago(idEmp,  idPru, codCar);
            });
            
        }else
        {
            cargarMedioPago(idEmp,  idPru, codCar);
        }

        function cargarMedioPago(idEmpleado,  idPrueba, codCargo) {
                employed = new EmpleadoPrueba();
                MedioPagoFactory.obtenerMediosPago(idEmpleado,idPrueba,function(data) {
                    $scope.mediosPago = data;
                });
                MedioPagoFactory.obtenerBancos(function(data) {
                    $scope.bancos = data;
                });
                MedioPagoFactory.obtenerTipoCta(function(data) {
                    $scope.tiposCta = data;
                });

                employed.$query({idEmpleado: idEmpleado,
                    idPrueba: idPrueba,
                    idCodCargo: codCargo}, function(response) {

                    response.prueba.tipoCuenta = response.prueba.tipoCuenta == 0 ? $scope.tiposCta[0].idTipoCuenta : response.prueba.tipoCuenta;
                    response.prueba.idBanco = response.prueba.idBanco == 0 ? $scope.bancos[0].idBanco : response.prueba.idBanco;
                    $scope.empleadoSession = response;
                });
        };

        $scope.aceptar = function() {
            $scope.empleadoSession.$update(function(response) {
                if (response.codigo == '200') {
                    $window.alert("Se guardaron los cambios");
                    $window.history.back();
                }
                else {
                    $scope.error = response.descripcion;
                }
                ;

                employed.$query({idEmpleado: idEmpleado,
                    idPrueba: idPrueba,
                    idCodCargo: codCargo}, function(response) {
                    $scope.empleadoSession = response;
                });
            });
        };
    }]);

app.controller("eventosController", ['$scope','$window', function eventosController($scope,$window) {
        $scope.selectTab = function(activeTab) {
            $scope.tab = activeTab;
        };

        $scope.isSelected = function(checkTab) {
            return $scope.tab === checkTab;
        };
        $scope.validarForm = function() {

            validacionOk = true;

            if ($scope.empleadoSession.prueba.idMedioPago == 0) {
                $window.alert('Debe seleccionar un medio de pago');
                validacionOk = false;
            }
            return validacionOk;
        }
        
        $scope.atras = function() {
            $window.history.back();
        }
    }]);
