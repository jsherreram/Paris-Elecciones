(function () {
    function GeolocalizarSitios($scope, Sitio, Maps, Empleado, Maps, sweet, $routeParams) {

        var self = this;        
        this.departamentoSede = "";
        this.departamento = "";
        this.municipioSede = "";
        this.mostrarPDS = false;
        this.idPrueba = 0;
        this.historial=0;
        self.geolocalizando = false;

        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            
            self.idPrueba = data.pruebaActual;
            
            Empleado.getBySession({}, function (data) {
                                
                self.municipio = data.municipio.codigoMunicipio;
                
                Sitio.obtenerTotalSitios({'id': 1, 'municipio': self.municipio, 'prueba': self.idPrueba}, function (data) {
                    self.totalSitios = data.id;            
                });

                Sitio.obtenerTotalSitios({'id': 2, 'municipio': self.municipio, 'prueba': self.idPrueba}, function (data) {
                    self.totalGeolocalizados = data.id;
                });

                Sitio.obtenerTotalSitios({'id': 3, 'municipio': self.municipio, 'prueba': self.idPrueba}, function (data) {
                    self.totalConfirmados = data.id;
                });

                Sitio.obtenerTotalSitios({'id': 4, 'municipio': self.municipio, 'prueba': self.idPrueba}, function (data) {
                    self.totalNoUbicados = data.id;
                });
            });
        });
                
        $scope.currentPage = 1;
        $scope.pageSize = 10;

        if ($routeParams.idSitio !== undefined) {
            this.mostrarPDS = true;
            this.sitio = Sitio.obtenerSitioPorId({id: $routeParams.idSitio}, function (data) {
                self.municipio = data.municipio;
                self.municipioSede = data.municipioSede;
                self.departamentoSede = data.departamentoSede.codigo;
                self.departamento = data.departamento.codigo;
                self.historial = Sitio.listarHistorialContactosSitio({codigoSitio: data.codigoSitio}, function (data) {})
            });

            this.sitiosAsignados = Sitio.listarPDSAsignados({id: $routeParams.idSitio}, function () {});
        }

        function save() {
            this.sitio.$updateCoordenadas({}, function (response) {                
            });
        }
        
        //function del modal que muestra en detalle un sitio
        this.DetallarSitio = function (id) {
            this.sitio = Sitio.obtenerSitioPorId({id: id}, function (data) {
            });
        };
        
        this.buscarSinGeolocalizacion = function () {
                                    
            this.sitios = Sitio.filtrarPorGeolocalizacion({'id': 1, 'municipio': self.municipio, 'prueba': self.idPrueba},function (data) {                
                $scope.currentPage = 1;
                $scope.pageSize = 10;
            });
        };
        
        this.buscarSinConfirmar = function () {
            
            this.sitios = Sitio.filtrarPorGeolocalizacion({'id': 2, 'municipio': self.municipio, 'prueba': self.idPrueba},function (data) {
                $scope.currentPage = 1;
                $scope.pageSize = 10;
            });
        };
        
        this.buscarSinUbicar = function () {
            
            this.sitios = Sitio.filtrarPorGeolocalizacion({'id': 3, 'municipio': self.municipio, 'prueba': self.idPrueba},function (data) {
                $scope.currentPage = 1;
                $scope.pageSize = 10;
            });
        };
        
        this.geolocalizar = function () {
            
            self.geolocalizando = true;
            this.sitios_ = Sitio.filtrarPorGeolocalizacion({'id': 1, 'municipio': self.municipio, 'prueba': self.idPrueba},function (data) {
                
                var total = data.length;                
                
                if(total == 0) {
                    self.geolocalizando = false;
                    sweet.show('Operaci\u00f3n Finalizada', 'No se encontraron sitios pendiente por geolocalizaci\u00f3n', "success");
                }
                else {
                    angular.forEach(data, function(sitio, key) {
                        console.log(sitio);
                        Maps.getCoordenadas(sitio.direccionSitio, sitio.municipio.nombre, (key*1000))
                        .then(function(data) {                        
                            this.sitio = sitio;
                            this.sitio.latitud = data.latitud;
                            this.sitio.longitud = data.longitud;
                            this.sitio.confirmado = 0;
                            save();
                            self.totalGeolocalizados++;
                            console.log("SI " + this.sitio.id + " " + data.latitud);
                            if(key == (total-1)) {
                                self.geolocalizando = false;
                                sweet.show('Operaci\u00f3n Finalizada', 'La geolocalizaci\u00f3n se realiz\u00f3 correctamente', "success");
                            }
                        }) 
                        .catch(function(err){                        
                            if(err == 'ZERO_RESULTS') {
                                this.sitio = sitio;
                                this.sitio.latitud = 0;
                                this.sitio.longitud = 0;
                                this.sitio.confirmado = 2;
                                save();
                                self.totalNoUbicados++;
                            }
                            else
                                console.log(err);
                           if(key == (total-1)){ 
                               self.geolocalizando = false;
                               sweet.show('Operaci\u00f3n Finalizada', 'La geolocalizaci\u00f3n se realiz\u00f3 correctamente', "success");
                           }                                   
                        });
                    });                
                }
            });            
        };
    }
    ;
    angular.module("app").controller('GeolocalizarSitios', GeolocalizarSitios);
})();

