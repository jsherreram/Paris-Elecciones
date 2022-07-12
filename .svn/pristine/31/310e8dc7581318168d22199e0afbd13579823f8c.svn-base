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
    <link href="/Paris/assets/css/sweetalert.css" rel="stylesheet" />

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
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/routes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/controllers/ConsultarPersonaPersonalCallController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/controllers/UsuarioPersonaCallControllers.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/controllers/GestionarPersonaPersonalCallController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/controllers/CambioEstadoPerCall.js"></script>
<script src="${pageContext.request.contextPath}/js/services/Empleado.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/js/services/TipoDocumento.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/services/EmpleadoPrueba.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/services/EstadoPersonaPrueba.js"></script>
<!-- Directives -->
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConsultarPersonalCall/js/directives/pruebaDetail.js"></script>
<script src="${pageContext.request.contextPath}/js/directives/selectTipoDocumento.js" type="text/javascript"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/sweetalert.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ngSweetAlert.js" ></script>
</body>


</html>