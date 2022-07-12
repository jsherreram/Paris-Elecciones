(function () {
    function UsuarioDepartamento($resource) {
        return $resource(formulario.global.api + '/UsuarioDepartamento', {}, {
            usuarioDepartamento:{
                url:formulario.global.api+'/UsuarioDepartamento/listar',
                method:'GET',
                isArray:true
             }
        
        });
    }
    ;
    angular.module('app.services').factory('UsuarioDepartamento', UsuarioDepartamento);
})();