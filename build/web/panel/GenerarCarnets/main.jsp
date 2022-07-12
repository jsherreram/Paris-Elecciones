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

<script type="text/javascript" src="${pageContext.request.contextPath}/panel/GenerarCarnets/js/config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/GenerarCarnets/js/routes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/GenerarCarnets/js/controllers/generarCarnet.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/services/Cargo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/services/Departamento.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/services/UsuarioDepartamento.js"></script>
<script src="${pageContext.request.contextPath}/js/services/Empleado.js" type="text/javascript" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/directives/selectDepartamento.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/services/Municipio.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/directives/selectMunicipio.js"></script>

</body>
</html>