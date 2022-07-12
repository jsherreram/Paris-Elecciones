'use strict';

var services = angular.module('app.services', ['ngResource']);

services.factory('AdministraAsistenciaFactory', function ($resource) {
    return {
    getAsistencia: function (idEvento, usuario, callback) {
        return $resource(formulario.global.api + '/personaAsignada/asistenciaSitio?idEvento=' + idEvento + '&usuario=' + usuario, {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        }).query(callback);
    },
            getEventos: function (idPrueba, usuario, callback) {
                return $resource(formulario.global.api + '/evento/listarXSitio?idPrueba=' + idPrueba + '&usuario=' + usuario, {}, {
                    query: {
                        method: 'GET',
                        isArray: true
                    }
                }).query(callback);
            },
            getSitio: function (idsitio, callback) {
                return $resource(formulario.global.api + '/sitio/getById?id=' + idsitio, {}, {
                    query: {
                        method: 'GET'
                    }
                }).query(callback);
            },
            listarAsistencia: function (idEvento, sitio, callback) {
                return $resource(formulario.global.api + '/asistencia/listarAsistenciaXSitio?idEvento=' + idEvento + '&codigoSitio=' + sitio, {}, {
                    query: {
                        method: 'GET',
                        isArray: true
                    }
                }).query(callback);
            },
            resumenAsistenciaXSitio: function (idEvento, sitio, callback) {
                return $resource(formulario.global.api + '/asistencia/resumenAsistenciaXSitio?idEvento=' + idEvento + '&codigoSitio=' + sitio, {}, {
                    query: {
                        method: 'GET',
                        isArray: true
                    }
                }).query(callback);
            },
            getAsignacion: function (idEvento, sitio, nrodoc, callback) {
                return $resource(formulario.global.api + '/asistencia/getAsignacion?idEvento=' + idEvento + '&codigoSitio=' + sitio + '&nrodoc=' + nrodoc, {}, {
                    query: {
                        method: 'GET',
                        isArray: true
                    }
                }).query(callback);
            },
            registrarAsistencia: function (idEvento, idDivipol, idEmpleado, biometrico, callback) {
                return $resource(formulario.global.api + '/asistencia/marcarAsistencia?idEvento=' + idEvento + '&idDivipol=' + idDivipol + '&idEmpleado=' + idEmpleado + '&biometrico=' + biometrico, {}, {
                    query: {
                        method: 'POST'
                    }
                }).query(callback);
            },
            getPersonaAsignada: function (id, callback) {
                return $resource(formulario.global.api + '/personaAsignada/buscar?id=' + id, {}, {
                    query: {
                        method: 'GET'
                    }
                }).query(callback);
            },
            getPersona: function (idPersona, callback) {
                return $resource(formulario.global.api + '/empleado/buscar?id=' + idPersona, {}, {
                    query: {
                        method: 'GET'
                    }
                }).query(callback);
            },
            getSitios: function (idPrueba, usuario, callback) {
                return $resource(formulario.global.api + '/UsuarioSitio/listar?usuario=' + usuario + '&idPrueba=' + idPrueba, {}, {
                    query: {
                        method: 'GET',
                        isArray: true
                    }
                }).query(callback);
            },
            getEventoActualParaTomaAsistencia: function (idPrueba, idDivipol, callback) {
                return $resource(formulario.global.api + '/evento/GetEventoActualParaTomaAsistencia?idPrueba=' + idPrueba + '&idDivipol=' + idDivipol, {}, {
                    query: {
                        method: 'GET'
                    }
                }).query(callback);
            },
            getEncuesta: function (idEvento, idDivipol, callback) {
                return $resource(formulario.global.api + '/encuesta/buscar?idEvento=' + idEvento + '&idDivipol=' + idDivipol, {}, {
                    query: {
                        method: 'GET'
                    }
                }).query(callback);
            },
            validaSalonesLlenos: function (idPrueba, codigoSitio, callback) {
                return $resource(formulario.global.api + '/nombramiento/validarSalonesCompletosCoordinadores?idPrueba=' + idPrueba + '&codigoSitio=' + codigoSitio, {}, {
                    query: {
                        method: 'GET',
                        isArray: false
                    }
                }).query(callback);
            },
            getParametroHora: function (idEvento, callback) {
                return $resource(formulario.global.api + '/parametroencuesta/buscarPorHora?idEvento=' + idEvento, {}, {
                    query: {
                        method: 'GET',
                        isArray: false
                    }
                }).query(callback);
            },
            validarAsistenciaTitularySuplente: function (idEvento, usuario, callback) {
                return $resource(formulario.global.api + '/personaAsignada/validarAsistenciaTitularSuplente?idEvento=' + idEvento + '&usuario=' + usuario, {}, {
                    query: {
                        method: 'GET',
                        isArray: false
                    }
                }).query(callback);
            },
            getParametrosEncuesta: function (idEvento, callback) {
                return $resource(formulario.global.api + '/parametroencuesta/listarParametrosPorEvento?idEvento=' + idEvento, {}, {
                    query: {
                        method: 'GET',
                        isArray: true
                    }
                }).query(callback);
            },
            getEvento: function (idEvento, callback) {
                return $resource(formulario.global.api + '/evento/buscar?id=' + idEvento, {}, {
                    query: {
                        method: 'GET'
                    }
                }).query(callback);
            },
            getFotosReunionPrevia: function (idSitio, idPrueba, callback) {
                return $resource(formulario.global.api + '/sitio/getFotosSitio?idSitio=' + idSitio + '&idPrueba=' + idPrueba, {}, {
                    query: {
                        method: 'POST',
                        isArray: true
                    }
                }).query(callback);
            },
            buscarCierre: function (idDivipol, idEvento, callback) {
                return $resource(formulario.global.api + '/CierreSesion/buscarCierre?idDivipol=' + idDivipol + '&idEvento=' + idEvento, {}, {
                    query: {
                        method: 'GET',
                        isArray: true
                    }
                }).query(callback);
            },
            validarAsistenciaEmpleado: function (idDivipol, idEvento, idEmpleado, callback) {
                return $resource(formulario.global.api + '/asistencia/validaAsistenciaEmpleado?idDivipol=' + idDivipol + '&idEvento=' + idEvento + '&idEmpleado=' + idEmpleado, {}, {
                    query: {
                        method: 'GET',
                        isArray: false
                    }
                }).query(callback);
            },
            asignarPersonaOtroSitioAPDS: function (id, nrodoc, callback) {
                return $resource(formulario.global.api + '/personaAsignada/asignarPersonaOtroSitioAPDS?id=' + id +'&nrodoc=' + nrodoc, {}, {
                    query: {
                        method: 'GET',
                        isArray: false
                    }
                }).query(callback);
            },
            desasignarPersonaSitio:function (iddcpe, callback) {
                return $resource(formulario.global.api + '/personaAsignada/desasignarPersonaSitio?id=' + iddcpe, {}, {
                    query: {
                        method: 'GET',
                        isArray: false
                    }
                }).query(callback);
            },
    };
});
(function () {
    function CambiaCargo($resource) {
        return $resource(formulario.global.api + '/personaAsignada/', {}, {
            obtenerPersonaCargoSitio: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/buscar/',
                isArray: false
            },
            listarCargosAsignados: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/listarCargosAsignados/',
                isArray: true
            },
            listarSalonesAsignados: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/listarSalonesAsignados/',
                isArray: true
            },
            buscarPersonasAsignadasCargo: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/listarPersonasAsignadasaCargo/',
                isArray: true
            },
            buscarPersonaAsignadaSalon: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/buscarPersonaAsignadaSalon/',
                isArray: false
            },
            updateCargo: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/actualizarNrodoc',
                isArray: false
            },
            updateSalon: {
                method: 'GET',
                url: formulario.global.api + '/personaAsignada/actualizarSalon',
                isArray: false
            }

        });


    }
    ;
    angular.module('app.services').factory('CambiaCargo', CambiaCargo);
})();

