<%@ page import="co.com.grupoasd.nomina.modelo.EmpleadoSesion;" %>
<%! EmpleadoSesion emp = new EmpleadoSesion();%>
formulario = {
    global: {
        app: '<%= request.getAttribute("app") %>',
        api: '/Paris/resources',
        angularBase: '/Paris/panel',        
        idPruebaActual: '26',
        formEngine: '<%= request.getAttribute("formEngine") %>',
        formatoFecha: '<%= request.getAttribute("formatoFecha") %>',
        authErrorStatus: <%= request.getAttribute("authErrorStatus") %>,
        cookieTokenKey: '<%= request.getAttribute("cookieTokenKey") %>',
        headerTokenKey: '<%= request.getAttribute("headerTokenKey") %>',
        logoutAction: '<%= request.getAttribute("logoutAction") %>',
        estadoForma: <%= request.getAttribute("estadoForma") %>        
    }
};
