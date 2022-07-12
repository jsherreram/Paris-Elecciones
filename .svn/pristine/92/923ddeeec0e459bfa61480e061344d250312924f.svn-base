(function () {
    function AdminCampagnaEncuesta(Campagna, Empleado, MonitoreoSitio, sweet, $window, $scope) {
        var idPrueba = 0;
        $scope.evento = 0;
        $scope.progreso = 0;
        $scope.campagna = 0;
        $scope.error = {};

        //Funcion para volver
        this.atras = function () {
            $window.history.back();
        };

        //Se obtiene el id de la prueba para enviarla en la consulta
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
            //Se consultan los eventos de tipo aplicacion y reunion previa
            $scope.eventos = MonitoreoSitio.listarEventosAplicacion({idPrueba: idPrueba}, function (response) {});
        });

        //Se iniciliazan los progresos
        $scope.progresos = [{id: 1, descripcion: "PRIMERA PARTE"}, {id: 2, descripcion: "SEGUNDA PARTE"}, {id: 3, descripcion: "TERCERA PARTE"}];

        //Se consultan las campagnas para listar
        $scope.campagnas = Campagna.listar({}, function (response) {});

        //Funcion para carga la campagna para gestion telefonica
        this.cargarCampagna = function () {
            var error = validateFields();
            if (!error) {
                var params = {
                    idPrueba: idPrueba,
                    idCampagna: $scope.campagna,
                    idProgreso: $scope.progreso,
                    idEvento: $scope.evento
                };
                Campagna.consultarDatosCampagnaEncuesta(params, function (response) {
                    $scope.datos = response;
                    console.log(response);
                    Campagna.insertarDatosCampagna($scope.datos, function (response) {
                        console.log(response);
                        if (response.codigo === '200') {
                            sweet.show('Felicidades', 'La Campa\u00F1a se carg\u00F3 correctamente!', "success");
                        } else {
                            sweet.show('Oopss', "Error al cargar la campa\u00F1a", "error");
                        }
                    });
                });
            }
        };

        function validateFields() {
            console.log("validating fields:", $scope.campagna, "-", $scope.progreso, "-", $scope.evento);
            var error = false;
            if ($scope.campagna == 0) {
                console.log("Error en campagna", $scope.campagna);
                $scope.error.campagna = true;
                error = true;
            } else {
                $scope.error.campagna = false;
            }

            if ($scope.progreso == 0) {
                console.log("Error en progreso");
                $scope.error.progreso = true;
                error = true;
            } else {
                $scope.error.progreso = false;
                console.log("No Error en progreso", $scope.progreso === 0, " Value:-", $scope.progreso, "-");
            }

            if ($scope.evento == 0) {
                console.log("Error en evento");
                $scope.error.evento = true;
                error = true;
            } else {
                $scope.error.evento = false;
            }

            return error;
        }

    }
    ;
    angular.module("app").controller('AdminCampagnaEncuesta', AdminCampagnaEncuesta);
})();