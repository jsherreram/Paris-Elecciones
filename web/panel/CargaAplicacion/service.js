'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('AplicacionFactory', function ($resource) {
    return {
        listarStatus: function(callback) {
            return $resource(formulario.global.api + '/statuscargue/listar?idTipoCargue=4',{},{
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


