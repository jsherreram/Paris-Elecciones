'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('SeguimientoFactory', function ($resource) {
    return {
        
        getEventos: function(idDivipol, callback) {
            return $resource(formulario.global.api+'/evento/listarEventoNoCapacitacionPorSitio?idDivipol='+idDivipol, {}, {
                query: {
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        
        getSitio: function(idsitio, callback) {
            return $resource(formulario.global.api+'/sitio/buscar?id='+idsitio, {}, {
                query: { 
                    method: 'GET'
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
        getSitiosXEvento: function(idEvento, usuario, callback) {
            return $resource(formulario.global.api+'/UsuarioSitio/listarXEvento?usuario='+usuario+'&idEvento='+idEvento, {}, {
                query: {
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        getEvento: function(idEvento, callback) {
            return $resource(formulario.global.api+'/evento/buscar?id='+idEvento, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        listarUbicacion: function(idEvento, sitio, callback) {
            return $resource(formulario.global.api+'/personaAsignada/listarXSitio?idEvento='+idEvento+'&codigoSitio='+sitio, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        getPersonaAsignada: function(id, callback) {
            return $resource(formulario.global.api+'/personaAsignada/buscar?id='+id,{}, {
                query: { 
                    method: 'GET'
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
        
        
        getEstadoActual: function(nrodoc, idPrueba, codigoCargo, callback) {
            return $resource(formulario.global.api+'/empleadoPrueba/getEstadoActual?nrodoc='+nrodoc+'&idPrueba='+idPrueba+'&codigoCargo='+codigoCargo,{}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        }
    };    
});

services.factory('PersonaAsignada', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/personaAsignada',{}, {
        update: { method: 'PUT' }
    });
}]);
