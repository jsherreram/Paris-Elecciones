/* global formulario */

(function () {
    function CargaPagosService($resource) {
        return $resource(formulario.global.api + '/Divitrans/', {}, {
            descargarDemo: {
                method: 'POST',
                url: formulario.global.api + '/Divitrans/demoPagoViaticos',
                headers: {
                    accept: 'application/vnd.ms-excel;charset=utf-8'
                },
                responseType: 'arraybuffer',
                cache: true,
                transformResponse: function (data) {
                    var csv;
                    if (data) {
                        csv = new Blob([data], {
                            type: 'application/vnd.ms-excel;charset=utf-8'
                        });
                    }
                    return {
                        response: csv
                    };
                }
            }
        });
    }
    ;
    angular.module('app.services').factory('CargaPagosService', CargaPagosService);
})();

