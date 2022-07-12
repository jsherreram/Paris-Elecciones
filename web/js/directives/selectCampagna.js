(function(){
    angular.module("app").directive("selectCampagna",['Campagna',function(Campagna){
        return {
            restrict:'E',
            templateUrl:'/Paris/templates/directives/SelectCampagna.html',
            scope:{campagna:"="},
            link: function(scope) {
                   scope.campagnas= Campagna.listar();
            }
        };
    }]);
})();