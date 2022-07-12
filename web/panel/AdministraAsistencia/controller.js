var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert', 'cgNotify']);
app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    }]);

//hacemos el ruteo de nuestra aplicación
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "templates/AdministraAsistencia.jsp",
        controller: "appControllerAsistencia"
    }).when("/:nrodoc", {
        templateUrl: "templates/AdministraAsistencia.jsp",
        controller: "appControllerAsistencia"
    }).when("/monitoreo/:idDivipol/:idEvento", {
        templateUrl: "templates/AdministraAsistencia.jsp",
        controller: "appControllerAsistencia"
    })
            .when('/Asignar/:idPersonaAsignada', {
                templateUrl: "templates/terceros-new.html",
                controller: "EmpleadoAsignar"
            })
            .when('/Desasignar/:idPersonaAsignada', {
                templateUrl: "templates/terceros-new.html",
                controller: "EmpleadoAsignar"
            })
            .when('/Editar/:idPersonaAsignada', {
                templateUrl: "templates/terceros-new.html",
                controller: "EmpleadoEditar"
            })
            .when('/CambiarCargo/:id', {
                templateUrl: "templates/cambiarCargo.html",
                controller: "CambiarCargo as controller"
            })
            .when("/AsignaSuplente/:dcpe", {
                templateUrl: "templates/Asignar.html",
                controller: "appControllerAsignaSuplente"
            })
            .otherwise({redirectTo: "/"});
});

app.directive('popOver', function ($compile, $templateCache) {
    var getTemplate = function () {
        $templateCache.put('templateId.html', 'This is the content of the template');
        //console.log($templateCache.get("popover_template.html"));
        return $templateCache.get("popover_template.html");
    }
    return {
        restrict: "A",
        transclude: true,
        template: "<span ng-transclude></span>",
        link: function (scope, element, attrs) {
            var popOverContent;
            if (scope.opciones) {
                var html = getTemplate();
                popOverContent = $compile(html)(scope);
                var options = {
                    content: popOverContent,
                    placement: "bottom",
                    html: true,
                    title: scope.title
                };
                $(element).popover(options);
            }
        },
        scope: {
            opciones: '=',
            title: '@'
        }
    };
});

//Variable que referencia el objeto de $scope
var scope;

