(function () {
    angular.module("app").controller("ActualizarMedioPagoController", function ($location, $routeParams, MedioPago, Sesion) {
        var self = this;

        self.listaTiposMediosPago = MedioPago.getTiposMediosPago({});
        self.estados = [
            {nombre: 'Activo', id: 1},
            {nombre: 'Inactivo', id: 0}
        ];
        self.costoTransaccionPorValor;


        self.crearMedio = function () {
            $location.path('/registrarMedioPago/');
        };
        //habilita la captura del costo de transacción por valor
        self.tranPorCosto = function () {
            self.costoTransaccionPorValor = true;
        };

        //habilita la captura del costo de transacción por porcentaje
        self.tranPorPorcentaje = function () {
            self.costoTransaccionPorValor = false;
        };

        Sesion.empleadoSesion(function (response2) {
            self.idPrueba = response2.pruebaActual;

            MedioPago.findMedioPago({idmedio_pago: $routeParams.idmedio_pago, idPrueba: self.idPrueba}, function (data) {
                self.objMedioPago = data;
                if (self.objMedioPago !== undefined) {
                    if (self.objMedioPago.costoTransaccionPorValor) {
                        self.transaccionPorCosto = true;
                    }
                    else {
                        self.transaccionPorCosto = false;
                    }

                    for (k = 0; k < self.listaTiposMediosPago.length; k++)
                    {
                        if (self.listaTiposMediosPago[k].idTipoMedioPago === self.objMedioPago.idTipoMedioPago)
                        {
                            self.objMedioPago.idTipoMedioPago = self.listaTiposMediosPago[k];
                            break;
                        }
                    }
                    for (k = 0; k < self.estados.length; k++)
                    {
                        if (self.estados[k].id === self.objMedioPago.activo)
                        {
                            self.objMedioPago.activo = self.estados[k];
                            break;
                        }
                    }
                }
                else {
                    alert("Medio de pago no encontrado");
                }
            });
        });
        self.regresar = function () {
            $location.path('/consultarMediosPago/');
        };


        self.registrarMedioPago = function () {

            var medioPago = new MedioPago({
                id_medio: self.objMedioPago.id_medio,
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
                costoTransaccionPorValor: self.objMedioPago.costoTransaccionPorValor
            });
            medioPago.$actualizarMedioPago(function (response) {

                if (response.codigo === '200')
                {
                    $location.path('/consultarMediosPago/');
                    alert("El medio de pago se ha actualizado correctamente!");
                } else
                {
                    alert(response.descripcion);
                }
            });
        };
    });

})();

