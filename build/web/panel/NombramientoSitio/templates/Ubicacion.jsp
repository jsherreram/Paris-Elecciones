    
<style type="text/css">

    table.width200,table.rwd_auto {border:1px solid #ccc;width:100%;margin:0 0 50px 0}
        .width200 th,.rwd_auto th {background:#ccc;padding:5px;text-align:center;}
        .width200 td,.rwd_auto td {border-bottom:1px solid #ccc;padding:5px;text-align:center}
        .width200 tr:last-child td, .rwd_auto tr:last-child td{border:0}
        
    .rwd {width:100%;overflow:auto;}
        .rwd table.rwd_auto {width:auto;min-width:100%}
            .rwd_auto th,.rwd_auto td {white-space: nowrap;}
            
    @media only screen and (max-width: 760px), (min-width: 768px) and (max-width: 1024px)  
    {
    
        table.width200, .width200 thead, .width200 tbody, .width200 th, .width200 td, .width200 tr { display: block; }
        
        .width200 thead tr { position: absolute;top: -9999px;left: -9999px; }
        
        .width200 tr { border: 1px solid #ccc; }
        
        .width200 td { border: none;border-bottom: 1px solid #ccc; position: relative;padding-left: 50%;text-align:left }
        
        .width200 td:before {  position: absolute; top: 6px; left: 6px; width: 45%; padding-right: 10px; white-space: nowrap;}
        
        .width200 td:nth-of-type(1):before { content: "Cargo"; }
        .width200 td:nth-of-type(2):before { content: "Identificación"; }
        .width200 td:nth-of-type(3):before { content: "Apellido1"; }
        .width200 td:nth-of-type(4):before { content: "Apellido2"; }
        .width200 td:nth-of-type(5):before { content: "Nombre1"; }
        .width200 td:nth-of-type(6):before { content: "Nombre2"; }
        .width200 td:nth-of-type(7):before { content: "Asistio"; }
        .width200 td:nth-of-type(8):before { content: "Acción"; }
        
        
        .descarto {display:none;}
        .fontsize {font-size:10px}
    }
    
    /* Smartphones (portrait and landscape) ----------- */
    @media only screen and (min-width : 320px) and (max-width : 480px) 
    {
        body { width: 320px; }
        .descarto {display:none;}
    }
    
    /* iPads (portrait and landscape) ----------- */
    @media only screen and (min-width: 768px) and (max-width: 1024px) 
    {
        body { width: 495px; }
        .descarto {display:none;}
        .fontsize {font-size:10px}
    }
    
    </style>    



<div class="content animate-panel" data-ng-init="listar()" >
    <div class="row">
        <div class="col-md-10">
            <div class="hpanel">
                <div class="panel-body">  
                    <form class="form-inline">

                        <div class="form-group col-md-6">
                            <label class="control-label" for="nombrePuesto">Nombre del Sitio: {{puestoNombre}}</label>
                        </div>
                        
                        <div class="form-group col-md-6">
                            <label class="control-label col-md-4" for="idSitio">Código Sitio:</label>
                            <div class="col-md-2">
                                <input type="text" ng-model="idSitio" id="idSitio" class="form-control" readonly="true">
                            </div>
                        </div>
                              
                        <div class="form-group col-md-12">
                            <label class="control-label" for="nombrePuesto">Dirección del Sitio: {{direccion}} </label>
                        </div>
                        
                        
                        <div class="form-group col-md-9">
                            <label class="control-label" for="nombrePuesto">Evento: {{evento.fecha|date:'dd/MM/yyyy'}} {{evento.cargo.descripcion}} {{evento.hora_inicial}} {{evento.nombre}}</label>
                        </div>
                  
                        <div class="form-group col-md-3">
                            <a  class="btn btn-default" ng-click="volver()">Volver</a>
                        </div>
                        
                     </form>   
                </div>
            </div>
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-10">
            <div class="panel panel-default">
                  <div class="panel-heading">
                    <div class="input-group col-md-12">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                        <input type="text" class="form-control" placeholder="Buscar" ng-model="search">

                    </div>

                </div> 
              <div class="panel-body">
                  <div class="table-responsive">
                    <table class="width200" >
                  <thead>
                  <tr>
                    <th>Id</th>  
                    <th>Cargo</th>
                    <th>Identificación</th>
                    <th>Apellido1</th>
                    <th>Apellido2</th>
                    <th>Nombre1</th>
                    <th>Nombre2</th>
                    <th>Celular</th>
                    <th>Ubicación</th>
                    <th>Acción</th>
                  </tr>
                  </thead>
                  <tbody>
                    
                    <tr ng-repeat="ubica in ubicaciones | filter:search">
                      <td>{{$index + 1}}</td>  
                      <td>{{ubica.cargo.descripcion}}</td>
                      <td>
                          <input type="text" ng-model="ubica.empleado.nrodoc" id="nrodoc" class="form-control">
                      </td>
                      <td>{{ubica.empleado.apellido1}}</td>
                      <td>{{ubica.empleado.apellido2}}</td>
                      <td>{{ubica.empleado.nombre1}}</td>
                      <td>{{ubica.empleado.nombre2}}</td>
                      <td>
                          <a href="sip:5{{ubica.empleado.celular}}">{{ubica.empleado.celular}}</a>
                      </td>
                      <td>{{ubica.ubicacion}}</td>
                      <td>
                        <a  ng-click="asignar(ubicaciones.indexOf(ubica))" class="btn btn-primary">Asignar</a>
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