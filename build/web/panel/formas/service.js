'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('TipoFormaFactory', function ($resource) {
    return {
        listar: function(callback) {
            return $resource(formulario.global.api + '/tipo-forma/listar/' + formulario.global.app, {}, {
                query: { 
                    method: 'GET', 
                    isArray:true
                }
            }).query(callback);
        },
        obtener: function(tipoFormaId, callback) {
            return $resource(formulario.global.api + '/tipo-forma/obtener/' + tipoFormaId, {}, {
                query: { 
                    method: 'GET'                    
                }
            }).query(callback);
        }
    };    
});

services.factory('FormularioFactory', function ($resource) {
    return {
	    query: function(tipoForma, callback) {
	      return $resource(formulario.global.api + '/forma/listar/' + tipoForma, {}, {
	             query: { 
                         method: 'GET', 
                         isArray: true
                     }
	      }).query(callback);
	
	    },
            suscribir: function(formaId, callback) {
	      return $resource(formulario.global.api + '/forma/suscribir/' + formaId, {}, {
	             query: { 
                         method: 'POST'                         
                     }
	      }).query(callback);	
	    }
    };
});

services.factory('ProduccionFactory', function ($resource) {
    return {
	    query: function(fechaInicio, fechaFin, callback) {
	      return $resource(formulario.global.api + '/reportes/produccion-usuario?inicio=' + fechaInicio + "&fin=" + fechaFin , {}, {
	             query: { 
                         method: 'GET', 
                         isArray: true
                     }
	      }).query(callback);
	
	    }
	  }
});