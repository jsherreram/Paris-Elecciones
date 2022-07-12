(function () {
    angular.module("app").controller("ConsultarPersonaPersonalCallController", function ($location, Empleado) {
        var self = this;
        this.empleado = new Empleado();
        
        //Busqueda de empleado y redireccion a pagina de gestion del empleado
        this.searchEmpleado = function () {
            self.empleado.tipodoc = self.tipoDocumento.codigo;
            self.empleado.$query({}, function (data) {
                if (data.idEmpleado !== 0) {
                    $location.path('/gestionar/' + data.idEmpleado);
                }
            });
        }
    });
})();
