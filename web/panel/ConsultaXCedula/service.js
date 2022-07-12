'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('SeguimientoFactory', function ($resource) {
    return {
        listarUbicacion: function(nrodoc, callback) {
            return $resource(formulario.global.api+'/personaAsignada/asistencia?nrodoc='+nrodoc, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        asignar: function(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto, idUbicacion, nroDoc,callback) {
            return $resource(formulario.global.api+'/seguimiento/persona/asignar/'+idEvento+'/'+idDepartamento+'/'+idMunicipio+'/'+idCargo+'/'+idZona+'/'+idPuesto+'/'+idUbicacion+'/'+nroDoc, {}, {
                update: { 
                    method: 'POST'
                }
            }).save();
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

