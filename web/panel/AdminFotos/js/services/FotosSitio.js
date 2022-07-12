(function () {
    function FotosSitio($resource) {
        return $resource(formulario.global.api + '/sitio/', {}, {
            getFotosSitio: {
                method: 'POST',
                url: formulario.global.api + '/sitio/getFotosSitio',
                isArray: true
            }
        });
    }
    ;
    angular.module('app.services').factory('FotosSitio', FotosSitio);
})();