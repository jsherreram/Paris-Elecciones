(function () {
    function Sesion($resource) {
        return $resource(formulario.global.api + '/empleado', {}, {
            getBySession: {
                method: 'GET',
                url: formulario.global.api + '/empleado/buscarPorSesion'
            },
            getEmpleadoPruebaSesion: {
                method: 'GET',
                url: formulario.global.api + '/empleado/empleadoSesion'
            },
            getSitios: {
                method: 'GET',
                url: formulario.global.api + '/UsuarioSitio/listar',
                isArray: true
            },
       
            getEventoActualParaSitio: {
                method: 'GET',
                url: formulario.global.api + '/evento/GetEventoActualParaTomaAsistencia',
                isArray: false
            },
            getEncuestaSesion: {
                method: 'GET',
                url: formulario.global.api + '/encuesta/encuestaSesion',
                isArray: false
            }
        });
    }
    ;
    angular.module('app.services').factory('Sesion', Sesion);
})();