'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('SeguimientoFactory', function ($resource) {
    return {

        listarConsolidadoNacional: function(idEvento, callback) {
            return $resource(formulario.global.api+'/seguimiento/nombramientoCargosNacional?idEvento=' + idEvento, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        capacitacionCargosNacionalDpto: function(idEvento,idD,idPrueba, callback) {
            return $resource(formulario.global.api+'/seguimiento/capacitacionCargosNacionalDpto?idEvento=' + idEvento+'&idD='+idD+'&idPrueba='+idPrueba, {}, {
                query: { 
                    method: 'GET',  
                    isArray:true
                }
            }).query(callback);
        },
        listarConsolidadoNacionalSitios: function(idPrueba, callback) {
            return $resource(formulario.global.api+'/seguimiento/nombramientoNacionalSitios?idPrueba=' + idPrueba, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarDepartamentos: function(idEvento, codigoCargo, callback) {
            return $resource(formulario.global.api+'/seguimiento/nombramientoNacional?idEvento=' + idEvento+'&codigoCargo='+codigoCargo, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarDepartamentosCapacitacion: function(idPrueba, codigoCargo, callback) {
            return $resource(formulario.global.api+'/seguimiento/listarDepartamentosCapacitacion?idPrueba=' + idPrueba+'&codigoCargo='+codigoCargo, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarDepartamentosSitios: function(idPrueba, callback) {
            return $resource(formulario.global.api+'/seguimiento/nombramientoNacionalSitios?idPrueba=' + idPrueba, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarMunicipios: function(idEvento, idDepartamento, codigoCargo, callback) {
            return $resource(formulario.global.api+'/seguimiento/nombramientoDepartamental?idEvento='+idEvento+'&'+'idDepartamento='+idDepartamento+'&codigoCargo='+codigoCargo, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarSitiosAll: function(idDepartamento,idPrueba, callback) {
            return $resource(formulario.global.api+'/seguimiento/listarSitiosAll?idDepartamento='+idDepartamento+'&idPrueba='+idPrueba, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarPuesto: function(idEvento, idDepartamento, idMunicipio, idCargo, callback) {
            return $resource(formulario.global.api+'/seguimiento/nombramientoMunicipal?idEvento='+idEvento+'&idDepartamento='+idDepartamento+'&idMunicipio='+idMunicipio+'&codigoCargo='+idCargo, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarPuestoSitios: function(idPrueba, callback) {
            return $resource(formulario.global.api+'/seguimiento/nombramientoMunicipalSitios?idPrueba=' + idPrueba, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        listarUbicacion: function(idEvento, idDepartamento, idMunicipio, idCargo, idPuesto, callback) {
            return $resource(formulario.global.api+'/seguimiento/nombramientoSitio?idEvento='+idEvento+'&idDepartamento='+idDepartamento+'&idMunicipio='+idMunicipio+'&codigoCargo='+idCargo+'&idPuesto='+idPuesto, {}, {
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
        getMunicipio: function(idDepartamento, idMunicipio , callback) {
            return $resource(formulario.global.api+'/municipio/buscar?idDpto='+idDepartamento+'&idMpio='+idMunicipio, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        }
    };    
});


services.factory('EventoFactory', function($resource) {
    return {
        obtenerPruebas: function(callback) {
            return $resource(formulario.global.api + '/prueba/listar', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerEventos: function(idPrueba, callback) {
            return $resource(formulario.global.api + '/evento/listarEventosAplicacion?idPrueba='+idPrueba,{}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };

});



