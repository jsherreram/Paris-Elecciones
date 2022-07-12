'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('TiposFormularioFactory', function ($resource) {
    return {
	    query: function(fechaInicio, fechaFin, callback) {
	      return $resource(formulario.global.api + '/reportes/tipos-forma?inicio=' + fechaInicio + "&fin=" + fechaFin , {}, {
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