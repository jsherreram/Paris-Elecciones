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

  <div class="content animate-panel" data-ng-init="listar()">
        <form class="form-inline" action="${pageContext.request.contextPath}/Reporte" method="post" target="_blank">
                <input type="hidden" name="tipoReporte" value="{{tipoReporte}}">
                <input type="hidden" name="codigoevento" value="{{idEvento}}">
                <input type="hidden" name="codigopuesto" value="{{codigoSitio}}">
                <input type="hidden" name="prueba" value="{{idprueba}}">
                
                <div>
                    <input class="btn btn-primary" type="submit" ng-click="setTipoReporte('asistenciaPuesto')" value ="Imprimir Lista de Asistencia">
                    
                    
                    <input class="btn btn-primary" type="submit" ng-click="setTipoReporte('planillaPagoRps')" value ="Planilla de Pagos">
                    
                    <a href="${pageContext.request.contextPath}/panel/ReportarEncuesta/main.jsp#/Encuesta/{{idEvento}}" class="btn btn-primary" >Diligenciar Encuesta</a>
                    
                    <a data-toggle="modal" data-target="#myModalS" ng-click="CalcularParaCerrar()" class="btn btn-primary">Cerrar Asistencia</a>
                    
                    <label class="control-label" for="nombrePuesto">{{puestoNombre}}</label>
                    <input type="text" ng-model="nombreEvento" id="nombreEvento" class="form-control" readonly="true">
                </div>
        </form>   
    </div>
    
  <accordion close-others="TwoAtATime">
    <accordion-group is-open="true">
        <accordion-heading>
            Tomar Asistencia <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': isopen, 'glyphicon-chevron-right': !isopen}"></i>
        </accordion-heading>
            <div class="row">
                <div class="col-lg-12">
                    <div class="hpanel">
                        <div class="panel-body">    
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
                                            
                                            <div ng-show="sinBiometrico===true" class="form-group">
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
                                        <td><DIV  id="inside"></DIV>  <div ng-show="mostrarBotonAsistenciaManual===true" > <a class="btn btn-primary"  ng-click="asistenciaManual()">Marcar Asistencia Manual</a>  </div> </td>
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
                                            <span ng-bind="'Cargo:'+ asignacion.descripcion"></span>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <span ng-bind="'Salón:' +  asignacion.salon"></span>
                                        </td>
                                        <td></td>
                                        <td>
                                            <label> {{totalAsistentes + ' de '+ totalRegistros}} </label>
                                            <a data-toggle="modal" data-target="#myModalResumen" ng-click="CalcularParaCerrar()" class="btn btn-primary btn-xs">Ver detalle...</a>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td style="width:30%;"></td>
                                        <td style="width:20%;"></td>
                                        <td style="width:50%;">
                                            <div class="progress">
                                              <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar"
                                              aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:{{totalAsistentes * 100 /totalRegistros}}%">
                                                {{((totalAsistentes * 100 /totalRegistros)+"").substring(0,4)}}%
                                              </div>
                                            </div>
                                        </td>
                                    </tr>
                                    

                                </table>    
                            </form>        
                        </div>
                    </div>
                </div>
            </div>
    </accordion-group>
    
      
    <accordion-group is-open="true">
      
        <accordion-heading>
            Listado de Personal <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': isopen, 'glyphicon-chevron-right': !isopen}"></i> 
        </accordion-heading>
    

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-primary">
                      <div class="panel-body">
                          <div class="table-responsive">
                            <table  WIDTH="600" class="table table-bordered table-hover" >
                          <thead>
                          <tr>
                            <th>Cargo</th>
                            <th>Identificación</th>
                            <th>Apellido1</th>
                            <th>Apellido2</th>
                            <th>Nombre1</th>
                            <td>Nombre2</td>
                            <td>Celular</td>
                            <td>Salón</td>
                            <td>Acción</td>
                          </tr>
                          </thead>
                          <tbody>
                              <tr style="color:#111" ng-style="ubica.asistio === 1 ? { 'background-color':'#FFFFFF'} : {'background-color':'#F2DEDE'}" ng-repeat="ubica in ubicaciones | orderBy:'descripcion' ">
                              
                              <td >{{ubica.descripcion}}</td>
                              <td>{{ubica.nrodoc}}</td>
                              <td>{{ubica.apellido1}}</td>
                              <td>{{ubica.apellido2}}</td>
                              <td>{{ubica.nombre1}}</td>
                              <td>{{ubica.nombre2}}</td>
                              <td>
                                  {{ubica.celular}}
                              </td>
                              <td>{{ubica.salon}}</td>
                              
                              <td>
                                 <a href="#/" pop-over data-trigger="focus" opciones="[{'name':'Asignar','operacion':'Asignar','id': ubica.id},{'name':'Cambiar Cargo ó Salón','operacion':'CambiarCargo','id': ubica.id},{'name':'Asignar Suplencia','operacion':'AsignaSuplente','id': ubica.id},{'name':'Des-asignar','operacion':'Desasignar','id': ubica.id},{'name':'Editar','operacion':'Editar','id': ubica.id}]" title="Opciones">+</a>
                              </td>
                             </tr>
                          </tbody>
                        </table>
                       </div>
                              
                      </div>
                    </div>
                </div>
            </div>
        
    </accordion-group>
  </accordion>
    
  <div class="modal fade" id="myModalS" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                
                <div ng-show="validarModal===true" class="modal-header" style="padding: 2%;">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <center><h4 class="modal-title">ADVERTENCIA</h4></center>
                </div>

                <div ng-show="validarModal===false" class="modal-header" style="padding: 2%;">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <center><h4 class="modal-title">INFORMACIÃ“N</h4></center>
                </div>
                
                
                <div class="modal-body">
                    <form  method="post" enctype="multipart/form-data" >
                        
                        <div ng-show="validarModal===true">
                            <div class="form-group">
                                <label class="control-label" for="file">Falta(n) {{faltante}} Persona(s) por marcar asistencia, si finaliza el proceso quedará como inasistente. si confirma Ingrese el Código de Seguridad y Selecione Aceptar</label>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <center><h4 class="col-lg-6 center center">{{aleatorio}}</h4></center>
                                    <center><input type="text"  ng-model="verificador" title="Ingresar el NÃºmero, Que Observa a la Izquierda de esta LÃ­nea" required name="Verificador" id="verificador" maxlength="6"></center>
                                </div>
                                <div class="row"><br></div>
                                
                                <input  class="btn btn-primary col-lg-6" type="submit" value ="Aceptar" ng-click="Cerrar()">
                                <button class="btn btn-default col-lg-6" type="button"  data-dismiss="modal">Cancelar</button>
                            </div>
                        </div>
                        

                        <div  ng-show="validarModal===false">
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
    </div><!-- /.modal -->

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

                              <tr  ng-repeat="resumen in resumencargos ">
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
    </div><!-- /.modal -->
    
</div>

<script type="text/ng-template" id="popover_template.html">
<ul class="nav nav-pills nav-stacked">
<li ng-repeat='opcion in opciones'>
<a href="{{'#/'+opcion.operacion+'/'+opcion.id}}">{{opcion.name}}</a>
</li>
</ul>
</script>
