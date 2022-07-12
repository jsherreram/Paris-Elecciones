
/* global formulario */

(function () {
    function MonitoreoSitio($resource) {
        return $resource(formulario.global.api + '/UsuarioSitio/', {}, {
            listarSitiosyRPS: {
                method: 'GET',
                url: formulario.global.api + '/seguimiento/seguimientoGeneralSitioEvento/',
                isArray:true
            },
            listarEventosAplicacion: {
                method: 'GET',
                url: formulario.global.api + '/evento/listarEventosAplicacion/',
                isArray:true
            },
            update: {
                method: 'PUT',
                url: formulario.global.api + '/seguimiento/updatedesconetado/'
            }
        });
    }
    ;
    angular.module('app.services').factory( 'MonitoreoSitio', MonitoreoSitio);
})();