app.controller("appControllerAsistencia", ['$scope', '$routeParams', '$sce', '$window', '$modal', 'AdministraAsistenciaFactory', 'Empleado', 'Sesion', 'SuplenteFactory', 'CierreAsistencia', 'sweet', '$interval', 'notify', 'FotosSitio', function seguimientoController($scope, $routeParams, $sce, $window, $modal, AdministraAsistenciaFactory, Empleado, Sesion, SuplenteFactory, CierreAsistencia, sweet, $interval, notify, FotosSitio) {
        $scope.oneAtATime = true;
        var pathname = window.location.pathname;
        var url = window.location.href;
        var domain = url.substring(0, url.indexOf(pathname));
        $scope.sesion = "";
        $scope.mostrarParaCoordinador = true;
        $scope.esPorEvento = false;
        $scope.cerrado = false;
        var self = this;
        scope = $scope;
        $scope.causasBiometrico = [
            {name: 'DISPOSITIVO AVERIADO', id: '1'},
            {name: 'HUELLA NO LEE', id: '2'},
            {name: 'EL SISTEMA NO ESTABA DISPONIBLE', id: '3'}
        ];
        $scope.causaNoBiometrico = $scope.causasBiometrico[0];
        function buscarEmpleado() {
            Empleado.getPersonaByCedula({nroDocumento: $scope.documento}, function (data) {
                $scope.empleado = data;

                this.empleado = data;

                $scope.mostrarBotonAsistenciaManual = false;
                AdministraAsistenciaFactory.getAsignacion($scope.idEvento, $scope.codigoSitio, $scope.documento, function (data) {
                    if (data.length > 0)
                    {
                        $scope.asignacion = data[0];

                        //actualizar los datos del pistoleo
                        if (p_nrotab === 6)
                        {
                            console.log("actualizar");
                            $scope.empleado.apellido1 = p_apellido1;
                            $scope.empleado.apellido2 = p_apellido2;
                            $scope.empleado.nombre1 = p_nombre1;
                            $scope.empleado.nombre2 = p_nombre2;
                            $scope.empleado.genero = p_genero;
                            var fecha = p_fechaNacimiento.substring(0, 4) + "-" + p_fechaNacimiento.substring(4, 6) + "-" + p_fechaNacimiento.substring(6, 8);

                            $scope.empleado.fechaNacimiento = Date.parse(fecha);

                            Empleado.actualizarDatosPrincipalesPistoleo($scope.empleado, function (response) {
                                if (response.codigo === '200')
                                {
                                    caracteresCedula = [];
                                    p_cedula = "";
                                    p_apellido1 = "";
                                    p_apellido2 = "";
                                    p_nombre1 = "";
                                    p_nombre2 = "";
                                    p_genero = "";
                                    p_fechaNacimiento = "";
                                    p_nrotab = 0;
                                    console.log("Datos de Pistoleo Actualizado");
                                }
                            });
                        } else
                        {
                            caracteresCedula = [];
                            p_cedula = "";
                            p_apellido1 = "";
                            p_apellido2 = "";
                            p_nombre1 = "";
                            p_nombre2 = "";
                            p_genero = "";
                            p_fechaNacimiento = "";
                            p_nrotab = 0;
                        }
                    } else
                    {
                        $scope.asignacion = null;
                        caracteresCedula = [];
                        p_cedula = "";
                        p_apellido1 = "";
                        p_apellido2 = "";
                        p_nombre1 = "";
                        p_nombre2 = "";
                        p_genero = "";
                        p_fechaNacimiento = "";
                        p_nrotab = 0;
                    }
                    validarEmpleado();
                });



            });
        }

        function validarEmpleado() {
            //si el usuario existe
            if ($scope.empleado.nrodoc > 0 && $scope.asignacion !== null) {
                $scope.imagen = "../../images/okazul.png";
                //si tiene una huella relacionada

                if ($scope.sinBiometrico === false)
                {
                    if ($scope.empleado.huella === "" || $scope.empleado.huella === undefined || $scope.empleado.huella === null) {
                        anadirAppletEnroll(domain);
                    } else {
                        anadirAppletIdentificacion(domain, $scope.empleado.huellaBase64);
                    }
                } else
                {
                    $scope.mostrarBotonAsistenciaManual = true;
                }

            } else {
                $scope.imagen = "../../images/chulo_no.gif";
            }

        }

        var caracteresCedula = [];
        var p_cedula = "";
        var p_apellido1 = "";
        var p_apellido2 = "";
        var p_nombre1 = "";
        var p_nombre2 = "";
        var p_genero = "";
        var p_fechaNacimiento = "";
        var p_nrotab = 0;

        $scope.key = function ($event) {

            caracteresCedula.push($event.key);

            if ($event.keyCode === 13) {
                $scope = this;
                scope = this;
                $scope.nrodocok = false;

                for (var i = 0; i < caracteresCedula.length; i++)
                {
                    if (caracteresCedula[i] === 'Tab')
                    {
                        p_nrotab++;
                    } else {

                        if (caracteresCedula[i] !== 'Shift' && caracteresCedula[i] !== 'Enter')
                        {
                            if (p_nrotab === 0)
                            {
                                p_cedula = p_cedula + caracteresCedula[i];
                            }

                            if (p_nrotab === 1)
                            {
                                p_apellido1 = p_apellido1 + caracteresCedula[i];
                            }

                            if (p_nrotab === 2)
                            {
                                p_apellido2 = p_apellido2 + caracteresCedula[i];
                            }

                            if (p_nrotab === 3)
                            {
                                p_nombre1 = p_nombre1 + caracteresCedula[i];
                            }

                            if (p_nrotab === 4)
                            {
                                p_nombre2 = p_nombre2 + caracteresCedula[i];
                            }

                            if (p_nrotab === 5)
                            {
                                p_genero = p_genero + caracteresCedula[i];
                            }

                            if (p_nrotab === 6)
                            {
                                p_fechaNacimiento = p_fechaNacimiento + caracteresCedula[i];
                            }
                        }
                    }
                }

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
                AdministraAsistenciaFactory.validarAsistenciaEmpleado($scope.asignacion.iddivipol, $scope.idEvento, $scope.empleado.idEmpleado, function (data) {

                    if (data.respuesta === true) {
                        sweet.show('Esta persona ya tiene asistencia registrada para esta Sesi\u00F3n, Por favor verifique');

                    } else {
                        AdministraAsistenciaFactory.registrarAsistencia($scope.idEvento, $scope.asignacion.iddivipol, $scope.empleado.idEmpleado, true, function (data) {
                            $scope.respuestaAsistencia = data;

                            if (data.codigo === '200') {
                                for (var i = 0; i < $scope.ubicaciones.length; i++) {
                                    if ($scope.ubicaciones[i].nrodoc == $scope.empleado.nrodoc)
                                    {
                                        $scope.ubicaciones[i].apellido1 = $scope.empleado.apellido1;
                                        $scope.ubicaciones[i].apellido2 = $scope.empleado.apellido2;
                                        $scope.ubicaciones[i].nombre1 = $scope.empleado.nombre1;
                                        $scope.ubicaciones[i].nombre2 = $scope.empleado.nombre2;

                                        if ($scope.ubicaciones[i].asistio === '0')
                                        {
                                            $scope.ubicaciones[i].asistio = '1';
                                            $scope.totalAsistentes++;
                                        }
                                    }
                                }
                            } else {
                                sweet.show('Oopss', data.descripcion, "error");
                            }

                        });
                    }
                });
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
        //Funcion invocada por el applet de biometria
        //recibe el objeto que contiene las
        //huellas e imagen del proceso de enrolamiento
        //No se debe modificar la firma de esta funcion
        $scope.enrolar = function (objeto) {


            if (objeto.imagenSerializada === undefined ||
                    objeto.huella === undefined) {
                alert('La huella o la imagen no pudieron ser procesadas. Vuelva a enrolar a la persona');
            } else {

                //Se añade la huella al objeto empleado
                $scope.empleado.huellaBase64 = objeto.huellaBase64;
                //Se añade la imagen de la huella al objeto empleado
                $scope.empleado.imagenHuellaBase64 = objeto.imagenBase64;
                //var empleado = new Empleado($scope.empleado);

                Empleado.enrolar($scope.empleado, function (response) {
                    if (response.codigo == '200')
                    {
                        AdministraAsistenciaFactory.validarAsistenciaEmpleado($scope.asignacion.iddivipol, $scope.idEvento, $scope.empleado.idEmpleado, function (data) {

                            if (data.respuesta === true) {
                                sweet.show('Esta persona ya tiene asistencia registrada para esta Sesi\u00F3n, Por favor verifique');

                            } else {

                                AdministraAsistenciaFactory.registrarAsistencia($scope.idEvento, $scope.asignacion.iddivipol, $scope.empleado.idEmpleado, true, function (data) {
                                    $scope.respuestaAsistencia = data;
                                    if (data.codigo === '200') {
                                        for (var i = 0; i < $scope.ubicaciones.length; i++) {
                                            if ($scope.ubicaciones[i].nrodoc == $scope.empleado.nrodoc)
                                            {
                                                $scope.ubicaciones[i].apellido1 = $scope.empleado.apellido1;
                                                $scope.ubicaciones[i].apellido2 = $scope.empleado.apellido2;
                                                $scope.ubicaciones[i].nombre1 = $scope.empleado.nombre1;
                                                $scope.ubicaciones[i].nombre2 = $scope.empleado.nombre2;

                                                if ($scope.ubicaciones[i].asistio === '0')
                                                {
                                                    $scope.ubicaciones[i].asistio = '1';
                                                    $scope.totalAsistentes++;
                                                }
                                            }
                                        }
                                    } else {
                                        sweet.show('Oopss', data.descripcion, "error");
                                    }

                                });
                            }
                        });

                        console.log("Empleado enrolado");


                    } else
                    {
                        console.log("Error: " + response.descripcion);
                        $scope.error = response.descripcion;
                    }
                    ;
                });
                $scope.documento = "";
                //obliga a recargar los elementos del html
                $scope.$apply();
                //se limpia el applet
                var elements = document.getElementById('inside');
                while (elements.hasChildNodes()) {
                    elements.removeChild(elements.firstChild);
                }
            }
        };
        $scope.asistenciaManual = function () {

            AdministraAsistenciaFactory.validarAsistenciaEmpleado($scope.asignacion.iddivipol, $scope.idEvento, $scope.empleado.idEmpleado, function (data) {

                if (data.respuesta === true) {
                    sweet.show('Esta persona ya tiene asistencia registrada para esta Sesi\u00F3n, Por favor verifique');

                } else {

                    AdministraAsistenciaFactory.registrarAsistencia($scope.idEvento, $scope.asignacion.iddivipol, $scope.empleado.idEmpleado, false, function (data) {
                        $scope.respuestaAsistencia = data;
                        if (data.codigo === '200') {
                            for (var i = 0; i < $scope.ubicaciones.length; i++) {
                                if ($scope.ubicaciones[i].nrodoc == $scope.empleado.nrodoc)
                                {
                                    $scope.ubicaciones[i].apellido1 = $scope.empleado.apellido1;
                                    $scope.ubicaciones[i].apellido2 = $scope.empleado.apellido2;
                                    $scope.ubicaciones[i].nombre1 = $scope.empleado.nombre1;
                                    $scope.ubicaciones[i].nombre2 = $scope.empleado.nombre2;

                                    if ($scope.ubicaciones[i].asistio === '0')
                                    {
                                        $scope.ubicaciones[i].asistio = '1';
                                        $scope.totalAsistentes++;
                                    }
                                }
                            }
                        } else {
                            sweet.show('Oopss', data.descripcion, "error");
                        }


                        $scope.documento = "";
                        $scope.mostrarBotonAsistenciaManual = false;
                        //obliga a recargar los elementos del html
                        $scope.$apply();
                    });
                }
            });



        };
        $scope.setTipoReporte = function (tipoRepo) {
            $scope.tipoReporte = tipoRepo;
        };
        $scope.listar = function () {

            $scope.totalRegistros = 100;
            $scope.totalAsistentes = 0;
            $scope.sinBiometrico = false;
            $scope.documento = "";
            //obtener la prueba actual
            Sesion.getEmpleadoPruebaSesion({}, function (data) {
                $scope.idprueba = data.pruebaActual;


                //obtener el sitio al que tiene permisos el usuario para la prueba
                AdministraAsistenciaFactory.getSitio($routeParams.idDivipol, function (data) {
                    $scope.idDivipol = data.id;
                    $scope.codigoSitio = data.codigoSitio;
                    $scope.puestoNombre = data.nombreSitio;
                    $scope.esBogota = false;
                    var contieneBogota = $scope.codigoSitio.substring(0, 5);
                    if (contieneBogota === "11001")
                    {
                        $scope.esBogota = true;
                    }


                    //obtener el codigo del evento actual apto para la toma de asistencia
                    Sesion.getEventoActualParaSitio({idPrueba: $scope.idprueba, idDivipol: $scope.idDivipol}, function (data) {
                        $scope.evento = data;
                        if ($routeParams.idEvento !== undefined) {
                            $scope.idEvento = $routeParams.idEvento;
                            AdministraAsistenciaFactory.getEvento($scope.idEvento, function (data) {
                                $scope.nombreEvento = data.nombre;
                                $scope.evento = data;
                            });
                            $scope.esPorEvento = true;

                            AdministraAsistenciaFactory.buscarCierre($scope.idDivipol, $scope.idEvento, function (data) {

                                if (data.length > 0) {
                                    $scope.cerrado = true;

                                }
                            });

                        }

                        if ($scope.idEvento > 0) {

                            AdministraAsistenciaFactory.listarAsistencia($scope.idEvento, $scope.codigoSitio, function (data) {
                                $scope.ubicaciones = data;

                                $scope.totalRegistros = $scope.ubicaciones.length;
                                for (var i = 0; i < $scope.ubicaciones.length; i++) {
                                    if ($scope.ubicaciones[i].asistio === '1')
                                    {
                                        $scope.totalAsistentes++;
                                    }
                                }

                            });
                        } else {
                            $scope.ubicaciones = "";
                        }

                    });
                });
            });
        };

        $scope.resumenAsistencia = function ()
        {
            AdministraAsistenciaFactory.resumenAsistenciaXSitio($scope.idEvento, $scope.codigoSitio, function (data) {
                $scope.resumencargos = data;
            });
        };
        $scope.CalcularParaCerrar = function () {
            $scope.validarModal = true;
            SuplenteFactory.GetRamdon(function (data) {
                $scope.aleatorio = data[0].numero;
            });
            SuplenteFactory.obtenerFaltantes($scope.idprueba, $scope.idDivipol, $scope.idEvento, function (data) {
                $scope.faltante = data[0].pendientes;
            });
            AdministraAsistenciaFactory.resumenAsistenciaXSitio($scope.idEvento, $scope.codigoSitio, function (data) {
                $scope.resumencargos = data;
            });
            $scope.cantidadFotos = 4;
            if ($scope.evento.tipoSesion === "REUNION PREV.BOGOTA" || $scope.evento.tipoSesion === "REUNION PREVIA") {
                $scope.listaFotos = FotosSitio.getFotosSitio({idSitio: $scope.codigoSitio, idPrueba: $scope.idprueba, folder: "sitio"}, function (data) {
                    $scope.cantidadFotos = data.length;
                });
            }
        };
        $scope.Cerrar = function () {

            var mensaje = "";

            if ($scope.aleatorio == $scope.verificador) {

                var cierre = new CierreAsistencia();
                {
                    cierre.idprueba = $scope.idprueba;
                    cierre.idDivipol = $scope.idDivipol;
                    cierre.codigoEvento = $scope.idEvento;
                }
                cierre.$insert(function (response) {
                    if (response.codigo === '200')
                    {
                        sweet.show('La asistencia se ha cerrado correctamente');
                        $scope.listar();
                        $scope.validarModal = false;
                        $scope.verificador = "";
                    }
                    ;
                });
            } else {
                sweet.show('Oopss', 'Numero de Verificaci\u00F3n no Coincide, Por Favor revisar', "error");
                $scope.CalcularParaCerrar();
                $scope.verificador = "";
            }


        };
        $scope.atras = function () {
            $window.history.back();
        };

        //Funcion para desasignar a una persona de la dcpe

        $scope.desasignar = function (id) {
            sweet.show({
                title: 'Confirmar',
                text: 'Est\u00e1 seguro que desea desasignarlo?',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3e6bcc',
                cancelButtonText: 'NO',
                confirmButtonText: 'SI',
                closeOnConfirm: false
            }, function () {
                AdministraAsistenciaFactory.desasignarPersonaSitio(id, function (data) {
                    if (data.respuesta === 'true') {

                        sweet.show({
                            title: 'Confirmar',
                            text: 'El Usuario se ha desasignado con exito',
                            type: 'success',
                            showCancelButton: true,
                            confirmButtonColor: '#3e6bcc',
                            confirmButtonText: 'Ok',
                            closeOnConfirm: false
                        }, function () {
                            location.reload();
                        });

                    } else {
                        sweet.show('Oopss', "error al desasignar el usuario", "error");
                    }
                });

            });

        }

    }]);
app.controller("EmpleadoAsignar", ['$scope', '$routeParams', '$location', '$sce', '$window', 'AdministraAsistenciaFactory', 'Empleado', 'PersonaAsignada', 'sweet', '$interval', 'notify', 'Sesion', function seguimientoController($scope, $routeParams, $location, $sce, $window, AdministraAsistenciaFactory, Empleado, PersonaAsignada, sweet, $interval, notify, Sesion) {
        var pathname = window.location.pathname;
        var url = window.location.href;
        var domain = url.substring(0, url.indexOf(pathname));
        var self = this;
        scope = $scope;
        $scope.causasBiometrico = [
            {name: 'DISPOSITIVO AVERIADO', id: '1'},
            {name: 'HUELLA NO LEE', id: '2'},
            {name: 'EL SISTEMA NO ESTABA DISPONIBLE', id: '3'}
        ];
        $scope.causaNoBiometrico = $scope.causasBiometrico[0];
        $scope.tipoIdentificacion = [
            {name: 'CEDULA CIUDADANIA', id: 'CC'},
            {name: 'CEDULA EXTRANJERIA', id: 'CE'}
        ];
        $scope.idPersonaAsignada = $routeParams.idPersonaAsignada;
        $scope.inicializar = function () {
            AdministraAsistenciaFactory.getPersonaAsignada($scope.idPersonaAsignada, function (data) {
                $scope.personaAsignada = data;
                $scope.parametros = AdministraAsistenciaFactory.getParametrosEncuesta($scope.personaAsignada.evento.codigoEvento, function (data1) {});
                $scope.empleado = {
                    idEmpleado: 0,
                    apellido1: "",
                    apellido2: "",
                    celular: "",
                    direccion: "",
                    email: "",
                    municipio: {codigoMunicipio: $scope.personaAsignada.municipio.codigoMunicipio, departamento: {codigo: $scope.personaAsignada.municipio.departamento.codigo, nombre: ""}, nombre: ''},
                    nombre1: "",
                    nombre2: "",
                    nrodoc: "",
                    tipodoc: $scope.tipoIdentificacion[0].id,
                    idpuesto: $scope.codigoSitio,
                    genero: "",
                    fechaNacimiento: "",
                    huellaBase64: "",
                    imagenHuellaBase64: ""
                };
            });
            $scope.sinBiometrico = false;
            $scope.conAsistencia = true;
        };
        $scope.ValidaExiste = function () {
            Empleado.getPersonaByCedula({nroDocumento: $scope.empleado.nrodoc}, function (data) {
                $scope.persona = data;
                if ($scope.persona.idEmpleado > 0)
                {
                    $scope.empleado.celular = $scope.persona.celular;
                    $scope.empleado.telefono = $scope.persona.telefono;
                    $scope.empleado.email = $scope.persona.email;
                    $scope.empleado.direccion = $scope.persona.direccion;
                    $scope.existeIdentificacion = true;
                    $scope.empleado.idEmpleado = $scope.persona.idEmpleado;
                    $scope.empleado.nombre1 = $scope.persona.nombre1;
                    $scope.empleado.nombre2 = $scope.persona.nombre1;
                    $scope.empleado.apellido1 = $scope.persona.apellido1;
                    $scope.empleado.apellido2 = $scope.persona.apellido2;
                    $scope.empleado.genero = $scope.persona.genero;
                    var fechaNacimiento = new Date($scope.persona.fechaNacimiento);
                    $scope.fechanac = fechaNacimiento.getFullYear() + '-' +
                            pad(fechaNacimiento.getMonth() + 1) + '-' +
                            pad(fechaNacimiento.getDate());

                }

            });
        };
        $scope.enrollar = function () {
            if ($scope.sinBiometrico === false) {
                anadirAppletEnroll(domain);
            }
        }

        //Funcion invocada por el applet de biometria
        //recibe el parametro boolean que indica si se realizo 
        //la identificaion de modo correcto
        //No se debe modificar la firma de esta funcion
        $scope.identificar = function (matchOK) {
            if (matchOK === "si") {
                $scope.imagen = "../../images/okazul.png";
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
        //Funcion invocada por el applet de biometria
        //recibe el objeto que contiene las
        //huellas e imagen del proceso de enrolamiento
        //No se debe modificar la firma de esta funcion
        $scope.enrolar = function (objeto) {

            if (objeto.imagenSerializada === undefined ||
                    objeto.huella === undefined) {
                alert('La huella o la imagen no pudieron ser procesadas. Vuelva a enrolar a la persona');
            } else {

                //Se añade la huella al objeto empleado
                $scope.huellaBase64 = objeto.huellaBase64;
                //Se añade la imagen de la huella al objeto empleado
                $scope.imagenHuellaBase64 = objeto.imagenBase64;
                //se limpia el applet
                //var elements = document.getElementById('inside');
                //while (elements.hasChildNodes()) {
                //    elements.removeChild(elements.firstChild);
                //}
            }
        };
        $scope.ValidarYGuardar = function () {

            if ($scope.sinBiometrico === true)
            {
                Save();
            } else
            {
                if ($scope.huellaBase64 === undefined)
                {
                    sweet.show('Oopss', "Huella no valida", "error");
                } else
                {
                    Save();
                }

            }


        };
        Save = function () {
            var usuario = leerCookie("APP-TOKEN");
            var codigoPuesto = $routeParams.codigopuesto;
            if (codigoPuesto === undefined) {
                codigoPuesto = "";
            }
            var numeroIdentificacion = $scope.empleado.nrodoc;
            $scope.empleado.fechaNacimiento = Date.parse($('#datepicker').val());
            $scope.empleado.huellaBase64 = ($scope.huellaBase64 === undefined ? "" : $scope.huellaBase64);
            $scope.empleado.imagenHuellaBase64 = ($scope.imagenHuellaBase64 === undefined ? "" : $scope.imagenHuellaBase64);
            //validar si insertar o actualizar
            if ($scope.existeIdentificacion === false || $scope.existeIdentificacion === undefined) {
                Empleado.insertarDatosPrincipalesRDS($scope.empleado, function (response) {
                    if (response.codigo === '200')
                    {
                        var idEmpleadoCreado = response.id;
                        //Asignar a la persona en la ubicación 
                        var personaAsignada = new PersonaAsignada($scope.personaAsignada);
                        personaAsignada.estado.codigoEstado = 1;
                        personaAsignada.empleado.nrodoc = numeroIdentificacion;
                        personaAsignada.$update(function (response) {
                            if (response.codigo === '200')
                            {
                                if ($scope.conAsistencia === true)
                                {
                                    var tipoAsistenciaBiometrico = true;
                                    if ($scope.sinBiometrico === true)
                                    {
                                        tipoAsistenciaBiometrico = false;
                                    }

                                    //Luego marcarle la asistencia
                                    AdministraAsistenciaFactory.registrarAsistencia($scope.personaAsignada.evento.codigoEvento, $scope.personaAsignada.iddivipol, idEmpleadoCreado, tipoAsistenciaBiometrico, function (data) {
                                        $scope.respuestaAsistencia = data;
                                        sweet.show('Felicidades', 'Usuario nombrado, enrolado y toma de asistencia con exito', "success");
                                        $window.history.back();
                                    });
                                } else {
                                    sweet.show('Felicidades', 'Usuario nombrado y enrolado con exito', "success");
                                    $window.history.back();
                                }

                            } else
                            {
                                sweet.show('Oopss', response.descripcion, "error");
                                $scope.error = response.descripcion;
                            }
                        });
                    } else
                    {
                        sweet.show('Oopss', response.descripcion, "error");
                        $scope.error = response.descripcion;
                    }
                });
            } else
            {
                Empleado.actualizarDatosPrincipalesRDS($scope.empleado, function (response) {
                    if (response.codigo === '200')
                    {
                        var idEmpleadoCreado = $scope.empleado.idEmpleado;
                        //Asignar a la persona en la ubicación 
                        var personaAsignada = new PersonaAsignada($scope.personaAsignada);
                        personaAsignada.estado.codigoEstado = 1;
                        personaAsignada.empleado.nrodoc = numeroIdentificacion;
                        personaAsignada.$update(function (response) {
                            if (response.codigo === '200')
                            {
                                if ($scope.conAsistencia === true)
                                {
                                    var tipoAsistenciaBiometrico = true;
                                    if ($scope.sinBiometrico === true)
                                    {
                                        tipoAsistenciaBiometrico = false;
                                    }

                                    //Luego marcarle la asistencia
                                    AdministraAsistenciaFactory.registrarAsistencia($scope.personaAsignada.evento.codigoEvento, $scope.personaAsignada.iddivipol, idEmpleadoCreado, tipoAsistenciaBiometrico, function (data) {
                                        $scope.respuestaAsistencia = data;
                                        sweet.show('Felicidades', 'Usuario nombrado, enrolado y toma de asistencia con exito', "success");
                                        $window.history.back();
                                    });
                                } else {
                                    sweet.show('Felicidades', 'Usuario nombrado y enrolado con exito', "success");
                                    $window.history.back();
                                }
                            } else if (response.codigo === '202' && $scope.personaAsignada.idTipoSitio === 5) {
                                sweet.show({
                                    title: 'Confirmar',
                                    text: response.descripcion + '.\n Desea cambiarlo?',
                                    type: 'warning',
                                    showCancelButton: true,
                                    confirmButtonColor: '#3e6bcc',
                                    cancelButtonText: 'NO',
                                    confirmButtonText: 'SI, Deseo Cambiarlo',
                                    closeOnConfirm: false
                                }, function () {
                                    AdministraAsistenciaFactory.asignarPersonaOtroSitioAPDS($scope.idPersonaAsignada, $scope.empleado.nrodoc, function (data) {
                                        if (data.respuesta === 'true') {
                                            if ($scope.conAsistencia === true)
                                            {
                                                var tipoAsistenciaBiometrico = true;
                                                if ($scope.sinBiometrico === true)
                                                {
                                                    tipoAsistenciaBiometrico = false;
                                                }

                                                //Luego marcarle la asistencia
                                                AdministraAsistenciaFactory.registrarAsistencia($scope.personaAsignada.evento.codigoEvento, $scope.personaAsignada.iddivipol, $scope.persona.idEmpleado, tipoAsistenciaBiometrico, function (data) {
                                                    $scope.respuestaAsistencia = data;
                                                    sweet.show('Felicidades', 'Usuario asignado, enrolado y toma de asistencia con exito', "success");
                                                    $window.history.back();
                                                });
                                            } else {
                                                sweet.show('Felicidades', 'Usuario asignado y enrolado con exito', "success");
                                                $window.history.back();
                                            }
                                        } else {
                                            sweet.show('Oopss', "error al asignar el usuario", "error");
                                        }
                                    })
                                });
                            } else
                            {
                                sweet.show('Oopss', response.descripcion, "error");
                            }
                        });
                    } else
                    {
                        sweet.show('Oopss', response.descripcion, "error");
                        $scope.error = response.descripcion;
                    }
                });
            }

        };

        $scope.atras = function () {
            $window.history.back();
        };
    }]);
app.controller("EmpleadoEditar", ['$scope', '$routeParams', '$location', '$sce', '$window', 'AdministraAsistenciaFactory', 'Empleado', 'PersonaAsignada', 'sweet', function seguimientoController($scope, $routeParams, $location, $sce, $window, AdministraAsistenciaFactory, Empleado, PersonaAsignada, sweet) {
        var pathname = window.location.pathname;
        var url = window.location.href;
        var domain = url.substring(0, url.indexOf(pathname));
        var self = this;
        scope = $scope;
        $scope.editar = true;
        $scope.causasBiometrico = [
            {name: 'DISPOSITIVO AVERIADO', id: '1'},
            {name: 'HUELLA NO LEE', id: '2'},
            {name: 'EL SISTEMA NO ESTABA DISPONIBLE', id: '3'}
        ];
        $scope.causaNoBiometrico = $scope.causasBiometrico[0];
        $scope.tipoIdentificacion = [
            {name: 'CEDULA CIUDADANIA', id: 'CC'},
            {name: 'CEDULA EXTRANJERIA', id: 'CE'}
        ];
        $scope.idPersonaAsignada = $routeParams.idPersonaAsignada;
        $scope.inicializar = function () {
            AdministraAsistenciaFactory.getPersonaAsignada($scope.idPersonaAsignada, function (data) {
                $scope.personaAsignada = data;
                var fechaNacimiento = new Date($scope.personaAsignada.empleado.fechaNacimiento);
                $scope.fechanac = fechaNacimiento.getFullYear() + '-' +
                        pad(fechaNacimiento.getMonth() + 1) + '-' +
                        pad(fechaNacimiento.getDate());
                if ($scope.personaAsignada.empleado.idEmpleado > 0)
                {
                    $scope.existeIdentificacion = true;
                }

                $scope.nrodocAntiguo = $scope.personaAsignada.empleado.nrodoc;
                $scope.empleado = {
                    idEmpleado: $scope.personaAsignada.empleado.idEmpleado,
                    apellido1: $scope.personaAsignada.empleado.apellido1,
                    apellido2: $scope.personaAsignada.empleado.apellido2,
                    celular: $scope.personaAsignada.empleado.celular,
                    direccion: $scope.personaAsignada.empleado.direccion,
                    email: $scope.personaAsignada.empleado.email,
                    municipio: {codigoMunicipio: $scope.personaAsignada.municipio.codigoMunicipio, departamento: {codigo: $scope.personaAsignada.municipio.departamento.codigo, nombre: ""}, nombre: ''},
                    nombre1: $scope.personaAsignada.empleado.nombre1,
                    nombre2: $scope.personaAsignada.empleado.nombre2,
                    nrodoc: $scope.personaAsignada.empleado.nrodoc,
                    tipodoc: $scope.personaAsignada.empleado.tipodoc,
                    idpuesto: $scope.codigoSitio,
                    genero: $scope.personaAsignada.empleado.genero,
                    fechaNacimiento: $scope.personaAsignada.empleado.fechaNacimiento,
                    huellaBase64: $scope.personaAsignada.empleado.huellaBase64,
                    imagenHuellaBase64: $scope.personaAsignada.empleado.imagenHuellaBase64
                };
                //Se añade la huella
                $scope.huellaBase64 = $scope.personaAsignada.empleado.huellaBase64;
                //Se añade la imagen de la huella 
                $scope.imagenHuellaBase64 = $scope.personaAsignada.empleado.imagenHuellaBase64;
            });
            $scope.sinBiometrico = false;
            $scope.conAsistencia = true;
        };
        //
        function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }




        $scope.ValidaExiste = function () {
            Empleado.getPersonaByCedula({nroDocumento: $scope.empleado.nrodoc}, function (data) {
                $scope.persona = data;
                if ($scope.persona.idEmpleado > 0)
                {
                    $scope.empleado.celular = $scope.persona.celular;
                    $scope.empleado.telefono = $scope.persona.telefono;
                    $scope.empleado.email = $scope.persona.email;
                    $scope.empleado.direccion = $scope.persona.direccion;
                    $scope.existeIdentificacion = true;
                    $scope.empleado.idEmpleado = $scope.persona.idEmpleado;
                }

            });
        };
        $scope.enrollar = function () {
            if ($scope.sinBiometrico === false) {
                anadirAppletEnroll(domain);
            }
        }

        //Funcion invocada por el applet de biometria
        //recibe el parametro boolean que indica si se realizo 
        //la identificaion de modo correcto
        //No se debe modificar la firma de esta funcion
        $scope.identificar = function (matchOK) {
            if (matchOK === "si") {
                $scope.imagen = "../../images/okazul.png";
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
        //Funcion invocada por el applet de biometria
        //recibe el objeto que contiene las
        //huellas e imagen del proceso de enrolamiento
        //No se debe modificar la firma de esta funcion
        $scope.enrolar = function (objeto) {

            if (objeto.imagenSerializada === undefined ||
                    objeto.huella === undefined) {
                alert('La huella o la imagen no pudieron ser procesadas. Vuelva a enrolar a la persona');
            } else {

                //Se añade la huella al objeto empleado
                $scope.huellaBase64 = objeto.huellaBase64;
                //Se añade la imagen de la huella al objeto empleado
                $scope.imagenHuellaBase64 = objeto.imagenBase64;
                //se limpia el applet
                //var elements = document.getElementById('inside');
                //while (elements.hasChildNodes()) {
                //    elements.removeChild(elements.firstChild);
                //}
            }
        };
        $scope.ValidarYGuardar = function () {

            if ($scope.sinBiometrico === true)
            {
                Save();
            } else
            {
                if ($scope.huellaBase64 === undefined || $scope.huellaBase64 === "")
                {
                    sweet.show('Oopss', "Huella no valida", "error");
                } else
                {
                    Save();
                }

            }


        };
        Save = function () {
            var usuario = leerCookie("APP-TOKEN");
            var codigoPuesto = $routeParams.codigopuesto;
            if (codigoPuesto === undefined) {
                codigoPuesto = "";
            }
            var numeroIdentificacion = $scope.empleado.nrodoc;
            $scope.empleado.fechaNacimiento = Date.parse($('#datepicker').val());
            $scope.empleado.huellaBase64 = ($scope.huellaBase64 === undefined ? "" : $scope.huellaBase64);
            $scope.empleado.imagenHuellaBase64 = ($scope.imagenHuellaBase64 === undefined ? "" : $scope.imagenHuellaBase64);
            //validar si insertar o actualizar
            if ($scope.existeIdentificacion === false || $scope.existeIdentificacion === undefined) {
                Empleado.insertarDatosPrincipalesRDS($scope.empleado, function (response) {
                    if (response.codigo === '200')
                    {
                        var idEmpleadoCreado = response.id;
                        //Asignar a la persona en la ubicación 
                        var personaAsignada = new PersonaAsignada($scope.personaAsignada);
                        personaAsignada.estado.codigoEstado = 1;
                        personaAsignada.empleado.nrodoc = numeroIdentificacion;
                        personaAsignada.$update(function (response) {
                            if (response.codigo === '200')
                            {
                                sweet.show('Felicidades', 'Registro actualizado y enrolado con exito', "success");
                                $window.history.back();
                            } else
                            {
                                sweet.show('Oopss', response.descripcion, "error");
                                $scope.error = response.descripcion;
                            }
                        });
                    } else
                    {
                        sweet.show('Oopss', response.descripcion, "error");
                        $scope.error = response.descripcion;
                    }
                });
            } else
            {
                Empleado.actualizarDatosPrincipalesRDS($scope.empleado, function (response) {
                    if (response.codigo === '200')
                    {
                        var idEmpleadoCreado = $scope.empleado.idEmpleado;
                        //Asignar a la persona en la ubicación 
                        var personaAsignada = new PersonaAsignada($scope.personaAsignada);
                        personaAsignada.estado.codigoEstado = 1;
                        personaAsignada.empleado.nrodoc = numeroIdentificacion;
                        if ($scope.empleado.nrodoc !== $scope.nrodocAntiguo) {
                            personaAsignada.$update(function (response) {
                                if (response.codigo === '200')
                                {
                                    sweet.show('Felicidades', 'Registro Actualizado y Nombrado con exito', "success");
                                    $window.history.back();
                                } else
                                {
                                    sweet.show('Oopss', response.descripcion, "error");
                                    $scope.error = response.descripcion;
                                }
                            });
                        } else {
                            sweet.show('Felicidades', 'Registro Actualizado con exito', "success");
                            $window.history.back();
                        }
                    } else
                    {
                        sweet.show('Oopss', response.descripcion, "error");
                        $scope.error = response.descripcion;
                    }
                });
            }

        };
        $scope.atras = function () {
            $window.history.back();
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

/***** CONTROLLER PARA CAMBIAR DE CARGO Y DE SALON A UNA PERSONA**/
(function () {
    function CambiarCargo(CambiaCargo, sweet, $window, $routeParams, Empleado) {
        var self = this;
        var idprueba = 0;
        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idprueba = data.pruebaActual;
        });
        this.seleccionado = 0;
        this.personaActual = CambiaCargo.obtenerPersonaCargoSitio({id: $routeParams.id}, function (data) {
            self.cargos = CambiaCargo.listarCargosAsignados({idDcpe: $routeParams.id, idPrueba: self.personaActual.evento.prueba.idprueba}, function (data) {});
            self.salones = CambiaCargo.listarSalonesAsignados({idPrueba: self.personaActual.evento.prueba.idprueba, codigoEvento: self.personaActual.evento.codigoEvento,
                codigoPuesto: self.personaActual.puesto, salon: self.personaActual.salon, codigoCargo: self.personaActual.cargo.codigoCargo}, function (data) {});
        });
        this.buscarPersonasAsignadasaCargo = function () {

            this.personasAsignadasCargo = CambiaCargo.buscarPersonasAsignadasCargo({idPrueba: self.personaActual.evento.prueba.idprueba, codigoEvento: self.personaActual.evento.codigoEvento,
                codigoPuesto: self.personaActual.puesto, codigoCargo: self.codigoCargo}, function (data) {});
        };
        this.buscarSalonesAsignados = function () {

            this.personaAsignadaSalon = CambiaCargo.buscarPersonaAsignadaSalon({id: self.salon}, function (data) {});
        };
        this.guardarCambiosCargos = function () {

            if (this.seleccionado === 0) {
                sweet.show("Debe seleccionar una persona para hacer el cambio");
            } else {

                var cambiaA = this.personaActual.empleado.nrodoc;
                var cambiaB = this.seleccionado.empleado.nrodoc;
                CambiaCargo.updateCargo({personaA: this.personaActual.id, personaB: this.seleccionado.id, nrodocA: cambiaA, nrodocB: cambiaB}, function (response) {

                    if (response.codigo === '200') {
                        sweet.show('Felicidades', 'Se ha realizado el cambio correctamente', "success");
                    } else {
                        sweet.show('Oopss', "Error al cambiar de cargos", "error");
                    }
                    $window.history.back();
                });
            }
        }

        this.guardarCambiosSalon = function () {

            if (this.personaAsignadaSalon === undefined) {
                sweet.show("Debe seleccionar un salon para hacer el cambio");
            } else {

                var cambiaA = this.personaActual.salon;
                var cambiaB = this.personaAsignadaSalon.salon;
                CambiaCargo.updateSalon({personaA: this.personaActual.id, personaB: this.personaAsignadaSalon.id, salonA: cambiaA, salonB: cambiaB}, function (response) {

                    if (response.codigo === '200') {
                        sweet.show('Felicidades', 'Se ha realizado el cambio de salones correctamente', "success");
                    } else {
                        sweet.show('Oopss', "Error al cambiar de salones", "error");
                    }
                    $window.history.back();
                });
            }
        }
    }
    ;
    angular.module("app").controller('CambiarCargo', CambiarCargo);
})();
app.controller("appControllerAsignaSuplente", ['$scope', '$routeParams', 'SuplenteActualiza', 'SuplenteFactory', '$window', function ($scope, $routeParams, SuplenteActualiza, SuplenteFactory, $window) {
        $scope.dcpe = $routeParams.dcpe;
        SuplenteFactory.obtenerSuplentes($scope.dcpe, function (data) {
            $scope.suplentes = data;
        });
        SuplenteFactory.obtenerfalton($scope.dcpe, function (data) {
            $scope.falton = data[0];
        });
        $scope.Save = function () {
            var prueba = new SuplenteActualiza();
            {
                prueba.idprueba = $scope.falton.id;
                prueba.dias = $scope.idReemplazo;
            }
            prueba.$update(function (response) {
                if (response.codigo == '200')
                {
                    $scope.atras();
                }
                ;
            })
        };
        $scope.atras = function () {
            $window.history.back();
        };
    }]);
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


//
function pad(num) {
    num = num + '';
    return num.length < 2 ? '0' + num : num;
}
