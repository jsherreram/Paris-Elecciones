(function () {
    function MedioPago($resource) {
        return $resource(formulario.global.api + '/medioPago/', {}, {
            getMediosPago: {
                method: 'GET',
                url: formulario.global.api + '/medioPago/listarMedios',
                isArray:true
            },
            getTiposMediosPago:{
                method: 'GET',
                url: formulario.global.api + '/medioPago/listarTiposMediosPago/',
                isArray:true
            },   
            registrarMedioPago: {
                method: 'POST',
                url: formulario.global.api + '/medioPago'
            },   
            actualizarMedioPago: {
                method: 'PUT',
                url: formulario.global.api + '/medioPago'
            },
            findMedioPago:{
                method:'GET',
                url:formulario.global.api+'/medioPago/findMedioPago/'
            },
            listarMediosMunicipio: {
                method: 'GET',
                url: formulario.global.api + '/medioPago/listarMediosMunicipio',
                isArray:true
            }

        });
    }
    ;
    angular.module('app.services').factory( 'MedioPago', MedioPago);
})();
