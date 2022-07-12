
(function () {
    function Sitio($resource) {
        return $resource(formulario.global.api + '/UsuarioSitio/', {}, {
            update: {
                method: 'PUT',
                url: formulario.global.api + '/seguimiento/updatedesconetado/'
            }
        });
    }
    ;
    angular.module('app.services').factory( 'Sitio', Sitio);
})();


