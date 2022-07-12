(function(){
    function TipoDocumento($resource) {
    return $resource(formulario.global.api + '/TiposDocumento/listarValidos', {}, {
        query: {
            method: 'GET',
            isArray: true
        }
    });
}
;
angular.module('app.services').factory('TipoDocumento', TipoDocumento);
})();