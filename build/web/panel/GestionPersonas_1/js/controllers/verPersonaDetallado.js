
(function () {
    function verPersonaDetallado($scope, $uibModalInstance, persona, Empleado) {
        var self = this;
        $scope.currentPage = 1;
        $scope.pageSize = 15;
        this.persona = Empleado.getEmpleado({id: persona}, function (data) {
            var fechaNacimiento = new Date(data.fechaNacimiento);
        });

        $scope.ok = function () {
            $uibModalInstance.close();


        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }
    ;
    angular.module('app').controller('verPersonaDetallado', verPersonaDetallado);
})();


/**DIrectiva que muestra el modal de ver Detalle de persona*/
angular.module("app").directive('detallePersona', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/verPersonaDetallado.html'
    };
});



