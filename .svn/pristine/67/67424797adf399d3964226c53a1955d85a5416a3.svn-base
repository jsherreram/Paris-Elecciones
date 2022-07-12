(function () {
    function GestionarPersona(Empleado, DepartamentoDane, Departamento, $scope, GestionPersonas, $uibModal, $log) {
        var self = this;
        $scope.currentPage = 1;
        $scope.pageSize = 15;
        this.departamentos = DepartamentoDane.listar({}, function (data) {});
        //Inicializamos los campos de filtro de busqueda
        this.apellido1 = "";
        this.apellido2 = "";
        this.nombre1 = "";
        this.nombre2 = "";
        this.nrodoc = "";
        var idPrueba = 0;
        var rol = "";
        var user = "";
        this.dpto = "";
        this.mensaje="";
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
            rol = data.rolActual;
            user = data.nrodoc;
            if (rol === "coordinador") {
                GestionPersonas.usuarioDepartamento({usuario: user, idPrueba: idPrueba}, function (data) {
                    self.dpto = data[0].codigo;

                });
            }
        });
        this.municipio = "0";
        //función que busca personas por departamento y municipio
        this.buscarPersonaRapida = function () {

            $("#modalLoading").modal('show');
            self.personas = Empleado.buscarEmpleadoPorFiltroBasico({nrodoc: this.nrodoc, nombre1: this.nombre1, nombre2: this.nombre2,
                apellido1: this.apellido1, apellido2: this.apellido2, dpto: this.dpto}, function (data) {
                if(data.length===0){
                    self.mensaje="No se encontraron personas registradas";
                }
                $("#modalLoading").modal('hide');
            });
        }


// funcion que abre el modal de busqueda avanzada
        this.mostrarBusquedaAvanzada = function () {
            $scope.personas = "";
            var modalInstance = $uibModal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'myModalBusquedaAvanzada.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: 'controller',
                size: 'lg',
                resolve: {
                    personas: function () {
                        return $scope.personas;
                    },
                    departamentos: function () {
                        return self.departamentos;
                    }
                }
            });
            modalInstance.result.then(function (personas) {
                self.personas = personas;
            }, function () {

            });
        }

