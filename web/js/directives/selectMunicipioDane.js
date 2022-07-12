(function(){
    angular.module("app").directive("selectMunicipioDane",['MunicipioDane',function(MunicipioDane){
        return {
            restrict:'E',
            templateUrl:'/Paris/templates/directives/SelectMunicipioDane.html',
            scope:{municipio:"=",departamentocodigo:"="},
            link: function(scope) {
                scope.$watch("departamentocodigo",function(newValue,oldValue) {
                   scope.municipios= MunicipioDane.getByDpto({idDpto: scope.departamentocodigo});
                });
            }
        };
    }]);
})();