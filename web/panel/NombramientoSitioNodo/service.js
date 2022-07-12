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
        getSitio: function(idDivipol, callback) {
            return $resource(formulario.global.api+'/sitio/getById?id='+idDivipol, {}, {
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
        getSitiosSinCierre: function(idPrueba, usuario, callback) {
            return $resource(formulario.global.api+'/UsuarioSitio/listarSinCierre?usuario='+usuario+'&idPrueba='+idPrueba, {}, {
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
        
        GetEventoNombramiento: function(idPrueba, callback) {
            return $resource(formulario.global.api+'/evento/GetEventoNombramiento?idPrueba='+idPrueba, {}, {
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
                
        listarUbicacion: function(idPrueba, idDivipol, callback) {
            return $resource(formulario.global.api+'/nombramiento/listarNombramiento?idPrueba='+idPrueba+'&idDivipol='+idDivipol, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        getPersonaAsignada: function(id, callback) {
            return $resource(formulario.global.api+'/nombramiento/buscar?id='+id,{}, {
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
        getEstadoActual: function(nrodoc, idPrueba, codigoCargo, callback) {
            return $resource(formulario.global.api+'/empleadoPrueba/getEstadoActual?nrodoc='+nrodoc+'&idPrueba='+idPrueba+'&codigoCargo='+codigoCargo,{}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        }        
    };    
});

services.factory('Nombramiento', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/nombramiento',{}, {
        update: { method: 'PUT' }
    });
}]);