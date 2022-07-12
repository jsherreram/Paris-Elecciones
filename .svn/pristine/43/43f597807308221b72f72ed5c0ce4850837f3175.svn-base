(function () {
    function ConsultarSitios(Sitio, PruebaSitio, Departamento, Municipio, Empleado, UsuarioDepartamento, sweet, filterFilter, $scope) {
        var self = this;
        this.showMsg = false;
        this.pruebas = PruebaSitio.listar({}, function (data) {});
        this.tiposSitio = Sitio.listarTipos({}, function (data) {});
        this.tipoSitio = 0;
        this.codigoSitio = "";
        this.municipio = "0";
        this.departamento = "0";
        var codigoMunicipio = "0";
        var idPrueba = 0;
        this.rol = "";
        var user = "";
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
            self.rol = data.rolActual;
            user = data.nrodoc;
 
            if(self.rol==='coordinador'){
             UsuarioDepartamento.usuarioDepartamento({usuario: user, idPrueba: idPrueba}, function (data) {
                self.departamento = data[0].codigo;
               self.buscarMunicipios();
            });
          }
        });
        this.departamentos = Departamento.listar({}, function (data) {});


        this.reset = function () {
            this.tipoSitio = 0;
            this.codigoSitio = "";
            this.departamento = "0";
            this.municipios = "";
            codigoMunicipio = "0";
        }

        //function del modal que muestra en detalle un sitio
        this.DetallarSitio = function (id) {
            this.sitio = Sitio.obtenerSitioPorId({id: id}, function (data) {
            });
        }

        // busca municipios de acuerdo al departamento
        this.buscarMunicipios = function () {
            this.municipios = Municipio.getByDpto({idDpto: self.departamento}, function (data) {});
        }

        this.buscarSitios = function () {


            if (this.municipio.codigoMunicipio != null) {
                codigoMunicipio = this.municipio.codigoMunicipio;
            }

            $("#modalLoading").modal('show');
         
            this.sitios = Sitio.getSitios({idPrueba: idPrueba, codigoSitio: this.codigoSitio, tipoSitio: this.tipoSitio, departamento: this.departamento, municipio: codigoMunicipio}, function (data) {
                // create empty search model (object) to trigger $watch on update
                $("#modalLoading").modal('hide');
                $scope.currentPage = 1;
                $scope.pageSize = 10;

            });
        }
    }
    ;
    angular.module("app").controller('ConsultarSitios', ConsultarSitios);
})();


angular.module("app").filter('startFrom', function () {
    return function (input, start) {
        if (input) {
            start = +start;
            return input.slice(start);
        }
        return [];
    };
});


