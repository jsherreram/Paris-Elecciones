(function () {
    angular.module("app").controller("ConsultarMediosPagoController", function ($location, MedioPago, Sesion) {
        var self = this;

        Sesion.empleadoSesion(function (response) {
            self.idPrueba = response.pruebaActual;

            MedioPago.getMediosPago({idPrueba: self.idPrueba}, function (response) {
                self.listaMediosPago = response;
                self.totalItems = self.listaMediosPago.length;
                self.currentPage = 1;
                self.numPerPage = 10;

                self.paginate = function (value) {
                    var begin, end, index;
                    begin = (self.currentPage - 1) * self.numPerPage;
                    end = begin + self.numPerPage;
                    index = self.listaMediosPago.indexOf(value);
                    return (begin <= index && index < end);
                };
            });
        });


        //Busqueda de empleado y redireccion a pagina de gestion del empleado
        self.crearMedio = function () {
            $location.path('/registrarMedioPago/');
        };

        //Redirecciona para editar medio de pago
        self.editarMedioPago = function (idmedio_pago) {
            $location.path('/actualizarMedioPago/' + idmedio_pago + '/');
        };

        self.configurarCobertura = function () {
            $location.path('/coberturaMunicipio/');
        };


        self.subirArchivo = function (idmedio_pago) {
            $location.path('/statusCargue/' + idmedio_pago + '/');
        };
    });

})();