services.factory('PersonaAsignada', ['$resource', function ($resource) {
        return $resource(formulario.global.api + '/personaAsignada', {}, {
            update: {method: 'PUT'}
        });
    }]);

services.factory('SuplenteFactory', function ($resource) {
    return {
        obtenerSuplentes: function (dcpe, callback) {
            return $resource(formulario.global.api + '/asignarsuplente/obtenerSuplentes?dcpe=' + dcpe, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerfalton: function (dcpe, callback) {
            return $resource(formulario.global.api + '/asignarsuplente/obtenerfalton?dcpe=' + dcpe, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        GetRamdon: function (callback) {
            return $resource(formulario.global.api + '/asignarsuplente/GetRamdon', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
        obtenerFaltantes: function (idPrueba, idDivipol, codigoEvento, callback) {
            return $resource(formulario.global.api + '/asignarsuplente/obtenerFaltantes?idPrueba=' + idPrueba + '&idDivipol=' + idDivipol + '&codigoEvento=' + codigoEvento, {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        }
    };
});

services.factory('SuplenteActualiza', ['$resource', function ($resource) {
        return $resource(formulario.global.api + '/asignarsuplente/Actualizar', {}, {
            update: {method: 'POST'}
        });
    }]);

services.factory('CierreAsistencia', ['$resource', function ($resource) {
        return $resource(formulario.global.api + '/asignarsuplente/CierreAsistencia', {}, {
            insert: {method: 'POST'}
        });
    }]);
