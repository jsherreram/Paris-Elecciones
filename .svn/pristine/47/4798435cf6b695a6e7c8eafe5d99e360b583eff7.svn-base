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
        <link href="/Paris/assets/css/sweetalert.css" rel="stylesheet" />
        <link href="/Paris/assets/css/angular-notify.min.css" rel="stylesheet" />
        <%@include file="/panel/includes/estilos.jsp" %>

    </head>
    <body class="pace-done hide-sidebar">

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

        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/NombramientoSalonSitio/js/controllers/controller.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/NombramientoSalonSitio/js/services/service.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/services/Empleado.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/services/Sesion.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/sweetalert.min.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ngSweetAlert.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/angular-notify.min.js" ></script>

    </body>
</html>