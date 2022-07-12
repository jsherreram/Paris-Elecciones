/* global formulario */

(function () {
    function Campagna($resource) {
        return $resource({}, {}, {
            listar: {
                method: 'GET',
                url: 'http://190.60.87.180/gestionDinomi/webresources/campagna/listarCampagnasActivas',
                isArray: true
            },
            consultarDatosCampagna: {
                method: 'GET',
                url: formulario.global.api + '/campagna/consultarDatosCampagna'
            },
            consultarDatosCampagnaEncuesta: {
                method: 'GET',
                url: formulario.global.api + '/campagna/consultarDatosCampagnaEncuesta'
            },
            insertarDatosCampagna: {
                method: 'POST',
                url: 'http://190.60.87.180/gestionDinomi/webresources/campagna/guardarDatosCampagna'
            }
        });
    }
    ;
    angular.module('app.services').factory('Campagna', Campagna);
})();
