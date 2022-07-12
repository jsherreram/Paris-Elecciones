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
        <link href="${pageContext.request.contextPath}/plantillas/font-awesome/css/font-awesome.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/plugins/iCheck/custom.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/plugins/steps/jquery.steps.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plantillas/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/dirPaginate.css" rel="stylesheet">
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
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/validate/jquery.validate.min.js"></script>

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
        <script src="${pageContext.request.contextPath}/js/directives/numbersDirective.js" type="text/javascript"></script>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/StatusActualizame/controller.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/StatusActualizame/service.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/sweetalert.min.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ngSweetAlert.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-datepicker.js" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/clockpicker/clockpicker.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/Empleado.js" type="text/javascript" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/dirPagination.js" charset="utf-8"></script>


    </body>


</html>