(function(){
    angular.module("app").directive("selectDepartamento",['Departamento',function(Departamento){
        return {
            restrict:'E',
            templateUrl:'/Paris/templates/directives/SelectDepartamento.html',
            link: function(scope) {
                   scope.departamentos= Departamento.listar();
            }
        };
    }]);
})();