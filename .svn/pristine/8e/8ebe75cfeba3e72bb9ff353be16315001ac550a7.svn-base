(function () {
    function ConciliacionPagosNomina(Empleado, ConciliacionPagosNominaService, $routeParams, $scope, sweet, $window) {
        var idPrueba = 0;
        $scope.codigoBarras = "";
        $scope.nroDocumento = "";
        $scope.error = {};
        $scope.pagototal = 0;

        //Se obtiene el id de la prueba para enviarla en la consulta
        Empleado.getEmpleadoPruebaSesion({}, function (response) {
            idPrueba = response.pruebaActual;
        });

        //Funcion para validar la cedula al ingresarla
        var valores = "";
        var yaTab = false;
        this.key = function ($event) {
            var code = $event.keyCode || $event.which;
            if (!yaTab) {
                if (code === 13) {
                    console.log("dio enter");
                    this.consultarPagos();
                    $event.preventDefault();
                }
                if (code === 9) {
                    console.log("dio tab");
                    yaTab = true;
                    $event.preventDefault();
                }
                valores = valores + $event.value;
            } else {
                if (code === 13) {
                    console.log("dio enter");
                    this.consultarPagos();
                    $event.preventDefault();
                }
                $event.preventDefault();
            }
        };


        this.limpiar = function () {
            $scope.pagos = {
                'eventos': []
            };
            $scope.nroDocumento ="";   
            $scope.items.totales.total = 0;
        };


        //Funcion para consultar los pagos que se deberian realizar a la cedula
        this.consultarPagos = function () {
            var error = false;
            yaTab = false;
            
            if ($scope.nroDocumento === undefined || $scope.nroDocumento == 0) {
                error = true;
                $scope.error.nroDocumento = true;
            }

            if (!error) {
                $scope.error = {};
                $('#modalLoading').modal('show');
                
                $scope.pagos = {
                    'eventos': []
                };
                
                $scope.pagos.eventos.push({ 'nombrenodo': 'Bogota', 'codigositio': '11001003','nombresitio':'AGUSTINIANO NORTE','cedula': 80544071, 'nombres':'PEDRO RODRIGUEZ', 'celular':'3165220301','cargo':'JEFE DE SALON','sesion':'REUNION PREVIA','valorSesion':41000,'asistio':true});
                $scope.pagos.eventos.push({ 'nombrenodo': 'Bogota', 'codigositio': '11001003','nombresitio':'AGUSTINIANO NORTE','cedula': 80544071, 'nombres':'PEDRO RODRIGUEZ', 'celular':'3165220301','cargo':'JEFE DE SALON','sesion':'DOMINGO MAÑANA','valorSesion':0,'asistio':false});
                $scope.pagos.eventos.push({ 'nombrenodo': 'Bogota', 'codigositio': '11001003','nombresitio':'AGUSTINIANO NORTE','cedula': 80544071, 'nombres':'PEDRO RODRIGUEZ', 'celular':'3165220301','cargo':'JEFE DE SALON','sesion':'DOMINGO TARDE','valorSesion':41000,'asistio':true});
                
                
                $('#modalLoading').modal('hide');
            }

        };
    }
    ;
    angular.module("app").controller('ConciliacionPagosNomina', ConciliacionPagosNomina);
})();