(function () {
    angular.module("app").controller("CargarArchivoController", function ($scope, $location, $routeParams,Sesion) {
        var self = this;
         Sesion.empleadoSesion( function (response) {
            self.prueba=response.pruebaActual;
        });
        self.idmedio_pago = $routeParams.idmedio_pago;

        self.validarForm = function () {
            if ($scope.file === "" || $scope.file === "undefined") {
                alert('Por favor seleccione todos los campos');
                return false;
            }
            return true;
        };


        self.regresar = function () {
            $location.path('/statusCargue/' + self.idmedio_pago + '/');
        };
    });

})();
