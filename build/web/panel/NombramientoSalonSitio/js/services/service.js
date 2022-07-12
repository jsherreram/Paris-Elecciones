'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('SeguimientoFactory', function ($resource) {
    return {
      
                
        listarUbicacion: function(idPrueba, idPuesto, callback) {
            return $resource(formulario.global.api+'/asignacionSalon/listarPersonasSitio?idPrueba='+idPrueba+'&idPuesto='+idPuesto, {}, {
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
          getParametrosEncuesta:function(idEvento, callback){
            return $resource(formulario.global.api+'/parametroencuesta/listarParametrosPorEvento?idEvento='+idEvento, {},{
                query:{
                    method:'GET',
                    isArray:true
                }
            }).query(callback);
        },
        getById:function(idDivipol, callback){
             return $resource(formulario.global.api+'/sitio/getById?id='+idDivipol, {},{
                query:{
                    method:'GET',
                    isArray:false
                }
            }).query(callback); 
        }
        
           
    };    
});

services.factory('Nombramiento', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api+'/asignacionSalon',{}, {
        updateSalon: { method: 'PUT' }
    });
}]);