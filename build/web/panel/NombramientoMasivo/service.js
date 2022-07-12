'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('ConvocatoriaFactory', function ($resource) {
    return {
        listarStatus: function(callback) {
            return $resource(formulario.global.api + '/statuscargue/listar?idTipoCargue=3',{},{
                query:{ 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        obtenerPruebas: function(callback) {
            return $resource(formulario.global.api + '/prueba/listarnocerradas', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    }; 
});