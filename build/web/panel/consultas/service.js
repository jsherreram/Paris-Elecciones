'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('CatalogoAportanteFactory', function ($resource) {
    return {
	    query: function(documento, callback) {
	      return $resource(formulario.global.api + '/catalogo/aportante/buscar?tipo=documento&valor=' + documento, {}, {
	             query: { 
                         method: 'GET', 
                         isArray: true                    
                     }
	      }).query(callback);
	
	    }
	  }
});

services.factory('ProduccionFactory', function ($resource) {
    return {
	    query: function(fechaInicio, fechaFin, callback) {
	      return $resource(formulario.global.api + '/reportes/produccion?inicio=' + fechaInicio + "&fin=" + fechaFin , {}, {
	             query: { 
                         method: 'GET', 
                         isArray: true                        
                     }
	      }).query(callback);
	
	    }
	  }
});