(function(){
    function MedioPago($resource){
        return $resource({},{},{
            listarMedios:{
                method: 'GET',
                url:formulario.global.api + '/medioPago/listarMediosPago/',
                isArray: true
            }
        })
    };
    angular.module('app.services').factory('MedioPago',MedioPago);
})();
