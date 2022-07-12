(function () {
    function CargarArchivoPersonas(CargaArchivo, Empleado, sweet, $scope, $window) {
        var self = this;
        this.idPrueba = 0;
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            self.idPrueba = data.pruebaActual;
        });
        this.status = CargaArchivo.listarStatus({idTipoCargue: 11}, function (data) {});

        $scope.currentPage = 1;
        $scope.pageSize = 10;

        this.atras = function () {
            $window.history.back();
        };
        this.limpiar=function(){
         
           $('#file').val("");
        }

    }

    ;
    angular.module("app").controller('CargarArchivoPersonas', CargarArchivoPersonas);
})();

