<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app='app'>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>N&oacute;mina | Panel de control</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <%@include file="/panel/includes/estilos.jsp" %>

</head>
<body>

<%@include file="/panel/includes/loading.jsp" %>

<!-- Header -->
<div id="header">  
    <%@include file="/panel/includes/header.jsp" %>
</div>

<!-- Navigation -->
<aside id="menu">
    <jsp:include page="/panel/includes/menu.jsp" />
</aside>

<!-- Main Wrapper -->
<div id="wrapper">
	<div ng-view></div>
</div>

<%@include file="/panel/includes/javascripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ui-bootstrap-tpls-1.3.2.min.js"></script>

        <!-- Mainly scripts -->
        <script src="${pageContext.request.contextPath}/plantillas/js/jquery-2.1.1.js"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/metisMenu/jquery.metisMenu.js"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
        <!-- Custom and plugin javascript -->

        <script src="${pageContext.request.contextPath}/plantillas/js/inspinia.js"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/pace/pace.min.js"></script>

        <!-- Steps -->
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/staps/jquery.steps.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/panel/GenerarArchivoNomina/js/config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/GenerarArchivoNomina/js/routes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/GenerarArchivoNomina/js/controllers/generarArchivo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/GenerarArchivoNomina/js/services/Pagos.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/services/Departamento.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/services/Empleado.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/directives/selectDepartamento.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/angular-file-saver.bundle.js" charset="utf-8"></script>

</body>
</html>