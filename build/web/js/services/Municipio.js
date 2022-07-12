(function(){
    function Municipio($resource){
        return $resource({},{},{
            getByDpto:{
                method: 'GET',
                url:formulario.global.api + '/municipio/listar/',
                isArray: true
            }
        })
    };
    angular.module('app.services').factory('Municipio',Municipio);
})();
