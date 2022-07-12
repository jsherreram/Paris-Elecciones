<style>
    #inside{

        font-size:12px;     
        border-color:#ff3366;
        width: 300px;
        height: 100px;
    }

    a[rel="popover"]{
        display: inline-block;
        margin: 20px;
    }        

</style>

<div class="accordion-test" ng-controller="appControllerAsistencia" >

    <div class="row">
        <div class="col-md-10 col-sm-offset-1">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2>{{nombreEvento}}</h2> <label class="control-label">{{codigoSitio}}-{{puestoNombre}}</label>
                    <p><label ng-show="cerrado === true">Sitio Cerrado</label></p>
                </div>

                <div class="panel-body" data-ng-init="listar()">    

                    <form class="form-inline" action="${pageContext.request.contextPath}/Reporte" method="post" target="_blank">
                        <input type="hidden" name="tipoReporte" value="{{tipoReporte}}">
                        <input type="hidden" name="codigoevento" value="{{idEvento}}">
                        <input type="hidden" name="codigopuesto" value="{{codigoSitio}}">
                        <input type="hidden" name="prueba" value="{{idprueba}}">

                        <div>
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/panel/MonitoreoGeneralSitio/main.jsp#/">Volver</a>
                        </div>
                    </form>   

                    <accordion close-others="TwoAtATime">
                        <accordion-group is-open="true">
                            <accordion-heading>
                                Tomar Asistencia <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': isopen, 'glyphicon-chevron-right': !isopen}"></i>
                            </accordion-heading>
                            <form id="reviewForm" name="reviewForm" class="form-inline">
                                <table style="width:100%;">
                                    <tr>
                                        <td style="width:30%;"> </td>
                                        <td style="width:20%;" > <label>Sin lector de Huella<input type="checkbox" id="sinBiometrico" name="sinBiometrico" ng-model="sinBiometrico"></label></td>
                                        <td style="width:50%;"> </td>
                                    </tr>

                                    <tr>
                                        <td> </td>
                                        <td> 

                                            <div ng-show="sinBiometrico === true" class="form-group">
                                                <label class="control-label" for="causaNoBiometrico">Observación:</label>
                                                <select id="tipoDoc" ng-model="causaNoBiometrico" ng-options="causa.name for causa in causasBiometrico" class="form-control" ></select>
                                            </div>

                                        </td>
                                        <td> </td>
                                    </tr>

                                    <tr>
                                        <td> 
                                            <label>Documento</label>
                                            <input type="number" id="documento" name="input" ng-model="documento" ng-keydown="key($event)" >

                                        </td>
                                        <td><DIV  id="inside"></DIV>  <div ng-show="mostrarBotonAsistenciaManual === true" > <a class="btn btn-primary"  ng-click="asistenciaManual()">Marcar Asistencia Manual</a>  </div> </td>
                                        <td><img   ng-src={{imagen}}></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <span ng-bind="empleado.nombre1 + ' ' + empleado.nombre2 + ' ' + empleado.apellido1 + ' ' + empleado.apellido2"></span>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <span ng-bind="'Cargo:'+asignacion.descripcion"></span>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                        </td>
                                        <td></td>
                                        <td>
                                            <label> {{totalAsistentes + ' de ' + totalRegistros}} </label>
                                            <a data-toggle="modal" data-target="#myModalResumen" ng-click="CalcularParaCerrar()" class="btn btn-primary btn-xs">Ver detalle...</a>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td style="width:30%;"></td>
                                        <td style="width:20%;"></td>
                                        <td style="width:50%;">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar"
                                                     aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:{{totalAsistentes * 100 / totalRegistros}}%">
                                                    {{((totalAsistentes * 100 / totalRegistros) + "").substring(0, 4)}}%
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>    
                            </form>        
                        </accordion-group>
                        <accordion-group is-open="true">

                            <accordion-heading>
                                Listado de Personal <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': isopen, 'glyphicon-chevron-right': !isopen}"></i> 
                            </accordion-heading>


                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel-heading">
                                        <div class="input-group col-md-12">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                                            <input type="text" class="form-control" placeholder="Buscar" ng-model="search">

                                        </div>

                                    </div> 
                                    <div class="panel panel-default">
                                        <div class="panel-body">                                      
                                            <table  WIDTH="600" class="table table-bordered table-hover" >
                                                <thead>
                                                    <tr>
                                                        <th>Cargo</th>
                                                        <th>Identificación</th>
                                                        <th>Apellido1</th>
                                                        <th>Apellido2</th>
                                                        <th>Nombre1</th>
                                                        <th>Nombre2</th>
                                                        <th>Celular</th>
                                                        <th>Ubicación</th>
                                                        <th>Acción</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr style="color:#111" ng-style="ubica.asistio === '1' ? {'background-color': '#FFFFFF'} : {'background-color': '#F2DEDE'}" ng-repeat="ubica in ubicaciones| orderBy:'descripcion'| filter:search">

                                                        <td >{{ubica.descripcion}}</td>
                                                        <td>{{ubica.nrodoc}}</td>
                                                        <td>{{ubica.apellido1}}</td>
                                                        <td>{{ubica.apellido2}}</td>
                                                        <td>{{ubica.nombre1}}</td>
                                                        <td>{{ubica.nombre2}}</td>
                                                        <td>
                                                            {{ubica.celular}}
                                                        </td>
                                                        <td>{{ubica.ubicacion}}</td>


                                                        <td>
                                                        <center>
                                                            <div class="btn-group"> 
                                                                <a type="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
                                                                    <span class="glyphicon glyphicon-plus"></span>
                                                                    <span class="sr-only">Toggle Dropdown</span> 
                                                                </a>
                                                                <ul class="dropdown-menu"> 
                                                                    <li ng-show="ubica.asistio === '0'"><a href="#/Asignar/{{ubica.id}}">Asignar</a></li>
                                                                    <li ng-show="ubica.asistio === '0' && ubica.nrodoc !== '0'"><a  ng-click="desasignar(ubica.id)">Des-asignar</a></li>
                                                                    <li ng-show="ubica.nrodoc !== '0'"><a href="#/Editar/{{ubica.id}}">Editar</a></li>

                                                                </ul> 
                                                            </div>
                                                        </center>
                                                        </td>
                                                        </tr>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                            </div>

                        </accordion-group>
                    </accordion>

                    <div class="modal fade" id="myModalS" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div ng-show="validarModal === true" class="modal-header" style="padding: 2%;">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <center><h4 class="modal-title">ADVERTENCIA</h4></center>
                                </div>

                                <div ng-show="validarModal === false" class="modal-header" style="padding: 2%;">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <center><h4 class="modal-title">INFORMACI\u00d3N</h4></center>
                                </div>


                                <div class="modal-body">
                                    <form  method="post" enctype="multipart/form-data" >

                                        <div ng-show="validarModal === true">
                                            <div class="form-group">
                                                <label class="control-label" for="file">Falta(n) {{faltante}} Persona(s) por marcar asistencia, si finaliza el proceso quedará como inasistente. si confirma Ingrese el Código de Seguridad y Selecione Aceptar</label>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <center><h4 class="col-lg-6 center center">{{aleatorio}}</h4></center>
                                                    <center><input type="text"  ng-model="verificador" title="Ingresar el N\u00famero, Que Observa a la Izquierda de esta LÃ­nea" required name="Verificador" id="verificador" maxlength="6"></center>
                                                </div>
                                                <div class="row"><br></div>

                                                <input  class="btn btn-primary col-lg-6" type="submit" value ="Aceptar" ng-click="Cerrar()">
                                                <button class="btn btn-default col-lg-6" type="button"  data-dismiss="modal">Cancelar</button>
                                            </div>
                                        </div>


                                        <div  ng-show="validarModal === false">
                                            <div class="form-group">
                                                <label class="control-label center center" for="file">El proceso fue terminado con exito</label>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <br>
                                                </div>
                                                <button class="btn btn-primary col-lg-6 center center" type="button"  data-dismiss="modal">Aceptar</button>
                                            </div>
                                        </div>


                                    </form>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div>

                    <div class="modal fade  bs-example-modal-lg" id="myModalResumen" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header" style="padding: 2%;">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title"><center>Resumen de Asistencia </center></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <table class="table table-responsive">
                                            <thead>
                                                <tr>
                                                    <th>Cargo</th>
                                                    <th>Total Personas</th>
                                                    <th>Asistentes</th>
                                                    <th>Faltantes</th>

                                                </tr>
                                            </thead>
                                            <tbody>

                                                <tr  ng-repeat="resumen in resumencargos">
                                                    <td>{{resumen.nombrecargo}}</td>
                                                    <td>{{resumen.asistio + resumen.noasistio}}</td>
                                                    <td>{{resumen.asistio}} </td>
                                                    <td>{{resumen.noasistio}}</td>
                                                </tr>


                                            </tbody>
                                        </table>

                                    </div>

                                    <div class="form-group">
                                        <button type="button" style="float: right" class="btn btn-default" data-dismiss="modal">Cancelar</button><br>
                                    </div>
                                </div>

                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

