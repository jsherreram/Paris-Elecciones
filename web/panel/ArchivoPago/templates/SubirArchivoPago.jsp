<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    Subir Archivo de Pago por Sitio
                </div>
                <div class="panel-body">  
                    <form name="reviewForm"  class="form-horizontal" ng-submit="buscar(idPrueba)" method="post" enctype="multipart/form-data">

                        <div class="col-md-10">
                            <div class="form-group">
                                <label class="control-label col-md-2" for="prueba">Prueba:</label>
                                <div class="col-md-offset-2"> 
                                    <select ng-model="idPrueba" name="idPrueba" class="form-control" required="required">
                                        <option ng-repeat="prueba in pruebas" value="{{prueba.idprueba}}">{{prueba.nombre}}</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div style="float:right">  
                                    <input class="btn btn-primary"   ng-click="buscar(idPrueba)" type="submit" value ="Buscar" style="margin-right:5px; display: inline-block;">
                                </div>
                            </div>
                        </div>

                    </form>   

                </div>
            </div>
        </div>
    </div>
    <div class="row" ng-show="mostrar">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div ng-show="total > 1 && soloConsultar" class="col-md-11" style="margin:2% 3% 1%; ">
                        <a class="btn btn-primary" data-toggle="modal"  style="float:right; margin-left: 2px" data-target="#myModalAllFiles">Subir Varios Archivos</a>
                        <a href="templates/GenerarCSV.jsp?idPrueba={{idPrueba}}&usuario={{usuario}}" style="float:right"  class="btn btn-primary"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> CSV</a>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                        <input type="text" class="form-control" placeholder="Buscar" ng-model="filtrar">
                    </div>
                    
                </div>                

                <div class="panel-body">
                    <table class="table table-striped">
                        <tr>
                            <th>Codigo Sitio</th>
                            <th>Nombre</th>
                            <th>Opciones</th>

                        </tr>
                        <tr ng-repeat="sitio in sitios|orderBy:'codigoSitio' | filter:filtrar">
                            <td> {{sitio.codigoPuesto}}</td>
                            <td>{{sitio.puesto}}</td>
                            <td ng-show="{{sitio.archivo === 'No' && soloConsultar}}">
                                <a class="btn btn-primary" data-toggle="modal" ng-click="seleccionarSitio(sitio.codigoPuesto)" data-target="#myModal">Subir Archivo</a>
                            </td>
                            <td ng-show="{{sitio.archivo ==='No'}} && !soloConsultar">
                               
                            </td>
                            <td ng-show="{{sitio.archivo === 'Si' && soloConsultar}}">
                                <a href="templates/verArchivo.jsp?idPrueba={{idPrueba}}&idSitio={{sitio.codigoPuesto}}" class="btn btn-primary" style="margin-bottom: 1px">Ver &nbsp; Archivo</a>
                                <a class="btn btn-primary"  data-toggle="modal" ng-click="seleccionarSitio(sitio.codigoPuesto)" data-target="#myModal" style="margin-bottom: 1px">Cambiar Archivo</a>
                            </td>
                            <td ng-show="{{sitio.archivo === 'Si' && !soloConsultar}}">
                                <a href="templates/verArchivo.jsp?idPrueba={{idPrueba}}&idSitio={{sitio.codigoPuesto}}" class="btn btn-primary" >Ver &nbsp; Archivo</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

    </div>     
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="padding: 2%;">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Subir Archivo Pago</h4>
                </div>
                <div class="modal-body">


                    <form   action="${pageContext.request.contextPath}/UploadFileServletPago" method="post" enctype="multipart/form-data">

                        <input type="text" name ="idPrueba" ng-model ="idPrueba"  style="display: none;" class="form-control" required="required" >
                        <input type="text" name ="codSitio" ng-model ="sitio"  style="display: none;" class="form-control" required="required" >
                        <input type="text" name ="multiple" value="no" style="display: none;" class="form-control" required="required">
                        <div class="form-group">
                            <label class="control-label" for="file">Archivo de Pago:</label>
                            <div>  
                                <input type="file" name ="file" ng-model ="file" accept="application/pdf" class="form-control" required="required" >
                            </div>
                        </div>
                        <div class="form-group">
                            <input class="btn btn-primary"  ng-click="validarForm()" type="submit" value ="Subir Archivo">
                        </div>
                    </form>

                </div>

            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <div class="modal fade" id="myModalAllFiles" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="padding: 2%;">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Subir Archivo(s) Pago</h4>
                </div>
                <div class="modal-body">


                    <form   action="${pageContext.request.contextPath}/UploadFileServletPago" method="post" enctype="multipart/form-data">

                        <input type="text" name ="idPrueba" ng-model ="idPrueba"  style="display: none;" class="form-control" required="required" >
                        <input type="text" name ="multiple" value="multiple" style="display: none;" class="form-control" required="required" >

                        <div class="form-group">
                            <label class="control-label" for="file">Archivo(s) de Pago: (Presione la tecla ctrl para seleccionar varios)</label>
                            <div>  
                                <input type="file" name ="file" ng-model ="file" accept="application/pdf" class="form-control" required="required" multiple="multiple">
                            </div>
                        </div>
                        <div class="form-group">
                            <input class="btn btn-primary"  ng-click="validarForm()" type="submit" value ="Subir Archivo">
                        </div>
                    </form>



                </div>

            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <% if (request.getSession().getAttribute("mensaje") != null) {%>
    <script>alert('<%=request.getSession().getAttribute("mensaje")%>')</script>
    <% request.getSession().removeAttribute("mensaje");
        }
    %>
</div>

