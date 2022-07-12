(function(){
    function OperadorCelular($resource){
        return $resource({},{},{
            listar:{
                method: 'GET',
                url:formulario.global.api + '/operadorcelular/listarOperadores/',
                isArray: true
            }
        })
    };
    angular.module('app.services').factory('OperadorCelular',OperadorCelular);
})();
