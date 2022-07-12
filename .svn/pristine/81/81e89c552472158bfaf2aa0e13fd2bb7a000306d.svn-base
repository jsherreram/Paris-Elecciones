(function(){
    function ZonaIcfes($resource){
        return $resource({},{},{
            listar:{
                method: 'GET',
                url:formulario.global.api + '/zonaIcfes/listar',
                isArray: true
            }
        })
    };
    angular.module('app.services').factory('ZonaIcfes',ZonaIcfes);
})();
