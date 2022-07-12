<div class="content animate-panel" data-ng-init="inicio('${pageContext.request.userPrincipal.name}')")>
    <div class="hpanel">
        <div class="panel-body">
            <form class="form-horizontal" ng-submit="Save()"  method="" id="frmCreateCapacitacion">
                
                <div class="form-group">
                    <label class="control-label col-md-2" for="idDepartamento">Departamento</label>
                    <div class="col-md-6">
                        <input type="text" ng-model="idDepartamento" title="Código Departamento" readonly="readonly" id="idDepartamento" class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="municipio">Municipio</label>
                    <div class="col-md-6">
                       <select id="codigoMunicipio" ng-model="municipio" required="required" ng-options="mun.nombre for mun in municipios"></select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="cargo">Cargo</label>
                    <div class="col-md-6">
                       <select id="codigoMunicipio" ng-model="cargo" required="required" ng-options="car.descripcion for car in cargos"></select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-md-2" for="cantidad">Cantidad Personas</label>
                    <div class="col-md-6">
                        <input type="number" ng-model="cantidad" title="cantidad" id="cantidad"  required = "required" class="form-control">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-md-2" for="fecha">fecha</label>
                    <div class="col-md-6">
                            <input type="date" required = "required" ng-model="fecha" title="fecha" id="fecha" class="form-control">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-md-2" for="horaInicial">Hora Inicial</label>
                    <div class="col-md-6">
                        <input type="time" ng-model="horaInicial" title="horaInicial" required="required" id="horaInicial" class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="horaFinal">Hora Final</label>
                    <div class="col-md-6">
                        <input type="time" ng-model="horaFinal" title="horaFinal" id="horaFinal" class="form-control">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-md-2" for="nombreCapacitador">Nombre Capacitador</label>
                    <div class="col-md-6">
                        <input type="text" ng-model="nombreCapacitador" title="nombreCapacitador" required="required" id="nombreCapacitador" class="form-control">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-md-2" for="salon">Salon</label>
                    <div class="col-md-6">
                        <input type="text" ng-model="salon" title="salon" id="salon" required="required" class="form-control">
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-md-6 col-md-offset-2">
                        <input type ="submit" class="btn btn-primary" value="Guardar">
                    </div>
                </div> 

                <div class="form-group">
                    <p class="text-danger">{{mensaje}}</p>
                </div>
                
            </form>
        </div>
    </div>        
</div>