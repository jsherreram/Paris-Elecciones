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

        });


        var ruta = $window.location.origin;

        this.generarCarnets = function () {

            //Genera el pdf
             $scope.url = ruta + "/Paris/CarneCargo?tipo=individual&nrodoc="+this.nrodoc;
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
    angular.module("app").controller('generarCarnetIndividual', generarCarnet);
})();