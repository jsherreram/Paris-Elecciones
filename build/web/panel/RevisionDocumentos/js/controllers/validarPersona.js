(function () {
    function validarPersona(Empleado, RevisaDocumento, DepartamentoDane, TipoDocumento, $routeParams, MedioPago, $scope, sweet, $window, $uibModal, $http, $sce) {

        var self = this;
        var usuario = "";
        $scope.pdfcedulas = [];
        $scope.pdfestudios = [];
        $scope.mostrar = true;

        //Se obtiene el id de la prueba para enviarla en la consulta
        Empleado.getEmpleadoPruebaSesion({}, function (response) {
            usuario = response.nrodoc;
        });

        this.mediosPagos = MedioPago.listarMedios({}, function (data) {});
        this.buscarEmpleado = function () {
            this.persona = Empleado.getEmpleadoSinHuella({id: $routeParams.id}, function (data) {
                var fechaNacimiento = new Date(data.fechaNacimiento);
                data.fechaNacimiento = fechaNacimiento.getFullYear() + '-' +
                        pad(fechaNacimiento.getMonth() + 1) + '-' +
                        pad(fechaNacimiento.getDate());
                data.fechaIngreso = new Date(data.fechaIngreso);
                buscarDataPersona(data);

                if (data.imgdociden === 1) {
                    Empleado.getHistorialArchivos({idEmpleado: $routeParams.id, tipoDoc: "dociden"}, function (data) {
                        $scope.pdfcedulas = data;
                        $scope.pdfcedula = data[0];
                        $scope.url = "/dociden/" + $scope.pdfcedula.nombre;
                        $http.get($scope.url,
                                {responseType: 'arraybuffer'})
                                .success(function (response) {
                                    var file = new Blob([(response)], {type: 'application/pdf'});
                                    var fileURL = URL.createObjectURL(file);
                                    $scope.content = $sce.trustAsResourceUrl(fileURL);
                                });
                    });
                }
            });
        }
        this.actualizarEstado = function (estado) {

            RevisaDocumento.actualizarEstadoEmpleado({'observaciones': self.persona.observacion, 'idEmpleado': $routeParams.id, 'idEstado': estado}, function (data) {
                if (data.codigo === '200') {
                    sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                    location.reload();
                } else {
                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                }
            });
        }
        this.rechazarPerfil = function (estado) {
            if (this.persona.observacion === undefined || this.persona.observacion === " ") {
                sweet.show(" Debe colocar las observaciones de la inconsistencia para que la persona pueda solucionarla");
            } else {
                sweet.show({
                    title: 'Confirmar',
                    text: 'Est\u00e1 seguro que desea colocar esta persona como Inconsistente?',
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3e6bcc',
                    cancelButtonText: 'NO',
                    confirmButtonText: 'SI',
                    closeOnConfirm: false
                }, function () {
                    self.actualizarEstado(estado);
                });
            }
        };


        this.volver = function () {
            $scope.mostrar = true;
        };


        this.habilitarEdicion = function () {
            $scope.mostrar = false;
        }


        // funcion que abre el modal de ver Detallado de persona
        self.verDocumento = function (path, name, extension) {
            var modalInstance = $uibModal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'myModalVerDocumento.html',
                controller: 'verDocumento',
                controllerAs: 'controller',
                size: 'lg',
                resolve: {
                    path: function () {
                        return path;
                    },
                    name: function () {
                        return name;
                    },
                    extension: function () {
                        return extension;
                    }

                }
            });
            modalInstance.result.then(function () {

            }, function () {

            });

        },
                //Funcion para generar el modal que va a mostrar la foto seleccionada
                this.showModalImage = function (idImage) {
                    var img = $('#' + idImage).attr("src");
                    $('#imgModal').attr("src", img);
                    $('#imgModal').width(700);
                    $('#imgModal').height(450);
                    $('#modalImage').modal('show');
                };





        // Para editar persona

        this.departamentos = DepartamentoDane.listar({}, function (data) {});
        this.tipoIdentificacion = TipoDocumento.query({}, function (data) {});

        var idPrueba = 0;
        this.rol = "";
        var user = "";


        function buscarDataPersona(data) {
            self.tipoDocumento = {};
            self.tipoDoc = data.tipodoc;
            self.municipio = data.municipioDane;
            self.departamento = data.municipioDane.departamento.codigo;
            self.estadoPersonal = {'codigoEstado': data.codigoEstado};
            self.actividadEconomica = {'codigo': data.codigoActividad};
            self.pago = data.idmediopago + "";
            if (data.municipioRut !== undefined) {
                self.municipioRut = data.municipioRut;
                self.departamentoRut = data.municipioRut.departamento.codigo;
            }
            var bdpdata = data;
            if ($routeParams.id !== undefined) {
                Empleado.getBySession({}, function (data) {
                    bdpdata.usuarioModifica = data.nrodoc;
                });

            } else {
                data.usuarioModifica = data.nrodoc;
            }
        }


        this.validarFecha = function () {
            $('#datepicker').val(self.persona.fechaNacimiento);
        };


        this.save = function () {
            var fecha = new Date();
            this.persona.fechaNacimiento = $('#datepicker').val();

            var fechaNac = new Date(this.persona.fechaNacimiento);

            if (fechaNac > fecha) {
                sweet.show("La fecha de nacimiento no puede ser mayor a la fecha actual, por favor verifique");
                return false;
            } else if (this.municipio === undefined || this.actividadEconomica === undefined) {
                var mensaje = "";
                if (this.municipio === undefined) {
                    mensaje += " El Municipio es obligatorio \n";
                }
                if (this.actividadEconomica === undefined) {
                    mensaje += " La actividad economica es obligatoria";
                }
                sweet.show(mensaje);

            } else {

                this.persona.codigoActividad = self.actividadEconomica.codigo;
                this.persona.municipioDane = self.municipio;
                this.persona.fechaNacimiento = Date.parse($('#datepicker').val());
                this.persona.tipodoc = self.tipoDoc;
                this.persona.idmediopago = self.pago;
                this.persona.municipioRut=self.municipioRut;
                this.persona.$update({}, function (response) {
                    if (response.codigo == '200') {
                        sweet.show('Felicidades', 'La informaci\u00F3n se actualiz\u00F3 correctamente', "success");
                    } else {
                        sweet.show('Oopss', "Error al actualizar la informaci\u00F3n", "error");
                    }
                    $scope.mostrar = true;
                    self.buscarEmpleado();
                });

            }
        }

        $('#datepicker').datepicker({
            orientation: "bottom",
            autoclose: true,
            todayHighlight: true,
            clearBtn: false
        });

        //
        function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }



    }
    ;
    angular.module("app").controller('validarPersona', validarPersona);
})();

(function () {
    function verDocumento($scope, $uibModalInstance, path, name, extension) {
        var self = this;

        this.path = path;
        this.name = name;
        this.tipo = extension;

        $scope.url = "/Elecciones/img/" + path + "/" + name;

        $scope.ok = function () {
            $uibModalInstance.close();
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }
    ;
    angular.module('app').controller('verDocumento', verDocumento);
})();


/**Directiva que muestra el modal de ver Detalle de persona*/
angular.module("app").directive('verDocumento', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/verDocumento.html'
    };
});
