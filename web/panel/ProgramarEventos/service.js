'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('Capacitacion', function ($resource) {
    return $resource(formulario.global.api + '/personaAsignada',{}, {
        create: { method: 'POST' }
    });
});

services.factory('EventoFactory', function ($resource) {
    return {
        obtenerEventos: function(idPrueba,callback) {
            if (idPrueba > 0 ){
            }else{idPrueba = 0;
            }
            return $resource(formulario.global.api + '/evento/listarAll?idPrueba='+idPrueba, {},{
                query: {
                    method: 'GET',
                    isArray: true 
                }
            }).query(callback);
        },
        obtenerEvento: function(codigoEvento,callback) {
            return $resource(formulario.global.api + '/evento/listarEvento?codigoEvento='+codigoEvento, {},{
                query: {
                    method: 'GET',
                    isArray: true 
                }
            }).query(callback);
        },
        obtenerTipSesion: function(callback) {
            return $resource(formulario.global.api + '/evento/listarTipSesion', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };    
});

services.factory('EventoActualiza', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/evento/actualizar',{}, {
        update: { method: 'POST' }
    });
}]);

services.factory('EventoInsert', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/evento',{}, {
        insert: { method: 'POST' }
    });
}]);