'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('CargoFactory', function ($resource) {
    return {
        obtenerCargos: function(callback) {
            return $resource(formulario.global.api + '/cargo/listar', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerCargo: function(idCargo,callback) {
            return $resource(formulario.global.api + '/cargo/buscar?id='+idCargo, {},{
                query: {
                    method: 'GET'
                }
            }).query(callback);
        },
        obtenerNivCargo: function(callback) {
            return $resource(formulario.global.api + '/cargo/listarNivCargo', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };    
});

services.factory('CargoActualiza', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/cargo/actualizar',{}, {
        update: { method: 'POST' }
    });
}]);

services.factory('CargoInsert', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/cargo/crear',{}, {
        insert: { method: 'POST' }
    });
}]);
