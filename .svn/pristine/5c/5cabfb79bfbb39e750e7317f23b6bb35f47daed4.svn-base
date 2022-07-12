(function(){
    angular.module("app").directive('selectActividadEconomica',['ActividadEconomica',function(ActividadEconomica){
        return {
            restrict:'E',
            link:function(scope){
                scope.actividadeseconomicas = ActividadEconomica.query();
            },
            templateUrl: '/Paris/templates/directives/SelectActividadEconomica.html',
            scope:{actividadeconomica:'='}
        };
    }]);
})();

