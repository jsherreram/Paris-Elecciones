<div class="content animate-panel">
    <div class="row">
        <div class="col-md-10">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3>Fecha Ultima Actualización en el Equipo del Representante ASD</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row" id="divBusqueda">
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="input-group col-md-12">
                        <div class="form-group col-md-3">
                            <label>Departamento: </label>
                            <select  class="form-control" name="filtroDepartamento" ng-model="search.nombreDepartamento">
                                <option value="">Seleccione</option>
                                <option ng-repeat="departamento in departamentoArray" value="{{departamento}}" >{{departamento}}</option> 
                            </select>
                        </div>
                        <div class="form-group col-md-3">
                            <label>Municipio: </label>
                            <select  class="form-control" name="filtroMunicipio" ng-model="search.nombreMunicipio">
                                <option value="">Seleccione</option>
                                <option ng-repeat="municipio in municipioArray" value="{{municipio}}" >{{municipio}}</option> 
                            </select>
                        </div>
                        <div class="form-group col-md-3">
                            <label>Estado: </label>
                            <select  class="form-control" name="estado" ng-model="search.fechadia">
                                <option value="">Seleccione</option>
                                <option ng-repeat="estado in estadoArray" value="{{estado}}" >{{estado}}</option> 
                            </select>
                        </div>
                        <!--<div class="form-group col-md-3">
                            <div class="panel-heading">
                                <h3>Total Regsitros {{filas}}</h3>
                            </div>
                        </div>-->
                    </div>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped table-hover width200">
                            <thead>
                                <tr>
                                    <th>Municipio</th>
                                    <th>Cod. Puesto</th>
                                    <th>Nombre Puesto</th>
                                    <th>Cedula</th>
                                    <th>RPS</th>
                                    <th>Teléfono</th>
                                    <th>Ultima Fecha Actualización</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr dir-paginate="reg in ultima_actualizacion | filter:search | itemsPerPage: pageSize " current-page="currentPage" pagination-id="pagStatus">
                                    <td>{{reg.nombreMunicipio}}</td>
                                    <td>{{reg.codigopuesto}}</td>
                                    <td>{{reg.nombrePuesto}}</td>
                                    <td>{{reg.nrodoc}}</td>
                                    <td>{{reg.RPS}}</td>
                                    <td>{{reg.telefono}}</td>
                                    <td>{{reg.ultimo_mes}}</td>
                                </tr>
                            </tbody>
                    </table>
                    </div>
                    <center>
                        <dir-pagination-controls boundary-links="true" on-page-change="pageChangeHandler(newPageNumber)" template-url="/Paris/templates/directives/dirPagination.tpl.html" pagination-id="pagStatus"></dir-pagination-controls>
                    </center>
            </div>
        </div>
    </div>
    </div>
</div>

<div class="modal fade" id="modalLoading" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                Paris - Elecciones
            </div>
            <div class="modal-body">
                <div class="progress">
                    <div class="progress-bar progress-bar-striped progress-info active" role="progressbar"
                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%">
                        Cargando...
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>