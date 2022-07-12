
(function () {
    function aplicacionServ($resource) {
        return $resource(formulario.global.api + '/EdicionCuadro/', {}, {
            listarFiltros: {
                method: 'POST',
                url: formulario.global.api + '/EdicionCuadro/filtros/',
                isArray:true
            },
            getConsulta: {
                method: 'POST',
                url: formulario.global.api + '/EdicionCuadro/consulta/',
                isArray:true
            },
            update: {
                method: 'PUT',
                url: formulario.global.api + '/EdicionCuadro/actualizar/'
            }
        });
    }
    ;
    angular.module('app.services').factory( 'aplicacionServ', aplicacionServ);
})();

