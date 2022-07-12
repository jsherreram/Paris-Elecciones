(function () {
    function generarCarnet(Departamento, UsuarioDepartamento, Municipio, Empleado, Cargo, $scope, $window, $http, $sce) {
        var self = this;
        this.departamento = 0;
        this.municipio = 0;
        var idPrueba=0;
        var user="";

        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
            self.rol = data.rolActual;
            user = data.nrodoc;

            if (self.rol === 'coordinador') {
                UsuarioDepartamento.usuarioDepartamento({usuario: user, idPrueba: idPrueba}, function (data) {
                    self.departamento = data[0].codigo;
                    self.municipios= Municipio.getByDpto({idDpto:self.departamento}, function (data) {});
                });
            }
        });


        //Se consultan los cargos para el filtro
        this.cargos = Cargo.listar({}, function (data) {});
        this.departamentos = Departamento.listar({}, function (data) {});

        this.buscarMunicipios = function () {
            this.municipio=0;
           this.municipios= Municipio.getByDpto({idDpto:self.departamento}, function (data) {});
        }
        
        var ruta = $window.location.origin;

        this.generarCarnets = function () {
          
            var codigoMunicipio = "";
           
            if (this.municipio !== undefined && this.municipio!==0) {
                codigoMunicipio = this.municipio.codigoMunicipio;
            }
            //Genera el pdf
            $scope.url = ruta + "/Paris/CarneCargo?tipo=general&codigoCargo=" + this.cargo + "&codigoDepartamento=" + this.departamento + "&codigoMunicipio=" + codigoMunicipio;
            $http.get($scope.url,
                    {responseType: 'arraybuffer'})
                    .success(function (response) {
                        var file = new Blob([(response)], {type: 'application/pdf'});
                        var fileURL = URL.createObjectURL(file);
                        $scope.content = $sce.trustAsResourceUrl(fileURL);

                    });

            $scope.httpHeaders = {Authorization: 'Bearer some-aleatory-token'};

        }

    }
    ;
    angular.module("app").controller('generarCarnet', generarCarnet);
})();