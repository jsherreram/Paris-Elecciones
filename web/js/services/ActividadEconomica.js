(function(){
    function ActividadEconomica($resource){
        return $resource({},{},{
            query:{
                url:formulario.global.api + '/actividadEconomica/listar',
                method: 'GET',
                isArray: true
            }
        });
    }
    angular.module('app.services').factory('ActividadEconomica',ActividadEconomica);
})();
