package co.com.grupoasd.nomina.portal.util;

import co.com.grupoasd.nomina.common.util.AuthUtil;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Clase utilitaria con funciones de login y logout
 *
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 * Control de cambios: 2015-03-24: Creacion de la clase
 */
public class LoginUtil {

    public static final String ROL_OPERADOR = ConfigUtil.getValue("rol_operador");
    public static final String ROL_ASESOR = ConfigUtil.getValue("rol_asesor");

    public static final String ROL_CAPACITADOR = ConfigUtil.getValue("rol_capacitador");

    public static final String ROL_COORDINADOR = ConfigUtil.getValue("rol_coordinador");
    public static final String ROL_ADMINISTRADOR = ConfigUtil.getValue("rol_administrador");
    public static final String ROL_RECTOR = ConfigUtil.getValue("rol_rector");
    public static final String ROL_EXAMINADOR = ConfigUtil.getValue("rol_examinador");

    public static final String ROL_DELEGADO = ConfigUtil.getValue("rol_delegado");
    public static final String ROL_CALL = ConfigUtil.getValue("rol_call");
    public static final String ROL_CONTABILIDAD = ConfigUtil.getValue("rol_contabilidad");
    public static final String ROL_CONTABILIDAD_READ_ONLY = ConfigUtil.getValue("rol_contabilidad_asd");
    public static final String ROL_FINANCIERO = ConfigUtil.getValue("rol_financiero");

    public static final String ROL_ADMIN_ICFES = ConfigUtil.getValue("rol_administrador_icfes");

    public static final String ROL_COORDINADOR_ICFES = ConfigUtil.getValue("rol_coordinador_icfes");

    public static final String ROL_COORDINADOR_PDS = ConfigUtil.getValue("rol_coordinador_pds");
    public static final String ROL_OPERARIO_COORDINADOR = ConfigUtil.getValue("rol_operario_coordinador");

    /**
     * Permite almacenar el usuario como una cookie del parametro response
     *
     * @param response HttpServletResponse donde se almacenara la cookie del
     * usuario
     * @param username Identificador del usuario a almacenar
     */
    public static void almacenarToken(HttpServletResponse response, String username) {
        //String token = AuthUtil.createJsonWebToken(username.toLowerCase().trim(), 1L);

        String token = username.toLowerCase().trim();
        Cookie cookie = new Cookie(AuthUtil.TOKEN_KEY, token);
        response.addCookie(cookie);

    }

    /**
     * Permite eliminar el usuario del parametro response
     *
     * @param response HttpServletResponse donde se eliminara el usuario
     */
    public static void eliminarToken(HttpServletResponse response) {
        Cookie cookie = new Cookie(AuthUtil.TOKEN_KEY, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     * Permite obtener la URL del panel al cual se debe redireccionar el usuario
     * de acuerdo al rol asignado
     *
     * @param request HttpServletRequest
     * @throws IllegalArgumentException Si el usuario no tiene los roles para
     * acceder a la aplicacion
     * @return Cadena con la URL del recurso a redireccionar
     */
    public static String obtenerPanelPorRol(HttpServletRequest request) {
        HttpSession sesion;
        EmpleadoSesion emp = new EmpleadoSesion();
        String rolActual = "";
        sesion = request.getSession();
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");

        if (emp == null) {
            return request.getContextPath() + "/" + ConfigUtil.getValue("url_login");
        }
        rolActual = emp.getRolActual();
        //if (request.isUserInRole(ROL_OPERADOR)) {
        if (rolActual != null) {
            if (rolActual.equals(ROL_ASESOR)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_asesor");
            } else if (rolActual.equals(ROL_COORDINADOR)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_coordinador");
            } else if (rolActual.equals(ROL_RECTOR)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_rector");
            } else if (rolActual.equals(ROL_EXAMINADOR)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_examinador");
            } else if (rolActual.equals(ROL_ADMINISTRADOR)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_administrador");
            } else if (rolActual.equals(ROL_CALL)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_call");
            } else if (rolActual.equals(ROL_DELEGADO)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_delegado");
            } else if (rolActual.equals(ROL_ADMIN_ICFES)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_administrador_icfes");
            } else if (rolActual.equals(ROL_CONTABILIDAD)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_contabilidad");
            } else if (rolActual.equals(ROL_COORDINADOR_ICFES)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_coordinador_icfes");
            } else if (rolActual.equals(ROL_CAPACITADOR)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_capacitador");
            } else if (rolActual.equals(ROL_COORDINADOR_PDS)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_coordinador_pds");
            } else if (rolActual.equals(ROL_CONTABILIDAD_READ_ONLY)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_contabilidad");
            } else if (rolActual.equals(ROL_OPERARIO_COORDINADOR)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_operario_coordinador");
            } else if (rolActual.equals(ROL_FINANCIERO)) {
                return request.getContextPath() + "/" + ConfigUtil.getValue("url_home_financiero");
            } else {
                throw new IllegalArgumentException("El usuario no tiene los roles necesarios.");
            }
        } else {
            return request.getContextPath() + "/" + ConfigUtil.getValue("url_login");
        }

    }
}
