/* global formulario */

(function () {
    function Sitio($resource) {
        return $resource(formulario.global.api + '/sitio/', {}, {
            listarTipos: {
                method: 'GET',
                url: formulario.global.api + '/sitio/listarTiposSitio',
                isArray: true
            },
            getSitios: {
                method: 'GET',
                url: formulario.global.api + '/sitio/consultarPorFiltros/',
                isArray: true
            },
            listarEstadosSitio: {
                method: 'GET',
                url: formulario.global.api + '/sitio/listarEstadosSitio',
                isArray: true
            },
            listarCategoriasSitio: {
                method: 'GET',
                url: formulario.global.api + '/sitio/listarCategoriasSitio',
                isArray: true
            },
            listarPDSSinAsignar: {
                method: 'GET',
                url: formulario.global.api + '/sitio/listarPDSSinAsignar',
                isArray: true
            },
            listarPDSAsignados: {
                method: 'GET',
                url: formulario.global.api + '/sitio/listarPDSAsignados',
                isArray: true
            },
            guardarPDSAsignados: {
                method: 'POST',
                url: formulario.global.api + '/sitio/guardarPDSAsignados/',
                isArray: true
            },
            obtenerSitioPorId: {
                method: 'GET',
                url: formulario.global.api + '/sitio/obtenerSitioPorId/',
                isArray: false
            },
            obtenerSitioPorCodigoPuesto: {
                method: 'GET',
                url: formulario.global.api + '/sitio/buscarPorCodigoPrueba/',
                isArray: false
            },
            filtrarPorGeolocalizacion: {
                method: 'GET',
                url: formulario.global.api + '/sitio/filtrarPorGeolocalizacion/',
                isArray: true
            },
            obtenerTotalSitios: {
                method: 'GET',
                url: formulario.global.api + '/sitio/obtenerTotalSitios/',
                isArray: false
            },
            updateCoordenadas: {
                method: 'PUT',
                url: formulario.global.api + '/sitio/actualizarCoordenadas'
            },
            update: {
                method: 'PUT',
                url: formulario.global.api + '/sitio'
            },
            crear: {
                method: 'POST',
                url: formulario.global.api + '/sitio'
            },
            listarHistorialContactosSitio: {
                method: 'GET',
                url: formulario.global.api + '/sitio/historialContactosSitio/',
                isArray: true
            },
            getFotosSitio: {
                method: 'POST',
                url: formulario.global.api + '/sitio/getFotosSitio',
                isArray: true
            },
            eliminarFotoSitio: {
                method: 'POST',
                url: formulario.global.api + '/sitio/eliminarFotoSitio'
            },
            listar: {
                method: 'GET',
                url: formulario.global.api + '/sitio/listar',
                isArray: true
            }, 
            getPDSByPrueba : {
                method: 'GET',
                url: formulario.global.api + '/sitio/getPDSByPrueba',
                isArray: true
            }
        });
    }
    ;
    angular.module('app.services').factory('Sitio', Sitio);
})();

