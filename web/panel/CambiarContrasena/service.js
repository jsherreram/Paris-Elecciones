'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('CambiarContrasenaFactory', function ($resource) {
    return {
        getEmpleadoPorSesion: function (callback) {
            return $resource(formulario.global.api + '/empleado/buscarPorSesion', {}, {
                query: {
                    method: 'GET'
                }
            }).query(callback);
        }
    };
});
services.factory('CambiarContrasenaActualiza', ['$resource', function ($resource) {
        return $resource(formulario.global.api + '/empleado/actualizarContrasena', {}, {
            update: {method: 'POST'}
        });
    }]);
