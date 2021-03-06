'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('PruebaFactory', function ($resource) {
    return {
        obtenerPruebas: function(callback) {
            return $resource(formulario.global.api + '/prueba/listarAll?idPrueba='+0, {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerPrueba: function(idPrueba,callback) {
            return $resource(formulario.global.api + '/prueba/listarAll?idPrueba='+idPrueba, {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerEstPrueba: function(callback) {
            return $resource(formulario.global.api + '/prueba/listarEstPrueba', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerTipPrueba: function(callback) {
            return $resource(formulario.global.api + '/prueba/listarTipPrueba', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerTipPruebaEsp: function(callback) {
                return $resource(formulario.global.api + '/prueba/listarTipPruebaEsp', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };    
});

services.factory('PruebaActualiza', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/prueba/actualizar',{}, {
        update: { method: 'POST' }
    });
}]);

services.factory('PruebaInsert', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/prueba',{}, {
        insert: { method: 'POST' }
    });
}]);
