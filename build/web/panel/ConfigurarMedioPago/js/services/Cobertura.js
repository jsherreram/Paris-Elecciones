(function () {
    function Cobertura($resource) {
        return $resource(formulario.global.api + '/cobertura/', {}, { 
            listarMediosMunicipio: {
                method: 'GET',
                url: formulario.global.api + '/medioPago/listarMediosMunicipio',
                isArray:true
            },
            listarMediosMunicipioAsignados: {
                method: 'GET',
                url: formulario.global.api + '/medioPago/listarMediosMunicipioAsignados',
                 isArray:true
            },
            listarStatus:  {
                    method: 'GET', 
                    url: formulario.global.api + '/statuscargue/listarPorRegistro',
                    isArray:true
            },
            registrarCobertura: {
                method: 'POST',
                url: formulario.global.api + '/medioPago/registrarCobertura',
            }
        
        });
    }
    ;
    angular.module('app.services').factory( 'Cobertura', Cobertura);
})();
