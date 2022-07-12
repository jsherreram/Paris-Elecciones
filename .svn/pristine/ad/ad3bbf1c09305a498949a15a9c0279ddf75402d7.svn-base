
(function () {
    function ListarSitio($resource) {
        return $resource(formulario.global.api + '/UsuarioSitio/', {}, {
            listarSitios: {
                method: 'GET',
                url: formulario.global.api + '/UsuarioSitio/listarXEvento/',
                isArray:true
            },
            
             listarEventosCapacitacion: {
                method: 'GET',
                url: formulario.global.api + '/evento/listarEventosCapacitacion/',
                isArray:true
            }
        });
    }
    ;
    angular.module('app.services').factory( 'ListarSitio', ListarSitio);
})();


