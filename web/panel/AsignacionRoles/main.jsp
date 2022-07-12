<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app='app'>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    
    <title>Asignar Roles | Panel de control</title>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/panel/AsignacionRoles/js/config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/AsignacionRoles/js/routes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/AsignacionRoles/js/controllers/ConsultarEmpleadosController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/AsignacionRoles/js/controllers/ActualizarEmpleadoController.js"></script>
<script src="${pageContext.request.contextPath}/panel/AsignacionRoles/js/services/Funcionario.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/js/services/Departamento.js" type="text/javascript" ></script>
</body>


</html>