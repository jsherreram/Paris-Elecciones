'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('SeguimientoFactory', function ($resource) {
    return {
        getEventos: function(idDivipol, callback) {
            return $resource(formulario.global.api+'/evento/listarEventoPorSitio?idDivipol='+idDivipol, {}, {
                query: {
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        getSitios: function(idPrueba, usuario, callback) {
            return $resource(formulario.global.api+'/UsuarioSitio/listar?usuario='+usuario+'&idPrueba='+idPrueba, {}, {
                query: {
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },  
        
        getEmpleadoPruebaSesion: function(callback) {
            return $resource(formulario.global.api+'/empleado/empleadoSesion', {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        
        
        getSitiosXEvento: function(idEvento, usuario, callback) {
            return $resource(formulario.global.api+'/UsuarioSitio/listarXEvento?usuario='+usuario+'&idEvento='+idEvento, {}, {
                query: {
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        }
    };    
});


