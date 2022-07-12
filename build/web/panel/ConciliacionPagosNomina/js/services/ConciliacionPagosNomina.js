/* global formulario */

(function () {
    function ConciliacionPagosNominaService($resource) {
        return $resource(formulario.global.api + '/nombramiento/', {}, {
            asignarPersonaEvento: {
                method: 'POST',
                url: formulario.global.api + '/nombramiento/asignarPersonaEvento'
            },
            consultarPagos: {
                method: 'GET',
                url: formulario.global.api + '/sitio/consultarPorFiltros/',
                isArray: true
            },
        });
    }
    ;
    angular.module('app.services').factory('ConciliacionPagosNominaService', ConciliacionPagosNominaService);
})();