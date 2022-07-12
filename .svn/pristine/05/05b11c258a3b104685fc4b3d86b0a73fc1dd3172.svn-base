<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content animate-panel" data-ng-init="listar()">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-content">
                    <form class="form-inline">

                        <div class="form-group col-md-6"> 
                            <a ng-show='url_regresar!==""' href="{{url_regresar}}" class="btn btn-sm btn-primary  col-md-3">Regresar</a>
                            <label class="control-label col-md-9">{{titulo}}</label>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <a href="templates/generaSabana.jsp?idDpto={{departamento.codigo}}&idprueba={{evento.prueba.idprueba}}" class="btn btn-sm btn-primary  col-md-3">Generar</a>
                            <label class="control-label col-md-9">Sabana de Información NODO</label>
                        </div>
                        
                        <div class="form-group col-md-6"> 
                            <label class="control-label col-md-2">Prueba:</label>
                            <label class="control-label col-md-4">{{evento.prueba.nombre}}</label>
                        </div>
                        
                        <div class="form-group col-md-6"> 
                            <label class="control-label col-md-2">Evento:</label>
                            <label class="control-label col-md-4">{{evento.nombre}}</label>
                        </div>
                        
                        <div ng-if="(tipoReporte=='N' || tipoReporte == 'A') && tipoConsulta>'1'" class="form-group col-md-6"> 
                            <label class="control-label col-md-2">Cargo:</label>
                            <label class="control-label col-md-4">{{cargo.descripcion}}</label>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-3">Departamento: </label>
                            <label class="control-label col-md-4">{{departamento.nombre}}</label>
                        </div>
                        
                        <div ng-if="tipoConsulta> '3' " class="form-group col-md-6"> 
                            <label class="control-label col-md-2">Municipio:</label>
                            <label class="control-label col-md-4">{{municipio.nombre}}</label>
                        </div>
                    </form> 
                            
                    <h2>{{((items.total.nombrados * 100 /items.total.examinadores)+"").substring(0,4)}}%</h2>
                    
                    <div class="progress">
                      <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar"
                      aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:{{items.total.nombrados * 100 /items.total.examinadores}}%">
                        {{((items.total.nombrados * 100 /items.total.examinadores)+"").substring(0,4)}}%
                      </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
              <div class="panel-body">
                  <div class="table-responsive">
                    <table  WIDTH="600" ng-init="items.total = {}" class="table table-striped table-bordered table-hover" >
                  <thead>
                  <tr>
                    <th WIDTH="30">CODIGO</th>  
                    <th WIDTH="40">DESCRIPCION</th>
                    <td WIDTH="30">PERSONAL REQUERIDO</td>
                    
                    <th ng-show="tipoReporte==='N' || tipoReporte==='NT'" WIDTH="30">NOMBRADO(S)</th>
                    <th ng-show="tipoReporte==='A' || tipoReporte==='AT'" WIDTH="30">ASISTENTE(S)</th>
                    <th ng-show="tipoReporte==='N' || tipoReporte==='NT'" WIDTH="30">PENDIENTE(S)</th>
                    <th ng-show="tipoReporte==='A' || tipoReporte==='AT'" WIDTH="30">AUSENTE(S)</th>
                    <td ng-show="tipoReporte==='N' || tipoReporte==='NT'" WIDTH="440">%NOMBRAMIENTO</td>
                    <td ng-show="tipoReporte==='A' || tipoReporte==='AT'" WIDTH="440">%ASISTENCIA</td>
                    
                  </tr>
                  </thead>
                  <tbody>
                    <tr ng-repeat="reg in registros | orderBy:(tipoReporte==='N' || tipoReporte==='NT')?'avanceconvocatoria':'avanceasistencia'">

                      <td><a href="{{url_detalle+reg.codigo}}">{{reg.codigo}}</a></td>
                      <td><a href="{{url_detalle+reg.codigo}}">{{reg.nombre}}</a></td>
                      <td ng-init="items.total.examinadores = items.total.examinadores + reg.total">{{reg.total}}</td>
                      
                      <td>
                          <div ng-init="(tipoReporte=='N' || tipoReporte=='NT')? items.total.nombrados = items.total.nombrados + reg.convocado_2 : items.total.nombrados = items.total.nombrados + reg.asistio ">
                            {{(tipoReporte=='N' || tipoReporte=='NT')?reg.convocado_2:reg.asistio}}
                          </div>
                      </td>

                      <td>
                          <div ng-init="(tipoReporte=='N' || tipoReporte=='NT')? items.total.pendientes = items.total.pendientes + ((reg.total-reg.convocado_2 < 0)?0:reg.total-reg.convocado_2) : items.total.pendientes = items.total.pendientes + ((reg.total-reg.asistio < 0)?0:reg.total-reg.asistio)">
                            <!--{{(tipoReporte=='N' || tipoReporte=='NT')?reg.pendiente:reg.total- reg.asistio}}-->
                            {{(tipoReporte=='N' || tipoReporte=='NT')?((reg.total-reg.convocado_2 < 0)?0:reg.total-reg.convocado_2):(reg.total- reg.asistio < 0)?0:reg.total- reg.asistio}}
                          </div>
                      </td>
                      
                      <td>
                        <div class="progress">
                          <div class="progress-bar {{(tipoReporte=='N' || tipoReporte=='NT')?reg.avanceconvocatoria>='100'?'':'progress-bar-warning':reg.avanceasistencia>='100'?'':'progress-bar-warning'}} progress-bar-striped active" role="progressbar"
                                aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:{{(tipoReporte=='N' || tipoReporte=='NT')?reg.avanceconvocatoria:reg.avanceasistencia}}%">
                            {{(tipoReporte=='N' || tipoReporte=='NT')?reg.avanceconvocatoria:reg.avanceasistencia}}%
                          </div>
                        </div>                  
                      </td>
                      
                    </tr>
                    <tr>
                        <td>Total</td>
                        <td>Total</td>
                        <td>{{items.total.examinadores}}</td>
                        <td>{{items.total.nombrados}}</td>                        
                        <td>{{items.total.pendientes}}</td>
                    </tr>
                  </tbody>
                </table>
                  </div>
              </div>
            </div>
        </div>
    </div>
</div>