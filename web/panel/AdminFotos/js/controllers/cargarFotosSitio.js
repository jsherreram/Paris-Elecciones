(function () {
    function CargarFotosSitio($scope, Sesion, sweet, Sitio, $window, $routeParams, $http, $sce) {
        var self = this;
        var numAdds = 0;
        $scope.idEvento = $routeParams.idEvento;
        //Se obtiene el id de la prueba para enviarla en la consulta
        Sesion.getEmpleadoPruebaSesion({}, function (data) {
            $scope.idPrueba = data.pruebaActual;
        }).$promise.then(function (result) {
            $scope.idSitio = $routeParams.idDivipol;
            if ($routeParams.folder === undefined) {
                $scope.folder = "sitio";
                $scope.title = "Sitio";
            } else {
                $scope.folder = $routeParams.folder;
                if ($routeParams.folder === "asistencia") {
                    $scope.title = "Listas de Asistencia";
                } else if ($routeParams.folder === "pagos") {
                    $scope.title = "de Pagos";
                } else {
                    $scope.title = "";
                }
            }
            $scope.url = "/fotosSitioElecciones/prueba_" + $scope.idPrueba + "/sitio_" + $scope.idSitio + "/evento_" + $scope.idEvento + "/asistencia/" + $scope.idSitio + ".pdf";
            $http.get($scope.url,
                    {responseType: 'arraybuffer'})
                    .success(function (response) {
                        var file = new Blob([(response)], {type: 'application/pdf'});
                        var fileURL = URL.createObjectURL(file);
                        $scope.content = $sce.trustAsResourceUrl(fileURL);
                    });

            //Se cargan las fotos ya cargadas
            //            $scope.listaFotos = Sitio.getFotosSitio({idSitio: $scope.idSitio, idPrueba: $scope.idPrueba, folder: $scope.folder, idEvento: $scope.idEvento}, function (data) {
            //                $scope.cantidadFotos = data.length;
            //                if ($scope.cantidadFotos >= 10) {
            //                    $scope.dzMethods.disable();
            //                }
            //            });
            //            });
        });


        //Funcion para devolver la pagina segun la historia del navegador
        this.atras = function () {
            $window.history.back();
        };
        var numAdds = 0;
        $scope.dzOptions = {
            url: '/Paris/CargarFotosServlet',
            paramName: 'file',
            parallelUploads: 1,
            maxFilesize: '10',
            dictDefaultMessage: 'Arrastre sus archivos aqu&#237; (o de click)',
            dictRemoveFile: "Quitar Archivo",
            dictFileTooBig: "Archivo no puede superar los 10MB",
            dictInvalidFileType: "Tipo de archivo no valido",
            acceptedFiles: 'application/pdf',
            addRemoveLinks: true,
            autoProcessQueue: false
        };
        //Handle events for dropzone
        //Visit http://www.dropzonejs.com/#events for more events
        $scope.dzCallbacks = {
            addedfile: function (file) {
                numAdds++;
                if (numAdds > 1) {
                    $scope.dzMethods.removeFile(file);
                }
                console.log("File:", file);
            },
            removedfile: function (file) {
                numAdds--;
                if (numAdds < 1) {
                    $scope.dzMethods.enable();
                }
            },
            sending: function (file, xhr, formData) {
                formData.append("idPrueba", $scope.idPrueba);
                formData.append("idSitio", $scope.idSitio);
                formData.append("folder", $scope.folder);
                formData.append("idEvento", $scope.idEvento);
                console.log("File:", file);
                console.log("xhr:", xhr);
                console.log("FormData:", formData);
                xhr.onload(function (data) {
                    console.log(data);
                });
                $('#modalLoading').modal('show');
            },
            complete: function (file) {
                $scope.dzMethods.processQueue();
            },
            queuecomplete: function () {
                $('#modalLoading').modal('hide');
                location.reload();
            },
            maxfilesexceeded: function (file) {
                $scope.dzMethods.removeFile(file);
            },
            error: function (file, message) {
                sweet.show({title: message, type: "error"});
            }
        };
        this.uploadFiles = function () {
            if (numAdds > 1) {
                sweet.show({title: "\u00A1No se pueden cargar mas de 1 PDF!", type: "warning"});
            } else {
                numAdds = 0;
                $scope.dzMethods.processQueue();
            }
        };


        //Apply methods for dropzone
        //Visit http://www.dropzonejs.com/#dropzone-methods for more methods
        $scope.dzMethods = {};
        //Funcion para generar el modal que va a mostrar la foto seleccionada
        $scope.showModalImage = function (idImage) {
            var img = $('#' + idImage).attr("src");
            $('#imgModal').attr("src", img);
            $('#imgModal').width(900);
            $('#imgModal').height(650);
            $('#modalImage').modal('show');
        };
        //Metodo para enviar las fotos de la cola generada por dropzone
        this.eliminarFoto = function (nombreFoto) {
            sweet.show({title: "\u00BFEsta seguro que desea eliminar la foto " + nombreFoto + " ?",
                text: "Esta acci\u00F3n no podra ser devuelta!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Si, estoy seguro!",
                closeOnConfirm: false}, function () {
                Sitio.eliminarFotoSitio({idPrueba: $scope.idPrueba, idSitio: $scope.idSitio, nombreFoto: nombreFoto, folder: $scope.folder, idEvento: $scope.idEvento}, function (data) {
                    sweet.show('Felicidades', 'La foto se elimin\u00F3 correctamente', "success");
                    //Se cargan las fotos ya cargadas
                    $scope.listaFotos = Sitio.getFotosSitio({idSitio: $scope.idSitio, idPrueba: $scope.idPrueba, folder: $scope.folder, idEvento: $scope.idEvento}, function (data) {
                        $scope.cantidadFotos = data.length;
                        if ($scope.cantidadFotos >= 5) {
                            $scope.dzMethods.disable();
                        } else {
                            $scope.dzMethods.enable();
                        }
                    });
                });
            });
        };
    }
    ;
    angular.module("app").controller('CargarFotosSitio', CargarFotosSitio);
})();