'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('Capacitacion', function ($resource) {
    return $resource(formulario.global.api + '/personaAsignada',{}, {
        create: { method: 'POST' }
    });
});


services.factory('CapacitacionFactory', function ($resource) {
    return {
        listarMunicipios: function(idDpto, callback) {
            return $resource(formulario.global.api + '/municipio/listar?idDpto='+ idDpto, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        listarDepartamentosDeUsuario: function(usuario,idPrueba, callback) {
            return $resource(formulario.global.api + '/UsuarioDepartamento/listar?usuario='+usuario+'&idPrueba='+idPrueba, {},{
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        listarCargos: function(callback) {
            return $resource(formulario.global.api + '/cargo/listar', {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        }                
    };    
});

