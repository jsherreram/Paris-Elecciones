<div class="content animate-panel" data-ng-init="listarEventos()" >
    <div class="row">
        <div class="col-md-10">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3> Generar Cuentas De Cobro </h3>
                </div>
                <div class="panel-body">

                    <form name="formGenerar" ng-submit="controller.generarCuentas()" method="post" target="_blank">

                        <div>
                            <div class="form-group col-md-6" ng-if="controller.rol !== 'coordinador'">
                                <label style="color: #f22">*</label>
                                <label id="departamento" data-toggle="popover" data-content="El departamento es requerido">Departamento: </label>
                                <select ng-model="controller.departamento" class="form-control">
                                    <option value="" selected="true">Seleccionar</option>
                                    <option ng-repeat="dpto in controller.departamentos" value="{{dpto.codigo}}" >{{dpto.nombre}}</option> 
                                </select>
                            </div>
                            <div class="form-group col-md-6" ng-if="controller.rol === 'coordinador'">
                                <label style="color: #f22">*</label>
                                <label id="departamento" data-toggle="popover" data-content="El departamento es requerido">Departamento:</label>
                                <select ng-model="controller.departamento" class="form-control" disabled>
                                    <option value="" selected="true">Seleccionar</option>
                                    <option ng-repeat="dpto in controller.departamentos" value="{{dpto.codigo}}" >{{dpto.nombre}}</option> 
                                </select>
                            </div>
                            <div class="form-group col-md-6">
                                <label style="color: #f22">*</label>
                                <label>Municipio: </label>
                                <select-municipio municipio="controller.municipio" departamentocodigo="controller.departamento" requerido="true"></select-municipio>

                            </div>
                            <div class="form-group col-md-6">
                                <label style="color: #f22">*</label>
                                <label id="cargo" data-toggle="popover" data-content="El Cargo es requerido">Cargo: </label>
                                <select  class="form-control" name="estados" ng-model="controller.cargo" multiple ng-required="true">
                                    <option value="" selected="true">Seleccionar</option>
                                    <option ng-repeat="cargo in controller.cargos" value="{{cargo.codigoCargo}}" >{{cargo.descripcion}}</option> 
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-md-6" >
                            <br> 
                            <input class="btn btn-primary" type="submit" value ="Generar Cuentas de Cobro">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class='row'>

        <div class="col-md-10" ng-if="content !== ''">
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
                        Cargando...
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
