'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('nodosFactory', function ($resource) {
    return{
        listarNodos: function(callback) {
            return $resource(formulario.global.api+'/departamento/listar',{},{
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },        
        listarEstado: function(idPrueba,departamento,callback){
            return $resource(formulario.global.api+'/seguimiento/seguimientoxdepartamento?idPrueba='+idPrueba+'&departamento='+departamento,{},{
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarSeguimientoEstado: function(idPrueba,callback){
            return $resource(formulario.global.api+'/seguimiento/seguimientoxestados?idPrueba='+idPrueba,{},{
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        }        
    };
});

services.factory('EventoFactory', function($resource) {
    return {
        obtenerPruebas: function(callback) {
            return $resource(formulario.global.api + '/prueba/listar', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };

});
