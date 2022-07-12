(function () {
    function aplicacion(Empleado, Departamento, sweet, aplicacionServ) {
        var self = this;
        self.parametros = {
            idprueba: self.idPrueba,
            nrodoc: self.nrodoc,
            departamento: self.departamentoSelect,
            municipio: self.municipioSelect,
            sitio: self.sitioSelect,
            evento: self.eventoSelect,
            cargo: self.cargoSelect,
            zona: self.zonaSelect,
            columna1: '',
            columna2: ''
        };
        self.locales = {
            municipio: true,
            sitio: true,
            evento: true,
            cargo: true
        };
        self.idPrueba = 0;
        self.nrodoc = 0;
        self.departamentoSelect = [];
        self.municipioSelect = [];
        self.sitioSelect = [];
        self.eventoSelect = [];
        self.cargoSelect = [];
        self.zonaSelect = [];
        self.aplicarFiltro = "";
        self.aplicacion = [];
        self.operar = 0;

        self.departamentosettings = {displayProp: 'nombre', scrollableHeight: '300px', scrollable: true, enableSearch: true, buttonDefaultText: 'Select Departamento', styleActive: true, keyboardControls: true};
        self.municipiosettings = {displayProp: 'nombre', scrollableHeight: '300px', scrollable: true, enableSearch: true, buttonDefaultText: 'Select Municipio', styleActive: true, keyboardControls: true};
        self.sitiosettings = {displayProp: 'nombre', scrollableHeight: '300px', scrollable: true, enableSearch: true, buttonDefaultText: 'Select Sitio', styleActive: true, keyboardControls: true};
        self.eventosettings = {displayProp: 'nombre', scrollableHeight: '300px', scrollable: true, enableSearch: true, buttonDefaultText: 'Select Evento', styleActive: true, keyboardControls: true};
        self.cargosettings = {displayProp: 'nombre', scrollableHeight: '300px', scrollable: true, enableSearch: true, buttonDefaultText: 'Select Cargo', styleActive: true, keyboardControls: true};
        self.zonasettings = {displayProp: 'nombre', scrollableHeight: '300px', scrollable: true, enableSearch: true, buttonDefaultText: 'Select Zona', styleActive: true, keyboardControls: true};

        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            self.parametros.idprueba = data.pruebaActual;
            self.parametros.nrodoc = data.nrodoc;
            self.findDepartamento();
        });

        self.findDepartamento = function () {
            self.departamentos = Departamento.getUsuarioPrueba(self.parametros, function (data) {
            });
        };

        self.Consultar = function () {
            $('#modalLoading').modal('show');
            self.aplicacion = aplicacionServ.getConsulta(self.parametros, function (data) {
                $('#modalLoading').modal('hide');
            });
        };

        self.Aplicar = function (reg) {
            if (reg.puestos < reg.asignados) {
                sweet.show('Oopss', 'No se Puede dejar una Cantidad Menos a los Asignados', 'error');
            } else {
                $('#modalLoading').modal('show');
                aplicacionServ.update(reg, function (response) {
                    $('#modalLoading').modal('hide');
                    if (response.codigo === '200') {
                        sweet.show('Cambios', 'Se ejecuto el Proceso Correctamente.', 'success');
                    } else
                    {
                        sweet.show('Oopss', 'Ocurrio el Error, reintente o Comuniquese con el Administrador', 'error');
                    }
                    ;
                });
            }
        };

        function ShowResults(value, index, ar) {
            self.aplicarFiltro += "'" + value.codigo + "',";
        }

        self.departamentoEvents = {onSelectionChanged: function () {
                self.aplicarFiltro = "";
                self.departamentoSelect.forEach(ShowResults);
                self.parametros.departamento = self.aplicarFiltro.substring(0, (self.aplicarFiltro.length - 1));
                self.parametros.municipio = "";
                self.parametros.sitio = "";
                self.parametros.evento = "";
                self.parametros.cargo = "";
                self.parametros.zona = "";
                self.municipioSelect = [];
                self.sitioSelect = [];
                self.eventoSelect = [];
                self.cargoSelect = [];
                self.zonaSelect = [];
                self.findAllFilters();
            }};
        self.municipioEvents = {onSelectionChanged: function () {
                self.aplicarFiltro = "";
                self.municipioSelect.forEach(ShowResults);
                self.parametros.municipio = self.aplicarFiltro.substring(0, (self.aplicarFiltro.length - 1));
                self.parametros.sitio = "";
                self.parametros.evento = "";
                self.parametros.cargo = "";
                self.parametros.zona = "";
                self.sitioSelect = [];
                self.eventoSelect = [];
                self.cargoSelect = [];
                self.zonaSelect = [];
                self.locales.municipio = false;
                self.findAllFilters();
                self.locales.municipio = true;
            }};
        self.sitioEvents = {onSelectionChanged: function () {
                self.aplicarFiltro = "";
                self.sitioSelect.forEach(ShowResults);
                self.parametros.sitio = self.aplicarFiltro.substring(0, (self.aplicarFiltro.length - 1));
                self.parametros.evento = "";
                self.parametros.cargo = "";
                self.parametros.zona = "";
                self.eventoSelect = [];
                self.cargoSelect = [];
                self.zonaSelect = [];
                self.locales.sitio = false;
                self.findAllFilters();
                self.locales.sitio = true;
            }};
        self.eventoEvents = {onSelectionChanged: function () {
                self.aplicarFiltro = "";
                self.eventoSelect.forEach(ShowResults);
                self.parametros.evento = self.aplicarFiltro.substring(0, (self.aplicarFiltro.length - 1));
                self.parametros.cargo = "";
                self.parametros.zona = "";
                self.cargoSelect = [];
                self.zonaSelect = [];
                self.locales.evento = false;
                self.findAllFilters();
                self.locales.evento = true;
            }};
        self.cargoEvents = {onSelectionChanged: function () {
                self.aplicarFiltro = "";
                self.cargoSelect.forEach(ShowResults);
                self.parametros.cargo = self.aplicarFiltro.substring(0, (self.aplicarFiltro.length - 1));
                self.parametros.zona = "";
                self.zonaSelect = [];
                self.locales.cargo = false;
                self.findAllFilters();
                self.locales.cargo = true;
            }};
        self.zonaEvents = {onSelectionChanged: function () {
                self.aplicarFiltro = "";
                self.zonaSelect.forEach(ShowResults);
                self.parametros.zona = self.aplicarFiltro.substring(0, (self.aplicarFiltro.length - 1));
            }};

        self.findAllFilters = function () {
            self.parametros.columna1 = 'd.codigoMunicipio';
            self.parametros.columna2 = 'concat(d.codigoMunicipio,\' - \',dp.nombreMunicipio)';
            aplicacionServ.listarFiltros(self.parametros, function (d1) {
                if (self.locales.municipio === true) {
                    self.municipios = d1;
                }
                self.parametros.columna1 = 'dp.idDivipol';
                self.parametros.columna2 = 'concat(d.codigoDepartamento,\' - \',dp.nombrePuesto)';
                aplicacionServ.listarFiltros(self.parametros, function (d2) {
                    if (self.locales.sitio === true) {
                        self.sitios = d2;
                    }
                    self.parametros.columna1 = 'e.codigoEvento';
                    self.parametros.columna2 = 'concat(e.coddepartamento,\' - \',e.nombre)';
                    aplicacionServ.listarFiltros(self.parametros, function (d3) {
                        if (self.locales.evento === true) {
                            self.eventos = d3;
                        }
                        self.parametros.columna1 = 'c.codigoCargo';
                        self.parametros.columna2 = 'c.descripcion';
                        aplicacionServ.listarFiltros(self.parametros, function (d4) {
                            if (self.locales.cargo === true) {
                                self.cargos = d4;
                            }
                            self.parametros.columna1 = 'd.codigoZona';
                            self.parametros.columna2 = 'd.codigoZona';
                            aplicacionServ.listarFiltros(self.parametros, function (d6) {
                                self.zonas = d6;
                            });
                        });
                    });
                });
            });
        };
    }
    ;
    angular.module("app").controller('aplicacion', aplicacion);
})();


