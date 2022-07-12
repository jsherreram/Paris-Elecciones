(function(){
    angular.module("app").directive("selectLocalidadDane",['LocalidadDane',function(LocalidadDane){
        return {
            restrict:'E',
            templateUrl:'/Paris/templates/directives/SelectLocalidadDane.html',
            scope:{municipiocodigo:"=", localidad:"="},
            link: function(scope) {
                scope.$watch("municipiocodigo",function(newValue,oldValue) {
                   scope.localidades= LocalidadDane.getByMunicipio({idMunicipio: scope.municipiocodigo});
                });
            }
        };
    }]);
})();
