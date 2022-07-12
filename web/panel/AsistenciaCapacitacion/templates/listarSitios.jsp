<div class="content animate-panel">

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3>Sitios de Capacitación</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group col-md-10">
                        <select class="form-control"  ng-model="controller.evento" ng-change="controller.buscar()">
                            <option ng-repeat="evento in controller.eventos" value="{{evento.codigoEvento}}">{{evento.fecha|date:'dd/MM/yyyy'}} {{evento.cargo.descripcion}} {{evento.hora_inicial}} {{evento.nombre}}</option>
                        </select>
                    </div>
               
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="input-group col-md-12">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                        <input type="text" class="form-control" placeholder="Buscar" ng-model="search">

                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <tr>
                            <th>Codigo Sitio</th>
                            <th>Nombre</th>
                            <th>Opcion</th>
                        </tr>
                        <tr ng-repeat="s in controller.sitios| filter:search">
                            <td> {{s.codigoSitio}}</td>
                            <td>{{s.nombreSitio}}</td>
                            <td><a class="btn btn-primary" href="/Paris/panel/AsistenciaCapacitacion/main.jsp#/ver/{{s.id}}/{{controller.evento}}">Asistencia</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

    </div>     

</div>
