 <div class="content animate-panel" data-ng-init="listar()" >

    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">  
                    <form class="form-inline" action="${pageContext.request.contextPath}/Reporte" method="post" target="_blank">
                        <div>
                            <a href="#/Puesto/{{evento.codigoEvento}}/{{departamento.codigo}}/{{municipio.codigoMunicipio}}/{{cargo.codigoCargo}}/{{zonaCodigo}}/{{zonaNombre}}" class="btn btn-sm btn-primary">Regresar</a>
                        </div>
                        <input type="hidden" name="codigodepartamento" value="{{departamento.codigo}}">
                        <input type="hidden" name="codigomunicipio" value="{{municipio.codigoMunicipio}}">
                        <input type="hidden" name="codigozona" value="{{zonaCodigo}}">
                        <input type="hidden" name="codigopuesto" value="{{puestoCodigo}}">
                        <input type="hidden" name="codigoevento" value="{{evento.codigoEvento}}">
                        
                        <input type="hidden" name="codigoCargo" value="{{cargo.codigoCargo}}">

                        <input type="hidden" name="titulo" value="LISTA DE ASISTENCIA">
                        <input type="hidden" name="tipoReporte" value="{{tipoReporte}}">
                        
                        <h2 class="font-light m-b-xs">
                            Información por Salón
                        </h2>
                        
                        <div class="form-group col-md-6"> 
                            <label class="control-label col-md-4" for="nombreEvento">Evento</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="evento.nombre" id="nombreEvento" class="form-control"  readonly="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreDepartamento">Departamento</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="departamento.nombre" id="nombreDepartamento" class="form-control"  readonly="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreMunicipio">Municipio</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="municipio.nombre" id="nombreMunicipio" class="form-control"  readonly="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="zonaNombre">Zona</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="zonaNombre" id="zonaNombre" class="form-control"  readonly="true">
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombrePuesto">Nombre Puesto</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="puestoNombre" id="nombrePuesto" class="form-control" readonly="true">
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="nombreCargo">Cargo</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="cargo.descripcion" id="nombreCargo" class="form-control"  readonly="true">
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label class="control-label col-md-4" for="tipoReporte">Tipo Reporte</label>
                                <div class="col-md-2">                            
                                    <SELECT name="tipoReporte" ng-model="tipoReporte" SIZE="1"> 
                                       <OPTION VALUE="listaAsistenciaCapacitacion">Lista Asistencia a Capacitacion</OPTION>
                                       <OPTION VALUE="StickerViaticos">Sticker de Viaticos</OPTION>
                                       <OPTION VALUE="StickerSillas">Sticker Sillas</OPTION>
                                    </SELECT>
                                </div>
                            </div>                        

                            <div class="form-group">
                                <div class="col-md-4">
                                    <input class="btn btn-primary" type="submit" value ="Generar">
                                </div>
                            </div>
                        </div>
                     </form>   

                </div>
            </div>
        </div>
    </div>
     
</div>
