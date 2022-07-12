'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('Actualizame', function ($resource) {
    return {
        ConsultaActualizame: function(nrodoc,idPrueba,callback) {
            return $resource(formulario.global.api + '/CierreSesion/listarJsonActualizame?nrodoc='+nrodoc+'&idPrueba='+idPrueba, {},{
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
        }
    };
});
