<div class="content animate-panel" >
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3> Generar Archivos</h3>
                </div>
                <div class="panel-body">

                    <form >

                        <div class="form-group col-md-6" >
                            <label style="color: #f22">*</label>
                            <label id="departamento" data-toggle="popover" data-content="El departamento es requerido">Departamento: </label>
                            <select ng-model="controller.departamento" class="form-control" >
                                <option value="" selected="true">Seleccionar</option>
                                <option ng-repeat="dpto in controller.departamentos" value="{{dpto.codigo}}" >{{dpto.nombre}}</option> 
                            </select>
                        </div>

                        <div class="form-group col-md-6">
                            <br> 
                            
                            <input class="btn btn-primary" id="export" type="button" value="Exportar" style="float: right;margin-right: 5px;" ng-click="controller.exportPagos()">

                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class='row'>
        <div class="col-md-12">
            <iframe class="embed-responsive-item"  width="100%" height="600"  frameborder="0" allowfullscreen ng-src="{{content}}"></iframe>
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
                        Generando...
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>