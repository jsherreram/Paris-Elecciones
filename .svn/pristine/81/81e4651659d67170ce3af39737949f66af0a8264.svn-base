(function () {
    function generarArchivo(Departamento, $scope, Empleado,Pagos, FileSaver) {
        var self = this;
        this.departamento = 0;
        var idPrueba=0;
        var user="";

        this.departamentos = Departamento.listar({}, function (data) {});

      Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
        });


        //Funcion que recibe el submit del formulario principal
        this.exportPagos = function () {
          $scope.exportFileName="archivo_pago_"+this.departamento+".xls";
            var params = {
                idPrueba: idPrueba,
                codigoDepartamento: this.departamento,
                nombreDepartamento: this.departamento
            };
              $('#modalLoading').modal('show');
            Pagos.exportArchivo(params, function (data) {
            }).$promise.then(function (result) {
                FileSaver.saveAs(result.response, $scope.exportFileName);
                $('#modalLoading').modal('hide');
            });
        };



    };
    angular.module("app").controller('generarArchivo', generarArchivo);
})();