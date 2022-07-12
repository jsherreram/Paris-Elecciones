 <div class="content animate-panel" data-ng-init="listar()" >
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">  
                    <form class="form-inline" action="${pageContext.request.contextPath}/Reporte" method="post" target="_blank">

                        <div>
                            <a href="{{url_regresar}}" class="btn btn-sm btn-primary">Regresar</a>
                        </div>
                        
                        
                        <h2 class="font-light m-b-xs">
                            {{(tipoReporte=='N' || tipoReporte=='NT')?'Nombramiento por Sitio':'Asistencia por sitio'}}
                        </h2>

                        <div class="form-group col-md-6"> 
                            <label class="control-label col-md-4" for="nombreEvento">Evento</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="evento.nombre" name="nombreEvento" class="form-control"  readonly="true">
                            </div>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreDepartamento">Departamento</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="departamento.nombre" name="nombreDepartamento" class="form-control"  readonly="true">
                            </div>
                        </div>
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
                    <th>Cargo</th>  
                    <th>Ubicación</th>
                    <th>Identificación</th>
                    <th>Apellido1</th>
                    <th>Apellido2</th>
                    <th>Nombre1</th>
                    <td>Nombre2</td>
                    <td>Celular</td>
                    <td>{{(tipoReporte=='N' || tipoReporte=='NT')?'Estado':'Asistio'}}</td>
                  </tr>
                  </thead>
                  <tbody>
                    
                    <tr ng-repeat="reg in registros | orderBy:'ubicacion' ">
                      <td>{{reg.cargo}}</td>
                      <td>{{reg.ubicacion}}</td>
                      <td>{{reg.nrodoc}}</td>
                      <td>{{reg.apellido1}}</td>
                      <td>{{reg.apellido2}}</td>
                      <td>{{reg.nombre1}}</td>
                      <td>{{reg.nombre2}}</td>
                      <td>{{reg.celular}}</td>

                      <td ng-show="tipoReporte==='N' || tipoReporte==='NT'" >{{reg.descripcion}}</td>
                      
                      <td ng-show="tipoReporte==='A' || tipoReporte==='AT'">{{reg.asistio==1?'SI':'NO'}}</td>
                      
                    </tr>
                  </tbody>
                </table>
                  </div>
              </div>
            </div>
        </div>
    </div>
</div>
