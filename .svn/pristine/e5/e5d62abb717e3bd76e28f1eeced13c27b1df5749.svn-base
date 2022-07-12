angular.module('app.services').factory('PruebaSitio', function ($resource) {
    return {
          listar: function (callback) {
            return $resource(formulario.global.api + '/prueba/listar', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                }
            }).query(callback);
        },
         
    };
});

