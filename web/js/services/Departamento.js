(function(){
    function Departamento($resource){
        return $resource({},{},{
            listar:{
                method: 'GET',
                url:formulario.global.api + '/departamento/listar',
                isArray: true
            },
            getUsuarioPrueba: {
                method: 'GET',
                url: formulario.global.api + '/departamento/listardepartamentouser',
                isArray: true
            }
        })
    };
    angular.module('app.services').factory('Departamento', Departamento);
})();
