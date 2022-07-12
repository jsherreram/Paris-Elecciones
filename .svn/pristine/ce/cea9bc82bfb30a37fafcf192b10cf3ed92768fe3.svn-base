(function () {
    function GestionPersonas($resource) {
        return $resource({}, {}, {
            listarCargos: {
                url: formulario.global.api + '/cargo/listar',
                method: 'GET',
                isArray: true
            },
            buscarPorFiltro: {
                url: formulario.global.api + '/empleado/busquedaAvanzada',
                method: 'GET',
                isArray: true
            },
            filtrarPorGeolocalizacion:{
                method:'GET',
                url:formulario.global.api+'/empleado/filtrarPorGeolocalizacion/',
                isArray:true
            },
            obtenerTotalGeolocalizados:{
                method:'GET',
                url:formulario.global.api+'/empleado/obtenerTotalGeolocalizados/',
                isArray:false
            },
            asignarMasivo:{
                method:'GET',
                url:formulario.global.api+'/empleado/asignarMasivo/',
                isArray:false
            },
             listarTiposPrueba:{
                url:formulario.global.api+'/prueba/listarTipPruebaEsp',
                method:'GET',
                isArray:true
            },
            listarNivelCargo:{
                method:'GET',
                url:formulario.global.api+'/cargo/listarNivCargo/',
                isArray:true
			},
              listarEstadosIcfes:{
                url:formulario.global.api+'/EstadosPersonal/listarEstadosIcfes',
                method:'GET',
                isArray:true
            },
              listarHistorialLaboral:{
                url:formulario.global.api+'/personaAsignada/listarHistorialLaboral',
                method:'GET',
                isArray:true
            },
             usuarioDepartamento:{
                url:formulario.global.api+'/UsuarioDepartamento/listar',
                method:'GET',
                isArray:true
             }
            
            
        });
    }
    angular.module('app.services').factory('GestionPersonas', GestionPersonas);
})();
