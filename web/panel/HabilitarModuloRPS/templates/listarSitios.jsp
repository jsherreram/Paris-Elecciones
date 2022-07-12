<div class="content animate-panel">

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3>Ver Módulo RPS </h3>
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
                            <th>Opciones</th>
                        </tr>
                        <tr ng-repeat="s in controller.sitios| filter:search">
                            <td> {{s.sitio.codigoSitio}}</td>
                            <td>{{s.sitio.nombreSitio}}</td>
                            <td  ng-show="s.usuario !== 0">
                                <a href="${pageContext.request.contextPath}/panel/AdministraAsistencia/main.jsp#/{{s.usuario}}"  class="btn btn-primary" >Ver Módulo RPS</a>

                            </td>
                            <td  ng-show="s.usuario === 0">
                                <span>RPS sin asignar</span>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

    </div>     

</div>
