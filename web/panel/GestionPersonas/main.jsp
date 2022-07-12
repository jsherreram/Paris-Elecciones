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
        <link href="${pageContext.request.contextPath}/plantillas/css/plugins/dropzone/dropzone.css" rel="stylesheet">
        <!-- Google Maps API -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDlXlfHjXDnFElAsLyQlFTo2bDGyi9wTgs"></script>

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
        <!-- Vendor scripts -->
        <script src="${pageContext.request.contextPath}/vendor/jquery/dist/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/jquery-ui/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/slimScroll/jquery.slimscroll.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/metisMenu/dist/metisMenu.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/iCheck/icheck.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/sparkline/index.js"></script>



        <!--  AngularJS -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/angular.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/angular/angular-route/angular-route.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/angular/angular-resource/angular-resource.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/angular/angular-cookies/angular-cookies.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/angular-animate.js" charset="utf-8"></script>
       <!-- <script type="text/javascript" src="${pageContext.request.contextPath}/angular/angular-bootstrap/ui-bootstrap.min.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ui-bootstrap-custom-1.3.3.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/angular/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ui-bootstrap-custom-tpls-1.3.3.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/global.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/global-service.js"></script>

        <!-- App scripts -->
        <script src="${pageContext.request.contextPath}/scripts/homer.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/afiliacion.js"></script>

        <script src="${pageContext.request.contextPath}/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js" type="text/javascript"></script>

        <!--   plugins 	 -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>

        <!--  More information about jquery.validate here: http://jqueryvalidation.org/	 -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>

        <!--  methods for manipulating the wizard and the validation -->
        <script src="${pageContext.request.contextPath}/assets/js/wizard.js"></script>


        <!-- Date Utils -->
        <script src="${pageContext.request.contextPath}/scripts/date.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/date-util.js"></script>

        <script src="${pageContext.request.contextPath}/scripts/biometria.js"></script>


        <!-- Clock picker -->
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/clockpicker/clockpicker.js"></script>

        <!-- checklist-model -->
        <script src="${pageContext.request.contextPath}/vendor/checklist-model/checklist-model.js"></script>

        <!-- MENU -->
        <script src="${pageContext.request.contextPath}/plantillas/js/jquery-2.1.1.js"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/metisMenu/jquery.metisMenu.js"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/inspinia.js"></script>


        <!-- Mainly scripts -->
        <script src="${pageContext.request.contextPath}/plantillas/js/jquery-2.1.1.js"></script>

        <script src="${pageContext.request.contextPath}/plantillas/js/bootstrap.min.js"></script>
       <!-- <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ui-bootstrap-tpls-1.3.2.min.js"></script>-->

        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/metisMenu/jquery.metisMenu.js"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
        <!-- Custom and plugin javascript -->

        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/pace/pace.min.js"></script>

        <!-- Steps -->
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/staps/jquery.steps.min.js"></script>

        <!-- Jquery Validate -->
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/validate/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/config.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/routes.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/gestionarPersona.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/RegistrarPersona.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/EditarPersonaDetallado.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/VerHistorialArchivos.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/cargarArchivoPersonas.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/verPersonaDetallado.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/verHistorialLaboral.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/geolocalizar.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/controllers/subirAhorroalaMano.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/services/GestionPersonas.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/panel/GestionPersonas/js/services/CargaArchivo.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/sweetalert.min.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ngSweetAlert.js" ></script>

        <!-- Services -->
        <script src="${pageContext.request.contextPath}/js/services/DepartamentoDane.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/TipoDocumento.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/services/MunicipioDane.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/ZonaIcfes.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/Empleado.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/ActividadEconomica.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/ZonaIcfes.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/LocalidadDane.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/Departamento.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/MedioPago.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/js/services/OperadorCelular.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/angular/angular-geolocation/dist/angularjs-geolocation.min.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/panel/Sitios/js/services/Maps.js" type="text/javascript"></script>        

        <!-- Directivas -->
        <script src="${pageContext.request.contextPath}/js/directives/selectActividadEconomica.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/directives/selectDepartamentoDane.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/directives/selectTipoDocumento.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/directives/selectMunicipioDane.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/directives/selectZonaIcfes.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/directives/selectLocalidadDane.js" type="text/javascript"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-datepicker.js" charset="utf-8"></script>

        <!-- Clock picker -->
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/clockpicker/clockpicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/picklist.js" charset="utf-8"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/dirPagination.js" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/plantillas/js/plugins/dropzone/dropzone.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/ngDropzone.js" type="text/javascript"></script>
    </body>
</html>
