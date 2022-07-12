(function () {
    function AdministrarCampagna(Campagna, Empleado, Municipio, Departamento, Cargo, sweet, $window, $scope) {
        var idPrueba = 0;
        $scope.estado = 0;
        $scope.campagna = 0;
        $scope.departamento = 0;
        $scope.municipio = 0;
        $scope.cargo = 0;

        //Funcion para volver
        this.atras = function () {
            $window.history.back();
        };

        //variables de paginacion
        $scope.currentPage = 1;
        $scope.pageSize = 10;

        //Se obtiene el id de la prueba para enviarla en la consulta
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
        });

        //Se consultan los departamentos Dane
        $scope.departamentos = Departamento.listar({}, function (data) {});

        //Se consultan los cargos para el filtro
        $scope.cargos = Cargo.listar({}, function (data) {});

        //Se consultan las campagnas para listar
        $scope.campagnas = Campagna.listar({}, function (response) {});

        // busca municipios de acuerdo al departamento
        this.buscarMunicipios = function () {
            $scope.municipios = Municipio.getByDpto({idDpto: $scope.departamento}, function (data) {});
        };

        //Funcion para carga la campagna para gestion telefonica
        this.cargarCampagna = function () {
            var params = {
                idPrueba: idPrueba,
                idCampagna: $scope.campagna,
                idCargo: $scope.cargo,
                idNodo: $scope.departamento,
                idMunicipio: $scope.municipio,
                idEstado: $scope.estado
            };
            Campagna.consultarDatosCampagna(params, function (response) {
                $scope.datos = response;
                console.log(response);
                Campagna.insertarDatosCampagna($scope.datos, function (response) {
                    console.log(response);
                    if (response.codigo === '200') {
                        sweet.show('Felicidades', 'La Campa\u00F1a se carg\u00F3 correctamente!', "success");
                    } else {
                        sweet.show('Oopss', "Error al cargar la campa\u00F1a", "error");
                    }
                });
            });
        };

    }
    ;
    angular.module("app").controller('AdministrarCampagna', AdministrarCampagna);
})();