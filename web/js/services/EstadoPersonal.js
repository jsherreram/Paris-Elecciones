(function () {
    function EstadoPersonal($resource){
      return $resource(formulario.global.api + '/EstadosPersonal/listar',{},{
          query:{
              method: 'GET',
              isArray: true
          }
      })  
    };
    angular.module('app.services').factory('EstadoPersonal',EstadoPersonal);
})();