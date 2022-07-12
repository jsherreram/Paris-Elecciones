(function () {
    function ValidarAsistenciaController($scope, Empleado, ValidaAsistencia, Departamento, $routeParams, $http, $sce, sweet, $window) {
        var self = this;
        var idPrueba = 0;
        this.codigoSitio = $routeParams.codigoSitio;


        this.departamentos = Departamento.listar({}, function (data) {});

        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;

            ValidaAsistencia.getSitio({id: self.codigoSitio, idPrueba: idPrueba}, function (data) {
                self.sitio = data;

                ValidaAsistencia.buscarSitioEventoValidado({idEvento: $routeParams.idEvento, idDivipol: self.sitio.id}, function (data) {
                    self.sitiorevisado = data[0].count;
                });
            });

            self.url = "/fotosSitioElecciones/prueba_" + idPrueba + "/sitio_" + self.codigoSitio + "/evento_" + $routeParams.idEvento + "/asistencia/" + self.codigoSitio + ".pdf";
            $http.get(self.url,
                    {responseType: 'arraybuffer'})
                    .success(function (response) {
                        var file = new Blob([(response)], {type: 'application/pdf'});
                        var fileURL = URL.createObjectURL(file);
                        self.content = $sce.trustAsResourceUrl(fileURL);
                    });
        });

        ValidaAsistencia.listarAsistenciaSitioEvento({idEvento: $routeParams.idEvento, codigoSitio: $routeParams.codigoSitio}, function (data) {
            $scope.listado = [];
            data.forEach(function (persona) {
                if (persona.asistenciabiometrica === 0 && persona.empleado.nrodoc > 0 && persona.asistio === true) {
                    $scope.listado.push(persona);
                }
            });
          
        });

        this.volver = function () {
            $window.history.back();
        };

        this.guardarValidacion = function (idDivipol, idEmpleado, validacion) {

            ValidaAsistencia.guardaValidacionAsistencia({idEvento: $routeParams.idEvento, idDivipol: idDivipol,
                idEmpleado: idEmpleado, validacion: validacion}, function (data) {
                if (data.codigo === '200') {
                    sweet.show('Felicidades', 'La validaci\u00F3n se guard\u00F3 correctamente', "success");
                } else {
                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                }
            });

        };

        this.guardarSitioRevisado = function () {

            sweet.show({
                title: 'Confirmar',
                text: 'Est\u00e1 seguro que desea guardar Sitio como revisado?',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3e6bcc',
                cancelButtonText: 'NO',
                confirmButtonText: 'SI',
                closeOnConfirm: false
            }, function () {
                ValidaAsistencia.guardaSitioEventoValidado({idDivipol: self.sitio.id, idEvento: $routeParams.idEvento}, function (data) {
                    if (data.codigo === '200') {
                        sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                        self.sitiorevisado = 1;
                    } else {
                        sweet.show('Oopss', "error al guardar la informaci\u00F3n ", "error");
                    }
                });

            });
        }


    }

    ;
    angular.module("app").controller('ValidarAsistenciaController', ValidarAsistenciaController);
})();

