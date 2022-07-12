<%-- 
    Document   : main
    Created on : 15-ene-2016, 18:11:42
    Author     : Grupo ASD
--%>

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

<script type="text/javascript" src="${pageContext.request.contextPath}/panel/MedioPago/controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/panel/MedioPago/service.js"></script>

</body>
</html>