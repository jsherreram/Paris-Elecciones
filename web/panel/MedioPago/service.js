'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('MedioPagoFactory', function($resource) {
    return {
        obtenerMediosPago: function(idEmpleado,idPrueba,callback) {
            return $resource(formulario.global.api + '/medioPago/listarMediosPago?idEmp='+idEmpleado+'&idPrueba='+idPrueba, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerBancos: function(callback) {
            return $resource(formulario.global.api + '/banco/listar', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerTipoCta: function(callback) {
            return $resource(formulario.global.api + '/medioPago/listarTipoCta', {}, {
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

services.factory('EmpleadoPrueba', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/empleadoPrueba',{}, {
        update: { method: 'PUT' },
        query: { method: 'GET',isArray: false}
    });
}]);
