(function () {
    function controllerAsignarSuplente($routeParams, $scope, Sesion, AsignaSuplente, AdministraAsistencia, sweet, $window, $uibModal) {
        var self = this;
        this.idprueba = 0;
        this.usuario = "";

        //obtener la prueba actual
        Sesion.getEmpleadoPruebaSesion({}, function (data) {
            self.idprueba = data.pruebaActual;
            self.usuario = data.nrodoc;


            self.personaAsignada = AsignaSuplente.getPersonaAsignada({id: $routeParams.id}, function (data) {
                self.sitio = AdministraAsistencia.buscarSitioPorCodigoPrueba({id: data.ubicacion, idPrueba: self.idprueba}, function (data1) {

                    self.suplentes = AsignaSuplente.buscarSuplentesConAsistenciaCapacitacion({idEvento: self.personaAsignada.evento.codigoEvento, idPrueba: self.idprueba,
                        longitud: self.sitio.longitud, latitud: self.sitio.latitud}, function (data) {});

                });


            });
        });

        this.asignar = function (documento, id) {

            AsignaSuplente.cambiarNombramiento({documentoTitular: this.personaAsignada.empleado.nrodoc, documentoSuplente: documento,
                idPrueba: this.idprueba, idSitio:this.personaAsignada.iddivipol, idEmpleado:id, codigoEvento:this.personaAsignada.evento.codigoEvento,idDcpe:$routeParams.id}, function (data) {

                if (data.respuesta === true) {
                    sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                    self.atras();

                } else {
                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                }

            });

        }


        // funcion que abre el modal de ver Historial Laboral de la persona
        this.verHistorialLaboral = function (id) {

     
                var modalInstance = $uibModal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'myModalVerHistorialLaboral.html',
                    controller: 'verHistorialLaboral',
                    controllerAs: 'controller',
                    size: 'lg',
                    resolve: {
                        persona: function () {
                            return id;
                        }
                    }
                });

                modalInstance.result.then(function (persona) {
                    self.persona = persona;
                }, function () {

                });
      

        }

        this.atras = function () {
            $window.history.back();
        }


    }
    ;
    angular.module('app').controller('AsignarSuplente', controllerAsignarSuplente);
})();




