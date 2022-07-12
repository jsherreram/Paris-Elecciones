(function () {
    function NombramientoCargo(Departamento, Municipio, Empleado, Evento, Cargo, ZonaIcfes, NombramientoCargoService, UsuarioDepartamento, $scope, sweet, $location, $routeParams) {
        var self = this;
        var idPrueba = 0;
        var usuarioAcutaliza = 0;
        this.departamento = 0;
        this.municipio = 0;
        this.cargo = 0;
        this.evento = 0;
        $scope.onlyNumbers = /^\d+$/;

        //Se consultan los departamentos Dane
        this.departamentos = Departamento.listar({}, function (data) {});

        //Se obtiene el id de la prueba para enviarla en la consulta
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
            usuarioAcutaliza = data.nrodoc;
            self.rol = data.rolActual;

            if (self.rol === 'coordinador') {
                UsuarioDepartamento.usuarioDepartamento({usuario: usuarioAcutaliza, idPrueba: idPrueba}, function (data) {
                    self.departamento = data[0].codigo;
                    self.buscarMunicipios();
                });
            }
              self.listarEventos();

        });

        this.listarEventos = function () {
            Evento.listarEventoAplicacion({idPrueba: idPrueba}, function (data) {
                $scope.eventos = data;
                $scope.evento = data[0].codigoEvento;

            });
        };



        //Se consultan los cargos para el filtro
        this.cargos = Cargo.listar({}, function (data) {});

        //Se listan las zonas del sistema
        this.zonas = ZonaIcfes.listar({}, function (data) {});

        // busca municipios de acuerdo al departamento
        this.buscarMunicipios = function () {
            this.municipios = Municipio.getByDpto({idDpto: self.departamento}, function (data) {});
        };

        //funcion para buscar los Nombramientos del sitio segun los filtros enviados
        this.buscarNombramientosSitio = function () {
            var error = false;
            console.log("filtros:", this.departamento, "-", this.cargo);
            if (this.departamento === 0) {
                error = true;
                $('#departamento').popover('show');
                $("#cargo").popover('hide');
                $("#evento").popover('hide');
            } else if (this.cargo === 0) {
                error = true;
                $('#cargo').popover('show');
                $("#departamento").popover('hide');
                $("#evento").popover('hide');
            } else if (this.evento === 0) {
                error = true;
                $("#evento").popover('show');
                $('#cargo').popover('hide');
                $("#departamento").popover('hide');
            } else {
                $("#departamento").popover('hide');
                $("#cargo").popover('hide');
                $("#evento").popover('hide');
            }
//
            if (!error) {
                $('#modalLoading').modal('show');
                var params = {
                    codigoMunicipio: this.municipio,
                    codigoCargo: this.cargos[this.cargo].codigoCargo,
                    idEvento: this.evento,
                    codigoDepartamento: this.departamento
                };
                //Se invoca al servicio de nombramiento que realiza la busqueda de los nombramientos
                this.nombramientos = NombramientoCargoService.getNombramientoCargoFiltros(params, function (response) {
                    $('#modalLoading').modal('hide');
                    $(window).scrollTop($("#divBusqueda").offset().top());
                });
            }
        };

        //Funcion encargada de desasignar a la persona del nombramiento en la pagina principal
        this.desasignarPrincipal = function (nombramientoModificar) {
            $('#modalLoading').modal('show');
            var nombramiento = nombramientoModificar;
            nombramiento.empleado.nrodoc = 0;
            nombramiento.estado.codigoEstado = 0;
            nombramiento.usuario = usuarioAcutaliza;
            var self = this;
            NombramientoCargoService.actualizarNombramiento(nombramiento, function (response) {
                if (response.codigo === '200') {
                    sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                    self.buscarNombramientosSitio();
                    $('#modalLoading').modal('hide');
                } else {

                }
            });
        };

        //Funcion para asignar un empleado al nombramiento
        this.asignarPrincipal = function (nombramientoModificar) {
            if (nombramientoModificar.empleado.nrodoc > 0) {
                $('#modalLoading').modal('show');
                var nombramiento = nombramientoModificar;
                nombramiento.usuario = usuarioAcutaliza;
                nombramiento.estado.codigoEstado = 1;
                var self = this;
                NombramientoCargoService.actualizarNombramiento(nombramiento, function (response) {
                    if (response.codigo === '200') {
                        sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                        self.buscarNombramientosSitio();
                    } else {
                        sweet.show('Error', response.descripcion, "error");
                        nombramiento.usuario = 0;
                        nombramiento.estado.codigoEstado = 0;
                    }
                    $('#modalLoading').modal('hide');
                });
            } else {
                sweet.show('Error', "Debe ingresar un documento para asignar!", "error");
            }
        };



//        //Funcion para ordenar la tabla
//        $scope.funcionOrdenar = function (nombramiento) {
//            return parseInt(nombramiento.divipol.codigoSitio);
//        };

    }
    ;
    angular.module("app").controller('NombramientoCargo', NombramientoCargo);
})();