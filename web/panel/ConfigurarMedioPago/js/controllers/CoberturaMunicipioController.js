(function() {
    angular.module("app").controller("CoberturaMunicipioController", function($location, DepartamentoDane, MunicipioDane, Cobertura,Sesion) {
        var self = this;
        self.filtros;
        self.listaDepartamentos = DepartamentoDane.listar({});
         Sesion.empleadoSesion(function (response) {
            self.idPrueba = response.pruebaActual;
        });

        self.buscarMunicipios = function() {
            self.listaMunicipios = MunicipioDane.getByDpto({idDpto: self.filtros.idDepartamento.codigo});
        };
        self.buscarMediosPagos = function() {
            self.listado = Cobertura.listarMediosMunicipio({codMunicipio: self.filtros.idMunicipio.codigoMunicipio,idPrueba:self.idPrueba});
            self.modoPagosSeleccionados = Cobertura.listarMediosMunicipioAsignados({codMunicipio: self.filtros.idMunicipio.codigoMunicipio,idPrueba:self.idPrueba});

        };

        self.regresar = function() {
            $location.path('/consultarMediosPago/');
        };
        self.registrarCobertura = function() {
            var cobertura = new Cobertura({
                codDep: self.filtros.idDepartamento.codigo,
                codMun: self.filtros.idMunicipio.codigoMunicipio,
                prueba: self.idPrueba,
                listaMedioPagos: self.modoPagosSeleccionados

            });
            var json = JSON.stringify(cobertura);

            Cobertura.registrarCobertura(json, function(response) {
                if (response.codigo === '200')
                {
                    alert("Los medios de pago se asignaron correctamente!");

                } else
                {
                    alert(response.descripcion);
                }
            });
        };
    });




    angular.module("app").controller("AppControllerStatus", function($location, Cobertura, $interval, $routeParams,Sesion) {
        var self = this;
        Sesion.empleadoSesion(function (response) {
            self.idPrueba = response.pruebaActual;
        });
        self.idmedio_pago = $routeParams.idmedio_pago;
        var cobertura = new Cobertura({
                idTipoCargue: 9, //Cobertura por municipios
                identificadorRegistro: self.idmedio_pago
            });
            
        Cobertura.listarStatus(cobertura, function(data) {
            self.status = data;

            //Paginacion
            self.totalItems = self.status.length;
            self.currentPage = 1;
            self.numPerPage = 20;

            self.paginateDet = function(value) {
                var begin, end, index;
                begin = (self.currentPage - 1) * self.numPerPage;
                end = begin + self.numPerPage;
                index = self.status.indexOf(value);
                return (begin <= index && index < end);
            };
        });

        self.regresar = function() {
            $location.path('/consultarMediosPago/');
        };

        //refrescar cada 10 segundos
        var timer = $interval(function() {
            var cobertura = new Cobertura({
                idTipoCargue: 9, //Cobertura por municipios
                identificadorRegistro: self.idmedio_pago
            });
            Cobertura.listarStatus(cobertura, function(data) {
                self.status = data;
                
                //Paginacion
                self.totalItems = self.status.length;
                self.currentPage = 1;
                self.numPerPage = 20;

                self.paginateDet = function(value) {
                    var begin, end, index;
                    begin = (self.currentPage - 1) * self.numPerPage;
                    end = begin + self.numPerPage;
                    index = self.status.indexOf(value);
                    return (begin <= index && index < end);
                };
            });

        }, 5000);
        self.subirArchivo = function() {
            $location.path('/cargarArchivo/' + self.idmedio_pago + '/' + self.idPrueba + '/');
        };

    });
})();



 