/* global formulario */

(function () {
    function Evento($resource) {
        return $resource(formulario.global.api + '/evento', {}, {
            listar: {
                method: 'GET',
                url: formulario.global.api + '/evento/listar',
                isArray: true
            },
              listarPorDepartamento: {
                method: 'GET',
                url: formulario.global.api + '/evento/listarEventosDepartamento',
                isArray: true
            },
            listarEventoAplicacion:{
                method: 'GET',
                url: formulario.global.api + '/evento/listarEventosAplicacion',
                isArray: true
            }
            
        });
    };
    angular.module('app.services').factory('Evento', Evento);
})();
