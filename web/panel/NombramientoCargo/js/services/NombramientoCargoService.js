/* global formulario */

(function () {
    function NombramientoCargoService($resource) {
        return $resource(formulario.global.api + '/nombramiento/', {}, {
            getNombramientoCargoFiltros: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/listarPorCargoEventoMunicipio',
                isArray: true
            },
            actualizarNombramiento: {
                method: 'PUT',
                url: formulario.global.api + '/personaAsignada'
            },
            buscarPersonasNombramiento: {
                method: 'GET',
                url: formulario.global.api + '/nombramiento/buscarPersonasNombramiento',
                isArray: true
            },
            getNombramientoById: {
                method: 'GET',
                url: formulario.global.api + '/nombramiento/getNombramientoCargoById'
            }
        });
    }
    ;
    angular.module('app.services').factory('NombramientoCargoService', NombramientoCargoService);
})();