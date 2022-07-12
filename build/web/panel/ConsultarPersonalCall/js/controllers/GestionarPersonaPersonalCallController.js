(function () {
    angular.module("app").controller("GestionarPersonaPersonalCallController", function ($routeParams, Empleado,sweet, EmpleadoPrueba, $window) {
        this.empleado = Empleado.getEmpleado({id:$routeParams.idEmpleado});
        this.eventos = Empleado.getEventos({id:$routeParams.idEmpleado});
        this.cancelarParticipacion = function(empleadoPrueba){
            var ep = new EmpleadoPrueba();
            ep.id = empleadoPrueba.id;
            ep.aprobado = empleadoPrueba.bloqueado;
            ep.idestpersona = 6;
            if(ep.aprobado===1){
                sweet.show('Oopss!', 'No es Posible Cancelar Participaci\u00f3n, ya existe Cierre de Evento(s)', 'error');
            }else{
                sweet.show({
                    title: 'Confirmar',
                    text: 'Esta Seguro que desea Cancelar la Participaci\u00f3n'+'?',
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3e6bcc',
                    cancelButtonText: 'NO',
                    confirmButtonText: 'SI, Deseo Cancelar',
                    closeOnConfirm: false
                }, function() {
                        ep.$update({},function(){
                            empleadoPrueba.estado = 'No_Participa';
                            sweet.show('Ok!', 'Se ha Registrado su Cancelaci\u00f3n Correctamente', 'success');
                        });
                });
            }
        };
        
        this.volver=function(){
            $window.history.back();
            
        }
        
    });

    //id= 6}

})();