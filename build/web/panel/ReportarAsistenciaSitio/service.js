'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('EncuestaFactory', function ($resource) {
    return {
        getEncuesta: function(idEvento, idDivipol, callback) {
            return $resource(formulario.global.api+'/encuesta/buscar?idEvento='+idEvento+'&idDivipol='+idDivipol, {}, {
                query: {
                    method: 'GET'
                }
            }).query(callback);
        },
        getEventos: function(idDivipol, callback) {
            return $resource(formulario.global.api+'/evento/listarEventoPorSitio?idDivipol='+idDivipol, {}, {
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

        getEmpleadoPruebaSesion: function(callback) {
            return $resource(formulario.global.api+'/empleado/empleadoSesion', {}, {
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
        }        
    };    
});

services.factory('Encuesta', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/encuesta',{}, {
        update: { method: 'PUT' }
    });
}]);


services.factory('SeguimientoFactory', function ($resource) {
    return {
        listarUbicacion: function(idEvento, codigoSitio, callback) {
            return $resource(formulario.global.api+'/personaAsignada/asistenciaSitioEvento?idEvento='+idEvento+'&codigoSitio='+codigoSitio, {}, {
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
        getEventos: function(idPrueba, usuario, callback) {
            return $resource(formulario.global.api+'/evento/listarXSitio?idPrueba='+idPrueba+'&usuario='+usuario, {}, {
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
        }       
    };    
});

services.factory('PersonaAsignada', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/personaAsignadaAsistencia',{}, {
        update: { method: 'PUT' }
    });
}]);

