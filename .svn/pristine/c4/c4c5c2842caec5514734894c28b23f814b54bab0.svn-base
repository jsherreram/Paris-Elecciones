(function () {
    angular.module("app").controller("ActualizarEmpleadoController", function ($location, $routeParams, Funcionario, Departamento) {
        var self = this;
        self.idEmpleado = $routeParams.idEmpleado;
        self.habilitarNodo = false;
        self.mostrarRoles = false;


        Departamento.listar(function (data) {
            self.listadoNodos = data;

        });
        Funcionario.listarGruposFuncionarios(function (data) {
            self.listadoRoles = data;

        });

        self.empleado = Funcionario.empleadoSesion(function (response) {
            self.idPruebaActual = response.pruebaActual;

            if (self.idEmpleado !== undefined) {
                self.mostrarRoles = false;

                Funcionario.buscarJsonFuncionario({idEmpleado: self.idEmpleado, idPrueba: self.idPruebaActual}, function (response) {
                    self.listEmpleado = response;

                    if (self.listEmpleado.length > 0) {
                        self.mostrarRoles = true;
                        if (self.listEmpleado[0].roles !== undefined) {
                            self.rolesSeleccionados = self.listEmpleado[0].roles.split(",");
                        } else {
                            self.rolesSeleccionados = [];
                        }

                        if (self.listEmpleado[0].numDep !== undefined) {
                            self.nodosSeleccionados = self.listEmpleado[0].numDep.split(",");
                        } else {
                            self.nodosSeleccionados = [];
                        }


                        self.nrodoc = self.listEmpleado[0].nrodoc;
                        for (var i = 0; i < self.rolesSeleccionados.length; i++) {
                            if (self.rolesSeleccionados[i] === "coordinador_icfes" || self.rolesSeleccionados[i] === "coordinador") {
                                self.habilitarNodo = true;
                                break;
                            }
                        }
                    } else {
                        alert("Usuario no encontrado");
                    }
                });
            }
        });

        self.buscarEmpleado = function () {
            Funcionario.buscarJsonFuncionarioCedula({idEmpleado: self.searchIdentificacion, idPrueba: self.idPruebaActual}, function (response) {
                self.listEmpleado = response;
                if (self.listEmpleado.length > 0) {
                    self.mostrarRoles = true;
                    if (self.listEmpleado[0].roles !== undefined)
                        self.rolesSeleccionados = self.listEmpleado[0].roles.split(",");
                    else
                        self.rolesSeleccionados = [];

                    if (self.listEmpleado[0].numDep !== undefined)
                        self.nodosSeleccionados = self.listEmpleado[0].numDep.split(",");
                    else
                        self.nodosSeleccionados = [];

                    self.nrodoc = self.listEmpleado[0].nrodoc;
                    for (var i = 0; i < self.rolesSeleccionados.length; i++) {
                        if (self.rolesSeleccionados[i] === "coordinador_icfes" || self.rolesSeleccionados[i] === "coordinador") {
                            self.habilitarNodo = true;
                            break;
                        }
                    }
                }
                else {
                    self.mostrarRoles = false;
                    self.habilitarNodo = false;
                    self.rolesSeleccionados = [];
                    self.nodosSeleccionados = [];
                    alert("Usuario no encontrado");
                }
            });
        };


        self.cambioRoles = function () {
            self.habilitarNodo = false;
            for (var i = 0; i < self.rolesSeleccionados.length; i++) {
                if (self.rolesSeleccionados[i] === "coordinador_icfes" || self.rolesSeleccionados[i] === "coordinador") {
                    self.habilitarNodo = true;
                    break;
                }
            }
        };



        self.actualizarFuncionario = function () {

            if (self.listEmpleado !== undefined) {
                if (self.listEmpleado[0].estadoicfes === 0) {
//                    self.isRolAdmin=false;
//                    for (var i = 0; i < self.rolesSeleccionados.length; i++) {
//                        if (self.rolesSeleccionados[i] === "administrador" || self.rolesSeleccionados[i] === "administrador_icfes") {
//                            self.isRolAdmin = true;
//                            break;
//                        }
//                    }
                    if (!self.habilitarNodo) {
                        self.nodosSeleccionados = [];
                    }
                    var funcionario = new Funcionario({
                        roles: self.rolesSeleccionados,
                        nodos: self.nodosSeleccionados,
                        idPrueba: self.idPruebaActual,
                        nroDoc: self.listEmpleado[0].nrodoc
                    });
                    var json = JSON.stringify(funcionario);

                    Funcionario.asignarRoles(json, function (response) {
                        if (response.codigo === '200')
                        {
                            $location.path('/consultarEmpleados/');
                            alert("La asignaci\u00F3n ha sido realizada!");
                        } else
                        {
                            alert(response.descripcion);
                        }
                    });
                }
                else {
                    alert("El empleado no se encuentra activo. No se puede modificar.");
                }
            }

        };

        self.regresar = function () {
            $location.path('/consultarEmpleados/');
        };


    });

})();

