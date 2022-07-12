var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert']);


app.config(['$httpProvider', function ($httpProvider) {

        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/mainConsulta.html",
        controller: "appController1"
    })
    $routeProvider.when("/Asignacion/:idPrueba/:cargoNombre/:idPersona", {
        templateUrl: "templates/consultaAsignacion.html",
        controller: "appController2"
    })
    $routeProvider.when("/FirmarContrato/:idEmpleado/:idPrueba/:formaFirma/:cargo", {
        templateUrl: "templates/consultaContrato.jsp",
        controller: "appControllerContrato"
    })

    $routeProvider.when("/Pagos/:idPrueba/:cargoNombre/:idPersona", {
        templateUrl: "templates/consultaPagos.html",
        controller: "appController4"
    })

    $routeProvider.when("/Asistencia/:idPrueba/:cargoNombre/:idPersona", {
        templateUrl: "templates/consultaAsistencia.html",
        controller: "appController5"
    })
    $routeProvider.when("/Viaticos/:idPrueba/:cargoNombre/:idPersona", {
        templateUrl: "templates/consultaViaticos.html",
        controller: "appController6"
    })
    $routeProvider.when("/CuentaCobro/:formaFirma", {
        templateUrl: "templates/consultaCuentaCobro.jsp",
        controller: "appController7"
    })
    $routeProvider.when("/ArchivoViaticos/:idEmpleado", {
        templateUrl: "templates/archivoPagoViaticos.html",
        controller: "appController8"
    })
    $routeProvider.when("/AutorizaViaticos/", {
        templateUrl: "templates/autorizarViaticos.html",
        controller: "appController9"
    }).otherwise({redirectTo: "/"});
});

app.controller("appController1", ['$scope', '$routeParams', 'ConsultaPruebasFactory', 'GMPago', function ($scope, $routeParams, ConsultaPruebasFactory, GMPago) {
        ConsultaPruebasFactory.getPersonaSesion(function (data) {
            $scope.persona = data;
            $scope.idPersona = $scope.persona.idEmpleado;
            ConsultaPruebasFactory.ConsultaPruebas($scope.persona.idEmpleado, function (data) {
                $scope.mispruebas = data;
            });
        });
        $scope.cancelar = function (reg) {
            var gmp = new GMPago();
            gmp.idempleado = reg.idempleado;
            gmp.idprueba = reg.idprueba;
            gmp.codigocargo = reg.codigocargo;
            gmp.$cancelarPrueba();
        };

    }]);

app.controller("appController2", ['$scope', '$routeParams', 'ConsultaPruebasFactory', '$window', function ($scope, $routeParams, ConsultaPruebasFactory, $window) {
        $scope.idPrueba = $routeParams.idPrueba;
        $scope.cargoNombre = $routeParams.cargoNombre;
        $scope.idPersona = $routeParams.idPersona;

        ConsultaPruebasFactory.ConsultaCargo($scope.idPersona, $scope.idPrueba, $scope.cargoNombre, function (data) {
            $scope.mipruebacargo = data;
        });
        ConsultaPruebasFactory.ConsultaAsignacion($scope.idPersona, $scope.idPrueba, $scope.cargoNombre, function (data) {
            $scope.miasignacion = data;
        });

        $scope.atras = function () {
            $window.history.back();
        }

    }]);

