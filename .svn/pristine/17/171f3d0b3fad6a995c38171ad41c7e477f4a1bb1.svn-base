(function () {
    function controllerListarSitios($scope, Sesion, ListarSitio, $window) {
        var self = this;
        this.idprueba=0;
        this.usuario="";
        $scope.currentPage2 = 1;
        $scope.pageSize2 = 5;
        //obtener la prueba actual
        Sesion.getEmpleadoPruebaSesion({}, function (data) {
            self.idprueba = data.pruebaActual;
            self.usuario = data.nrodoc;

            self.sitios = ListarSitio.listarSitiosyRPS({usuario:data.nrodoc, idPrueba:data.pruebaActual}, function (data) {});
        });
    }
    ;
    angular.module('app').controller('ControllerListarSitios', controllerListarSitios);
})();




