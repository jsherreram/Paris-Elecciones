'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('ArchivoAsistenciaFactory', function ($resource) {
    return {
        obtenerPruebas: function (callback) {
            return $resource(formulario.global.api + '/prueba/listarnocerradas', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        getSitios: function (idPrueba, codEvento,usuario, callback) {
            return $resource(formulario.global.api + '/UsuarioSitio/listarArchivoAsistenciaSitio?codEvento='+codEvento+'&usuario='+usuario+'&idPrueba=' + idPrueba, {}, {
                query: {
                    method: 'GET',
                    isArray: true
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
    };
});



