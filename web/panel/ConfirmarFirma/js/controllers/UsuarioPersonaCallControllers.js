(function () {
    angular.module("app").controller("UsuarioPersonalCall", function ($routeParams, $location, Empleado) {
        this.empleado = Empleado.getEmpleado({id: $routeParams.idEmpleado});
        this.rdbRecPass = undefined;
        this.rdbBlkUsr = undefined;
        var self = this;
        this.save = function () {
            console.log('rdbRecPass', this.rdbRecPass);
            console.log('rdbBlkUsr', this.rdbBlkUsr);
            if (this.rdbRecPass === 'true')
            {
                self.empleado.$recoveryPassword();
            }
            if (this.rdbBlkUsr === 'true'){
                console.log("Bloquear Usuario");
            }else if (this.rdbBlkUsr === 'false'){
                console.log("Desbloquear Usuario");
            }
            $location.path('/gestionar/'+$routeParams.idEmpleado);
        };
    });
})();

