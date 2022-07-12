'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('EventoAdicionalFactory', function ($resource) {
    return {
        getEventos: function (idPrueba, callback) {
            return $resource(formulario.global.api + '/evento/findAllEventosByIdPruebaNoCapacitacion?idPrueba=' + idPrueba, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        getEvento: function (idEvento, callback) {
            return $resource(formulario.global.api + '/evento/buscar?id=' + idEvento, {}, {
                query: {
                    method: 'GET'
                }
            }).query(callback);
        },
        getSitios: function (idPrueba, callback) {
            return $resource(formulario.global.api + '/sitio/listar?idPrueba=' + idPrueba, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        getSitio: function(idSitio, callback) {
            return $resource(formulario.global.api+'/sitio/findById?id=' + idSitio, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        getCargos: function (callback) {
            return $resource(formulario.global.api + '/cargo/listar', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        getCargo: function (idCargo, callback) {
            return $resource(formulario.global.api + '/cargo/buscar?id=' + idCargo, {}, {
                query: {
                    method: 'GET'
                }
            }).query(callback);
        },
        getEmpleadoPruebaSesion: function (callback) {
            return $resource(formulario.global.api + '/empleado/empleadoSesion', {}, {
                query: {
                    method: 'GET'
                }
            }).query(callback);
        },
        getEventoAdicional: function (idPrueba, idEvento, idSitio, idCargo, ubicacion, callback) {
            return $resource(formulario.global.api + '/detalleCargoPuestoEvento/find?idPrueba=' + idPrueba + '&idEvento=' + idEvento + '&idSitio=' + idSitio + '&idCargo=' + idCargo + '&ubicacion=' + ubicacion, {}, {
                query: {
                    method: 'GET'
                }
            }).query(callback);
        }
    };
});

services.factory('EventoAdicionalInsert', ['$resource', function ($resource) {
        return $resource(formulario.global.api + '/detalleCargoPuestoEvento/create', {}, {
            insert: {method: 'POST'}
        });
    }]);
