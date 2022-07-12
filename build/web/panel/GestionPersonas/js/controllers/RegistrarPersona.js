(function () {
    function EditarPersona($routeParams, Empleado, GestionPersonas, TipoDocumento, DepartamentoDane, MedioPago,OperadorCelular, $location, $window, sweet) {
        var self = this;
        this.alert = [];
        this.showMsg = false;
        this.departamentos = DepartamentoDane.listar({}, function (data) {});
        this.cargos = GestionPersonas.listarCargos({}, function (data) {});
        this.estadosIcfes = GestionPersonas.listarEstadosIcfes({}, function (data) {});
        this.mostrar = true;
        this.titulo = "";
        this.tipoIdentificacion = TipoDocumento.query({}, function (data) {});
        var usuario = leerCookie("APP-TOKEN");
        var id = 0;
        self.idmediopago='2';

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
        
        this.mediosPagos=MedioPago.listarMedios({}, function(data){});
        this.operadores=OperadorCelular.listar(function(data){});

        function buscarDataPersona(data) {
            self.tipoDocumento = {};
            self.tipoDoc = data.tipodoc;
            self.municipio = data.municipioDane;
            self.departamento = data.municipioDane.departamento.codigo;
            self.municipioRut = data.municipioRut;
            self.departamentoRut = data.municipioRut.departamento.codigo;
            self.estadoPersonal = {'codigoEstado': data.codigoEstado};
            self.actividadeconomica = {'codigo': data.codigoActividad};

            var bdpdata = data;
            if ($routeParams.idPersona !== undefined) {
                Empleado.getBySession({}, function (data) {
                    bdpdata.usuarioModifica = data.nrodoc;
                });

            } else {
                data.usuarioModifica = data.nrodoc;
            }
        }
        this.titulo = "Registrar Persona";
        if ($routeParams.idPersona !== undefined) {
            this.titulo = "Editar Persona";
            this.persona = Empleado.getEmpleado({id: $routeParams.idPersona}, function (data) {
                var fechaNacimiento = new Date(data.fechaNacimiento);
                data.fechaNacimiento = fechaNacimiento.getFullYear() + '-' +
                        pad(fechaNacimiento.getMonth() + 1) + '-' +
                        pad(fechaNacimiento.getDate());
                data.fechaIngreso = new Date(data.fechaIngreso);
                buscarDataPersona(data);
                data.estadoIcfes.codigoEstado = "" + data.estadoIcfes.codigoEstado;
                self.idmediopago=data.idmediopago;
            });
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
        };

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
            } else if (this.tipoDoc === undefined || this.municipio === undefined || this.actividadeconomica === undefined) {
                var mensaje = "";
                if (this.tipoDoc === undefined) {
                    mensaje += " El tipo de Documento es obligatorio \n";
                }
                if (this.municipio === undefined) {
                    mensaje += " El Municipio es obligatorio \n";
                }
                if (this.actividadeconomica === undefined) {
                    mensaje += " La actividad economica es obligatoria";
                }
                sweet.show(mensaje);

            } else {

                var empleado = new Empleado({
                    apellido1: this.persona.apellido1,
                    apellido2: this.persona.apellido2,
                    celular: this.persona.celular,
                    direccion: this.persona.direccion,
                    email: this.persona.email,
                    genero: this.persona.genero,
                    fechaNacimiento: Date.parse($('#datepicker').val()),
                    municipioDane: this.municipio,
                    nombre1: this.persona.nombre1,
                    nombre2: this.persona.nombre2,
                    nrodoc: this.persona.nrodoc,
                    telefono: this.persona.telefono,
                    tipodoc: this.tipoDoc.codigo,
                    usuarioCrea: usuario,
                    codigoActividad: self.actividadeconomica.codigo,
                    idmediopago: self.idmediopago,
                    idpuesto: self.persona.idpuesto,
                    idOperador:self.persona.idOperador,
                    municipioRut:self.municipioRut

                });
                if (this.existe.idEmpleado > 0)
                {
                    sweet.show("Ya existe una persona registrada con ese numero de documento");

                } else {
                    Empleado.registrarDatosBasicos(empleado, function (response) {
                        if (response.codigo == '200')
                        {
                            id = response.id;
                            if ((numAdds + this.cantidadFotos) > 1) {
                                sweet.show({title: "Se ha creado la persona pero \u00A1No se pueden cargar mas de 1 PDF!", type: "warning"});
                            } else {
                                numAdds = 0;
                                self.dzMethods.processQueue();
                                sweet.show('Felicidades', 'La persona se ha creado correctamente', "success");
                            }

                        } else
                        {
                            sweet.show('Oopss', 'Hubo un error al crear la persona', "error");
                        }
                        $window.history.back();

                    });
                }
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
                formData.append("idEmpleado", id);
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
            error: function (file, message) {
                sweet.show({title: message, type: "error"});
            }
        };
        //Handle events for dropzone
        //Visit http://www.dropzonejs.com/#events for more events


        self.uploadFiles = function () {
            if ((numAdds + self.cantidadFotos) > 1) {
                sweet.show({title: "\u00A1No se pueden cargar mas de 1 PDF!", type: "warning"});
            } else {
                numAdds = 0;
                self.dzMethods.processQueue();
            }
        };
        //Apply methods for dropzone
        //Visit http://www.dropzonejs.com/#dropzone-methods for more methods
        self.dzMethods = {};

        //Funcion para generar el modal que va a mostrar la foto seleccionada
        self.showModalImage = function (idImage) {
            var img = $('#' + idImage).attr("src");
            $('#imgModal').attr("src", img);
            $('#imgModal').width(900);
            $('#imgModal').height(650);
            $('#modalImage').modal('show');
        };

    }
    ;

    angular.module("app").controller('RegistrarPersona', EditarPersona);
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


