(function () {
    function NotificacionPago(Empleado, CargaArchivo, FileSaver, NotificacionPagoService, $routeParams, $scope, sweet, $window) {
        $scope.urlUpload = "/Paris/NotificacionPagoServlet";
        $scope.exportFileName = "exportNotificacionPago.csv";
        $scope.exportDemoFileName = "demoNotificacionPago.csv";

        //Se obtiene el id de la prueba para enviarla en la consulta
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            $scope.idPrueba = data.pruebaActual;
        });

        //Se carga el historial de archivos cargados y su log de errores
        CargaArchivo.listarStatus({idTipoCargue: 12}, function (response) {
            console.log("Cargue de Archivo de Pagos para Notificar:", response);
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

        //Funcion para descargar el log de errores del cargue seleccionado
        this.descargaLogErrores = function (index) {
            var cargue = $scope.listStatus[index];
            CargaArchivo.generarReporteErrores({idStatusCargue: cargue.id}, function (response) {
            }).$promise.then(function (result) {
                var exportFileName = "ERR_" + cargue.id + ".csv";
                FileSaver.saveAs(result.response, exportFileName);
            });
        };

        //Funcion encargada de descargar el reporte de personal que ya confirmo la lectura del correo
        this.descargaReporte = function () {
            $('#modalLoading').modal('show');
            NotificacionPagoService.export({'prueba': {'idprueba': $scope.idPrueba}}, function (response) {
            }).$promise.then(function (result) {
                FileSaver.saveAs(result.response, $scope.exportFileName);
            });
            $('#modalLoading').modal('hide');
        };

        //
        this.descargaDemo = function () {
            $('#modalLoading').modal('show');
            NotificacionPagoService.getDemoFile({}, function (response) {
            }).$promise.then(function (result) {
                FileSaver.saveAs(result.response, $scope.exportDemoFileName);
            });
            $('#modalLoading').modal('hide');
        };
    }
    ;
    angular.module("app").controller('NotificacionPago', NotificacionPago);
})();