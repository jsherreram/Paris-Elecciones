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
        getEventos: function(idPrueba, usuario, callback) {
            return $resource(formulario.global.api+'/evento/listarXSitio?idPrueba='+idPrueba+'&usuario='+usuario, {}, {
                query: {
                    method: 'GET'
                }
            }).query(callback);
        },
        
        getEvento: function(idEvento, callback) {
            return $resource(formulario.global.api+'/evento/buscar?id='+idEvento, {}, {
                query: { 
                    method: 'GET'
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

