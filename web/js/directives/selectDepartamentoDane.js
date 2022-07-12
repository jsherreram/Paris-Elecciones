(function(){
    angular.module("app").directive("selectDepartamentoDane",['DepartamentoDane',function(DepartamentoDane){
        return {
            restrict:'E',
            templateUrl:'/Paris/templates/directives/SelectDepartamentoDane.html',
            link: function(scope) {
                   scope.departamentos= DepartamentoDane.listar();
            }
        };
    }]);
})();