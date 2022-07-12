(function () {
    function controllerListarSitio($scope, Sesion, ListarSitio, $window) {
        var self = this;
        this.idprueba = 0;
        this.usuario = "";
        $scope.currentPage2 = 1;
        $scope.pageSize2 = 5;
        //obtener la prueba actual
        Sesion.getEmpleadoPruebaSesion({}, function (data) {
            self.idprueba = data.pruebaActual;
            self.usuario = data.nrodoc;

            self.eventos = ListarSitio.listarEventosCapacitacion({idPrueba: data.pruebaActual, usuario:data.nrodoc}, function (data) {
                self.evento=data[0].codigoEvento+"";
         
                self.buscar();
            });
        });

        this.buscar = function () {
            self.sitios =ListarSitio.listarSitios({usuario: this.usuario, idPrueba: this.idprueba, idEvento:this.evento}, function (data) {});

        }
    }
    ;
    angular.module('app').controller('ControllerListarSitios', controllerListarSitio);
})();




