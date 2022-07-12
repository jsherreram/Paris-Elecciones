package co.com.grupoasd.nomina.portal.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConfigUtil {
        
    private static Context context = null;
    
    public static String getValue(String key) {
        switch(key) {
            case "orbeon_app": return "NEPS"; 
            case "orbeon_form_runner": return "/afiliacion-portal/logout-action";
            case "portal_api_public_url": return "/resources";
            case "portal_api_private_url": return "/afiliacion-portal/logout-action";
            case "portal_action_logout": return "/afiliacion-portal/logout-action";
            case "portal_cookie_token_key": return "APP-TOKEN";
            case "portal_header_token_key": return "X-AUTH-USER"; 
            case "portal_auth_http_error": return "403";
            case "portal_date_format": return "yyyy-MM-dd";
            case "connection_timeout": return "5000"; 
                
            // Todos los usuarios que utilicen la aplicacion deben tener el rol_operador
            case "rol_operador": return "operador";
            case "rol_asesor": return "asesor";
            case "rol_coordinador": return "coordinador";
            case "rol_rector": return "rector";
            case "rol_administrador": return "administrador"; 
            case "rol_representante": return "representante"; 
            case "rol_examinador": return "examinador";
            case "rol_call": return "call";
            case "rol_delegado": return "delegado";
            case "rol_contabilidad": return "contabilidad";
            case "rol_contabilidad_asd": return "contabilidad_asd";
            case "rol_financiero": return "financiero";
            case "rol_administrador_icfes": return "administrador_icfes";
            case "rol_coordinador_icfes": return "coordinador_icfes";
            case "rol_capacitador": return "capacitador";
            case "rol_coordinador_pds": return "coordinador_pds";
            case "rol_operario_coordinador": return "operario_coordinador";
            
            // URLs relativas donde sera redireccionado el usuario luego de autenticarse de acuerdo al rol
            case "url_home_asesor": return "panel/AdministraAsistencia/main.jsp";
            case "url_home_coordinador": return "panel/InicioMovil/main.jsp";    
            case "url_home_administrador": return "panel/InicioMovil/main.jsp";               
            case "url_home_representante": return "panel/ReportarAsistenciaCedula/main.jsp";
            case "url_home_rector": return "panel/NombramientoCoordinadorSitio/main.jsp";
            case "url_home_examinador": return "panel/InicioMovil/main.jsp";
            case "url_home_call": return "panel/InicioMovil/main.jsp";
            case "url_home_delegado": return "panel/InicioMovil/main.jsp";
            case "url_home_contabilidad": return "panel/InicioMovil/main.jsp";
            case "url_home_financiero": return "panel/InicioMovil/main.jsp";     
            case "url_home_administrador_icfes": return "panel/MonitoreoNombramiento/main.jsp";
            case "url_home_coordinador_icfes": return "panel/MonitoreoNombramientoNodo/main.jsp";
            case "url_home_capacitador": return "panel/InicioMovil/main.jsp";
            case "url_home_coordinador_pds": return "panel/Suplencias/main.jsp";     
            case "url_home_operario_coordinador": return "panel/MonitoreoPds/main.jsp";
            
                
                
            // URL relativa de la pagina de autenticacion
            case "url_login": return "login";
            // Endpoint WS Rest para consultar que el usuario existe
            case "endpoint_obtener_usuario": return "catalogo/usuario/obtener";
            default: throw new IllegalArgumentException("Llave no configurada: " + key);
        }
        
    }
    
    private static String loadFromJNDI(String key) {
        try {
            if(context == null) {
                InitialContext initialContext = new InitialContext();
                context = (Context) initialContext.lookup("java:/comp/env");
            }
            return (String) context.lookup(key);
        } catch (NamingException ex) {
            ex.printStackTrace(); // TODO jcastellanos cambiar por auditoria
            throw new IllegalArgumentException("Llave no configurada: " + key);
        }
        
    }
    
}
