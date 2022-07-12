'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('ConvocatoriaFactory', function ($resource) {
    return {
        listarStatus: function(callback) {
            return $resource(formulario.global.api + '/statuscargue/listar?idTipoCargue=1',{},{
                query:{ 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarPruebasEnConvocatoria: function(callback) {
            return $resource(formulario.global.api + '/prueba/listar',{},{
                query:{ 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        }
    }; 
});


