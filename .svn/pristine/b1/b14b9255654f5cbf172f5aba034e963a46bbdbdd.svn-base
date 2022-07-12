
<%@ page import="co.com.grupoasd.nomina.dao.PruebaDao" %>
<%@ page import="co.com.grupoasd.nomina.modelo.EmpleadoSesion;" %>
<%@ page import="co.com.grupoasd.nomina.modelo.Prueba;" %>
<%@ page import="co.com.grupoasd.nomina.modelo.PruebasxPersona;" %>
<%! EmpleadoSesion emp = new EmpleadoSesion();%>

<%!int i = 0;%>
<% emp = (EmpleadoSesion) session.getAttribute("empleado"); %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Page title -->
        <title>Nomina | Ingreso de usuarios</title>

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

        <!-- Vendor styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/fontawesome/css/font-awesome.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/metisMenu/dist/metisMenu.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/animate.css/animate.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/dist/css/bootstrap.css" />

        <!-- App styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/pe-icon-7-stroke/css/helper.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">

    </head>
    <body class="blank">

        <div class="splash"> <div class="splash-title"><h1>N&oacute;mina Elecciones</h1><img src="${pageContext.request.contextPath}/images/loading-bars.svg" width="64" height="64" /> </div> </div>

        <div class="login-container">
            <div class="row">
                <div class="col-md-12">
                    <div class="text-center m-b-md">
                        <h3>Sistema Nómina Personal Elecciones</h3>
                        <small>2016</small>
                    </div>
                    <div class="hpanel">
                        <div class="panel-body">
                            <h4>Pruebas disponibles</h4>
                            <form action="${pageContext.request.contextPath}/escojePrueba" method="post" id="pruebasDisponibles">
                                <table class="table-bordered table-responsive table-striped">
                                    <colgroup>
                                        <col class="col-lg-3">
                                        <col class="col-lg-3">
                                        <col class="col-lg-3">
                                    </colgroup>
                                    <thead></thead>
                                    <tbody>
                                        <% for (PruebasxPersona prueba : emp.getPruebas()) {%>                                            
                                        <tr>                                
                                            <td><input required type="radio" name="tipoEscogido" value="<%= prueba.getGrupo()+"-"+ prueba.getPrueba()%>"/></td>
                                            <td><%=((Prueba) new PruebaDao().getById(prueba.getPrueba())).getNombre()%></td>          
                                            <td align="center"><%=prueba.getGrupo()%></td>                                            
                                            <input type="hidden" name="valorEscogido" value="<%= prueba.getGrupo()+"-"+ prueba.getPrueba()%>"/>
                                        </tr>
                                        <%}%>
                                        <tr>
                                            <td>
                                                
                                            </td>
                                            
                                                <td align="center"><input type="submit" name="Ingresar" value="Ingresar"</td>
                                            
                                            <td>
                                                
                                            </td>
                                        </tr>
                                    </tbody>    
                                </table>
                                    
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" >
                <div class="col-md-12 text-center">
                    <a href="http://grupoasd.com.co/" target="_BLANK" >
                        <img src="${pageContext.request.contextPath}/images/asd.png" alt="Logo Grupo ASD" />
                    </a>
                    <br/>
                    &copy; 2016 - Grupo ASD - Todos los derechos reservados
                </div>
            </div>

        </div>

    </div>

</div>

<!-- Vendor scripts -->
<script src="${pageContext.request.contextPath}/vendor/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/metisMenu/dist/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/iCheck/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/sparkline/index.js"></script>

<!-- App scripts -->
<script src="${pageContext.request.contextPath}/scripts/homer.js"></script>

</body>
</html>