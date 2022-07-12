(function () {
    angular.module("app").controller("GestionarPersonaPersonalCallController", function ($routeParams, Empleado, EmpleadoPrueba) {
        this.empleado = Empleado.getEmpleado({id:$routeParams.idEmpleado});
        this.eventos = EmpleadoPrueba.getPruebas({id:$routeParams.idEmpleado});
        this.cancelarParticipacion = function(empleadoPrueba){
            console.log("Entro: ",empleadoPrueba);
            var ep = new EmpleadoPrueba();
            ep.id = empleadoPrueba.id;
            ep.idestpersona = 6;
            ep.$update({},function(){
                empleadoPrueba.estado = 'No_Participa';
            });
        };
        
          // si la solicitud es por huella llama esta funcion
        this.enrolar = function (idEmpleado) {

           openPopPupEnrolar(idEmpleado);
        };
    });
    
    //id= 6}
    
})();