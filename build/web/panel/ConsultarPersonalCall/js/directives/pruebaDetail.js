(function(){
    angular.module("app").directive("pruebaDetail",function(){
        return{
            restrict: 'E',
            templateUrl: 'templates/directives/prueba-detail.html',
            scope:{ evento:"="}
        }
    })
})();

