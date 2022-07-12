 <div class="content animate-panel" data-ng-init="listar()" >
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">  
                    <form class="form-inline" action="${pageContext.request.contextPath}/Reporte" method="post" target="_blank">

                        <div>
                            <a href="{{url_regresar}}" class="btn btn-sm btn-primary">Regresar</a>
                        </div>

                        <div ng-if="tipoReporte!='S'">
                            <h2 class="font-light m-b-xs">
                                {{(tipoReporte=='N' || tipoReporte=='NT')?'Nombramiento por Sitio':'Asistencia por sitio'}}
                            </h2>
                            
                            <div class="form-group col-md-6"> 
                                <label class="control-label col-md-4" for="nombreEvento">Evento</label>
                                <div class="col-md-2">
                                    <input type="text" ng-model="evento.nombre" name="nombreEvento" class="form-control"  readonly="true">
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreDepartamento">Departamento</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="departamento.nombre" name="nombreDepartamento" class="form-control"  readonly="true">
                            </div>
                        </div>
                        
                        <div ng-if="tipoReporte!='S'">
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4" for="nombreMunicipio">Municipio</label>
                                <div class="col-md-2">
                                    <input type="text" ng-model="municipio.nombre" name="nombreMunicipio" class="form-control"  readonly="true">
                                </div>
                            </div>
                        
                            <div class="form-group col-md-6">
                                <label class="control-label col-md-4" for="nombrePuesto">Puesto</label>
                                <div class="col-md-2">
                                    <input type="text" ng-model="codigoPuesto" id="nombrePuesto" class="form-control" readonly="true">
                                </div>
                            </div>
                        </div>

                     </form>   
                </div>
            </div>
        </div>
    </div>
    
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
              <div class="panel-body">
                  <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" >
                  <thead>
                    <tr>
                      <td>{{(tipoReporte=='S')?'Puesto':'Cargo'}}</td>
                      <td>{{(tipoReporte=='S')?'Código Puesto':'Ubicación'}}</td>
                      <td>{{(tipoReporte=='S')?'Dirección':'Identificación'}}</td>
                      <td>{{(tipoReporte=='S')?'Rector':'Apellido1'}}</td>
                      <td>{{(tipoReporte=='S')?'Telefono':'Apellido2'}}</td>
                      <td>{{(tipoReporte=='S')?'Municipio':'Nombre1'}}</td>
                      <td>{{(tipoReporte=='S')?'Estado':'Nombre2'}}</td>
                      <td>{{(tipoReporte=='S')?'Examinandos':'Celular'}}</td>

                      <td ng-if="tipoReporte=='S'">Email</td>
                      <td ng-if="tipoReporte!='S'">{{(tipoReporte=='N' || tipoReporte=='NT')?'':'Asistio'}}</td>
                    </tr>
                  </thead>
                  <tbody>
                    <tr ng-repeat="reg in registros | orderBy:(tipoReporte==='S')?'ordenamiento':'ubicacion'"> 
                              <td ng-if="tipoReporte!='S'">{{reg.cargo}}</td>
                              <td ng-if="tipoReporte!='S'">{{reg.ubicacion}}</td>
                              <td ng-if="tipoReporte!='S'">{{reg.nrodoc}}</td>
                              <td ng-if="tipoReporte!='S'">{{reg.apellido1}}</td>
                              <td ng-if="tipoReporte!='S'">{{reg.apellido2}}</td>
                              <td ng-if="tipoReporte!='S'">{{reg.nombre1}}</td>
                              <td ng-if="tipoReporte!='S'">{{reg.nombre2}}</td>
                              <td ng-if="tipoReporte!='S'">{{reg.celular}}</td>
                              <td ng-show="tipoReporte==='N' || tipoReporte==='NT'"></td>
                              <td ng-show="tipoReporte==='A' || tipoReporte==='AT'">{{reg.asistio==1?'SI':'NO'}}</td>
                              
                              <td ng-if="tipoReporte=='S'">{{reg.nombrePuesto}}</td>
                              <td ng-if="tipoReporte=='S'">{{reg.codigoPuesto}}</td>
                              <td ng-if="tipoReporte=='S'">{{reg.direccionPuesto}}</td>
                              <td ng-if="tipoReporte=='S'">{{reg.nombreRector}}</td>
                              <td ng-if="tipoReporte=='S'">{{reg.telefono}}</td>
                              <td ng-if="tipoReporte=='S'">{{reg.nombreMunicipio}}</td>
                              <td ng-if="tipoReporte=='S'">{{reg.EstadoSitio}}</td>
                              <td ng-if="tipoReporte=='S'">{{reg.nExaminandos}}</td>
                              <td ng-if="tipoReporte=='S'">{{reg.email}}</td>
                    </tr>
                  </tbody>
                </table>
                  </div>
              </div>
            </div>
        </div>
    </div>
</div>
