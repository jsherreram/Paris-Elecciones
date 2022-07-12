
(function () {
    function listarSitios($resource) {
        return $resource(formulario.global.api + '/UsuarioSitio/', {}, {
            listarSitiosyRPS: {
                method: 'GET',
                url: formulario.global.api + '/UsuarioSitio/listarSitiosyRPS/',
                isArray:true
            },
         
        });
    }
    ;
    angular.module('app.services').factory( 'ListarSitio', listarSitios);
})();


