(function () {
    function verHistorial($scope, $routeParams, id, Empleado, $window, $uibModalInstance, sweet) {
        var self = this;
        $scope.currentPage = 1;
        $scope.pageSize = 10;
        self.tipoDocumento = "ahorroalamano";
        self.idEmpleado = id;
        Empleado.getHistorialArchivos({idEmpleado: self.idEmpleado, tipoDoc: self.tipoDocumento}, function (data) {
            self.archivos = data;
        });


        var numAdds = 0;
        self.dzOptions = {
            url: '/Paris/UploadFileServlet',
            paramName: 'file2',
            parallelUploads: 1,
            maxFilesize: '10',
            dictDefaultMessage: 'Arrastre sus archivos aqu&#237; (o haga click)',
            dictRemoveFile: "Quitar Archivo",
            dictFileTooBig: "Archivo no puede superar los 10MB",
            dictInvalidFileType: "Tipo de archivo no valido",
            acceptedFiles: 'application/pdf',
            addRemoveLinks: true,
            autoProcessQueue: false
        };

        //Handle events for dropzone
        //Visit http://www.dropzonejs.com/#events for more events
        self.dzCallbacks = {
            addedfile: function (file) {
                numAdds++;
                if (numAdds > 1) {
                    self.dzMethods.removeFile(file);
                }
                console.log("File:", file);
            },
            removedfile: function (file) {
                numAdds--;
                if (numAdds < 1) {
                    self.dzMethods.enable();
                }
            },
            sending: function (file, xhr, formData) {
                formData.append("idEmpleado", self.idEmpleado);
                formData.append("folder", self.folder);
                console.log("File:", file);
                console.log("xhr:", xhr);
                console.log("FormData:", formData);
                xhr.onload(function (data) {
                    console.log(data);
                });
                $('#modalLoading').modal('show');

            },
            complete: function (file) {
                self.dzMethods.processQueue();
            },
            maxfilesexceeded: function (file) {
                self.dzMethods.removeFile(file);
            },
            error: function (file, message) {
                sweet.show({title: message, type: "error"});
            },
            queuecomplete: function () {
                Empleado.getHistorialArchivos({idEmpleado: self.idEmpleado, tipoDoc: self.tipoDocumento}, function (data) {
                    self.archivos = data;
                });
                $('#modalLoading').modal('hide');
            },
        };

        this.uploadFiles = function () {
            if ((numAdds + self.cantidadFotos) > 1) {
                sweet.show({title: "\u00A1No se pueden cargar mas de 1 PDF!", type: "warning"});
            } else {
                numAdds = 0;
                self.dzMethods.processQueue();
            }
        };
        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }
    ;
    angular.module('app').controller('subirAhorroalaMano', verHistorial);
})();


/**DIrectiva que muestra el modal de Busqueda avanzada*/
angular.module("app").directive('subirAhorro', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/ahorroalaMano.html'
    };
});