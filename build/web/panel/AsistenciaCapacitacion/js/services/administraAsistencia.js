(function () {
    function AdministraAsistencia($resource) {
        return $resource(formulario.global.api + '/asistencia/', {}, {
            listarAsistencia: {
                method: 'GET',
                url: formulario.global.api + '/asistencia/listarAsistenciaXSitio',
                isArray: true
            },
            getSitio: {
                method: 'GET',
                url: formulario.global.api + '/sitio/getById',
                isArray: false
            },
            buscarSitioPorCodigoPrueba:{
              method:'GET',
              url:formulario.global.api+'/sitio/buscarPorCodigoPrueba',
              isArray:false
            },
            
            getEvento: {
                method: 'GET',
                url: formulario.global.api + '/evento/buscar',
                isArray: false
            },
            getAsignacion: {
                method: 'GET',
                url: formulario.global.api + '/asistencia/getAsignacion',
                isArray: true
            },
            validarAsistenciaEmpleado: {
                method: 'GET',
                url: formulario.global.api + '/asistencia/validaAsistenciaEmpleado',
                isArray: false
            },
            registrarAsistencia: {
                method: 'GET',
                url: formulario.global.api + '/asistencia/marcarAsistencia2'
            }
        });
    }
    ;
    angular.module('app.services').factory('AdministraAsistencia', AdministraAsistencia);
})();
