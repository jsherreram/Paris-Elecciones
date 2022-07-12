<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content animate-panel" data-ng-init="listarDepartamentos('${pageContext.request.userPrincipal.name}')">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">
                    <form class="form-inline" >
                        <div>
                            <a href="#/" class="btn btn-sm btn-primary">Regresar</a>
                        </div>
                        
                        <h2 class="font-light m-b-xs">
                            Nombramiento de Personal
                        </h2>
                        
                        <div  form-group col-md-6>
                            <label class="control-label col-md-2" for="nombreEvento">Evento</label>
                            <div class="col-md-4">
                                <input type="text" ng-model="evento.nombre" id="nombreEvento" class="form-control" readonly="true">
                            </div>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <div class="col-md-2">
                                <a href="templates/verArchivo_1.jsp?idEvento={{evento.codigoEvento}}&idDpto=X" class="btn btn-primary">Listado de Personal Programado</a>
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
                    <tr ng-repeat="dpto in departamento">
                      <td>{{dpto.codigoDepartamento}}</td>
                      <td>{{dpto.nombre}}</td>
                      <td>{{dpto.estado0}}</td>
                      <td>{{dpto.estado1}}</td>
                      <td>{{dpto.estado2}}</td>
                      <td>{{dpto.estado3}}</td>
                      <td>{{dpto.estado4}}</td>
                      <td>{{dpto.estado0+dpto.estado1+dpto.estado2+dpto.estado3+dpto.estado4}}</td>
                      <td>{{dpto.noasistio}}</td>
                      <td>
                          <a href="#/Departamental/{{evento.codigoEvento}}/{{dpto.codigoDepartamento}}" class="btn btn-sm btn-default">Ingresar</a>
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
