<div class="content animate-panel">

    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3>Consultar y Firmar Cuentas de Cobro</h3>
                </div>
                <div class="panel-body">    
                    <div class="col-md-12">
                        <table style="width:100%;">
                            <tr>
                                <td> 
                                    <label>Documento: </label>
                                    <input type="number" id="documento" name="input" ng-model="documento" ng-keydown="key($event)" >
                                </td>
                                <td><DIV  id="inside"></DIV> </td>
                                <td><img   ng-src={{imagen}}></td>
                            </tr>
                            <tr>
                                <td>
                                    <br>
                                    <span ng-bind="empleado.nombre1 + ' ' + empleado.nombre2 + ' ' + empleado.apellido1 + ' ' + empleado.apellido2"></span>
                                    <p><span>{{empleado.celular}}</span></p>
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                        <a style="float: right;" role="button" href="templates/exportarPersonas.jsp?dpto={{dpto}}&idPrueba={{idPrueba}}" class="btn btn-primary" > Personas Cuenta de cobro firmadas</a>
                    </div>
                    <div ng-show="confirmado" class="col-md-6 col-md-offset-3" style="margin-top:5%; margin-bottom: 5%;">
                        <h4>La cuenta de cobro ya fue firmada</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>
                        Cuenta de Cobro                    
                    </h3>
                </div>
                <div class="panel-body">
                    <br>
                    <div class="col-md-10" ng-show="mostrarCuenta === true">
                        <iframe class="embed-responsive-item"  width="100%" height="600"  frameborder="0" allowfullscreen ng-src="{{content}}"></iframe>
                    </div>

                    <br><br>

                </div>


            </div>
        </div>
    </div>
</div>
