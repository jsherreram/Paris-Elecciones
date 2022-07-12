<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">                   
                    <h2 class="font-light m-b-xs">
                        Cargue Salones, Ubicación
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
                <form class="form-horizontal" action="${pageContext.request.contextPath}/SalonesServlet" method="post" enctype="multipart/form-data">
                    <label class="control-label col-md-2" for="prueba">Prueba</label>
                    <div class="form-group">
                        <div class="col-md-6">
                            <select name="prueba" SIZE="1">
                                <option ng-repeat="reg in pruebas | orderBy:'-fechaaplicacion' " value="{{reg.idprueba}}">{{reg.nombre}}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-2">  
                            <input type="file" name ="file" accept=".csv" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-2">  
                            <input class="btn btn-primary" type="submit" value ="Cargar">
                            <a href="templates/verArchivo.jsp" class="btn btn-primary">Descargar Ejemplo.csv</a>
                        </div>
                    </div>
                </form>
             </div>
            </div>
        </div>
    </div>
</div>