(function () {
    function buscarPersonas(Departamento, Municipio, Empleado, RevisaDocumento, $scope, $window, $http, $sce) {
        var self = this;
        this.departamento = "0";
        this.nrodoc = "";
        this.estado = "-1";
        this.personas = [];
        var idPrueba = 0;
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
            self.idPrueba = idPrueba;
        });

        //Se consultan los cargos para el filtro
        this.estados = RevisaDocumento.listarEstados({}, function (data) {

        });

        this.buscarMunicipios = function () {
            this.municipios = Municipio.getByDpto({idDpto: self.departamento}, function (data) {});
        }

        this.departamentos = Departamento.listar({}, function (data) {});

        this.buscarPersonas = function () {
            var codMunicipio = "0";
            $("#modalLoading").modal('show');

            if (this.municipio !== undefined) {
                if (this.municipio !== null) {
                    codMunicipio = this.municipio.codigoMunicipio;
                }
            }
            if (this.estado === "") {
                this.estado = "-1";
            }

            $scope.personas = RevisaDocumento.buscarPersonas({idDpto: self.departamento, idMunicipio: codMunicipio,
                idEstado: self.estado, nrodoc: self.nrodoc, idPrueba: idPrueba}, function (data) {
                $scope.currentPage = 1;
                $scope.pageSize = 10;
                $("#modalLoading").modal('hide');
            });
        }

        this.validar = function () {
            this.mcpio="0";
            if (this.municipio !== undefined) {
                if (this.municipio !== null) {
                    this.mcpio = this.municipio.codigoMunicipio;
                }
            }
            if (this.estado === "") {
                this.estado = "-1";
            }
        }

    }
    ;
    angular.module("app").controller('buscarPersonas', buscarPersonas);
})();