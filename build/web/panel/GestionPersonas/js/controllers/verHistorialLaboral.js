(function () {
    function verHistorialLaboral($scope, $uibModalInstance, persona, Empleado, GestionPersonas, $window) {
        var self = this;
        $scope.currentPage2 = 1;
        $scope.pageSize2 = 5;
        
        this.persona = Empleado.getEmpleado({id:persona}, function (data) {
            self.persona.nombre1 = data.nombre1 + " " + data.nombre2 + " " + data.apellido1 + " " + data.apellido2;

        });
        GestionPersonas.listarHistorialLaboral({id: persona}, function (data) {
            self.historial = data;
        });

        $scope.ok = function () {
            $uibModalInstance.close();

        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }
    ;
    angular.module('app').controller('verHistorialLaboral', verHistorialLaboral);
})();


/**Directiva que muestra el modal de ver Detalle de persona*/
angular.module("app").directive('historialLaboral', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/verHistorialLaboralPersona.html'
    };
});

