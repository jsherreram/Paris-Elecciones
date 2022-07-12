(function () {
    function EditarPersona($routeParams, Empleado, GestionPersonas, TipoDocumento, DepartamentoDane, MedioPago, OperadorCelular, $location, $window, sweet, $uibModal, $scope) {
        var self = this;
        this.alert = [];
        this.showMsg = false;
        this.departamentos = DepartamentoDane.listar({}, function (data) {});
        this.cargos = GestionPersonas.listarCargos({}, function (data) {});
        this.estadosIcfes = GestionPersonas.listarEstadosIcfes({}, function (data) {});
        this.mostrar = true;
        this.titulo = "Editar Persona";
        this.tipoIdentificacion = TipoDocumento.query({}, function (data) {});

        this.mediosPagos = MedioPago.listarMedios({}, function (data) {});
        this.operadores = OperadorCelular.listar(function (data) {});
        var idPrueba = 0;
        this.rol = "";
        var user = "";
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
            self.rol = data.rolActual;
            user = data.nrodoc;
            if (self.rol === "coordinador") {
                GestionPersonas.usuarioDepartamento({usuario: user, idPrueba: idPrueba}, function (data) {
                    self.departamento = data[0].codigo;
                });
            }
        });

        function buscarDataPersona(data) {
            self.tipoDocumento = {};
            self.tipoDoc = data.tipodoc;
            self.municipio = data.municipioDane;
            self.departamento = data.municipioDane.departamento.codigo;
            if (data.municipioRut !== undefined) {
                self.municipioRut = data.municipioRut;
                self.departamentoRut = data.municipioRut.departamento.codigo;
            }
            self.estadoPersonal = {'codigoEstado': data.codigoEstado};
            self.actividadEconomica = {'codigo': data.codigoActividad};
            self.pago = data.idmediopago + '';
            self.persona.idOperador = data.idOperador + '';
            var bdpdata = data;
            if ($routeParams.idPersona !== undefined) {
                Empleado.getBySession({}, function (data) {
                    bdpdata.usuarioModifica = data.nrodoc;
                });

            } else {
                data.usuarioModifica = data.nrodoc;
            }
        }
        if ($routeParams.idPersona !== undefined) {

            this.persona = Empleado.getEmpleado({id: $routeParams.idPersona}, function (data) {
                var fechaNacimiento = new Date(data.fechaNacimiento);
                data.fechaNacimiento = fechaNacimiento.getFullYear() + '-' +
                        pad(fechaNacimiento.getMonth() + 1) + '-' +
                        pad(fechaNacimiento.getDate());
                data.fechaIngreso = new Date(data.fechaIngreso);
                buscarDataPersona(data);
            });
        } else {
            this.persona = Empleado.getBySession({}, function (data) {
                var fechaNacimiento = new Date(data.fechaNacimiento);
                data.fechaNacimiento = fechaNacimiento.getFullYear() + '-' +
                        pad(fechaNacimiento.getMonth() + 1) + '-' +
                        pad(fechaNacimiento.getDate());
                data.fechaIngreso = new Date(data.fechaIngreso);
                buscarDataPersona(data);
                data.estadoIcfes.codigoEstado = "" + data.estadoIcfes.codigoEstado;
            });
            this.mostrar = false;
        }

        this.ValidaExiste = function () {
            if ($routeParams.idPersona === undefined) {
                this.existe = Empleado.getPersonaByCedula({nroDocumento: self.persona.nrodoc}, function (data) {

                    if (data.idEmpleado > 0)
                    {
                        sweet.show("Ya existe una persona registrada con ese numero de documento");
                    }

                });
            }
        };

        this.cambio = function () {
            alert(self.departamento);
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
                this.persona.municipioRut=self.municipioRut;
                this.persona.fechaNacimiento = Date.parse($('#datepicker').val());
                this.persona.tipodoc = self.tipoDoc;
                this.persona.idmediopago = self.pago;
                this.persona.$update({}, function (response) {
                    if ($routeParams.idPersona !== undefined) {
                        if (response.codigo == '200') {
                            sweet.show('Felicidades', 'La informaci\u00F3n se actualiz\u00F3 correctamente', "success");
                        } else {
                            sweet.show('Oopss', "Error al actualizar la informaci\u00F3n", "error");
                        }
                        $window.history.back();
                    } else {
                        if (response.codigo == '200') {

                            sweet.show('Felicidades', "La informaci\u00F3n se actualiz\u00F3 correctamente", "success");

                        } else {
                            sweet.show('Oopss', "Error al actualizar la informaci\u00F3n", "error");
                        }
                        $window.history.back();
                    }

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

        var numAdds = 0;

        self.dzOptions = {
            url: '/Paris/UploadFileServlet',
            paramName: 'file',
            parallelUploads: 1,
            maxFilesize: '10',
            dictDefaultMessage: 'Arrastre sus archivos aqu&#237; (o haga click)',
            dictRemoveFile: "Quitar Archivo",
            dictFileTooBig: "Archivo no puede superar los 10MB",
            dictInvalidFileType: "Tipo de archivo no valido",
            acceptedFiles: 'application/pdf',
            addRemoveLinks: true,
            autoProcessQueue: false
        };

        //Handle events for dropzone
        //Visit http://www.dropzonejs.com/#events for more events
        self.dzCallbacks = {
            addedfile: function (file) {
                numAdds++;
                if (numAdds > 1) {
                    self.dzMethods.removeFile(file);
                }
                console.log("File:", file);
            },
            removedfile: function (file) {
                numAdds--;
                if (numAdds < 1) {
                    self.dzMethods.enable();
                }
            },
            sending: function (file, xhr, formData) {
                formData.append("idEmpleado", self.persona.idEmpleado);
                formData.append("folder", self.folder);
                console.log("File:", file);
                console.log("xhr:", xhr);
                console.log("FormData:", formData);
                xhr.onload(function (data) {
                    console.log(data);
                });
                $('#modalLoading').modal('show');
            },
            complete: function (file) {
                self.dzMethods.processQueue();
            },
            maxfilesexceeded: function (file) {
                self.dzMethods.removeFile(file);
            },
            queuecomplete: function () {
                self.verHistorialArchivo('dociden');
                $('#modalLoading').modal('hide');
            },
            error: function (file, message) {
                sweet.show({title: message, type: "error"});
            }
        };

        this.uploadFiles = function () {
            if ((numAdds + self.cantidadFotos) > 1) {
                sweet.show({title: "\u00A1No se pueden cargar mas de 1 PDF!", type: "warning"});
            } else {
                numAdds = 0;
                self.dzMethods.processQueue();
            }
        };
        //Apply methods for dropzone
        //Visit http://www.dropzonejs.com/#dropzone-methods for more methods
        this.dzMethods = {};

        //Funcion para generar el modal que va a mostrar la foto seleccionada
        this.showModalImage = function (idImage) {
            var img = $('#' + idImage).attr("src");
            $('#imgModal').attr("src", img);
            $('#imgModal').width(900);
            $('#imgModal').height(650);
            $('#modalImage').modal('show');
        };



// funcion que abre el modal de ver Detallado de persona
        this.verHistorialArchivo = function (tipoD) {

            var modalInstance = $uibModal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'myModalVerHistorial.html',
                controller: 'verHistorial',
                controllerAs: 'controller',
                size: 'lg',
                resolve: {
                    id: function () {
                        return self.persona.idEmpleado;
                    },
                    tipoDoc: function () {
                        return tipoD;
                    }
                }
            });
            modalInstance.result.then(function () {

            }, function () {

            });
        }

    }
    ;

    angular.module("app").controller('EditarPersonaDetallado', EditarPersona);
})();

function leerCookie(nombre) {
    var lista = document.cookie.split(";");
    for (i in lista) {
        var busca = lista[i].search(nombre);
        if (busca > -1) {
            micookie = lista[i]
        }
    }
    var igual = micookie.indexOf("=");
    var valor = micookie.substring(igual + 1);
    return valor;
}

/**DIrectiva que muestra el modal de Busqueda avanzada*/
angular.module("app").directive('historialArchivos', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/historicoArchivo.html'
    };
});


