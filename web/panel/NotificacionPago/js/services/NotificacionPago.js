/* global formulario */

(function () {
    function NotificacionPagoService($resource) {
        return $resource(formulario.global.api + '/generar/', {}, {
            export: {
                method: 'POST',
                url: formulario.global.api + '/generar/exportarNotificacionPagos',
                headers: {
                    accept: 'application/vnd.ms-excel;charset=utf-8'
                },
                responseType: 'arraybuffer',
                cache: true,
                transformResponse: function (data) {
                    var cvs;
                    if (data) {
                        cvs = new Blob([data], {
                            type: 'application/vnd.ms-excel;charset=utf-8'
                        });
                    }
                    return {
                        response: cvs
                    };
                }
            }, getDemoFile: {
                method: 'POST',
                url: formulario.global.api + '/generar/demoNotificacionPago',
                headers: {
                    accept: 'application/vnd.ms-excel;charset=utf-8'
                },
                responseType: 'arraybuffer',
                cache: true,
                transformResponse: function (data) {
                    var cvs;
                    if (data) {
                        cvs = new Blob([data], {
                            type: 'application/vnd.ms-excel;charset=utf-8'
                        });
                    }
                    return {
                        response: cvs
                    };
                }
            }

        });
    }
    ;
    angular.module('app.services').factory('NotificacionPagoService', NotificacionPagoService);
})();