(function () {
    function CargaPagoViaticos(Empleado, CargaPagosService, FileSaver, CargaArchivo, $scope, sweet) {
        $scope.urlUpload = "/Paris/PagosServlet";

        //Se obtiene el id de la prueba para enviarla en la consulta
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            $scope.idPrueba = data.pruebaActual;
        });

        CargaArchivo.listarStatus({idTipoCargue: 10}, function (response) {
            console.log("Cargue de Viaticos:", response);
            $scope.currentPage = 1;
            $scope.pageSize = 5;
            $scope.listStatus = response;
        });

        $("#fileUpload").fileinput({
            showUpload: true,
            language: "es",
            previewFileType: 'any',
            browseLabel: "Examinar",
            removeLabel: "Borrar",
            uploadLabel: "Cargar Archivo"});

        //Funcion para descargar archivo de ejemplo para el cargue de pago de viaticos
        this.descargaDemo = function () {
            $('#modalLoading').modal('show');
            CargaPagosService.descargarDemo({}, function (response) {
            }).$promise.then(function (result) {
                var exportFileName = "demoPagoViaticos.csv";
                FileSaver.saveAs(result.response, exportFileName);
            });
            $('#modalLoading').modal('hide');
        };

        //Funcion para descargar el log de errores del cargue seleccionado
        this.descargaLogErrores = function (index) {
            var cargue = $scope.listStatus[index];
            CargaArchivo.generarReporteErrores({idStatusCargue: cargue.id}, function (response) {
            }).$promise.then(function (result) {
                var exportFileName = "ERR.csv";
                FileSaver.saveAs(result.response, exportFileName);
            });
        };

//        sweet.show({title: "\u00BFEsta seguro de cambiar el estado de los " + count + " viaticos?",
//            text: "esta acci\u00F3n no podra ser devuelta!",
//            type: "warning",
//            showCancelButton: true,
//            confirmButtonColor: "#DD6B55",
//            confirmButtonText: "Si, estoy seguro!",
//            closeOnConfirm: false}, function () {
//
//            if (count > 0) {
//                Viaticos.cambiarEstadoViaticos(params, function (response) {
//                    if (response.codigo === '200') {
//                        sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
//                        control.consultarViaticos();
//                    } else {
//                        sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
//                    }
//                });
//            } else {
//                sweet.show('Error', 'Debe seleccionar un viatico para cambiar de estado', "error");
//            }
//        });
    }
    ;
    angular.module("app").controller('CargaPagoViaticos', CargaPagoViaticos);
})();