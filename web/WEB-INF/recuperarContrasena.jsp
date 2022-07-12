<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>Elecciones | Cambio de contrase�a</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <!--<link rel="shortcut icon" type="image/ico" href="favicon.ico" />-->

    <!-- Vendor styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/metisMenu/dist/metisMenu.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/animate.css/animate.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/dist/css/bootstrap.css" />

    <!-- App styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/pe-icon-7-stroke/css/helper.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">

</head>
<body class="blank">

<!-- Simple splash screen-->
<div class="splash"> <div class="splash-title"><h1>N&oacute;mina Elecciones</h1><img src="${pageContext.request.contextPath}/images/loading-bars.svg" width="64" height="64" /> </div> </div>
<!--[if lt IE 7]>
<p class="alert alert-danger">Esta utilizando una versi&oacute;n de navegador <strong>desactualizada</strong>. Por favor <a href="http://browsehappy.com/">actualice su navegador</a> para mejorar su experiencia de usuario.</p>
<![endif]-->

<div class="login-container">
    <div class="row" style="display: none" id="div-login">
        <div class="col-md-12">
            <div class="text-center m-b-md">
                <h3>PARIS - Sistema Administraci�n Personal Elecciones</h3>
                <small>2016</small>
            </div>
            <div class="hpanel">
                <div class="panel-body">                        
                        <form action="${pageContext.request.contextPath}/recuperaContrasena" method="post" id="passwdForm">
                            <div class="form-group">
                                <label class="control-label" for="username">N&uacute;mero de documento</label>
                                <input type="text" title="Ingrese su documento" required="" value="" name="documento" id="documento" class="form-control" maxlength="50">
                                <span class="help-block small">Ingrese su numero de documento</span>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="password">Correo electr&oacute;nico</label>
                                <input type="email" title="Digite su correo electronico" required="" value="" name="correo" id="correo" class="form-control" maxlength="50">
                                <span class="help-block small">Ingrese su correo electronico</span>
                            </div>                           
                            <input type="submit" name="Ingresar" value="Ingresar" class="btn btn-info btn-block" />                
                        </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row" id="div-browser-error">
        <div class="col-md-12">
            <div class="hpanel">
                <div class="panel-body text-center">
                    <i class="pe-7s-lock big-icon text-success"></i>
                    <br/>
                    <p>No se puede ejecutar aplicaci&oacute;n en este navegador. Por favor instale y utilice Google Chrome
                     &uacute;ltima versi&oacute;n, para poder ingresar a la aplicaci&oacute;n.</p>
                </div>
            </div>
        </div>        
    </div>

    <div class="row" >
        <div class="col-md-12 text-center">
            <a href="http://grupoasd.com.co/" target="_BLANK" >
                <img src="${pageContext.request.contextPath}/images/asd.png" alt="Logo Grupo ASD" />
            </a>
            <br/>
            &copy; 2016 - Grupo ASD - Todos los derechos reservados
        </div>
    </div>
                            
</div>

</div>

</div>

<!-- Vendor scripts -->
<script src="${pageContext.request.contextPath}/vendor/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/metisMenu/dist/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/iCheck/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/sparkline/index.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bowser/bowser.min.js"></script>

<!-- App scripts -->
<script src="${pageContext.request.contextPath}/scripts/homer.js"></script>
<script src="${pageContext.request.contextPath}/scripts/browserdetect.js"></script>


</body>
</html>