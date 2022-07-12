
/* global formulario */

(function () {
    function CargaArchivo($resource) {
        return $resource(formulario.global.api + '/statuscargue/', {}, {
            listarStatus: {
                method: 'GET',
                url: formulario.global.api + '/statuscargue/listar/',
                isArray: true
            }, 
            generarReporteErrores: {
                method: 'POST',
                url: formulario.global.api + '/statuscargue/generarReporteErrores',
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
    angular.module('app.services').factory('CargaArchivo', CargaArchivo);
})();


