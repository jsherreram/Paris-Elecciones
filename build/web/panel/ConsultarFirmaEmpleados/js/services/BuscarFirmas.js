'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('BuscarFirmas', function($resource) {
    return {
        obtenerPruebas: function(idEmp, callback) {
            return $resource(formulario.global.api + '/empleadoPrueba/listadoPruebasEmpleado', {}, {
                query: {
                    method: 'GET',
                    isArray: true,
                    params:{id:idEmp}
                }
            }).query(callback);
        },
        
         buscarPersonasFirmas: function(idPrueba, idEmp,callback) {
            return $resource(formulario.global.api + '/confirmacion/listarEmpleados', {}, {
                query: {
                    method: 'GET',
                    isArray: true,
                    params:{idPrueba:idPrueba, idEmp: idEmp}
               
                }
            }).query(callback);
        },
          getPersonaSesion: function (callback) {
           return $resource(formulario.global.api + '/empleado/buscarPorSesion', {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },    
    
    };

});

