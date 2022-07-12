(function () {
    function controllerMonitoreoSitio($scope, Sesion, MonitoreoSitio, Sitio, $window) {
        var self = this;
        this.idprueba = 0;
        this.usuario = "";
        $scope.currentPage2 = 1;
        $scope.pageSize2 = 5;
        
        //obtener la prueba actual
        Sesion.getEmpleadoPruebaSesion({}, function (data) {
            self.idprueba = data.pruebaActual;
            self.usuario = data.nrodoc;

            self.eventos = MonitoreoSitio.listarEventosAplicacion({idPrueba: data.pruebaActual}, function (data) {
                self.evento = data[0].codigoEvento;
                self.buscar();
            });
       

        });

        this.buscar = function () {
            $("#modalLoading").modal('show');
            self.sitios = MonitoreoSitio.listarSitiosyRPS({usuario: this.usuario, idPrueba: this.idprueba, idEvento: this.evento}, function (data) {
                $("#modalLoading").modal('hide');
            });
        };

        this.revertir = function (s) {
            var sitio = new Sitio({
                estado: s.desconectado,
                iddivipol: s.iddivipol,
                idPrueba: this.idprueba
            });
            sitio.$update({}, function () {
            });
        };
    }
    ;
    angular.module('app').controller('ControllerMonitoreoSitio', controllerMonitoreoSitio);
})();




