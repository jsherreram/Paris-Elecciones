<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app='app'>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <title>Configurar Medio de Pago | Panel de control</title>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/routes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/controllers/ConsultarMediosPagoController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/controllers/RegistrarMedioPagoController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/controllers/ActualizarMedioPagoController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/controllers/CargarArchivoController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/controllers/CoberturaMunicipioController.js"></script>
<script src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/services/MediosPago.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/services/Cobertura.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/panel/ConfigurarMedioPago/js/services/Sesion.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/js/services/DepartamentoDane.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/js/services/MunicipioDane.js" type="text/javascript" ></script>
</body>


</html>