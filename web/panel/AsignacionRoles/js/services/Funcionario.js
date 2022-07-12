(function () {
    function Funcionario($resource) {
        return $resource(formulario.global.api + '/funcionario/', {}, {
            listarJsonFuncionarios: {
                method: 'GET',
                url: formulario.global.api + '/empleadoPrueba/listarJsonFuncionarios',
                isArray:true
            },
            buscarJsonFuncionario: {
                method: 'GET',
                url: formulario.global.api + '/empleadoPrueba/buscarJsonFuncionario',
                isArray:true
            },
             buscarJsonFuncionarioCedula: {
                method: 'GET',
                url: formulario.global.api + '/empleadoPrueba/buscarJsonFuncionarioCedula',
                isArray:true
            },
            listarGruposFuncionarios: {
                method: 'GET',
                url: formulario.global.api + '/grupo/listarGruposFuncionarios',
                isArray:true
            },
            asignarRoles: {
                method: 'POST',
                url: formulario.global.api + '/empleadoPrueba/asignarRoles',
            },
            empleadoSesion: {
                method: 'GET',
                url: formulario.global.api + '/empleado/empleadoSesion',
            }
            
        });
    }
    ;
    angular.module('app.services').factory( 'Funcionario', Funcionario);
})();
