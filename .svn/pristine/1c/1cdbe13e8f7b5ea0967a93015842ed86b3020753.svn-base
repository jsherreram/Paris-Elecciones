(function () {
    angular.module('app').directive('selectEstadoPersonal',['EstadoPersonal',function(EstadoPersonal){
            return {
                restrict:'E',
                templateUrl:'/Paris/templates/directives/SelectEstadoPersonal.html',
                scope:{
                    estadopersonal:'='
                },
                link:function(scope){
                    scope.estadospersonal = EstadoPersonal.query();
                }
            }
    }])
})();

