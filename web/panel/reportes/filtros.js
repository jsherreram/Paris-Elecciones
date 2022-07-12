 angular.module('app', [])
  .controller('ControladorFiltros', ['$scope', function($scope) {
  $scope.empleados =
    [
        {
        TipoDoc:'CC', NroDoc: '80544071', Apellido1: 'RODRIGUEZ', Apellido2: 'SUAREZ', Nombre1: 'PEDRO',
        Nombre2: 'ENRIQUE', Sexo: 'M', FNacimiento: new Date(),
        Depto:'CALDAS', Ciudad: 'MANIZALES', Direccion: 'DG 89A NRO 115 55 INT 4 APT 304',
        Telefono: '7594483', Celular: '3165220301', Email: 'prodriguez@grupoasd.com.co',
        CodActividad: '1101', Rut: true, FotocopiaCedula: true
        },
        {
        TipoDoc:'CC', NroDoc: '52715432', Apellido1: 'MUÑOZ', Apellido2: 'OLAYA', Nombre1: 'NOHORA',
        Nombre2: 'JOHANNA', Sexo: 'F', FNacimiento: new Date(),
        Depto:'BOGOTA', Ciudad: 'BOGOTA', Direccion: 'DG 89A NRO 115 55 INT 4 APT 304',
        Telefono: '7594483', Celular: '3115963898', Email: 'babila10@gmail.com',
        CodActividad: '1101', Rut: true, FotocopiaCedula: true
        },
        {
        TipoDoc:'CC',  NroDoc: '39522333', Apellido1: 'OLAYA', Apellido2: 'BEJARANO', Nombre1: 'NOHORA',
        Nombre2: 'CECILIA', Sexo: 'F', FNacimiento: new Date(),
        Depto:'CALDAS', Ciudad: 'MANIZALES', Direccion: 'DG 89A NRO 115 55 INT 4 APT 304',
        Telefono: '7594483', Celular: '3202893534', Email: 'nocilara@gmail.com',
        CodActividad: '1101', Rut: true, FotocopiaCedula: true
        }
    ];

  $scope.ordenarPor = function(orden) {
    $scope.ordenSeleccionado = orden;
  };
}]);