<div class="content animate-panel">

    <div class="row">
        <div class="col-md-10">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3>Toma de Asistencia Biométrica </h3>
                </div>
                <div class="panel-body">
                    <div class="form-group col-md-10">
                        <select class="form-control"  ng-model="controller.evento" ng-change="controller.buscar()">
                            <option ng-repeat="evento in controller.eventos" value="{{evento.codigoEvento}}">{{evento.nombre}}</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="input-group col-md-10">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                        <input type="text" class="form-control" placeholder="Buscar" ng-model="search">

                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <tr>
                            <th>Codigo Sitio</th>
                            <th>Nombre</th>
                            <th>% Asistencia</th>
                            <th> Cerrado</th>
                            <th> Opción </th>
                        </tr>
                        <tr ng-repeat="s in controller.sitios| filter:search">
                            <td> {{s.codigoPuesto}}</td>
                            <td>{{s.nombrePuesto}}</td>
                            <td>
                                <div class="progress">
                                    <div class="progress-bar {{((s.totalAsistencia * 100 / s.total) + '').substring(0, 4) >= 100 ? '' : 'progress-bar-warning'}} progress-bar-striped active" role="progressbar"
                                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:{{((s.totalAsistencia * 100 / s.total) + '').substring(0, 4)}}%">
                                        {{((s.totalAsistencia * 100 / s.total) + "").substring(0, 4)}}%
                                    </div>
                                </div>
                            </td>
                            <td>
                        <center><span  ng-show="s.cerrado == 'SI'" class="glyphicon glyphicon-lock" aria-hidden="true"></span></center>
                        </td>

                        <td>
                            <div ng-if="s.cerrado=='NO'">
                                <a href="${pageContext.request.contextPath}/panel/AdministraAsistencia/main.jsp#/monitoreo/{{s.idDivipol}}/{{controller.evento}}"  class="btn btn-primary" >Tomar Asistencia</a>
                            </div>
                        </td>

                        </tr>
                    </table>
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
