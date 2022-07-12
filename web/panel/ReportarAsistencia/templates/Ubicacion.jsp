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
                        <input type="hidden" name="tipoReporte" value="{{tipoReporte}}">
                        
                        <h2 class="font-light m-b-xs">
                            Informaci�n por Sitio
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
                      
                    <div>
                        <a href="#/seleccionarArchivo/{{departamento.codigo}}/{{municipio.codigoMunicipio}}/{{zonaCodigo}}/{{puestoCodigo}}/{{cargo.codigoCargo}}/{{evento.codigoEvento}}" class="btn btn-primary">Adjuntar Lista de Asistencia</a>
                    </div>
                      
                      
                  <tr>
                    <th>fecha</th>
                    <td>Acci�n</td>
                  </tr>
                  </thead>
                  <tbody>
                    
                    <tr ng-repeat="soporte in soportes | orderBy:'fecha' ">
                      <td>{{soporte.fecha}}</td>
                      <td><a href="templates/verArchivo.jsp?id={{soporte.id}}&idDpto={{soporte.municipio.departamento.codigo}}" class="btn btn-primary">Ver Pdf</a></td>
                    </tr>
                  </tbody>
                </table>
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
                    <table class="table table-striped table-bordered table-hover" >
                  <thead>
                  <tr>
                    <th>Ubicaci�n</th>
                    <th>Identificaci�n</th>
                    <th>Apellido1</th>
                    <th>Apellido2</th>
                    <th>Nombre1</th>
                    <td>Nombre2</td>
                    <td>Celular</td>
                    <td>Asistio</td>
                    <td>Acci�n</td>
                  </tr>
                  </thead>
                  <tbody>
                    
                    <tr ng-repeat="ubica in ubicaciones | orderBy:'ubicacion' ">
                      <td>{{ubica.ubicacion}}</td>
                      <td>{{ubica.empleado.nrodoc}}</td>
                      <td>{{ubica.empleado.apellido1}}</td>
                      <td>{{ubica.empleado.apellido2}}</td>
                      <td>{{ubica.empleado.nombre1}}</td>
                      <td>{{ubica.empleado.nombre2}}</td>
                      <td>{{ubica.empleado.celular}}</td>
                      
                      <td>
                         <input type="checkbox" ng-model="ubica.asistio">
                      </td>
                      
                      <td>
                          <a ng-click="asignar(ubicaciones.indexOf(ubica))" class="btn btn-primary" >Guardar</a>
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
