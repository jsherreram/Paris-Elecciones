(function () {
    function listarSitioAsistenciaController(Empleado, ValidaAsistencia, Departamento) {
        var self = this;
        var idPrueba = 0;

        this.departamentos = Departamento.listar({}, function (data) {});

        Empleado.getEmpleadoPruebaSesion({}, function (data) {
            idPrueba = data.pruebaActual;
        });
        
        this.buscarEventosPorDepartamento = function () {
            this.eventos = ValidaAsistencia.buscarEventosPorSitio({idPrueba: idPrueba, idDpto: self.departamento}, function (data) {});
        };

        this.buscarSitiosPorDepartamento = function () {
            this.sitios = ValidaAsistencia.buscarSitiosPorDepartamento({dpto: self.departamento, idPrueba: idPrueba, idEvento:self.evento}, function (data) {});
        };


    }
    ;
    angular.module("app").controller('ListarSitiosAsistenciaController', listarSitioAsistenciaController);
})();

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


