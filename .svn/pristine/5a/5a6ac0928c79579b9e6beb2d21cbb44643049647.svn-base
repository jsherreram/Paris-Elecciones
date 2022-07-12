var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert']);


app.config(['$httpProvider', function ($httpProvider) {

        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/ReversarCierre.jsp",
        controller: "appController1"
    });
});

app.controller("appController1", ['$scope', 'CierreSesion', 'Empleado', 'ReversarAsistencia','sweet','$location', function ($scope, CierreSesion, Empleado,ReversarAsistencia,sweet,$location) {
        
        var idPrueba = 0;
        var fila = 0;
        $scope.listar = function(){
            
        };
            Empleado.getEmpleadoPruebaSesion(function (data) {
                idPrueba = data.pruebaActual;
                CierreSesion.getPersonaSesion(function (data) {
                    $scope.persona = data;
                    $scope.nrodoc = $scope.persona.nrodoc;
                    CierreSesion.ConsultaCierres($scope.persona.nrodoc,idPrueba, function (data) {
                        $scope.cierresdesesion = data;
                    });
                });
            });
        
        
        $scope.confirm = function(reg) {
            var cierre = new ReversarAsistencia();{
                    cierre.idprueba     = idPrueba;
                    cierre.idDivipol    = reg.idDivipol;
                    cierre.codigoEvento = reg.codigoevento;
            }
            if (reg.abrirsesion == true){
                sweet.show('Oopss!','Ya no se Permite esta Operaci\u00F3n para el Evento\n'+reg.nombre,'warning');
            }
            else{
                fila    =  reg.rownum; 
                sweet.show({
                    title: 'Confirmar',
                    text: 'Esta Seguro que desea Reversar el Cierre de Asistencia del Evento\n'+reg.nombre+'\n Del Sitio '+reg.nombrePuesto+'?',
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3e6bcc',
                    cancelButtonText: 'NO',
                    confirmButtonText: 'SI, Borrar el Cierre',
                    closeOnConfirm: false
                }, function() {
                         cierre.$delete(function (response) {
                            if (response.codigo === '200')
                            { 
                                CierreSesion.ConsultaCierres($scope.persona.nrodoc,idPrueba, function (data) {
                                    $scope.cierresdesesion = data;
                                });
                                 sweet.show('Borrado!', 'El Cierre ha Sido Borrado', 'success');
                            };
                        });
                });
            }
        };
    
        $scope.CalcularParaCerrar = function (reg) {
            var cierre = new ReversarAsistencia();{
                    cierre.idprueba     = idPrueba;
                    cierre.idDivipol    = reg.idDivipol;
                    cierre.codigoEvento = reg.codigoevento;
                }
            $scope.validarModal = true;
            CierreSesion.GetRamdon(function (data) {
                $scope.aleatorio = data[0].numero;
            });
        }; 
        $scope.reversar = function () {
            if ( $scope.aleatorio == $scope.verificador) {
                 cierre.$delete(function (response) {
                    if (response.codigo === '200')
                    {  
                       $scope.listar();
                       $scope.validarModal = false;
                       $scope.verificador = "";
                    };
                });
            }else{
                sweet.show('Oopss', 'Numero de Verificación no Coincide, Por Favor revisar', "error");
                $scope.CalcularParaCerrar();
                $scope.verificador = "";
            }
        }; 

    }]);
