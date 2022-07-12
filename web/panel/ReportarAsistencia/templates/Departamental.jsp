 <div class="content animate-panel" data-ng-init="listarMunicipios()">
    
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">                   
                    <form class="form-inline" action="${pageContext.request.contextPath}/CuentaCobro" method="post" target="_blank">
                        <div>
                            <a href="#/Nacional/{{evento.codigoEvento}}" class="btn btn-sm btn-primary">Regresar</a>
                        </div>

                        <input type="hidden" name="codigoDepartamento" value="{{departamento.codigo}}">
                        <input type="hidden" name="codigoEvento" value="{{evento.codigoEvento}}">
                        <input type="hidden" name="tipoReporte" value="departamental">
                        
                        <h2 class="font-light m-b-xs">
                            Información por Municipio
                        </h2>
                        <div form-group col-md-6>
                            <label class="control-label col-md-2" for="nombreEvento">Evento</label>
                            <div class="col-md-4">
                                <input type="text" ng-model="evento.nombre" id="nombreEvento" class="form-control" readonly="true">
                            </div>
                        </div>
                        
                        <div form-group col-md-6>
                            <label class="control-label col-md-2" for="nombreDepartamento">Departamento</label>
                            <div class="col-md-4">
                                <input type="text" ng-model="departamento.nombre" id="nombreDepartamento" class="form-control" readonly="true">
                            </div>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <div class="col-md-2">
                                <input style="display:{{display3}};" class="btn btn-primary" type="submit" value ="Cuentas de Cobro Monitor RPE">
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
                    <th>codigo</th>
                    <th>nombre</th>
                    <th>Vacante(s)</th>
                    <th>Programado(s)</th>
                    <th>En Auditoria</th>
                    <td>Inconsistente(s)</td>
                    <td>Revisado(s)</td>
                    <td>Total</td>
                    <td>Ausente(s)</td>
                    <td>AcciÃ³n</td>
                  </tr>
                  </thead>
                  <tbody>
                    <tr ng-repeat="mpio in municipio">
                      <td>{{mpio.codigoMunicipio}}</td>
                      <td>{{mpio.nombre}}</td>
                      <td>{{mpio.estado0}}</td>
                      <td>{{mpio.estado1}}</td>
                      <td>{{mpio.estado2}}</td>
                      <td>{{mpio.estado3}}</td>
                      <td>{{mpio.estado4}}</td>
                      <td>{{mpio.estado0+mpio.estado1+mpio.estado2+mpio.estado3+mpio.estado4}}</td>
                      <td>{{mpio.noasistio}}</td>
                      <td>
                          <a href="#/Municipal/{{evento.codigoEvento}}/{{departamento.codigo}}/{{mpio.codigoMunicipio}}" class="btn btn-sm btn-default">Ingresar</a>
                      </td>
                    </tr>
                  </tbody>
                </table>
                  </div>
              </div>
            </div>
        </div>
    </div>
</div>
