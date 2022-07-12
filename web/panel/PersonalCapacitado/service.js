'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('Capacitacion', function ($resource) {
    return $resource(formulario.global.api + '/personaAsignada',{}, {
        create: { method: 'POST' }
    });
});

services.factory('SeguimientoCapacitacion', function ($resource) {
    return {
        getEstadoCapacitacion: function(idPrueba,usuario,callback) {
            return $resource(formulario.global.api+'/personaAsignada/personalEnCapacitacion?usuario='+usuario+'&idPrueba='+idPrueba , {}, {
                query: { 
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };    
});
