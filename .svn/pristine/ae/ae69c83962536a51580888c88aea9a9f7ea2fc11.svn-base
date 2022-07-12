(function () {
    angular.module("app").directive("selectTipoDocumento", function () {
        return{
            restrict: 'E',
            templateUrl: '/Paris/templates/directives/SelectTipoDocumento.html',
            controller: function ($scope, TipoDocumento) {
                $scope.tiposdocumento = TipoDocumento.query();
            },
            scope: {tipodocumento: "="}
        }
    })
})();

