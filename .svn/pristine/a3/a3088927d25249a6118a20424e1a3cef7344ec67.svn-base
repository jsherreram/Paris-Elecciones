(function(){
    function Localidad($resource){
        return $resource({},{},{
           getByMunicipio:{
                method: 'GET',
                url:formulario.global.api + '/localidad/listar/',
                isArray: true
            }
        })
    };
    angular.module('app.services').factory('LocalidadDane',Localidad);
})();
