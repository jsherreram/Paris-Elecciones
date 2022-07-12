(function () {
    angular.module("app").controller("ConsultarEmpleadosController", function ($location, Funcionario) {
        var self = this;
        Funcionario.empleadoSesion( function (response) {
            self.idPruebaActual=response.pruebaActual;
        });
        Funcionario.listarGruposFuncionarios(function (data) {
            self.listadoRoles = data;
        });
        
        self.buscar = function () {

            if (self.searchIdentificacion !== undefined || self.searchApellido1 !== undefined ||
                    self.searchNombre1 !== undefined || self.searchRol!==undefined) {
                if(self.searchIdentificacion!==undefined){
                    self.nrodoc=self.searchIdentificacion;
                }else{
                    self.nrodoc=0;
                }
                
                if(self.searchRol!==undefined){
                    self.rol=self.searchRol.codigoGrupo;
                }else{
                    self.rol=undefined;
                }
                
                Funcionario.listarJsonFuncionarios({nrodoc: self.nrodoc,apellido1: self.searchApellido1,nombre1: self.searchNombre1,
                    rol:self.rol,idPrueba: self.idPruebaActual}, function (response) {
                    self.lstFuncionarios = response;
                    self.totalItems = self.lstFuncionarios.length;
                    self.currentPage = 1;
                    self.numPerPage = 10;

                    self.paginate = function (value) {
                        var begin, end, index;
                        begin = (self.currentPage - 1) * self.numPerPage;
                        end = begin + self.numPerPage;
                        index = self.lstFuncionarios.indexOf(value);
                        return (begin <= index && index < end);
                    };
                });
            }
            ;
        };

        //Redirecciona para actualizar empleado
        self.actualizar = function (idEmpleado) {
            $location.path('/actualizarEmpleado/' + idEmpleado + '/');
        };
        
         //Redirecciona para consultar empleado
        self.asignarRol = function () {
            $location.path('/asignarRol/');
        };
        
    });

})();


