'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('EventoFactory', function ($resource) {
    return {
        obtenerEventosPorPrueba: function (idPrueba, callback) {
            return $resource(formulario.global.api + '/evento/listarAll?idPrueba=' + idPrueba, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerEvento: function (codigoEvento, callback) {
            return $resource(formulario.global.api + '/evento/listarEvento?codigoEvento=' + codigoEvento, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };
});

services.factory('EventoActualiza', ['$resource', function ($resource) {
        return $resource(formulario.global.api + '/evento/update', {}, {
            update: {method: 'POST'}
        });
    }]);

services.factory('EventoInsert', ['$resource', function ($resource) {
        return $resource(formulario.global.api + '/evento/create', {}, {
            insert: {method: 'POST'}
        });
    }]);
