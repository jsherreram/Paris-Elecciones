
//Variable que referencia el objeto de $scope
var scope;

(function () {
    function controllerAdministrarAsistencia($routeParams, $scope, AdministraAsistencia, Sesion, Empleado, $window, sweet) {
        $scope.oneAtATime = true;
        var self = this;
        this.idprueba = 0;
        this.usuario = "";
        var idEvento = $routeParams.idEvento;
        var pathname = window.location.pathname;
        var url = window.location.href;
        var domain = url.substring(0, url.indexOf(pathname));
        this.totalRegistros = 100;
        this.totalAsistentes = 0;
        this.sinBiometrico = false;
        this.documento = "";
        scope = $scope;

        //obtener la prueba actual
        Sesion.getEmpleadoPruebaSesion({}, function (data) {
            self.idprueba = data.pruebaActual;
            self.usuario = data.nrodoc;

        });

        this.evento = AdministraAsistencia.getEvento({id: idEvento}, function (data) {});

        this.sitio = AdministraAsistencia.getSitio({id: $routeParams.idDivipol}, function (data) {
            self.personasAsignadas = AdministraAsistencia.listarAsistencia({idEvento: idEvento, codigoSitio: data.codigoSitio}, function (data1) {
                self.totalRegistros = data1.length;
                for (var i = 0; i < self.personasAsignadas.length; i++) {
                    if (self.personasAsignadas[i].asistio === '1')
                    {
                        self.totalAsistentes++;
                    }
                }
            });

        });

        this.causasBiometrico = [
            {name: 'DISPOSITIVO AVERIADO', id: '1'},
            {name: 'HUELLA NO LEE', id: '2'},
            {name: 'EL SISTEMA NO ESTABA DISPONIBLE', id: '3'}
        ];
        this.causaNoBiometrico = this.causasBiometrico[0];
        function buscarEmpleado() {
            Empleado.getPersonaByCedula({nroDocumento: self.documento}, function (data) {
                self.empleado = data;

                self.mostrarBotonAsistenciaManual = false;
                AdministraAsistencia.getAsignacion({idEvento: idEvento, codigoSitio: self.sitio.codigoSitio, nrodoc: self.documento}, function (data) {
                    if (data.length > 0)
                    {
                        self.asignacion = data[0];
                    } else
                    {
                        self.asignacion = null;
                    }
                    validarEmpleado();
                });
            });
        }

        function validarEmpleado() {



//si el usuario existe
            if (self.empleado.nrodoc > 0 && self.asignacion !== null) {

                self.imagen = "../../images/okazul.png";
                //si tiene una huella relacionada

                if (self.sinBiometrico === false)
                {
                    if (self.empleado.huella === "" || self.empleado.huella === undefined || self.empleado.huella === null) {
                        anadirAppletEnroll(domain);

                    } else {
                        anadirAppletIdentificacion(domain, self.empleado.huellaBase64);
                    }
                } else
                {
                    self.mostrarBotonAsistenciaManual = true;
                }

            } else {
                self.imagen = "../../images/chulo_no.gif";
            }

        }

        this.key = function ($event) {

            if ($event.keyCode === 13) {
                $scope = this;
                scope = this;
                this.nrodocok = false;
                buscarEmpleado();
            }

            if (this.nrodocok === true)
            {
                $event.preventDefault();
            } else
            {
                if ($event.which === 9) {
                    this.nrodocok = true;
                    $event.preventDefault();
                }
            }

        };
        //Funcion invocada por el applet de biometria
        //recibe el parametro boolean que indica si se realizo 
        //la identificaion de modo correcto
        //No se debe modificar la firma de esta funcion
        self.identificar = function (matchOK) {

            if (matchOK === "si") {

                self.imagen = "../../images/okazul.png";
                AdministraAsistencia.validarAsistenciaEmpleado({idDivipol: self.asignacion.iddivipol, idEvento: idEvento, idEmpleado: self.empleado.idEmpleado}, function (data) {

                    if (data.respuesta === true) {
                        sweet.show('Esta persona ya tiene asistencia registrada para esta Sesi\u00F3n, Por favor verifique');

                    } else {

                        AdministraAsistencia.registrarAsistencia({idEvento: idEvento, idDivipol: self.asignacion.iddivipol, idEmpleado: self.empleado.idEmpleado, biometrico: true}, function (data) {
                            self.respuestaAsistencia = data;

                            if (data.codigo === '200') {
                                for (var i = 0; i < self.personasAsignadas.length; i++) {
                                    if (self.personasAsignadas[i].nrodoc == self.empleado.nrodoc)
                                    {
                                        if (self.personasAsignadas[i].asistio === '0')
                                        {
                                            self.personasAsignadas[i].asistio = '1';
                                            self.totalAsistentes++;
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
                self.imagen = "../../images/chulo_no.gif";

            }
            self.documento = "";
            //obliga a recargar los elementos del html
//            self.$apply();
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
        self.enrolar = function (objeto) {

            if (objeto.imagenSerializada === undefined ||
                    objeto.huella === undefined) {
                alert('La huella o la imagen no pudieron ser procesadas. Vuelva a enrolar a la persona');
            } else {


                //Se añade la huella al objeto empleado
                self.empleado.huellaBase64 = objeto.huellaBase64;
                //Se añade la imagen de la huella al objeto empleado
                self.empleado.imagenHuellaBase64 = objeto.imagenBase64;
                //var empleado = new Empleado($scope.empleado);

                Empleado.enrolar(self.empleado, function (response) {

                    if (response.codigo == '200')
                    {
                        AdministraAsistencia.validarAsistenciaEmpleado({idDivipol: self.asignacion.iddivipol, idEvento: idEvento, idEmpleado: self.empleado.idEmpleado}, function (data) {

                            if (data.respuesta === true) {
                                sweet.show('Esta persona ya tiene asistencia registrada para esta Sesi\u00F3n, Por favor verifique');

                            } else {

                                AdministraAsistencia.registrarAsistencia({idEvento: idEvento, idDivipol: self.asignacion.iddivipol, idEmpleado: self.empleado.idEmpleado, biometrico: true}, function (data) {
                                    self.respuestaAsistencia = data;

                                    if (data.codigo === '200') {
                                        for (var i = 0; i < self.personasAsignadas.length; i++) {
                                            if (self.personasAsignadas[i].nrodoc == self.empleado.nrodoc)
                                            {
                                                if (self.personasAsignadas[i].asistio === '0')
                                                {
                                                    self.personasAsignadas[i].asistio = '1';
                                                    self.totalAsistentes++;
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
                        self.error = response.descripcion;
                    }
                    ;
                });
                self.documento = "";
                //obliga a recargar los elementos del html
//                self.$apply();
                //se limpia el applet
                var elements = document.getElementById('inside');
                while (elements.hasChildNodes()) {
                    elements.removeChild(elements.firstChild);
                }
            }
        };
        this.asistenciaManual = function () {

            AdministraAsistencia.validarAsistenciaEmpleado({idDivipol: this.asignacion.iddivipol, idEvento: idEvento, idEmpleado: this.empleado.idEmpleado}, function (data) {

                if (data.respuesta === true) {
                    sweet.show('Esta persona ya tiene asistencia registrada para esta Sesi\u00F3n, Por favor verifique');

                } else {

                    AdministraAsistencia.registrarAsistencia({idEvento: idEvento, idDivipol: self.asignacion.iddivipol, idEmpleado: self.empleado.idEmpleado, biometrico: false}, function (data) {
                        self.respuestaAsistencia = data;
                        
                            if (data.codigo === '200') {
                                for (var i = 0; i < self.personasAsignadas.length; i++) {
                                    if (self.personasAsignadas[i].nrodoc == self.empleado.nrodoc)
                                    {
                                        if (self.personasAsignadas[i].asistio === '0')
                                        {
                                            self.personasAsignadas[i].asistio = '1';
                                            self.totalAsistentes++;
                                        }
                                    }
                                }
                            } else {
                                sweet.show('Oopss', data.descripcion, "error");
                            }
            
                        self.documento = "";
                        self.mostrarBotonAsistenciaManual = false;
                        //obliga a recargar los elementos del html
                        $scope.$apply();
                    });
                }
            });



        };

        this.atras = function () {
            $window.history.back();
        }

    }
    ;
    angular.module('app').controller('AdministrarAsistencia', controllerAdministrarAsistencia);
})();

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



