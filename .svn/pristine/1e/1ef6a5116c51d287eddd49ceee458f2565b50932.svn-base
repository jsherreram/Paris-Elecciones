 <div class="content animate-panel" data-ng-init="listarPuesto()">
    
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">                   
                    <form class="form-inline" action="${pageContext.request.contextPath}/CuentaCobro" method="post" target="_blank">

                        <div>
                            <a href="#/Municipal/{{evento.codigoEvento}}/{{departamento.codigo}}/{{municipio.codigoMunicipio}}" class="btn btn-sm btn-primary">Regresar</a>
                        </div>
                        
                        <input type="hidden" name="codigoDepartamento" value="{{departamento.codigo}}">
                        <input type="hidden" name="codigoMunicipio" value="{{municipio.codigoMunicipio}}">
                        <input type="hidden" name="codigoCargo" value="{{cargo.codigoCargo}}">
                        <input type="hidden" name="codigoEvento" value="{{evento.codigoEvento}}">
                        <input type="hidden" name="tipoReporte" value="puesto">
                        
                        
                        <h2 class="font-light m-b-xs">
                            Informaci�n por Puestos
                        </h2>
                        <div class="form-group col-md-6"> 
                            <label class="control-label col-md-4" for="nombreEvento">Evento</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="evento.nombre" id="nombreEvento" class="form-control"  readonly="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreDepartamento">Departamento</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="departamento.nombre" id="nombreDepartamento" class="form-control"  readonly="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreMunicipio">Municipio</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="municipio.nombre" id="nombreMunicipio" class="form-control"  readonly="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="zonaNombre">Zona</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="zonaNombre" id="zonaNombre" class="form-control"  readonly="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreCargo">Cargo</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="cargo.descripcion" id="nombreCargo" class="form-control"  readonly="true">
                            </div>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <div class="col-md-2">
                                <input class="btn btn-primary" type="submit" value ="Cuentas de Cobro">
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
                    <th>dirección</th>
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
                    <tr ng-repeat="puesto in puestos">
                      <td>{{puesto.codigoPuesto}}</td>
                      <td>{{puesto.nombrePuesto}}</td>
                      <td>{{puesto.direccionPuesto}}</td>
                      <td>{{puesto.estado0}}</td>
                      <td>{{puesto.estado1}}</td>
                      <td>{{puesto.estado2}}</td>
                      <td>{{puesto.estado3}}</td>
                      <td>{{puesto.estado4}}</td>
                      <td>{{puesto.estado0+puesto.estado1+puesto.estado2+puesto.estado3+puesto.estado4}}</td>
                      <td>{{puesto.noasistio}}</td>
                      <td>
                          <a href="#/Ubicacion/{{evento.codigoEvento}}/{{departamento.codigo}}/{{municipio.codigoMunicipio}}/{{cargo.codigoCargo}}/{{zonaCodigo}}/{{zonaNombre}}/{{puesto.codigoPuesto}}/-{{puesto.nombrePuesto}}" class="btn btn-sm btn-default">Ingresar</a>
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