app.controller("appControllerContrato", ['$scope', '$routeParams', '$http', '$sce', '$window', 'ConsultaPruebasFactory', '$interval', function ($scope, $routeParams, $http, $sce, $window, ConsultaPruebasFactory, $interval) {

        $scope.confirmado = false;
        $scope.clic = false;
        $scope.idPrueba = $routeParams.idPrueba;
        $scope.idEmpleado = $routeParams.idEmpleado;
        $scope.cargo = $routeParams.cargo;
        var ruta = $window.location.origin;

        //verifica si la persona confirmó ya el contrato
        ConsultaPruebasFactory.buscarConfirmacionPersona($scope.idEmpleado, $scope.idPrueba, $scope.cargo, 'contrato', function (data) {
            if (data.idEmpleado != 0) {
                $scope.confirmado = true;
            }

            //Genera el PDF
            $scope.url = ruta + '/Paris/Reporte?id=' + $routeParams.idEmpleado + '&prueba=' + $routeParams.idPrueba + '&cargo=' + data.codigoCargo + '&tipoReporte=contrato&codigoevento=0';
            $http.get($scope.url,
                    {responseType: 'arraybuffer'})
                    .success(function (response) {
                        archi = response;
                        var file = new Blob([(response)], {type: 'application/pdf'});
                        var fileURL = URL.createObjectURL(file);
                        $scope.content = $sce.trustAsResourceUrl(fileURL);

                    });

        });

        //verifica si la petición es por click o por huella
        if ($routeParams.formaFirma === "clic") {
            $scope.clic = true;
        }

        $scope.httpHeaders = {Authorization: 'Bearer some-aleatory-token'};
        $scope.atras = function () {
            $window.history.back();
        }

        //confirma el contrato
        $scope.confirmarContrato = function (reg) {
            ConsultaPruebasFactory.confirmar($scope.idEmpleado, $scope.idPrueba, $scope.cargo, 'contrato', function (data) {
                var mensaje = data;
                $scope.confirmado = true;
            });
        }
        //buscar la presona que esta en sesion
        ConsultaPruebasFactory.getPersonaById($scope.idEmpleado, function (data) {
            $scope.persona = data;
        });

        //verifica si la persona confirmó ya el contrato
        ConsultaPruebasFactory.buscarConfirmacionPersona($scope.idEmpleado, $scope.idPrueba, $scope.cargo, 'contrato', function (data) {
            if (data.idEmpleado != 0) {
                $scope.confirmado = true;
            }
        });
        // si la solicitud es por huella llama esta funcion
        $scope.aceptar = function () {
            openPopPupFirmarCuenta($scope.idEmpleado, $scope.idPrueba, $scope.cargo, "contrato");

            var timer = $interval(function () {
                if ($scope.confirmado === false) {
                    //Verificar si la persona ya aceptó el contrato
                    ConsultaPruebasFactory.buscarConfirmacionPersona($scope.idEmpleado, $scope.idPrueba, $scope.cargo, 'contrato', function (data) {
                        if (data.idEmpleado != 0) {
                            $scope.confirmado = true;
                        }
                    });
                }
            }, 5000);

        }
    }]);


app.controller("appController4", ['$scope', '$routeParams', 'ConsultaPruebasFactory', 'GMPago', '$window', function ($scope, $routeParams, ConsultaPruebasFactory, GMPago, $window) {

        //$scope.selected;
        $scope.idPrueba = $routeParams.idPrueba;
        $scope.cargoNombre = $routeParams.cargoNombre;
        $scope.idPersona = $routeParams.idPersona;

        ConsultaPruebasFactory.ConsultaCargo($scope.idPersona, $scope.idPrueba, $scope.cargoNombre, function (data) {
            $scope.mipruebacargo = data;
        });
        ConsultaPruebasFactory.ConsultaPagos($scope.idPersona, $scope.idPrueba, $scope.cargoNombre, function (data) {
            $scope.mispagos = data;
        });



        $scope.aceptar = function (reg) {
            var gmp = new GMPago();
            gmp.idcuentacobro = reg.idcuentacobro;
            gmp.$save();
            // ConsultaPruebasFactory.ConfirmaPago(reg, function (data) {
        };

        $scope.atras = function () {
            $window.history.back();
        }


    }]);

app.controller("appController5", ['$scope', '$routeParams', 'ConsultaPruebasFactory', '$window', function ($scope, $routeParams, ConsultaPruebasFactory, $window) {
        $scope.idPrueba = $routeParams.idPrueba;
        $scope.cargoNombre = $routeParams.cargoNombre;
        $scope.idPersona = $routeParams.idPersona;

        ConsultaPruebasFactory.ConsultaCargo($scope.idPersona, $scope.idPrueba, $scope.cargoNombre, function (data) {
            $scope.mipruebacargo = data;
        });
        ConsultaPruebasFactory.ConsultaAsistencia($scope.idPersona, $scope.idPrueba, $scope.cargoNombre, function (data) {
            $scope.miasistencia = data;
        });

        $scope.atras = function () {
            $window.history.back();
        }

    }]);

