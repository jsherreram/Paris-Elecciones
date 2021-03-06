'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('Capacitacion', function ($resource) {
    return $resource(formulario.global.api + '/personaAsignada',{}, {
        create: { method: 'POST' }
    });
});

services.factory('EventoFactory', function ($resource) {
    return {
        getEmpleadoPruebaSesion: function(callback) {
            return $resource(formulario.global.api+'/empleado/empleadoSesion', {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
           getSitio: function(idsitio, callback) {
            return $resource(formulario.global.api+'/sitio/getById?id='+idsitio, {}, {
                query: { 
                    method: 'GET'
                }
            }).query(callback);
        },
        obtenerEventos: function(idPrueba,callback) {
            if (idPrueba > 0 ){
            }else{idPrueba = 0;
            }
            return $resource(formulario.global.api + '/evento/listarAll?idPrueba='+idPrueba, {},{
                query: {
                    method: 'GET',
                    isArray: true 
                }
            }).query(callback);
        },
        obtenerEventosDepartamento: function(idPrueba,usuario,callback) {
            if (idPrueba > 0 ){
            }else{idPrueba = 0;
            }
            return $resource(formulario.global.api + '/evento/listarDepartamento?idPrueba='+idPrueba+'&usuario='+usuario , {}, {
                query: {
                    method: 'GET',
                    isArray: true 
                }
            }).query(callback);
        },
        obtenerdepartamentouser: function(idPrueba,usuario,callback) {
            return $resource(formulario.global.api + '/departamento/listardepartamentouser?idprueba='+idPrueba+'&nrodoc='+usuario , {}, {

                query: {
                    method: 'GET',
                    isArray: true 
                }
            }).query(callback);
        },
        obtenerSitiosuser: function(idPrueba,usuario,callback) {
            return $resource(formulario.global.api + '/sitio/ListarSitioUsuario?idPrueba='+idPrueba+'&usuario='+usuario , {}, {
                query: {
                    method: 'GET',
                    isArray: true 
                }
            }).query(callback);
        },
        obtenerEvento: function(codigoEvento,callback) {
            return $resource(formulario.global.api + '/evento/listarEvento?codigoEvento='+codigoEvento, {},{
                query: {
                    method: 'GET',
                    isArray: true 
                }
            }).query(callback);
        },
        obtenerCargos: function(callback) {
            return $resource(formulario.global.api + '/cargo/listar', {},{
                query: {
                    method: 'GET',
                    isArray: true 
                }
            }).query(callback);
        },
        obtenerTipSesion: function(callback) {
            return $resource(formulario.global.api + '/evento/listarTipSesion', {},{
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };    
});

services.factory('EventoActualiza', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/evento/actualizar',{}, {
        update: { method: 'POST' }
    });
}]);

services.factory('CapacitacionEvento', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/evento/actualizarCapacitacionDcpe',{}, {
        update: { method: 'POST' }
    });
}]);

services.factory('EventoInsert', ['$resource' ,function ($resource) {
    return $resource(formulario.global.api + '/evento',{}, {
        insert: { method: 'POST' }
    });
}]);
