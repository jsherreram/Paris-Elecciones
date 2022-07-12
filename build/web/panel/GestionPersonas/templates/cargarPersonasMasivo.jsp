<div class="content animate-panel">
    <div class="row">
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">                  
                    <h2>Cargue Archivo de Personas</h2>
                </div>
                <div class="panel-body">  
                    <form class="form-horizontal" action="" method="post" enctype="multipart/form-data">


                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">  
                                <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" ng-click="controller.limpiar()">Cargar Archivo</a>
                                <a href="templates/verArchivo.jsp?name=demoPersonasElecciones" class="btn btn-primary">Descargar Ejemplo.csv</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-10">
            <div class="panel panel-primary">
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover" >
                            <thead>
                                <tr>
                                    <th>Nombre Archivo</th>
                                    <th>Hora Inicio</th>
                                    <th>Hora Finalización</th>
                                    <th>Cantidad de Registros</th>
                                    <th>Registros Ok</th>
                                    <th>Registros Error</th>
                                    <th>Estado</th>
                                    <th>Log Errores</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr dir-paginate="st in controller.status| orderBy:'-id'| filter:search | itemsPerPage: pageSize" current-page="currentPage">
                                    <td>{{st.nombreArchivo}}</td>
                                    <td>{{st.fechaHoraInicio}}</td>
                                    <td>{{st.fechaHoraFinal}}</td>
                                    <td>{{st.cantidadRegistrosTotal}}</td>
                                    <td>{{st.cantidadRegistrosProcesadosOk}}</td>
                                    <td>{{st.cantidadRegistrosProcesadosError}}</td>
                                    <td>{{st.estado}}</td>
                                    <td>
                                        <a style="display:{{st.mostrarImagenArchivoError}};" href="../Sitios/templates/verErrores.jsp?idStatus={{st.id}}">
                                            <img src="../../images/excell.png" alt="Logo Excel" width="20" height="20" />
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <center>
                            <dir-pagination-controls boundary-links="true" on-page-change="pageChangeHandler(newPageNumber)" template-url="/Paris/templates/directives/dirPagination.tpl.html"></dir-pagination-controls>
                        </center>
                        <% if (request.getSession().getAttribute("mensaje") != null) {%>
                        <script>alert('<%=request.getSession().getAttribute("mensaje")%>')</script>
                        <% request.getSession().removeAttribute("mensaje");
                            }
                        %>

                    </div>
                        <a ng-click="controller.atras()" style="float:right" class="btn btn-primary">Regresar</a>

                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="padding: 2%;">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Cargar Archivo</h4>
                </div>
                <div class="modal-body">

                    <form   action="${pageContext.request.contextPath}/PersonaMasivoServlet" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="control-label" for="file">Examinar:</label>
                            <div>  
                                <input type="file" name ="file" id="file" ng-model ="file" accept=".csv" class="form-control" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <input class="btn btn-primary"  type="submit" value ="Subir Archivo">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>               
                        </div>
                    </form>
                </div>

            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>