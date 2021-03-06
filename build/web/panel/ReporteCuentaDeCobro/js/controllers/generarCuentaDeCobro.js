(function () {
    function generarCuentaCobro(Departamento, Empleado, UsuarioDepartamento, Cargo, $scope, $window, $http, $sce) {
        var self = this;
        this.departamento = 0;
        this.municipio = 0;
        var idPrueba = 0;
        var user = "";
        this.tipoReporte = "";

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
                });
            }
        });

        var ruta = $window.location.origin;

        this.generarCuentas = function () {
            $("#modalLoading").modal('show');
            //Genera el pdf
            $scope.url = ruta + "/Paris/CuentaCobro?tipoReporte=reporte&codigoCargo=" + this.cargo + "&codigoDepartamento=" + this.departamento + "&codigoMunicipio=" + this.municipio.codigoMunicipio + "&codigoEvento=0&idPrueba=" + idPrueba;

            $http.get($scope.url,
                    {responseType: 'arraybuffer'})
                    .success(function (response) {
                        var file = new Blob([(response)], {type: 'application/pdf'});
                        var fileURL = URL.createObjectURL(file);
                        $scope.content = $sce.trustAsResourceUrl(fileURL);
                        $("#modalLoading").modal('hide');
                    });

            $scope.httpHeaders = {Authorization: 'Bearer some-aleatory-token'};

        };

        this.limpiar = function () {
            $scope.content = "";
            $scope.nrodoc = "";
            this.municipio = 0;

        };

    }
    ;
    angular.module("app").controller('generarCuentaCobro', generarCuentaCobro);
})();