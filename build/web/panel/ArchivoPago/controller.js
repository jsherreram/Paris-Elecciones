var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);
app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/:opcion", {
        templateUrl: "templates/SubirArchivoPago.jsp",
        controller: "appControllerPago",
    })
            .otherwise({redirectTo: "/"});
});

function leerCookie(nombre) {
    var lista = document.cookie.split(";");
    for (i in lista) {
        var busca = lista[i].search(nombre);
        if (busca > -1) {
            micookie = lista[i]
        }
    }
    var igual = micookie.indexOf("=");
    var valor = micookie.substring(igual + 1);
    return valor;
}


app.controller("appControllerPago", ['$scope', '$routeParams', 'ArchivoPagoFactory', function ($scope, $routeParams, ArchivoPagoFactory) {

        $scope.idPrueba = 0;
        $scope.sitio = "";
        $scope.mostrar = false;
        $scope.total = 0;
        var usuario = leerCookie("APP-TOKEN");
        $scope.usuario = usuario;

        $scope.soloConsultar = true;

        if ($routeParams.opcion === "consultar") {
            $scope.soloConsultar = false;
        }

        ArchivoPagoFactory.obtenerPruebas(
                function (data) {
                    $scope.pruebas = data;
                    $scope.idPrueba = $scope.pruebas[0].idprueba;
                    $scope.buscar($scope.idPrueba);

                });

        $scope.buscar = function (prueba) {
            $scope.idPrueba = prueba;
            ArchivoPagoFactory.getSitios(
                    $scope.idPrueba,
                    usuario,
                    function (data) {
                        $scope.sitios = data;
                        $scope.total = data.length;
                        $scope.somethingHere = data.length > 0 ? data[0].codigoSitio : -1;
                        $scope.mostrar = true;

                    });
        };

        $scope.seleccionarSitio = function (sitio) {
            $scope.sitio = sitio;
        }




        $scope.validarForm = function () {
            if ($scope.file === "" || $scope.file === "undefined") {
                alert('Por favor seleccione todos los campos');
                return false;
            }

            return true;
        }
        
    }]);


