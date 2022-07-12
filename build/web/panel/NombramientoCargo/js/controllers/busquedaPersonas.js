(function () {
    function BusquedaPersonas(Empleado, NombramientoCargoService, DepartamentoDane, MunicipioDane, Sitio, $routeParams, $scope, sweet, $window, GestionPersonas) {
        var self = this;
        var idPrueba = 0;
        $scope.departamentoDane = 0;
        $scope.municipioDane = 0;
        $scope.salones = 0;
        $scope.distancia = "";
        $scope.viaja = 0;
        var params = {};
        $scope.selected = true;
        //idSitio/:codDpto/:codMun/:codZona/:sal/:cargo/:idNom

        //Se obtiene el id de la prueba para enviarla en la consulta
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            self = this;
        }).$promise.then(function (response) {
            idPrueba = response.pruebaActual;
            if ($routeParams.codDpto !== undefined && $routeParams.idSitio !== undefined && $routeParams.cargo !== undefined) {
                params = {
                    idSitio: $routeParams.idSitio,
                    nivelCargo: $routeParams.nivel,
                    idPrueba: idPrueba
                };
                if ($routeParams.codDpto > 0) {
                    params.codigoDepartamento = $routeParams.codDpto;
                    DepartamentoDane.listarDepartamentosNodo({idDepartamento: $routeParams.codDpto}, function (response) {
                        params.codDeptoDane = response[0].codigo;
                        //Funcion encargada de buscar las personas candidatas a ser nombradas en el sitio especifico
                        $scope.personas = NombramientoCargoService.buscarPersonasNombramiento({params: params}, function (response) {
                            $scope.currentPage = 1;
                            $scope.pageSize = 5;
                            $("html, body").animate({scrollTop: $("#divBusqueda").height()}, "slow");
                        });
                    });
                }
            }
        });

        //Se busca el sitio en el que se deben asignar los cargos para mostrar el detalle del sitio
        $scope.sitio = Sitio.obtenerSitioPorId({id: $routeParams.idSitio}, function (response) {});
        //Se buscan los departamentos para los filtros de busqueda
        $scope.departamentos = DepartamentoDane.listarDepartamentosNodo({idDepartamento: $routeParams.codDpto}, function (response) {
            response[0].selected = "selected";
            $scope.departamentoDane = response[0].codigo;
            $scope.municipios = MunicipioDane.getByDpto({idDpto: response[0].codigo}, function (resp) {
                resp[0].selected = "selected";
                $scope.municipioDane = resp[0].codigoMunicipio;
            });
        });
        //Se buscan los municipios segun el departamento seleccionado
        this.buscarMunicipios = function () {
            $scope.municipios = MunicipioDane.getByDpto({idDpto: $scope.departamentoDane}, function (response) {});
        };

        //Funcion para buscar las personas que pueden ser nombradas
        this.buscarPersonas = function () {
            var error = false;
            if ($scope.departamentoDane === 0 || $scope.departamentoDane === undefined) {
                $("#labelDep").popover('show');
                error = true;
            } else if ($scope.municipioDane === 0 || $scope.municipioDane === undefined) {
                $("#labelMun").popover('show');
                $("#labelDep").popover('hide');
                error = true;
            } else {
                $("#labelDep").popover('hide');
                $("#labelMun").popover('hide');
            }

            if (!error) {
                params = {
                    idSitio: $routeParams.idSitio,
                    codigoDepartamento: $routeParams.codDpto,
                    cantidadSalones: $scope.salones,
                    nivelCargo: $routeParams.nivel,
                    codDeptoDane: $scope.departamentoDane,
                    codigoMunDane: $scope.municipioDane,
                    distancia: $scope.distancia,
                    idPrueba: idPrueba
                };

                if ($scope.viaja >= 0) {
                    params.viajar = $scope.viaja;
                }

                $scope.personas = NombramientoCargoService.buscarPersonasNombramiento({params: params}, function (response) {
                    $scope.currentPage = 1;
                    $scope.pageSize = 5;
                    $("html, body").animate({scrollTop: $("#divBusqueda").height()}, "slow");
                });
            }
        };

        //Funcion para asignar la persona seleccionada al nombramiento
        this.asignarPersona = function (personaAsignar) {
            $('#modalLoading').modal('show');
            var persona = personaAsignar;
            NombramientoCargoService.getNombramientoById({id: $routeParams.idNom}, function (nombramiento) {
                nombramiento.usuario = 0;
                nombramiento.estado.codigoEstado = 1;
                var cargoObj = {nivel_cargo : $routeParams.nivel};
                nombramiento.empleado.cargoobj = cargoObj;
                nombramiento.empleado.nrodoc = persona.empleado.nrodoc;
                nombramiento.idPrueba = idPrueba;
                //Se actualiza el nombramiento con el empleado seleccionado
                NombramientoCargoService.actualizarNombramiento(nombramiento, function (response) {
                    if (response.codigo === '200') {
                        sweet.show({title: "Asignaci\u00F3n realizada para " + $scope.sitio.nombreSitio + "!",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonText: "OK",
                            closeOnConfirm: true}, function () {
                            $window.location.href = "./main.jsp#/" + $routeParams.codDpto + "/" + $routeParams.codMun + "/" + $routeParams.codZona + "/" + $routeParams.sal + "/" + $routeParams.nivel +"/" + $routeParams.page+ "/" + $routeParams.cargo;
                        });
                    } else {
                        sweet.show('Error', response.descripcion, "error");
                        nombramiento.usuario = 0;
                        nombramiento.estado.codigoEstado = 0;
                    }
                    $('#modalLoading').modal('hide');
                });
            });
        };
        
        //Funcion encargada de volver al prototipo anterior
        this.volver = function(){
            $window.location.href = "./main.jsp#/" + $routeParams.codDpto + "/" + $routeParams.codMun + "/" + $routeParams.codZona + "/" + $routeParams.sal + "/" + $routeParams.nivel +"/"+ $routeParams.page+"/"+ $routeParams.cargo;
        };

        //Funcion encargada de visualizar el historial laboral de la persona seleccionada
        this.verHistorialLaboral = function (personaConsulta) {
            var person = personaConsulta;
            $scope.trabajador = Empleado.getEmpleado({id: person.empleado.idEmpleado}, function (data) {
                $scope.trabajador.nombre1 = data.nombre1 + " " + data.nombre2 + " " + data.apellido1 + " " + data.apellido2;
            });
            GestionPersonas.listarHistorialLaboral({id: person.empleado.idEmpleado}, function (data) {
                $scope.historial = data;
                $scope.currentPage2 = 1;
                $scope.pageSize2 = 3;
                $("#modalHistorial").modal("show");
            });
        };
        
        //Funcion para ordenar la tabla
        $scope.funcionOrdenar = function (persona) {
            return parseInt(persona.empleado.nrodoc);
        };
    }
    ;
    angular.module("app").controller('BusquedaPersonas', BusquedaPersonas);
})();