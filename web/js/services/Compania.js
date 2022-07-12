/* global formulario */

(function () {
    function Compania($resource) {
        return $resource(formulario.global.api + '/Compania', {}, {
            listar: {
                method: 'GET',
                url: formulario.global.api + '/Compania/listar',
                isArray: true
            }
        });
    }
    ;
    angular.module('app.services').factory('Compania', Compania);
})();
	