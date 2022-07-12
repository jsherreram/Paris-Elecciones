'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('ArchivoPagoFactory', function ($resource) {
    return {
        obtenerPruebas: function (callback) {
            return $resource(formulario.global.api + '/prueba/listarnocerradas', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        getSitios: function (idPrueba,usuario, callback) {
            return $resource(formulario.global.api + '/UsuarioSitio/listarArchivoPagoSitio?usuario='+usuario+'&idPrueba=' + idPrueba, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
    };
});



