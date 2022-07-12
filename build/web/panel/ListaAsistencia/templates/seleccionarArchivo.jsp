<div class="content animate-panel" data-ng-init="listarSitios()" >
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
              <div class="panel-heading">
                  <h3> Generar Lista Asistencia </h3>
              </div>
              <div class="panel-body">

                <form  action="${pageContext.request.contextPath}/Reporte" method="post" target="_blank">
                    
                    <input type="hidden" name="tipoReporte" value="asistenciaPuesto">
                    <input type="hidden" name="codigoevento" value="{{evento}}">
                    <input type="hidden" name="codigopuesto" value="{{sitio.codigoSitio}}">

                      <div class="form-group col-md-5">
                            <label for="sitio"> Sitio: </label><br>

                            <select class="form-control" ng-model="sitio"                             
                                    ng-options="sit.nombreSitio for sit in sitios"  ng-change='listarEventos()' >
                            </select>     

                        </div>
                        <div class="form-group col-md-5">
                            <label for="evento">Sesi�n:</label>

                            <select class="form-control" id="evento" ng-model="evento" required="required" >
                                <option ng-repeat="evento in eventos" value="{{evento.codigoEvento}}"> {{evento.fecha|date:'dd/MM/yyyy'}} {{evento.cargo.descripcion}} {{evento.hora_inicial}} {{evento.nombre}}</option>  
                            </select>

                        </div>
                    
                    <div class="form-group col-md-2">
                        <br> 
                            <input class="btn btn-primary" type="submit" value ="Lista Asistencia">
                   
                    </div>
                    
                </form>
             </div>
            </div>
        </div>
    </div>
</div>