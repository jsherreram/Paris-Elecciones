(function () {
    function RegistrarSitio($scope, Sitio, Empleado, Maps, ZonaIcfes, Departamento, sweet, filterFilter, $location, $window, $routeParams) {

        var self = this;
        this.tiposSitios = Sitio.listarTipos({}, function (data) {});
        this.departamentos = Departamento.listar({}, function (data) {});
        this.zonas = ZonaIcfes.listar({}, function (data) {});
        this.categoriasSitio = Sitio.listarCategoriasSitio({}, function (data) {});
        this.estadosSitio = Sitio.listarEstadosSitio({}, function (data) {});
        this.departamentoSede = "";
        this.departamento = "";
        this.municipioSede = "";
        this.mostrarPDS = false;
        this.idPrueba = 0;
        this.historial=0;
        this.mapaActivo = false;
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            self.idPrueba = data.pruebaActual;

            if ($routeParams.idSitio !== undefined) {
                self.sitiosSinAsignar = Sitio.listarPDSSinAsignar({id: $routeParams.idSitio, idPrueba: self.idPrueba}, function (data) {});

            }
        });
        $scope.currentPage = 1;
        $scope.pageSize = 10;

        if ($routeParams.idSitio !== undefined) {
            this.mostrarPDS = true;
            this.sitio = Sitio.obtenerSitioPorId({id: $routeParams.idSitio}, function (data) {
                self.municipio = data.municipio;
                self.municipioSede = data.municipioSede;
                self.departamentoSede = data.departamentoSede.codigo;
                self.departamento = data.departamento.codigo;
                self.tipoSitio=data.tipoSitio.idTipoSitio;
                self.historial = Sitio.listarHistorialContactosSitio({codigoSitio: data.codigoSitio}, function (data) {})
            });

            this.sitiosAsignados = Sitio.listarPDSAsignados({id: $routeParams.idSitio}, function () {});
        }

        this.save = function () {
            
            if(!this.mapaActivo)
                return;
            
            var municipioS;
            if (self.municipioSede !== undefined) {
                municipioS = {codigoMunicipio: self.municipioSede.codigoMunicipio, departamento: {codigo: this.departamentoSede, nombre: ""}, nombre: self.municipioSede.nombre};
            } else {
                municipioS = {codigoMunicipio: "", departamento: {codigo: "", nombre: ""}, nombre: ""};
            }

 
            if ($routeParams.idSitio !== undefined) {


                this.sitio.municipio = self.municipio;
                this.sitio.fechaReunionPrevia = $('#datepicker').val();
                this.sitio.horaReunionPrevia = $('#horaReunion').val();
                this.sitio.municipioSede = municipioS;
                this.sitio.departamento.codigo = this.departamento;
                this.sitio.departamentoSede.codigo = this.departamentoSede;
                this.sitio.latitud = Maps.getLatitud();
                this.sitio.longitud = Maps.getLongitud();
                this.sitio.confirmado = 1;
                this.sitio.tipoSitio.idTipoSitio=this.tipoSitio;
           
                this.sitio.pds = this.sitiosAsignados;
                this.sitio.$update({}, function (response) {
                    if (response.codigo === '200') {
                        sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                    } else {
                        sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                    }

                    $location.path('#/');
                });

            } else {

                if (this.codigoSitio.id !== 0) {
                    sweet.show('Oopss', "No se puede guardar porque ya existe un sitio creado con ese c\u00F3digo", "error");
                    return false;
                } else {
                    var estado = 0;
                    if (this.sitio.estadoSitio !== undefined) {
                        estado = this.sitio.estadoSitio.idEstadoSitio;
                    }
                    var sitio = new Sitio({
                        idPrueba: this.idPrueba,
                        codigoSitio: this.sitio.codigoSitio,
                        departamento: {codigo: this.departamento, nombre: ""},
                        municipio: {codigoMunicipio: this.municipio.codigoMunicipio, departamento: {codigo: this.departamento, nombre: ""}, nombre: this.municipio.nombre},
                        nombreSitio: this.sitio.nombreSitio,
                        direccionSitio: this.sitio.direccionSitio,
                        barrio: this.sitio.barrio,
                        categoriaSitio: this.sitio.categoriaSitio,
                        idZona: this.sitio.idZona,
                        instruccionesLlegada: this.sitio.instruccionesLlegada,
                        nombreRector: this.sitio.nombreRector,
                        telefonoRector: this.sitio.telefonoRector,
                        emailRector: this.sitio.emailRector,
                        faxRector: this.sitio.faxRector,
                        codigoSede: this.sitio.codigoSede,
                        departamentoSede: {codigo: this.departamentoSede, nombre: ""},
                        municipioSede: municipioS,
                        direccionSede: this.sitio.direccionSede,
                        emailSede: this.sitio.emailSede,
                        nombreSede: this.sitio.nombreSede,
                        rectorSede: this.sitio.rectorSede,
                        telefonoSede: this.sitio.telefonoSede,
                        cantidadSalones: this.sitio.cantidadSalones,
                        nExaminandos: this.sitio.nExaminandos,
                        fechaReunionPrevia: $('#datepicker').val(),
                        horaReunionPrevia: $('#horaReunion').val(),
                        medioPago: this.sitio.medioPago,
                        tomaAsistencia: this.sitio.tomaAsistencia,
                        tipoSitio: {idTipoSitio:this.tipoSitio, nombre:""},
                        latitud: Maps.getLatitud(),
                        longitud: Maps.getLongitud(),
                        confirmado: 1,
                        espolivalente: this.sitio.espolivalente,
                        estadoSitio: {idEstadoSitio: estado, descripcion: ""}
                    });

                    sitio.$crear({}, function (response) {
                        codigo = response.codigo;

                        if (codigo === '200') {

                            sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");

                        } else {
                            sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                        }
                        $location.path('#/RegistrarSitio');
                    });
                }
            }
        }

        this.validarSitio = function () {
            this.codigoSitio = Sitio.obtenerSitioPorCodigoPuesto({id: self.sitio.codigoSitio, idPrueba: this.idPrueba}, function (data) {
                if (data.id !== 0 && $routeParams.idSitio == undefined) {
                    sweet.show('Oopss', "Ya existe un sitio creado con ese c\u00F3digo", "error");
                } else {
                    self.historial = Sitio.listarHistorialContactosSitio({codigoSitio: self.sitio.codigoSitio}, function (data) {})
                }
            });
        };
        
        this.ubicarDireccion = function () {
            
            this.mapaActivo = true;
            
            if((this.sitio.latitud == undefined || this.sitio.latitud == 0) && (this.sitio.longitud == undefined || this.sitio.longitud == 0)) {
                
                if(this.sitio.direccionSitio && this.municipio.nombre) {
                    Maps.printAddress(this.sitio.direccionSitio, this.municipio.nombre);
                }
            }
            else {
                Maps.printCoordinates(this.sitio.latitud, this.sitio.longitud, this.sitio.direccionSitio, this.municipio.nombre, this.sitio.confirmado);  
                
                // Consulta los Jefes de salon
                Empleado.buscarAsignados({"idSitio": $routeParams.idSitio, "nivelCargo": 7}, function (data) {
                    angular.forEach(data, function(empleado, key) {                                                                                                                                                
                       Maps.printCercanos(empleado, "blue");   
                    });                                        
                });
                
                // Consulta los coordinadores de salon
                Empleado.buscarAsignados({"idSitio": $routeParams.idSitio, "nivelCargo": 3}, function (data) {
                    angular.forEach(data, function(empleado, key) {                                                                                                                                                
                       Maps.printCercanos(empleado, "green");   
                    });                                        
                });
            }
        };

    }
    ;
    angular.module("app").controller('RegistrarSitio', RegistrarSitio);
})();

