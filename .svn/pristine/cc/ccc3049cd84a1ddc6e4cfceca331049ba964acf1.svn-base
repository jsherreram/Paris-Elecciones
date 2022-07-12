'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('ConsultaPruebasFactory', function ($resource) {
    return {
        obtenerPruebas: function (callback) {
            return $resource(formulario.global.api + '/prueba/listar', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        ConsultaAsignacion: function (idEmp, idPrueba, cargoNombre, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/listarasignacion?idEmp=' + idEmp + '&idPrueba=' + idPrueba + '&cargoNombre=' + cargoNombre, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        ConsultaViaticos: function (idEmp, idPrueba, cargoNombre, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/listarviaticos?idEmp=' + idEmp + '&idPrueba=' + idPrueba + '&cargoNombre=' + cargoNombre, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        ArchivoPlanoViaticos: function (idEmp, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/listarplanoviaticos?idEmp=' + idEmp, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        AutorizarViaticos: function (idEmp, idPrueba, codCargo, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/autorizarviaticos?idEmp=' + idEmp + '&idPrueba=' + idPrueba + '&codCargo=' + codCargo, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        ConsultaPagos: function (idEmp, idPrueba, cargoNombre, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/listarpagos?idEmp=' + idEmp + '&idPrueba=' + idPrueba + '&cargoNombre=' + cargoNombre, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        ConsultaAsistencia: function (idEmp, idPrueba, cargoNombre, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/listarasistencia?idEmp=' + idEmp + '&idPrueba=' + idPrueba + '&cargoNombre=' + cargoNombre, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        ConsultaPruebas: function (idEmp, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/listar?idEmp=' + idEmp, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        ConsultaCargo: function (idEmp, idPrueba, cargoNombre, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/listarcargo?idEmp=' + idEmp + '&idPrueba=' + idPrueba + '&cargoNombre=' + cargoNombre, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        getPersonaSesion: function (callback) {
            return $resource(formulario.global.api + '/empleado/buscarPorSesion', {}, {
                query: {
                    method: 'GET'
                }
            }).query(callback);
        },
        getPersonaById: function (id, callback) {
            return $resource(formulario.global.api + '/empleado/buscar', {id: '@id'}, {
                query: {
                    method: 'GET',
                    params: {id: id}
                }
            }).query(callback);
        },
        /*Wcv No me Funciono Pendiente de Revisar y Ajustar*/
        ConfirmaPago: function (reg, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/confirmapago', reg, {
                update: {
                    method: 'POST'
                }
            }).save();
        },
        confirmar: function (nrodoc, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/confirmacontrato?nrodoc=' + nrodoc, {}, {
                query: {
                    method: 'POST'
                }
            }).query(callback);
        },
        buscarConfirmacionPersona: function (idEmpleado, idPrueba, codCargo, tipo, callback) {
            return $resource(formulario.global.api + '/gestionmediopagos/buscarconfirmacion?idempleado=' + idEmpleado + '&idprueba=' + idPrueba + '&codcargo=' + codCargo + '&tipo=' + tipo, {}, {
                query: {
                    method: 'GET',
                }
            }).query(callback);
        },
        usuarioDepartamento: function (usuario, idPrueba, callback) {
            return $resource(formulario.global.api + '/UsuarioDepartamento/listar', {usuario: '@usuario', idPrueba: '@idPrueba'}, {
                query: {
                    method: 'GET',
                    params: {idPrueba: idPrueba, usuario: usuario},
                    isArray: true
                }
            }).query(callback);
        }
    };
});

angular.module('app.services').service('GMPago', function ($resource) {
    return $resource(formulario.global.api + '/gestionmediopagos/confirmapago', {},
            {save: {method: 'POST'},
                cancelarPrueba: {method: 'POST',
                    url: formulario.global.api + '/gestionmediopagos/cancelaprueba'},
                confirmaViatico: {method: 'POST',
                    url: formulario.global.api + '/gestionmediopagos/confirmaviatico'},
                aceptarContrato: {method: 'POST',
                    url: formulario.global.api + '/gestionmediopagos/confirmacontrato'}
            }
    );
})


