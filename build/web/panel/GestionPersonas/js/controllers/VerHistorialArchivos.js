(function () {
    function AdjuntarArchivos($scope, $routeParams, $window) {
        var self = this;

        $scope.currentPage = 1;
        $scope.pageSize = 10;
        self.idPersona = $routeParams.idPersona;
        self.idDpto = $routeParams.idDpto;
        self.idEmpleado = $routeParams.idEmpleado;

        this.atras = function () {
            $window.history.back();
        };
    }
    ;
    angular.module('app').controller('AdjuntarArchivos', AdjuntarArchivos);
})();

(function () {
    function verHistorial($scope, $routeParams,  id, tipoDoc,Empleado, $window, $uibModalInstance) {
        var self = this;
        $scope.currentPage = 1;
        $scope.pageSize = 10;
        self.tipoDocumento = tipoDoc;
        self.idEmpleado =id;
        Empleado.getHistorialArchivos({idEmpleado: self.idEmpleado, tipoDoc: self.tipoDocumento}, function (data) {
            self.archivos = data;
        });

          $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }
    ;
    angular.module('app').controller('verHistorial', verHistorial);
})();
