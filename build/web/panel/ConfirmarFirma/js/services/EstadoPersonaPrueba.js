(function () {
    function EstadoPersonaPrueba($resource) {
        return $resource(formulario.global.api + '/estadoPersonaPrueba', {}, {
        })
    }
    angular.module('app.services').factory('EstadoPersonaPrueba', EstadoPersonaPrueba);
})();