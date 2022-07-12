<div class="content animate-panel" data-ng-init="listarEventos()" >
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3> Generar Carnets</h3>
                </div>
                <div class="panel-body">

                    <form  ng-submit="controller.generarCarnets()" method="post" target="_blank">

                        <div class="form-group col-md-6" ng-hide="controller.rol === 'coordinador'">
                            <label style="color: #f22">*</label>
                            <label id="departamento" data-toggle="popover" data-content="El departamento es requerido">Departamento: </label>
                            <select ng-model="controller.departamento" class="form-control" ng-change="controller.buscarMunicipios()">
                                <option value="" selected="true">Seleccionar</option>
                                <option ng-repeat="dpto in controller.departamentos" value="{{dpto.codigo}}" >{{dpto.nombre}}</option> 
                            </select>
                        </div>
                        <div class="form-group col-md-6" ng-show="controller.rol === 'coordinador'">
                            <label style="color: #f22">*</label>
                            <label id="departamento" data-toggle="popover" data-content="El departamento es requerido">Departamento: </label>
                            <select ng-model="controller.departamento" class="form-control" disabled>
                                <option value="" selected="true">Seleccionar</option>
                                <option ng-repeat="dpto in controller.departamentos" value="{{dpto.codigo}}" >{{dpto.nombre}}</option> 
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Municipio: </label>
                            <select  ng-model="controller.municipio" class="form-control" 
                                    ng-options="municipioObj.nombre for municipioObj in controller.municipios track by municipioObj.codigoMunicipio">
                                <option style="display:none" value="">Seleccione</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label style="color: #f22">*</label>
                            <label id="cargo" data-toggle="popover" data-content="El Cargo es requerido">Cargo: </label>
                            <select  class="form-control" name="estados" ng-model="controller.cargo" ng-required="true" multiple style="height: 120px;">
                                <option value="" selected="true">Seleccionar</option>
                                <option ng-repeat="cargo in controller.cargos" value="{{cargo.codigoCargo}}" >{{cargo.descripcion}}</option> 
                            </select>
                        </div>

                        <div class="form-group col-md-6">
                            <br> 
                            <input class="btn btn-primary" type="submit" value ="Generar">

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