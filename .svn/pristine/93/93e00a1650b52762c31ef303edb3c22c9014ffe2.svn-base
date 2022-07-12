 <div class="content animate-panel" data-ng-init="listar()" >
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">  
                    <form class="form-inline" action="${pageContext.request.contextPath}/Reporte" method="post" target="_blank">

                        <div>
                            <a href="#/Puesto/{{evento.codigoEvento}}/{{departamento.codigo}}/{{municipio.codigoMunicipio}}/{{cargo.codigoCargo}}/{{zonaCodigo}}/{{zonaNombre}}" class="btn btn-sm btn-primary">Regresar</a>
                        </div>
                        
                        <input type="hidden" name="codigodepartamento" value="{{departamento.codigo}}">
                        <input type="hidden" name="codigomunicipio" value="{{municipio.codigoMunicipio}}">
                        <input type="hidden" name="codigozona" value="{{zonaCodigo}}">
                        <input type="hidden" name="codigopuesto" value="{{puestoCodigo}}">
                        <input type="hidden" name="codigoevento" value="{{evento.codigoEvento}}">
                        <input type="hidden" name="titulo" value="LISTA DE ASISTENCIA">
                        <input type="hidden" name="tipoReporte" value="puesto">
                        
                        <h2 class="font-light m-b-xs">
                            Información por Ubicación
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
                            <label class="control-label col-md-4" for="zonaNombre">Zona</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="zonaNombre" id="zonaNombre" class="form-control"  readonly="true">
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombrePuesto">Nombre Puesto</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="puestoNombre" id="nombrePuesto" class="form-control" readonly="true">
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreCargo">Cargo</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="cargo.descripcion" id="nombreCargo" class="form-control"  readonly="true">
                            </div>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="fecha">Fecha</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="capacitacion.fechaCapacitacion" name="fecha" class="form-control"  readonly="true">
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="hora">Hora Inicial</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="capacitacion.horaInicialCapacitacion" name="hora" class="form-control"  readonly="true">
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="salon">Salon</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="capacitacion.salonCapacitacion" name="salon" class="form-control"  readonly="true">
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
                    <th>Ubicación</th>
                    <th>Identificación</th>
                    <th>Apellido1</th>
                    <th>Apellido2</th>
                    <th>Nombre1</th>
                    <td>Nombre2</td>
                    <td>Celular</td>
                    <td>Telefono</td>
                    <td>Asistio</td>
                    <td>Estado</td>
                    <td>Acción</td>
                  </tr>
                  </thead>
                  <tbody>
                    
                    <tr ng-repeat="ubica in ubicaciones | orderBy:'ubicacion' ">
                      <td>{{ubica.ubicacion}}</td>
                      <td>
                          <label style="display:{{ubica.display2}};">{{ubica.empleado.nrodoc}}</label>
                          <input style="display:{{ubica.display}};" type="text" ng-model="ubica.empleado.nrodoc" id="nrodoc" class="form-control">
                      </td>
                      <td>{{ubica.empleado.apellido1}}</td>
                      <td>{{ubica.empleado.apellido2}}</td>
                      <td>{{ubica.empleado.nombre1}}</td>
                      <td>{{ubica.empleado.nombre2}}</td>
                      <td>{{ubica.empleado.celular}}</td>
                      <td>{{ubica.empleado.telefono}}</td>
                      <td>{{ubica.asistio}}</td>
                      <td>{{ubica.estado.descripcion}}</td>

                      <td>
                        <a  style="display:{{ubica.display}};" ng-click="asignar(ubicaciones.indexOf(ubica))" class="btn btn-primary">Asignar</a>
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