app.controller("appController6", ['$scope', '$routeParams', 'ConsultaPruebasFactory', '$window', function ($scope, $routeParams, ConsultaPruebasFactory, $window) {
        $scope.idPrueba = $routeParams.idPrueba;
        $scope.cargoNombre = $routeParams.cargoNombre;
        $scope.idPersona = $routeParams.idPersona;
        ConsultaPruebasFactory.ConsultaViaticos($scope.idPersona, $scope.idPrueba, $scope.cargoNombre, function (data) {
            $scope.misviaticos = data;
        });

        $scope.atras = function () {
            $window.history.back();
        }

    }]);

app.controller("appController7", ['$scope', '$routeParams', '$window', '$http', '$sce', 'ConsultaPruebasFactory', '$interval', 'Empleado', 'sweet', function ($scope, $routeParams, $window, $http, $sce, ConsultaPruebasFactory, $interval, Empleado, sweet) {
        var pathname = window.location.pathname;
        var self=this;
        var url = window.location.href;
        var domain = url.substring(0, url.indexOf(pathname));
        $scope.confirmado = false;
        $scope.clic = false;
        $scope.mostrarCuenta = false;
        $scope.idPrueba=0;
        var user;
        var rol="";
         Empleado.getEmpleadoPruebaSesion({}, function (data) {
            $scope.idPrueba = data.pruebaActual;
            rol = data.rolActual;
            user = data.nrodoc;
            if (rol === "coordinador") {
                ConsultaPruebasFactory.usuarioDepartamento( user, $scope.idPrueba, function (data) {
                    $scope.dpto = data[0].codigo;
                });
            }
        });
        
        var ruta = $window.location.origin;
        function buscarEmpleado() {
            Empleado.getPersonaByCedula({nroDocumento: $scope.documento}, function (data) {
                $scope.empleado = data;
                this.empleado = data;

                if ($scope.empleado.idEmpleado !== 0) {
                    $scope.buscarCuentaCobro();
                    $scope.mostrarCuenta = true;
                }
                validarEmpleado();
            });
        }

        function validarEmpleado() {
            //si el usuario existe
            //Verificar si la persona ya aceptó la cuenta de cobro
            ConsultaPruebasFactory.buscarConfirmacionPersona($scope.empleado.idEmpleado, 38, "10", 'cuenta de cobro', function (data) {

                if (data.idEmpleado !== 0) {
                    $scope.confirmado = true;
                } else {
                    if ($scope.empleado.nrodoc > 0) {
                        $scope.imagen = "../../images/okazul.png";
                        //si tiene una huella relacionada

                        if ($scope.empleado.huella === "" || $scope.empleado.huella === undefined || $scope.empleado.huella === null) {
                            anadirAppletEnroll(domain);
                        } else {
                            anadirAppletIdentificacion(domain, $scope.empleado.huellaBase64);
                        }

                    } else {
                        $scope.imagen = "../../images/chulo_no.gif";
                    }
                }
            });

        }
        $scope.key = function ($event) {

            if ($event.keyCode === 13) {
                $scope = this;
                scope = this;
                $scope.nrodocok = false;
                buscarEmpleado();

            }

            if ($scope.nrodocok === true)
            {
                $event.preventDefault();
            } else
            {
                if ($event.which === 9) {
                    $scope.nrodocok = true;
                    $event.preventDefault();
                }
            }

        };
        //Funcion invocada por el applet de biometria
        //recibe el parametro boolean que indica si se realizo 
        //la identificaion de modo correcto
        //No se debe modificar la firma de esta funcion
        $scope.identificar = function (matchOK) {
            if (matchOK === "si") {
                $scope.imagen = "../../images/okazul.png";
                confirmarCuenta();
            } else {
                $scope.imagen = "../../images/chulo_no.gif";
            }
            $scope.documento = "";
            //obliga a recargar los elementos del html
            $scope.$apply();
            //se limpia el applet
            var elements = document.getElementById('inside');
            while (elements.hasChildNodes()) {
                elements.removeChild(elements.firstChild);
            }
        };


        $scope.buscarCuentaCobro = function () {
            //Genera el pdf
            $scope.url = ruta + "/Paris/CuentaCobro?tipoReporte=prueba&nrodoc=" + $scope.documento + "&codigoDepartamento=&codigoMunicipio=&codigoEvento=0";
            $http.get($scope.url,
                    {responseType: 'arraybuffer'})
                    .success(function (response) {
                        var file = new Blob([(response)], {type: 'application/pdf'});
                        var fileURL = URL.createObjectURL(file);
                        $scope.content = $sce.trustAsResourceUrl(fileURL);

                    });

            $scope.httpHeaders = {Authorization: 'Bearer some-aleatory-token'};

        }
        $scope.atras = function () {
            $window.history.back();
        }
        //verifica si la petición es por click o por huella
        if ($routeParams.formaFirma === "clic") {
            $scope.clic = true;
        }
        ConsultaPruebasFactory.getPersonaById($scope.idempleado, function (data) {
            $scope.persona = data;
        });

        function confirmarCuenta() {
            ConsultaPruebasFactory.confirmar($scope.documento, function (data) {
                if (data.codigo === '200') {
                    sweet.show('Felicidades', 'La Cuenta de cobro de guard\u00F3 correctamente', "success");
                } else {
                    sweet.show('Oopss', "Error al guardar la informaci\u00F3n", "error");

                }
                $scope.documento = "";
             
            });
        }
//
//        //si la petición de aceptar es por huella llama esta funcion
//        $scope.aceptar = function () {
//            openPopPupFirmarCuenta($scope.idempleado, $scope.idprueba, $scope.codcargo, "cuenta de cobro");
//            var timer = $interval(function () {
//                if ($scope.confirmado === false) {
//                    //Verificar si la persona ya aceptó la cuenta de cobro
//                    ConsultaPruebasFactory.buscarConfirmacionPersona($scope.idempleado, $scope.idprueba, $scope.codcargo, 'cuenta de cobro', function (data) {
//
//                        if (data.idEmpleado != 0) {
//                            $scope.confirmado = true;
//                        }
//                    });
//                }
//            }, 5000);
//        }



    }]);

