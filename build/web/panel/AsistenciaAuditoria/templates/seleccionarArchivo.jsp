<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">                   
                    <h2 class="font-light m-b-xs">
                        Adjuntar Lista de Asistencia
                    </h2>
                    
                </div>
            </div>
        </div>
    </div>
    
    <div class="stats-label text-color">
        <span class="font-extra-bold font-uppercase"></span>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
              <div class="panel-heading">
              </div>
              <div class="panel-body">

                <form class="form-horizontal" action="${pageContext.request.contextPath}/UploadFileServletAsistencia" method="post" enctype="multipart/form-data">
                    
                    <div class="form-group">
                        <label class="control-label col-md-2" for="idDpto">Departamento</label>
                        <div class="col-md-6">
                            <input type="text" name="idDpto" ng-model="idDpto" class="form-control" readonly="true"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2" for="idMpio">Municipio</label>
                        <div class="col-md-6">
                            <input type="text" name="idMpio" ng-model="idMpio" class="form-control" readonly="true"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2" for="idZona">Zona</label>
                        <div class="col-md-6">
                            <input type="text" name="idZona" ng-model="idZona" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-md-2" for="idPuesto">Puesto</label>
                        <div class="col-md-6">
                            <input type="text" name="idPuesto" ng-model="idPuesto" class="form-control" readonly="true"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-md-2" for="idCargo">Cargo</label>
                        <div class="col-md-6">
                            <input type="text" name="idCargo" ng-model="idCargo" class="form-control" readonly="true"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2" for="idEvento">Evento</label>
                        <div class="col-md-6">
                            <input type="text" name="idEvento" ng-model="idEvento" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-md-2" for="usuario">Usuario</label>
                        <div class="col-md-6">
                            <input type="text" name="usuario" value="${pageContext.request.userPrincipal.name}" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    
                    
                    
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-2">  
                            <input type="file" name ="file" accept="application/pdf" class="form-control" >
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-2">  
                            <input class="btn btn-primary" type="submit" value ="Subir Archivo">
                        </div>
                    </div>
                    
                </form>
             </div>
            </div>
        </div>
    </div>
</div>