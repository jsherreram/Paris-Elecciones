
(function () {
    function AsignaSuplente($resource) {
        return $resource(formulario.global.api + '/UsuarioSitio/', {}, {
            getPersonaAsignada: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/buscar',
                isArray: false
            },
            buscarSuplentesConAsistenciaCapacitacion: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/buscarSuplentesConAsistenciaCapacitacion',
                isArray: true
            },
            cambiarNombramiento: {
                method: 'GET',
                url: formulario.global.api + '/nombramiento/cambiarNombramiento',
                isArray: false
            }
        });
    }
    ;
    angular.module('app.services').factory('AsignaSuplente', AsignaSuplente);
})();

