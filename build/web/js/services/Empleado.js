(function () {
    function Empleado($resource) {
        return $resource(formulario.global.api + '/empleado/',
                {tipodoc: '@tipodoc', nrodoc: '@nrodoc'}, {
            query: {
                method: 'GET',
                isArray: false,
                url: formulario.global.api + '/empleado/buscarDocumento2?nroDocumento=:nrodoc&tipoDocumento=:tipodoc'
            },
            getEmpleado: {
                method: 'GET',
                isArray: false,
                url: formulario.global.api + '/empleado/buscar'
            },
            getEmpleadoSinHuella:{
                method:'GET',
                isArray:false,
                url:formulario.global.api+'/empleado/buscarSinHuella'
            },
            getPersonaByCedula: {
                method: 'GET',
                isArray: false,
                url: formulario.global.api + '/empleado/buscarNroDoc'
            },
            getEventos: {
                method: 'GET',
                isArray: true,
                url: formulario.global.api + '/empleado/eventos'
            },
            recoveryPassword: {
                method: 'POST',
                url: formulario.global.api + '/UsuarioLogin/restablecerContrasena'
            },
            getBySession: {
                method: 'GET',
                url: formulario.global.api + '/empleado/buscarPorSesion'
            },
            update: {
                method: 'PUT',
                url: formulario.global.api + '/empleado'
            },
            registrarDatosBasicos: {
                method: 'POST',
                url: formulario.global.api + '/empleado/insertarDatosBasicos'
            },
            enrolar: {
                method: 'POST',
                url: formulario.global.api + '/empleado/enrolar'
            },
            insertarDatosPrincipalesRDS: {
                method: 'POST',
                url: formulario.global.api + '/empleado/insertarDatosPrincipalesRDS'
            },
            actualizarDatosPrincipalesRDS: {
                method: 'POST',
                url: formulario.global.api + '/empleado/actualizarDatosPrincipalesRDS'
            },
            actualizarDatosPrincipalesPistoleo: {
                method: 'POST',
                url: formulario.global.api + '/empleado/actualizarDatosPrincipalesPistoleo'
            },
            actualizarDatosBasicos: {
                method: 'POST',
                url: formulario.global.api + '/empleado/actualizarDatosBasicos'
            },
            getHistorialArchivos: {
                method: 'GET',
                isArray: true,
                url: formulario.global.api + '/empleado/verHistorial'

            },
            getEmpleadoPruebaSesion: {
                method: 'GET',
                url: formulario.global.api + '/empleado/empleadoSesion'
            },
            updateCoordenadas: {
                method: 'PUT',
                url: formulario.global.api + '/empleado/actualizarCoordenadas'
            },
            buscarAsignados: {
                method: 'GET',
                url: formulario.global.api + '/empleado/buscarAsignados',
                isArray: true
            },
            buscarEmpleadosPorFiltro: {
                method: 'GET',
                isArray: true,
                url: formulario.global.api + '/empleado/buscarEmpleadosPorFiltro'
            },
            buscarEmpleadosPorDepartamentoMunicipio: {
                method: 'GET',
                isArray: true,
                url: formulario.global.api + '/empleado/buscarEmpleadosPorDepartamentoMunicipio'
            },
            buscarEmpleadoPorFiltroBasico: {
                method: 'GET',
                isArray: true,
                url: formulario.global.api + '/empleado/busquedaBasica'
            },
            buscarEmpleadoPorFiltroBasico_1: {
                method: 'GET',
                isArray: true,
                url: formulario.global.api + '/empleado/busquedaBasica_1'
            },
            buscarDocumentosFaltantes: {
                method: 'GET',
                isArray: false,
                url: formulario.global.api + '/empleado/consultaDocumentosEmpleados'
            }


        });
    }
    ;
    angular.module('app.services').factory('Empleado', Empleado);
})();
