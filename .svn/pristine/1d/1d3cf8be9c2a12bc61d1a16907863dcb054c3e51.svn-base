(function () {
    function EmpleadoPrueba($resource) {
        return $resource(formulario.global.api + '/empleadoPrueba/', {}, {
            query: {
                method: 'GET',
                url: formulario.global.api + '/empleadoPrueba/getPruebaEmpleado'
            },
            queryById:{
                method: 'GET',
                url: formulario.global.api + '/empleadoPrueba/getPruebaEmpleadoByID/'
            },
            update:{
                method: 'PUT',
                url: formulario.global.api + '/empleadoPrueba/update-estado-prueba/'
            }
        });
    }
    ;
    angular.module('app.services').factory( 'EmpleadoPrueba', EmpleadoPrueba);
})();