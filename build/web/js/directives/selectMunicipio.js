(function(){
    angular.module("app").directive("selectMunicipio",['Municipio',function(Municipio){
        return {
            restrict:'E',
            templateUrl:'/Paris/templates/directives/SelectMunicipio.html',
            scope:{municipio:"=",departamentocodigo:"=", requerido:"="},
            link: function(scope) {
                scope.$watch("departamentocodigo",function(newValue,oldValue) {
                   scope.municipios= Municipio.getByDpto({idDpto: scope.departamentocodigo});
                });
            }
        };
    }]);
})();