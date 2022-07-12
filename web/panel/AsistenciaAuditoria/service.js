'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('SeguimientoFactory', function ($resource) {
    return {
        listarDepartamentos: function(idEvento, usuario, callback) {
            return $resource(formulario.global.api+'/seguimiento/nacional?idEvento=' + idEvento+'&usuario='+usuario , {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        getDepartamento: function(idDepartamento, callback) {
            return $resource(formulario.global.api+'/departamento/buscar?idDpto=' + idDepartamento, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        getEventos: function(callback) {
            return $resource(formulario.global.api+'/evento/listar', {}, {
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
        getCargo: function(idCargo, callback) {
            return $resource(formulario.global.api+'/cargo/buscar?id='+idCargo, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        listarMunicipios: function(idEvento, idDepartamento, callback) {
            return $resource(formulario.global.api+'/seguimiento/departamental?idEvento='+idEvento+'&'+'idDepartamento='+idDepartamento, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarCargosMunicipio: function(idEvento, idDepartamento, idMunicipio, callback) {
            return $resource(formulario.global.api+'/seguimiento/municipal?idEvento='+idEvento+'&idDepartamento='+idDepartamento+'&idMunicipio='+idMunicipio, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        getMunicipio: function(idDepartamento, idMunicipio , callback) {
            return $resource(formulario.global.api+'/municipio/buscar?idDpto='+idDepartamento+'&idMpio='+idMunicipio, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        listarCargosZona: function(idEvento, idDepartamento, idMunicipio, idCargo ,callback) {
            return $resource(formulario.global.api+'/seguimiento/zonal?idEvento='+idEvento+'&idDepartamento='+idDepartamento+'&idMunicipio='+idMunicipio+'&idCargo='+idCargo, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarPuesto: function(idEvento, idDepartamento, idMunicipio, idCargo, idZona, callback) {
            return $resource(formulario.global.api+'/seguimiento/puesto?idEvento='+idEvento+'&idDepartamento='+idDepartamento+'&idMunicipio='+idMunicipio+'&idCargo='+idCargo+'&idZona='+idZona, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
                
        listarUbicacion: function(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto, callback) {
            return $resource(formulario.global.api+'/personaAsignada/listar?idEvento='+idEvento+'&idDepartamento='+idDepartamento+'&idMunicipio='+idMunicipio+'&idCargo='+idCargo+'&idZona='+idZona+'&idPuesto='+idPuesto, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
                
        listarPersona: function(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto, idUbicacion, callback) {
            return $resource(formulario.global.api+'/personaAsignada/buscar?idEvento='+idEvento+'&idDepartamento='+idDepartamento+'&idMunicipio='+idMunicipio+'&idCargo='+idCargo+'&idZona='+idZona+'&idPuesto='+idPuesto+'&idUbicacion='+idUbicacion, {}, {
                query: { 
                    method: 'GET'
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
        listarSoportes: function(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto, callback) {
            return $resource(formulario.global.api+'/soporteAsistencia/listar?idEvento='+idEvento+'&idDepartamento='+idDepartamento+'&idMunicipio='+idMunicipio+'&idCargo='+idCargo+'&idZona='+idZona+'&idPuesto='+idPuesto, {}, {
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
        }       
    };    
});

services.factory('PersonaAsignada', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/personaAsignadaAsistencia',{}, {
        update: { method: 'PUT' }
    });
}]);

