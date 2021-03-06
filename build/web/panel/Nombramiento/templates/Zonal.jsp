 <div class="content animate-panel" data-ng-init="listarCargosZona()">
    
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                    <div class="panel-body">                   
                        <form class="form-inline"  action="${pageContext.request.contextPath}/CarneCargo" method="post" target="_blank">
                            <div>
                                <a href="#/Municipal/{{evento.codigoEvento}}/{{departamento.codigo}}/{{municipio.codigoMunicipio}}" class="btn btn-sm btn-primary">Regresar</a>
                            </div>
                            
                            <input type="hidden" name="codigoDepartamento" value="{{departamento.codigo}}">
                            <input type="hidden" name="codigoMunicipio" value="{{municipio.codigoMunicipio}}">
                            <input type="hidden" name="codigoCargo" value="{{cargo.codigoCargo}}">
                            <input type="hidden" name="codigoEvento" value="{{evento.codigoEvento}}">
                            
                            
                            <h2 class="font-light m-b-xs">
                                Información por Zonas
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
                            <div form-group col-md-6>
                                <label class="control-label col-md-2" for="nombreMunicipio">Municipio</label>
                                <div class="col-md-4">
                                    <input type="text" ng-model="municipio.nombre" id="nombreMunicipio" class="form-control" readonly="true">
                                </div>
                            </div>
                            <div form-group col-md-6>
                                <label class="control-label col-md-2" for="nombreCargo">Cargo</label>
                                <div class="col-md-4">
                                    <input type="text" ng-model="cargo.descripcion" id="nombreCargo" class="form-control" readonly="true">
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
                    <td>Acción</td>
                  </tr>
                  </thead>
                  <tbody>
                    <tr ng-repeat="zona in zonas">
                      <td>{{zona.codigoZona}}</td>
                      <td>{{zona.nombreZona}}</td>
                      <td>{{zona.estado0}}</td>
                      <td>{{zona.estado1}}</td>
                      <td>{{zona.estado2}}</td>
                      <td>{{zona.estado3}}</td>
                      <td>{{zona.estado4}}</td>
                      <td>{{zona.estado0+zona.estado1+zona.estado2+zona.estado3+zona.estado4}}</td>
                      <td>{{zona.noasistio}}</td>
                      <td>
                          <a href="#/Puesto/{{evento.codigoEvento}}/{{departamento.codigo}}/{{municipio.codigoMunicipio}}/{{cargo.codigoCargo}}/{{zona.codigoZona}}/zz-{{zona.nombreZona}}" class="btn btn-sm btn-default">Ingresar</a>
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
