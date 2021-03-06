<%@ page import="co.com.grupoasd.nomina.modelo.EmpleadoSesion;" %>
<% EmpleadoSesion emp = new EmpleadoSesion();%>
<% emp = (EmpleadoSesion) session.getAttribute("empleado");%>


<div id="navigation">
    <ul class="nav metismenu" id="side-menu">
        <li class="nav-header">
            <div class="dropdown profile-element"> <span>
                    <img alt="image" class="img-circle"  src="${pageContext.request.contextPath}/plantillas/img/user.jpg" />
                </span>
                <a style="padding: 2%" data-toggle="dropdown" class="dropdown-toggle" href="#">
                    <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold"><%=emp.getNombre1()%> <%=emp.getApellido1()%></strong>
                        </span> </span> </a>
            </div>
        </li>

        <% if (emp.getRolActual().equals("administrador")) {%> 
        <li>
            <a href="${pageContext.request.contextPath}/panel/InicioMovil/main.jsp#"><i class="fa fa-database"></i> <span class="nav-label">Administración</span> <span class="fa arrow"></span></a>
            <ul class="nav nav-second-level">
                <li><a href="${pageContext.request.contextPath}/panel/Prueba/main.jsp#/">Pruebas</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/Evento/main.jsp#/">Eventos</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/EventosAdicionales/main.jsp#/">Eventos Adicionales</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/Sitios/main.jsp#/">Sitios</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/CrudCargos/main.jsp#/">Cargos</a></li>
                <!--<li><a href="${pageContext.request.contextPath}/panel/CargaAplicacion/main.jsp#/">Cuadro Aplicación</a></li>-->
                <!--<li><a href="${pageContext.request.contextPath}/panel/CargaCapacitacion/main.jsp#/">Cuadro Capacitación<span class="nav-label"></span></a></li>-->
                <li><a href="${pageContext.request.contextPath}/panel/EdicionCuadroCapacitacion/main.jsp#/">Edición Cuadro Capacitación/Evento(s)</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/AsignacionRoles/main.jsp#/">Asignación Roles<span class="nav-label"></span></a></li>
            </ul>
        </li>
        <%}%>




        <% if (emp.getRolActual().equals("administrador")) {%> 
        <li>
            <a href="#/"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Seguimiento</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramiento/main.jsp#/Consulta/N">Nombramiento Por Cargo</a></li>                          
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramiento/main.jsp#/Consulta/NT">Nombramiento Por Sitio</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramiento/main.jsp#/Consulta/A">Asistencia Por Cargo</a></li>   
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramiento/main.jsp#/Consulta/AT">Asistencia Por Sitio</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramiento/main.jsp#/Nacional/CA/2/1/TODOS/<%= emp.getPruebaActual()%>">Seguimiento Capacitaciones</a></li>
            </ul>
        </li>
        <%}%>

        <% if (emp.getRolActual().equals("coordinador")) {%> 
        <li>
            <a href="#/"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Seguimiento</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/N">Nombramiento Por Cargo</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/NT">Nombramiento Por Sitio</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/A">Asistencia por Cargo</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramientoNodo/main.jsp#/Consulta/AT">Asistencia Por Sitio</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoNombramiento/main.jsp#/Nacional/CA/2/1/TODOS/<%= emp.getPruebaActual()%>">Seguimiento Capacitacion</a></li>
            </ul>
        </li>
        <%}%>            

        <% if (emp.getRolActual().equals("administrador") || emp.getRolActual().equals("coordinador")) {%> 
        <li>
            <a href="#/"><i class="fa fa-sitemap"></i> <span class="nav-label">Nombramiento</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="${pageContext.request.contextPath}/panel/NombramientoSitio/main.jsp#/">Por Sesion</a></li>    
                <li><a href="${pageContext.request.contextPath}/panel/ProgramarCapacitaciones/main.jsp#/">Capacitación</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/NombramientoCargo/main.jsp#/">Por Cargo</a> </li>    
            </ul>
        </li>
        <%}%>

        <% if (emp.getRolActual().equals("administrador") || emp.getRolActual().equals("coordinador") || emp.getRolActual().equals("contabilidad")) {%>             
        <li>
            <a href="#/"><i class="fa fa-users"></i> <span class="nav-label">Personal</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="${pageContext.request.contextPath}/panel/GestionPersonas/main.jsp#/">Administrar Personal</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/ConfirmarFirma/main.jsp#/">Enrolar</a></li>
            </ul>
        </li>
        <%}%>
        
        

        <% if (emp.getRolActual().equals("administrador") || emp.getRolActual().equals("coordinador")) {%>             
        <li>
            <a href="#/"><i class="fa fa-files-o"></i> <span class="nav-label">Reportes</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="${pageContext.request.contextPath}/panel/ReporteCuentaDeCobro/main.jsp#/">Generar Cuentas de Cobro</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/ListaAsistencia/main.jsp#/">Lista Asistencia Por Sitio</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/ReporteAsistenciaMunicipio/main.jsp#/">Lista Asistencia Por Municipio</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/GenerarCarnets/main.jsp#/">Generar Carnets</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/GenerarCarnetIndividual/main.jsp#/">Generar Carnet por Cédula <span class="label label-primary pull-right">Nuevo</span> </a></li>
                <li><a href="${pageContext.request.contextPath}/panel/ReportarAsistencia/main.jsp#/Consulta/A">Personal Nombrado <span class="label label-primary pull-right">Es Jurado</span> </a></li>
                <li><a href="${pageContext.request.contextPath}/panel/PersonalCapacitado/main.jsp#/">Personal Capacitado</a></li>
                
                <% if (emp.getRolActual().equals("administrador")) {%>
                    <li><a href="${pageContext.request.contextPath}/panel/GenerarArchivoNomina/main.jsp#/">Generar Archivo Pagos</a></li>
                <%}%>        
                
            </ul>
        </li>
        <%}%>

        <% if (emp.getRolActual().equals("administrador")) {%>             
        <li>
            <a href="#/"><i class="fa fa-edit"></i> <span class="nav-label">Convocatoria</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="${pageContext.request.contextPath}/panel/Convocatoria/main.jsp#/">Masiva desde Archivo</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/AceptacionConvocatoria/main.jsp#/">Aceptacion Masiva</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/CargaNoContactados/main.jsp#/">No Contactados</a></li>
            </ul>
        </li>
        <%}%>

        <% if (emp.getRolActual().equals("administrador") || emp.getRolActual().equals("coordinador")) {%>             
        <li>
            <a href="#/"><i class="fa fa-magic"></i> <span class="nav-label">Asistencia</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="${pageContext.request.contextPath}/panel/ReportarAsistenciaSitio/main.jsp#/">Manual</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/MonitoreoGeneralSitio/main.jsp#/">Biométrica</a></li>
                <li><a href="${pageContext.request.contextPath}/panel/AsistenciaCapacitacion/main.jsp#/">Capacitación</a></li>
            </ul>
        </li>
        <%}%>

        <% if (emp.getRolActual().equals("examinador")) {%> 
        <li>
            <a href="#/"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Defunciones</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="${pageContext.request.contextPath}/panel/GestionPersonas_1/main.jsp#/">Consultar</a></li>
            </ul>
        </li>
        <%}%>


        <% if (emp.getRolActual().equals("contabilidad") || emp.getRolActual().equals("contabilidad_asd") || emp.getRolActual().equals("administrador")) {%>
            <li><a href="${pageContext.request.contextPath}/panel/RevisionDocumentos/main.jsp#/"> <i class="fa fa-users"></i><span class="nav-label"> Validar Personal</span></a></li>
            <li><a href="${pageContext.request.contextPath}/panel/GestionPersonas/main.jsp#/"> <i class="fa fa-user"></i><span class="nav-label"> Administrar Personal</span></a></li>
            <li><a href="${pageContext.request.contextPath}/panel/ValidarAsistencia/main.jsp#/"> <i class="fa fa-files-o"></i><span class="nav-label"> Validar Asistencia</span></a></li>
            <li><a href="${pageContext.request.contextPath}/panel/NotificacionPago/main.jsp#/"> <i class="fa fa-money"></i><span class="nav-label"> Notificación de Pagos</span></a></li>

            <li><a href="${pageContext.request.contextPath}/panel/GenerarArchivoNomina/main.jsp#/">Generar Archivo Pagos</a></li>
        <% }%>

        <% if (emp.getRolActual().equals("administrador") || emp.getRolActual().equals("contabilidad") || emp.getRolActual().equals("coordinador") || emp.getRolActual().equals("examinador") || emp.getRolActual().equals("operador") || emp.getRolActual().equals("contabilidad_asd")) {%>
            <li><a href="${pageContext.request.contextPath}/panel/CambiarContrasena/main.jsp#/"> <i class="fa fa-key"></i><span class="nav-label"> Cambiar Contraseńa</span></a></li>
        <%}%>
            
    </ul>
</div>
