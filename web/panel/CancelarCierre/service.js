'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('CierreSesion', function ($resource) {
    return {
        ConsultaCierres: function(nrodoc,idPrueba,callback) {
            return $resource(formulario.global.api + '/CierreSesion/listarJsonCierres?nrodoc='+nrodoc+'&idPrueba='+idPrueba, {},{
                query: { 
                    method: 'GET',
                    isArray:true
                }
            }).query(callback);
        },
        getPersonaSesion: function(callback) {
            return $resource(formulario.global.api + '/empleado/buscarPorSesion', {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        GetRamdon: function(callback) {
            return $resource(formulario.global.api + '/asignarsuplente/GetRamdon', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };
});

services.factory('ReversarAsistencia', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/CierreSesion/ReversarCierre',{}, {
        delete: { method: 'POST' }
    });
}]);
