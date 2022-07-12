(function() {
    angular.module("app").controller("RegistrarMedioPagoController", function($location, MedioPago,Sesion) {
        var self = this;
        Sesion.empleadoSesion(function (response) {
            self.idPrueba = response.pruebaActual;
        });

        self.listaTiposMediosPago = MedioPago.getTiposMediosPago({});

        self.estados = [
                        {nombre: 'Activo', id: 1},
                        {nombre: 'Inactivo', id: 0}
                    ];
        self.objMedioPago;
        self.crearMedio = function() {
            $location.path('/registrarMedioPago/');
        };

        //habilita la captura del costo de transacción por valor
        self.tranPorCosto = function() {
            self.objMedioPago.costoTransaccionPorValor = true;
        };

        //habilita la captura del costo de transacción por porcentaje
        self.tranPorPorcentaje = function() {
            self.objMedioPago.costoTransaccionPorValor = false;
        };

        self.regresar = function() {
            $location.path('/consultarMediosPago/');
        };

        self.registrarMedioPago = function() {

            var medioPago = new MedioPago({
                nombre: self.objMedioPago.nombre,
                activo: self.objMedioPago.activo.id,
                idTipoMedioPago: self.objMedioPago.idTipoMedioPago.idTipoMedioPago,
                solicitarEntidadBancaria: self.objMedioPago.solicitarEntidadBancaria,
                solicitarNumeroCuenta: self.objMedioPago.solicitarNumeroCuenta,
                solicitarTipoCuenta: self.objMedioPago.solicitarTipoCuenta,
                auxilioTransporte: self.objMedioPago.auxilioTransporte,
                costoTransaccion: self.objMedioPago.costoTransaccion,
                porcentajeTransaccion: self.objMedioPago.porcentajeTransaccion,
                descripcion: self.objMedioPago.descripcion,
                costoTransaccionPorValor: self.objMedioPago.costoTransaccionPorValor,
                idPrueba:self.idPrueba
            });
            medioPago.$registrarMedioPago(function(response) {
                if (response.codigo === '200')
                {
                    $location.path('/consultarMediosPago/');
                    alert("El medio de pago se ha registrado correctamente!");

                } else
                {
                    alert(response.descripcion);

                }

            });
        };


    });

})();
