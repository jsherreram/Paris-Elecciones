<div class="content animate-panel">
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">                   
                    <h2 class="font-light m-b-xs">
                        Seleccione los Cargos a Generar Carné
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

                <form class="form-inline"  action="${pageContext.request.contextPath}/CarneCargo" method="post" target="_blank">
                    
                    <div class="form-group">
                        <label class="control-label col-md-2" for="idDpto">Departamento</label>
                        <div class="col-md-6">
                            <input type="text" name="codigoDepartamento" ng-model="idDpto" class="form-control" readonly="true"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2" for="idMpio">Municipio</label>
                        <div class="col-md-6">
                            <input type="text" name="codigoMunicipio" ng-model="idMpio" class="form-control" readonly="true"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2" for="idEvento">Id Evento</label>
                        <div class="col-md-6">
                            <input type="text" name="codigoEvento" ng-model="idEvento" class="form-control" readonly="true"/>
                        </div>
                    </div>
                    
                    <fieldset>
                            <legend>Cargos que desea generar</legend>
                            <input type='checkbox' name='codigoCargo17' value='17'/>AUXILIAR CCA <br />
                            <input type='checkbox' name='codigoCargo8' value='8'/>ARCHIVO ELECTRONICO <br />
                            <input type='checkbox' name='codigoCargo10' value='10'/>ATENCION AUDITORES <br />
                            <input type='checkbox' name='codigoCargo24' value='24'/>ATENCION DELEGADOS / REGISTRADORES <br />
                            <input type='checkbox' name='codigoCargo15' value='15'/>AUXILIAR ELECTORAL / INSTRUCTORES <br />
                            <input type='checkbox' name='codigoCargo18' value='18'/>CLASIFICADORES - MESA DE CONTROL  <br />
                            <input type='checkbox' name='codigoCargo11' value='11'/>COORDINADOR TRANSMISION DATOS <br />
                            <input type='checkbox' name='codigoCargo16' value='16'/>COORDINADOR CCA - COORDINADOR DEPARTAMENTAL TRANSMISION  <br />
                            <input type='checkbox' name='codigoCargo21' value='21'/>COORDINADOR DIGITALIZACION   <br />
                            <input type='checkbox' name='codigoCargo22' value='22'/>COORDINADOR REVISION  <br />
                            <input type='checkbox' name='codigoCargo25' value='25'/>COORDINADOR TRANSMISION MUNICIPAL <br />
                            <input type='checkbox' name='codigoCargo28' value='28'/>COORDINADOR SOPORTE ESCRUTINIO <br />
                            <input type='checkbox' name='codigoCargo4' value='4' />COORD. CENTRO RECEP. TELEFONICA <br />
                            <input type='checkbox' name='codigoCargo9' value='9' />COORD. SUPERV. DE VERIFICACION <br />
                            <input type='checkbox' name='codigoCargo3' value='3' />CORRECTOR <br />
                            <input type='checkbox' name='codigoCargo7' value='7' />DIGITALIZADOR <br />
                            <input type='checkbox' name='codigoCargo23' value='23'/>PATINADORES (ARCHIVO ELECTRÓNICO A MESA DE INVESTIGACION) <br />
                            <input type='checkbox' name='codigoCargo19' value='19'/>PATINADORES DE DIGITALIZACION (ESCANER A ARCHIVO ELECTRONICO) <br />
                            <input type='checkbox' name='codigoCargo1' value='1' />RECOLECTOR ACTAS RECEP. TELEFONICA <br />
                            <input type='checkbox' name='codigoCargo5' value='5' />RECEPTOR_TELEFONICO <br />
                            <input type='checkbox' name='codigoCargo14' value='14'/>RECEPTOR TELEFONICO BACKUP <br />
                            <input type='checkbox' name='codigoCargo27' value='27'/>SOPORTE TECNICO ESCRUTINIO <br />
                            <input type='checkbox' name='codigoCargo20' value='20'/>SOPORTE DE HARDWARE  <br />
                            <input type='checkbox' name='codigoCargo12' value='12'/>TRANSMISOR DE ACTAS <br />
                            <input type='checkbox' name='codigoCargo26' value='26'/>TRANSMISOR DE ACTAS BACKUP <br />
                            <input type='checkbox' name='codigoCargo6' value='6'>VERIFICADOR <br />
                    </fieldset>                    
                    

                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-2">  
                            <input class="btn btn-primary" type="submit" value ="Generar">
                        </div>
                    </div>
                    
                </form>
             </div>
            </div>
        </div>
    </div>
</div>