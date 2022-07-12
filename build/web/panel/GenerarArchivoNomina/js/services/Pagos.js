/* global formulario */

(function () {
    function Pagos($resource) {
        return $resource(formulario.global.api + '/pago/', {}, {
            exportArchivo: {
                method: 'POST',
                url: formulario.global.api + '/pago/exportarPagos',
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
    angular.module('app.services').factory('Pagos', Pagos);
})();