// funcion que abre el modal de ver Detallado de persona
        this.verDetallado = function (id) {

            var modalInstance = $uibModal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'myModalVerDetallado.html',
                controller: 'verPersonaDetallado',
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

        // funcion que abre el modal de ver Detallado de persona
        this.subirArchivoAhorroalaMano= function (id) {

            var modalInstance = $uibModal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'myModalAhorroalaMano.html',
                controller: 'subirAhorroalaMano',
                controllerAs: 'controller',
                size: 'lg',
                resolve: {
                    id: function () {
                        return id;
                    }
                }
            });
            modalInstance.result.then(function () {

            }, function () {

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


    }
    ;
    angular.module("app").controller('GestionarPersona', GestionarPersona);
})();
/**DIrectiva que muestra el modal de Busqueda avanzada*/
angular.module("app").directive('busquedaAvanzada', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/busquedaAvanzada.html'
    };
});
/**Controller del modal de busqueda avanzada*/
(function () {
    function ModalInstanceCtrl($scope, $uibModalInstance, personas, departamentos, GestionPersonas, Departamento) {
        var self = this;
        $scope.personas = personas;
        this.departamentos = departamentos;
        this.cargos = GestionPersonas.listarCargos({}, function (data) {});
        this.nodos = Departamento.listar({}, function (data) {});
        this.tiposPrueba = GestionPersonas.listarTiposPrueba({}, function (data) {});
        this.cargando = false;
        self.prueba = "";
        self.persona = "";
        this.municipio = "0";
        var codigoMunicipio = "";
        if (this.municipio.codigoMunicipio != null) {
            codigoMunicipio = this.municipio.codigoMunicipio;
        }
        $scope.limpiar = function () {
            limpiar();
        }

        function limpiar() {

            self.prueba = "";
            self.persona.bilingue = "";
            self.persona.manejodiscapacidad = "";
            self.persona.experienciahuellas = "";
            self.persona.nivelacademico = "";
            self.persona.intepretacionsenas = "";
            self.persona.departamento = "";
            self.persona.cargo = "";
            self.persona.nodo = "";
            self.persona.genero = "";
            self.persona.notaEvaluacionDesde = "";
            self.persona.notaEvaluacionHasta = "";
            self.prueba.fechaInicial = "";
            self.prueba.fechaFinal = "";
            self.prueba.tipoPrueba = "";
        }

        $scope.ok = function () {
            self.cargando = true;
            var bilingue = self.persona.bilingue;
            var interpreta = self.persona.intepretacionsenas;
            var dactilo = self.persona.experienciahuellas;
            var manejaDiscapacidad = self.persona.manejodiscapacidad;
            if (self.persona.bilingue == undefined) {
                bilingue = -1;
            }
            if (self.persona.intepretacionsenas == undefined) {
                interpreta = -1;
            }
            if (self.persona.experienciahuellas == undefined) {
                dactilo = -1;
            }
            if (self.persona.manejodiscapacidad == undefined) {
                manejaDiscapacidad = -1;
            }
            var fechaInicial = "";
            if (self.prueba.fechaInicial != undefined) {
                fechaInicial = new Date(self.prueba.fechaInicial);
                fechaInicial = fechaInicial.getFullYear() + '-' +
                        pad(fechaInicial.getMonth() + 1) + '-' +
                        pad(fechaInicial.getDate());
            }
            var fechaFinal = "";
            if (self.prueba.fechaFinal != undefined) {
                fechaFinal = new Date(self.prueba.fechaFinal);
                fechaFinal = fechaFinal.getFullYear() + '-' +
                        pad(fechaFinal.getMonth() + 1) + '-' +
                        pad(fechaFinal.getDate());
            }
            $scope.personas = GestionPersonas.buscarPorFiltro({departamento: self.persona.departamento, municipio: codigoMunicipio, nivel: self.persona.nivelacademico,
                cargo: self.persona.cargo, bilingue: bilingue, interpretaSenas: interpreta,
                manejoDiscapacidad: manejaDiscapacidad, notaEvaluacionDesde: self.persona.notaEvaluacionDesde, genero: self.persona.genero,
                nodo: self.persona.nodo, fechaInicial: fechaInicial, fechaFinal: fechaFinal, tipoPrueba: self.prueba.tipoPrueba,
                notaEvaluacionHasta: self.persona.notaEvaluacionHasta, experienciaHuellas: dactilo}, function (data) {

                $scope.valor = data;
                self.cargando = false;
                $uibModalInstance.close($scope.valor);
                limpiar();
            });
        };
        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }
    }
    ;
    angular.module('app').controller('ModalInstanceCtrl', ModalInstanceCtrl);
})();
(function () {
    function ModalInstanceCtrl2($scope, $uibModalInstance) {


        $scope.ok = function () {

            $uibModalInstance.close($scope.valor);
        };
        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }
    ;
    angular.module('app').controller('ModalInstanceCtrl2', ModalInstanceCtrl2);
})();
angular.module("app").directive('popOver', function ($compile, $templateCache) {
    var self = this;
    var getTemplate = function () {
        $templateCache.put('templateId.html', 'This is the content of the template');
        //console.log($templateCache.get("popover_template.html"));
        return $templateCache.get("popover_template.html");
    }
    return {
        restrict: "A",
        transclude: true,
        template: "<span ng-transclude></span>",
        link: function (scope, element, attrs) {
            var popOverContent;
            if (scope.opciones) {
                var html = getTemplate();
                popOverContent = $compile(html)(scope);
                var options = {
                    content: popOverContent,
                    placement: "bottom",
                    html: true,
                    title: scope.title
                };
                $(element).popover(options);
            }
        },
        scope: {
            opciones: '=',
            title: '@'
        }
    };
});
angular.module("app").directive("modalShow", function ($parse) {
    return {
        restrict: "A",
        link: function (scope, element, attrs) {

            //Hide or show the modal
            scope.showModal = function (visible, elem) {
                if (!elem)
                    elem = element;
                if (visible)
                    $(elem).modal("show");
                else
                    $(elem).modal("hide");
            }

            //Watch for changes to the modal-visible attribute
            scope.$watch(attrs.modalShow, function (newValue, oldValue) {
                scope.showModal(newValue, attrs.$$element);
            });
            //Update the visible value when the dialog is closed through UI actions (Ok, cancel, etc.)
            $(element).bind("hide.bs.modal", function () {
                $parse(attrs.modalShow).assign(scope, false);
                if (!scope.$$phase && !scope.$root.$$phase)
                    scope.$apply();
            });
        }

    };
});