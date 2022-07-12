(function(){
    angular.module("app").directive("selectZonaIcfes",['ZonaIcfes',function(ZonaIcfes){
        return {
            restrict:'E',
            templateUrl:'/Paris/templates/directives/SelectZonaIcfes.html',
            scope:{zona:"="},
            link: function(scope) {
                   scope.zonas= ZonaIcfes.listar();
            }
        };
    }]);
})();