(function () {
    function CambioEstadoPerCall($routeParams, Empleado, EmpleadoPrueba, $location, EstadoPersonaPrueba) {
        this.ep = new EmpleadoPrueba.queryById({id: $routeParams.idPrueba});
        this.estados = new EstadoPersonaPrueba.query();
        var self = this;
        this.change = function () {
            var epc = new EmpleadoPrueba();
            epc.id = self.ep.id;
            if (self.estado !== undefined) {
                self.ep.idestpersona = self.estado.id;
                self.ep.$update({},
                        function (data) {
                            self.ep.estado = self.estado.codigo;

                        });
            }
        }
        this.back = function(){
            $location.path('/gestionar/'+this.ep.idEmpleado);
        }
    }
    angular.module('app').controller('CambioEstadoPerCall', CambioEstadoPerCall);
})();