app.controller("appController8", ['$scope', '$routeParams', 'ConsultaPruebasFactory', '$window', function ($scope, $routeParams, ConsultaPruebasFactory, $window) {
        $scope.idEmpleado = $routeParams.idEmpleado;
        ConsultaPruebasFactory.ArchivoPlanoViaticos($scope.idEmpleado, function (data) {
            $scope.pagoviaticos = data;
        });
        $scope.atras = function () {
            $window.history.back();
        }
    }]);

app.controller("appController9", ['$scope', 'ConsultaPruebasFactory', '$window', 'GMPago', function ($scope, ConsultaPruebasFactory, $window, GMPago) {
        /*$scope.idEmpleado = $routeParams.idEmpleado;
         $scope.idPrueba = $routeParams.idPrueba;
         $scope.codCargo = $routeParams.codCargo;*/

        idprueba = 0;
        codCargo = 0;
        ConsultaPruebasFactory.obtenerPruebas(
                function (data) {
                    $scope.pruebas = data;
                });
        ConsultaPruebasFactory.getPersonaSesion(function (data) {
            $scope.persona = data;
            $scope.idEmpleado = $scope.persona.idEmpleado;
        });
        $scope.buscar = function (idPrueba) {
            ConsultaPruebasFactory.AutorizarViaticos($scope.idEmpleado, idPrueba, codCargo, function (data) {
                $scope.pendienteaprobarviaticos = data;
            });
        };

        $scope.atras = function () {
            $window.history.back();
        };
        $scope.confirmaViatico = function (reg) {
            var gmp = new GMPago();
            gmp.idempleado = reg.idempleado;
            gmp.idprueba = reg.idprueba;
            reg.poraceptar = 1;
            gmp.$confirmaViatico();
        };
    }]);

//invocación del applet de enrolamiento
function anadirAppletEnroll(url) {
    script = "<APPLET codebase='" + url + "/Paris/recursos' CODE='co.com.grupoasd.biometria.interfaz.CapturaHuella2.class' WIDTH='180' HEIGHT='180'>";
    script += "<param name='archive' value='BiometriaUnit.jar'>";
    script += "<param name='IDENTIFICACION' value='enrolar'>";
    script += "</APPLET>";
    document.getElementById('inside').innerHTML = script;
}

//invocación de applet de identificación
function anadirAppletIdentificacion(url, id) {
    script = "<APPLET codebase='" + url + "/Paris/recursos' CODE='co.com.grupoasd.biometria.interfaz.VerificarHuella2.class' WIDTH='180' HEIGHT='180'>";
    script += "<param name='archive' value='BiometriaUnit.jar'>";
    script += "<param name='HUELLAS' value='" + JSON.stringify(id) + "'>";
    script += "</APPLET>";
    document.getElementById('inside').innerHTML = script;
}