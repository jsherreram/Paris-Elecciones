
<div class="content animate-panel" >
    <div class="row ">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading"><h3 class="panel-title">Cargar Archivo de cobertura</h3>
                <div class="row"> 
                        <div class="col-lg-2 col-md-2 " style="float: right">
                            <button type="button" ng-click="controller.regresar();" class="btn btn-default right">Regresar</button>
                        </div>
                    </div>
                </div>

                <div class="row"> 
                    <div class="col-md-12 ">
                        <div class="panel panel-primary no-border ">
                            <div class="panel-body">
                                
                                <form class="form-horizontal" action="${pageContext.request.contextPath}/CoberturaMedioPagosServlet" method="post" enctype="multipart/form-data">
                                    <div class="row"> 
                                        <label class="control-label col-md-2" for="prueba"></label>
                                        <div class="col-md-6 ">
                                             <input type="text" name ="prueba" ng-model ="controller.prueba"  style="display: none;" class="form-control" required="required" >
                                        </div>
                                    </div>
                                    <label class="control-label col-md-2" for="idmedio_pago"></label>
                                    <div class="col-md-6 ">
                                        <input type="text" name ="idmedio_pago" ng-model ="controller.idmedio_pago"  style="display: none;" class="form-control" required="required" >
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-4">  
                                            <input type="file" name ="file" accept=".csv" class="form-control" >
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-md-offset-4">
                                         <input class="btn btn-primary"  ng-click="controller.validarForm()" type="submit" value ="Guardar">
                                    <a href="templates/verArchivo.jsp" class="btn btn-primary">Descargar Ejemplo.csv</a>
                                    </div>
                                    
                                </form>  
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
     <% if (request.getSession().getAttribute("mensaje") != null) {%>
    <script>alert('<%=request.getSession().getAttribute("mensaje")%>')</script>
    <% request.getSession().removeAttribute("mensaje");
        }
    %>                               
</div>

