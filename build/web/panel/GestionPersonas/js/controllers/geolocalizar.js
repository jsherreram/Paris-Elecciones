(function () {
    function Geolocalizar($scope, Empleado, GestionPersonas, sweet) {

        var self = this;
        self.geolocalizando = false;
        self.asignando = false;
        self.cargo = 1;
        self.nivelCargo = GestionPersonas.listarNivelCargo({}, function (data) {});

        Empleado.getBySession({}, function (data) {
                        
            self.departamentoDane = data.municipioDane.departamento;
            self.municipio = data.municipio.codigoMunicipio;            
            
            Empleado.getEmpleadoPruebaSesion({}, function (data) {
                
                self.prueba = data.pruebaActual;
                
                GestionPersonas.listarNivelCargo({}, function (data) {
                    //console.log(data);
                });                

                GestionPersonas.obtenerTotalGeolocalizados({'id': 1, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba}, function (data) {
                    self.totalSitios = data.id;            
                });

                GestionPersonas.obtenerTotalGeolocalizados({'id': 2, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba}, function (data) {
                    self.totalGeolocalizados = data.id;
                });

                GestionPersonas.obtenerTotalGeolocalizados({'id': 3, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba}, function (data) {
                    self.totalConfirmados = data.id;
                });

                GestionPersonas.obtenerTotalGeolocalizados({'id': 4, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba}, function (data) {
                    self.totalNoUbicados = data.id;
                });
            });
                    
        });
        
        this.asignarMasivo = function () {
            
            self.asignando = true;
            GestionPersonas.asignarMasivo({"cargo": self.cargo, "prueba": self.prueba, "municipio": self.municipio}, function (response) {

                if (response.codigo === '200') {
                    self.asignando = false;
                    sweet.show('Operaci\u00f3n Finalizada', 'Los empleados se han asignado correctamente', "success");

                } else {
                    sweet.show('Oopss', "El proceso de asignaci\u00f3n termin\u00f3 con errores", "error");
                }
            });
        };
        
        this.changeCargo = function () {
            
            GestionPersonas.obtenerTotalGeolocalizados({'id': 1, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba }, function (data) {
                self.totalSitios = data.id;            
            });
        
            GestionPersonas.obtenerTotalGeolocalizados({'id': 2, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba }, function (data) {
                self.totalGeolocalizados = data.id;
            });
            
            GestionPersonas.obtenerTotalGeolocalizados({'id': 3, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba }, function (data) {
                self.totalConfirmados = data.id;
            });
        
            GestionPersonas.obtenerTotalGeolocalizados({'id': 4, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba }, function (data) {
                self.totalNoUbicados = data.id;
            });            
        };

        function save() {
            Empleado.updateCoordenadas(this.empleado, function (response) {
                // No se hace nada, ya que se esta realizando una actualización masiva de coordenadas de personas
            });
        };               
        /**
        this.buscarSinGeolocalizacion = function () {
                                    
            this.sitios = Sitio.filtrarPorGeolocalizacion({'id': 1, 'prueba': self.idPrueba},function (data) {                
                $scope.currentPage = 1;
                $scope.pageSize = 10;
            });
        };
        
        this.buscarSinConfirmar = function () {
            
            this.sitios = Sitio.filtrarPorGeolocalizacion({'id': 2, 'prueba': self.idPrueba},function (data) {
                $scope.currentPage = 1;
                $scope.pageSize = 10;
            });
        };
        
        this.buscarSinUbicar = function () {
            
            this.sitios = Sitio.filtrarPorGeolocalizacion({'id': 3, 'prueba': self.idPrueba},function (data) {
                $scope.currentPage = 1;
                $scope.pageSize = 10;
            });
        };*/
        
        this.geolocalizar = function () {
            
            self.geolocalizando = true;
            GestionPersonas.filtrarPorGeolocalizacion({'id': 1, 'departamento': self.departamentoDane.codigo, "cargo": self.cargo, "prueba": self.prueba },function (data) {
                console.log(data.length);
                var nextAddress = 0;
                var delay = 1;        
                var geolocalizacion = new google.maps.Geocoder();
                var empleado;
                var direccion;
                
                var theNext = function() {
                                    
                if (nextAddress < data.length) {

                    empleado = data[nextAddress];
                    
                    direccion = empleado.direccion + ", " + empleado.municipioDane.nombre + ", Colombia";
                    if(empleado.barrio.length > 0)                    
                        direccion = empleado.direccion + ", " + empleado.barrio + ", " + empleado.municipioDane.nombre + ", Colombia";                                
                    
                    console.log(direccion);
                    setTimeout(function(){
                        geolocalizacion.geocode({address: direccion}, function (results, status) {                

                            if (status == google.maps.GeocoderStatus.OK) {                                
                                var p = results[0].geometry.location;
                                var lat = p.lat();
                                var lng = p.lng();
                                //console.log("SI: " + direccion + " " + nextAddress);
                                this.empleado = Empleado.getEmpleado({id: empleado.idEmpleado});
                                this.empleado.idEmpleado = empleado.idEmpleado;
                                this.empleado.latitud = lat;
                                this.empleado.longitud = lng;
                                this.empleado.confirmado = 0;
                                save();
                                self.totalGeolocalizados++;
                            }                        
                            else {

                                if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT) {
                                    nextAddress--;                        
                                    delay++;
                                    //console.log("NO: " + direccion + " " + nextAddress);
                                } else {
                                    //console.log("NA: " + direccion + " " + nextAddress);
                                    this.empleado = Empleado.getEmpleado({id: empleado.idEmpleado});
                                    this.empleado.idEmpleado = empleado.idEmpleado;
                                    this.empleado.latitud = 0;
                                    this.empleado.longitud = 0;
                                    this.empleado.confirmado = 2;
                                    save();
                                    self.totalNoUbicados++;
                                }
                            }
                            theNext();
                        });
                    }, delay);

                    nextAddress++;

                } else {
                    self.geolocalizando = false;        
                    sweet.show('Operaci\u00f3n Finalizada', 'La geolocalizaci\u00f3n se realiz\u00f3 correctamente', "success");
                }
            };                       
            theNext();
            });            
        };
    }
    ;
    angular.module("app").controller('Geolocalizar', Geolocalizar);
})();

