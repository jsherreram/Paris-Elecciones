(function () {
    function reporteAsistenciaMuncipio(Departamento, Empleado, UsuarioDepartamento, Cargo, Evento, $scope, $window, $http, $sce) {
        var self = this;
        this.departamento = 0;
        this.municipio = 0;
        var idPrueba=0;

        //Se consultan los cargos para el filtro
        this.cargos = Cargo.listar({}, function (data) {});
        this.departamentos = Departamento.listar({}, function (data) {});

        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
            self.rol = data.rolActual;
            user = data.nrodoc;

            if (self.rol === 'coordinador') {
                UsuarioDepartamento.usuarioDepartamento({usuario: user, idPrueba: idPrueba}, function (data) {
                    self.departamento = data[0].codigo;
                    self.listarEventos();
                });
            }
        });
        
          this.listarEventos = function () {
            Evento.listarPorDepartamento({idPrueba: idPrueba , idDpto: self.departamento}, function (data) {
                $scope.eventos = data;
                $scope.evento = data[0].codigoEvento;

            });
        };

        var ruta = $window.location.origin;

        this.generarCuentas = function () {
            $("#modalLoading").modal('show');
            //Genera el pdf
            $scope.url = ruta + "/Paris/Reporte?tipoReporte=asistenciaMunicipio&codigomunicipio=" + this.municipio.codigoMunicipio + "&codigoevento="+this.evento;
            $http.get($scope.url,
                    {responseType: 'arraybuffer'})
                    .success(function (response) {
                        var file = new Blob([(response)], {type: 'application/pdf'});
                        var fileURL = URL.createObjectURL(file);
                        $scope.content = $sce.trustAsResourceUrl(fileURL);
                        $("#modalLoading").modal('hide');
                    });

            $scope.httpHeaders = {Authorization: 'Bearer some-aleatory-token'};

        }

    }
    ;
    angular.module("app").controller('reporteAsistenciaMuncipio', reporteAsistenciaMuncipio);
})();