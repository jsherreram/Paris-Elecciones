'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('ConvocatoriaFactory', function ($resource) {
    return {
        listarxPersona: function(callback) {
            return $resource(formulario.global.api + '/ConfirmarEvento/listar', {},{
                query: { 
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        listarXIdEmpleado: function(idEmpleado, callback) {
            return $resource(formulario.global.api + '/ConfirmarEvento/listarXIdEmpleado?idEmpleado='+idEmpleado, {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        getPersonaSesion: function(callback) {
            return $resource(formulario.global.api + '/empleado/buscarPorSesion', {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        }        
    };    
});

services.factory('Convocatoria', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/ConfirmarEvento',{}, {
        update: { method: 'PUT' }
    });
}]);