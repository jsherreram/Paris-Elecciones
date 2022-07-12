
(function () {
    function RevisaDocumento($resource) {
        return $resource(formulario.global.api + '/estado/', {}, {
            listarEstados: {
                method: 'GET',
                url: formulario.global.api + '/estado/listar/',
                isArray:true
            },
            buscarPersonas:{
                method:'GET',
                url:formulario.global.api+'/empleado/listarParaValidar',
                isArray:true
            },
            actualizarEstadoEmpleado:{
                method:'POST',
                url:formulario.global.api+'/empleado/actualizarEstadoEmpleado',
            }
         
        });
    }
    ;
    angular.module('app.services').factory( 'RevisaDocumento', RevisaDocumento);
})();


