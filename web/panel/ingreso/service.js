'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('PersonaFactory', function ($resource) {
    return {
        listarPersonas: function(idDpto, idEstado , callback) {
            return $resource(formulario.global.api + '/empleado/listarPorEstado?idDpto='+idDpto+'&idEstado='+idEstado, {},{
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        listarPersonasJson: function(idDpto, idEstado , callback) {
            return $resource(formulario.global.api + '/empleado/listarPorEstadoJson?idDpto='+idDpto+'&idEstado='+idEstado, {},{
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        getPersona: function(idPersona, callback) {
            return $resource(formulario.global.api + '/empleado/buscar?id=' + idPersona, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
                
        listarDepartamentos: function(callback) {
            return $resource(formulario.global.api + '/departamento/listar', {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
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
        listarActividad: function(callback) {
            return $resource(formulario.global.api + '/actividadEconomica/listar',{}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        crear: function(empleado, callback) {
            return $resource(formulario.global.api + '/empleado/crear',empleado, {
                update: { 
                    method: 'POST'
                }
            }).save();
        }                
    };    
});

services.factory('PersonaFactory2', function ($resource) {
    return $resource(formulario.global.api + '/empleado',{}, {
        create: { method: 'POST' }
    });
});


services.factory('Empleado', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/empleado',{}, {
        update: { method: 'PUT' }
    });
}]);


