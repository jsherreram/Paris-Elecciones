<div class="content animate-panel">
    <div class="row">
        <div class="col-md-10">
            <div class="hpanel">
                <div class="panel-body">                   
                    <h2>
                        Adjuntar o Actualizar Documentos
                    </h2>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-10">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <center> Documentos</center>
                </div>
                <div class="panel-body">

                    <form class="form-horizontal" action="${pageContext.request.contextPath}/UploadFileServlet" method="post" enctype="multipart/form-data">

                        <div class="form-group">
                            <label class="control-label col-md-2" for="idDpto">Departamento</label>
                            <div class="col-md-6">
                                <input type="text" name="idDpto" ng-model="controller.idDpto" class="form-control" readonly="true"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-2" for="idPersona">Identificación</label>
                            <div class="col-md-6">
                                <input type="text" name="idPersona" ng-model="controller.idPersona" class="form-control" readonly="true"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-2" for="file">Documento de Identificación y Rut</label>
                            <div class="col-md-offset-2">  
                                <input type="file" name ="file" accept="application/pdf" class="form-control" >
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-offset-2">  
                                <input class="btn btn-primary" type="submit" value ="Subir Archivo(s)">
                                <a class="btn btn-default"  ng-click="controller.atras()">Cancelar</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>