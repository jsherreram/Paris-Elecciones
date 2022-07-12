
(function () {
    function ValidaAsistencia($resource) {
        return $resource(formulario.global.api + '/sitio/', {}, {
            buscarSitiosPorDepartamento: {
                method: 'GET',
                url: formulario.global.api + '/sitio/buscarSitioPorDepartamento',
                isArray: true
            },
            buscarEventosPorSitio: {
                method: 'GET',
                url: formulario.global.api + '/evento/listarEventosDepartamento/',
                isArray: true
            },
            listarAsistenciaSitioEvento: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/asistenciaSitioEvento',
                isArray: true
            },
            guardaValidacionAsistencia: {
                method: 'GET',
                url: formulario.global.api + '/asistencia/guardaValidacionAsistencia',
            },
            guardaSitioEventoValidado: {
                methos: 'GET',
                url: formulario.global.api + '/sitio/guardaSitioEventoValidado',
            },
            getSitio: {
                method: 'GET',
                url: formulario.global.api + '/sitio/buscarPorCodigoPrueba',
            },
            buscarSitioEventoValidado:{
                method:'GET',
                url:formulario.global.api+'/sitio/buscarSitioEventoValidado',
                isArray:true
            }
        });
    }
    ;
    angular.module('app.services').factory('ValidaAsistencia', ValidaAsistencia);
})();

