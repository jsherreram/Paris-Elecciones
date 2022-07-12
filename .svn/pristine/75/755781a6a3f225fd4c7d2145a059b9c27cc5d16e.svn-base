<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">                   
                    <h2 class="font-light m-b-xs">
                        Cancelar Sesiones Cerradas por Sitio.
                    </h2>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="table-responsive">
                    <table  class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Nodo</th>
                            <th>Municipio</th>
                            <th>Cod. Puesto</th>
                            <th>Nombre Puesto</th>
                            <th>Cedula</th>
                            <th>RPS</th>
                            <th>Fecha Cierre</th>
                            <th>Evento</th>
                            <th>Fecha Evento</th>
                            <th>Reversar</th>
                            
                        </tr>
                        </thead>
                        <tbody>
                            <tr class="gradeX" ng-repeat="reg in cierresdesesion ">
                                <td>{{reg.nombreDepartamento}}</td>
                                <td>{{reg.nombreMunicipio}}</td>
                                <td>{{reg.codigopuesto}}</td>
                                <td>{{reg.nombrePuesto}}</td>
                                <td>{{reg.nrodoc}}</td>
                                <td>{{reg.RPS}}</td>
                                <td>{{reg.fecha_actualiza}}</td>
                                <td>{{reg.nombre}}</td>
                                <td>{{reg.fecha}}</td>
                                <td><div ng-if="reg.estado == 0">
                                        <button type="button" class="btn btn-primary" ng-click="confirm(reg)">Reversar</button>
                                    </div>
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
