(function () {
    function CargarArchivoSitios(CargaArchivo, Empleado, sweet, $scope) {
        var self = this;
        this.idPrueba = 0;
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            self.idPrueba = data.pruebaActual;
        });
        this.status = CargaArchivo.listarStatus({idTipoCargue: 5}, function (data) {});

        $scope.currentPage = 1;
        $scope.pageSize = 10;

    }

    ;
    angular.module("app").controller('CargarArchivoSitios', CargarArchivoSitios);
})();


(function () {
    function CargarArchivoPDSSitios($scope,CargaArchivo, Empleado, sweet) {
        var self = this;
        $scope.currentPage = 1;
        $scope.pageSize = 10;
        this.idPrueba = 0;
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            self.idPrueba = data.pruebaActual;
        });
        this.status = CargaArchivo.listarStatus({idTipoCargue: 8}, function (data) {});

    }

    ;
    angular.module("app").controller('CargarArchivoPDSSitios', CargarArchivoPDSSitios);
})();

