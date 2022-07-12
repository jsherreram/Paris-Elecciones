
(function () {
    function CargaArchivo($resource) {
        return $resource(formulario.global.api + '/statuscargue/', {}, {
            listarStatus: {
                method: 'GET',
                url: formulario.global.api + '/statuscargue/listar/',
                isArray:true
            },
         
        });
    }
    ;
    angular.module('app.services').factory( 'CargaArchivo', CargaArchivo);
})();


