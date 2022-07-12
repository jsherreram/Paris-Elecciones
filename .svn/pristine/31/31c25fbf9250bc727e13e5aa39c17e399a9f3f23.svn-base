/* global formulario */

(function () {
    function Cargo($resource) {
        return $resource(formulario.global.api + '/cargo', {}, {
            listar: {
                method: 'GET',
                url: formulario.global.api + '/cargo/listar',
                isArray: true
            }
        });
    };
    angular.module('app.services').factory('Cargo', Cargo);
})();
