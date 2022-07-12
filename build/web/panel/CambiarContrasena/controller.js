var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert']);

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

// Hacemos el ruteo de nuestra aplicación.
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/CambiarContrasena.html",
        controller: "appController1"
    })
            .otherwise({redirectTo: "/"});
});

app.controller("appController1", ['$scope', '$location', 'CambiarContrasenaFactory', 'CambiarContrasenaActualiza', '$window', 'sweet', function ($scope, $location, CambiarContrasenaFactory, CambiarContrasenaActualiza, $window, sweet) {

        CambiarContrasenaFactory.getEmpleadoPorSesion(function (data) {
            $scope.nrodoc = data.nrodoc;
            $scope.Save = function (reg) {
                var texto = "";
                if (reg.clave === null || reg.clave === '' || reg.clave === undefined) {
                    texto = texto + "Debe ingresar una nueva contrase\u00F1a.\n";
                }
                if (reg.clave2 === null || reg.clave2 === '' || reg.clave2 === undefined) {
                    texto = texto + "Debe confirmar la nueva contrase\u00F1a.\n";
                }
                if (reg.clave !== reg.clave2) {
                    texto = texto + "Las contrase\u00F1as deben coincidir.\n";
                }
                if (reg.clave.length < 8 || reg.clave2.length < 8) {
                    texto = texto + "La contrase\u00F1a debe tener como m\u00EDnimo 8 caracteres.\n";
                }
                if (texto !== '') {
                    sweet.show({
                        title: 'Oopss!',
                        text: 'Resolver los Siguiente(s)\n' + texto,
                        type: 'warning',
                        showCancelButton: false,
                        confirmButtonColor: '#3e6bcc',
                        cancelButtonText: 'Cancelar',
                        confirmButtonText: 'OK, he entendido',
                        closeOnConfirm: false
                    });
                } else {
                    var empleado = new CambiarContrasenaActualiza();
                    {
                        empleado.nrodoc = $scope.nrodoc;
                        empleado.clave = reg.clave;
                        empleado.clave2 = reg.clave2;
                    }
                    ;
                    empleado.$update({}, function (response) {
                        if (response.codigo === '200') {
                            sweet.show('Felicidades', 'La informaci\u00F3n se guard\u00F3 correctamente', "success");
                            $location.path('/' + response.id);
                        } else {
                            sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");
                        }
                    });
                }
            };
            // span = Show | Hide.
            $scope.showPassword = function () {
                $scope.inputType = !$scope.inputType;
            };
            // icon = eye | eye-slash.
            const showPassword = document.querySelector('#showPassword');
            const password1 = document.querySelector('#nuevaPassword');
            const password2 = document.querySelector('#nuevaPasswordConfirma');
            showPassword.addEventListener('click', function () {
                const type1 = password1.getAttribute('type') === 'password' ? 'text' : 'password';
                password1.setAttribute('type', type1);
                const type2 = password2.getAttribute('type') === 'password' ? 'text' : 'password';
                password2.setAttribute('type', type2);
                this.classList.toggle('fa-eye-slash');
            });
        });
    }]);
app.filter('startFrom', function () {
    return function (input, start) {
        if (input) {
            start = +start;
            return input.slice(start);
        }
        return [];
    };
});
