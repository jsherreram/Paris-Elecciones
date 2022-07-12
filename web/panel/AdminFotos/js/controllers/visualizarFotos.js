(function () {
    function VisualizarFotosSitio($scope, Sesion, sweet, Sitio, $window) {

        //Se obtiene el id de la prueba para enviarla en las consultas
        var self = this;
        Sesion.getEmpleadoPruebaSesion({}, function (data) {
            $scope.idPrueba = data.pruebaActual;
            $scope.nrodoc = data.nrodoc;
        }).$promise.then(function (result) {
            self.sitios = Sitio.listar({idPrueba: $scope.idPrueba}, function (response) {
            });
        });

        //Funcion para devolver la pagina segun la historia del navegador
        this.atras = function () {
            $window.history.back();
        };

        //Metodo encargado de consultar las fotos del sitio
        this.consultarFotosSitio = function () {
            self.listaFotos = Sitio.getFotosSitio({idSitio: this.codigoSitio, idPrueba: $scope.idPrueba, folder: "sitio"}, function (data) {
            });
        };

    }
    ;
    angular.module("app").controller('VisualizarFotosSitio', VisualizarFotosSitio);
})();