(function () {
    function Departamento($resource) {
        return $resource({}, {}, {
            listar: {
                method: 'GET',
                url: formulario.global.api + '/departamento/listarDptoDane',
                isArray: true
            },
            listarDepartamentosNodo: {
                method: 'GET',
                url: formulario.global.api + '/departamento/listarDepartamentosNodo',
                isArray: true
            }

        })
    }
    ;
    angular.module('app.services').factory('DepartamentoDane', Departamento);
})();
