<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app='app'>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Page title -->
        <title>Nómina | Panel de control</title>

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

        <%@include file="/panel/includes/estilos.jsp" %>
        <link href="${pageContext.request.contextPath}/plantillas/css/bootstrap.min.css" rel="stylesheet">
        <link href="/Paris/assets/css/sweetalert.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/plantillas/font-awesome/css/font-awesome.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/plugins/iCheck/custom.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/plugins/steps/jquery.steps.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
        <!--         Google Maps API 
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDlXlfHjXDnFElAsLyQlFTo2bDGyi9wTgs"></script>-->
        <!--        <style>#map {height: 350px;} form .content {padding: 0} .map {width: 100% !important; height: 100% !important; padding: 0 !important}</style>-->
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

        <!-- Recursos propios funcionalidad -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/NombramientoCargo/js/config.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/NombramientoCargo/js/routes.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/NombramientoCargo/js/controllers/nombramientoCargo.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/NombramientoCargo/js/controllers/busquedaPersonas.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/NombramientoCargo/js/services/NombramientoCargoService.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/Sitios/js/services/Sitio.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/services/GestionPersonas.js"></script>
        <!-- Jquery Validate -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/global.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/global-service.js"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/validate/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/sweetalert.min.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ngSweetAlert.js" ></script>
        <script src="${pageContext.request.contextPath}/js/services/Municipio.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/Departamento.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/MunicipioDane.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/DepartamentoDane.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/ZonaIcfes.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/Empleado.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/Cargo.js" type="text/javascript" ></script>
                <script type="text/javascript" src="${pageContext.request.contextPath}/js/services/UsuarioDepartamento.js"></script>
                        <script type="text/javascript" src="${pageContext.request.contextPath}/js/services/Evento.js"></script>
        <script src="${pageContext.request.contextPath}/js/directives/selectDepartamento.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/directives/selectMunicipio.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/directives/numbersDirective.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/angular/angular-geolocation/dist/angularjs-geolocation.min.js" type="text/javascript" ></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-datepicker.js" charset="utf-8"></script>

        <!-- Clock picker -->
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/clockpicker/clockpicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/picklist.js" charset="utf-8"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/dirPagination.js" charset="utf-8"></script>
        <link href="${pageContext.request.contextPath}/assets/css/dirPaginate.css" rel="stylesheet">
    </body>
</html>