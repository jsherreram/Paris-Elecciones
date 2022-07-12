package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.nomina.common.model.HttpResponse;
import co.com.grupoasd.nomina.common.util.RestUtil;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.PruebasxPersona;
import co.com.grupoasd.nomina.negocio.empleado.IEmpleadoImpl;
import co.com.grupoasd.nomina.negocio.usuario.UsuarioController;
import co.com.grupoasd.nomina.portal.util.ConfigUtil;
import co.com.grupoasd.nomina.portal.util.LoginUtil;
import static co.com.grupoasd.nomina.portal.util.LoginUtil.ROL_OPERADOR;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import java.security.Principal;
import javax.servlet.http.HttpSession;

/**
 * Servlet para el manejo de autenticacion de usuarios
 *
 * @author Juan Carlos Castellanos <jccastellanos@grupoasd.com.co>
 * Control de cambios: 2015-03-11: Creacion de la clase
 */
@WebServlet(name = "LoginActionServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Manejo de la peticion GET. Despliega la pagina con el formulario de
     * autenticacion.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si el usuario ya esta autenticado es redirigido al home del usuario
        if (request.isUserInRole(ROL_OPERADOR)) {
            response.sendRedirect(LoginUtil.obtenerPanelPorRol(request));
        } // De lo contrario se despliega el formulario de autenticacion
        else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    /**
     * Manejo de la peticion POST. El formulario de autenticacion (metodo GET)
     * envia los datos por POST los cuales son recibidos y manejados por este
     * metodo.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException TODO En los flujo de pagina de respuesta de error
     * agregar un mensaje a la pagina para indicar cual fue el error
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IEmpleadoImpl empleado = new IEmpleadoImpl();
        HttpSession sesion;
        EmpleadoSesion emp = new EmpleadoSesion();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            try {
                LoginUtil.almacenarToken(response, username);
                request.login(username.toLowerCase().trim(), password);
                UsuarioController usuario = new UsuarioController();
                if (usuario.esContraseñaReseteada(username)) {

                    request.getRequestDispatcher("/WEB-INF/" + "reestablecerContrasena.jsp").forward(request, response);
                    return;
                }
                emp = empleado.getEmpleadoSesion(request.getUserPrincipal().toString());
                sesion = request.getSession();
                sesion.setAttribute("empleado", emp);
                //valida si tiene más de una prueba de la cual pertenezca para que escoja la prueba que aplica para la sesion, también el rol que toma para esa prueba

                if (emp.getBloqueado().equals(1)) {
                    LoginUtil.eliminarToken(response);
                    HttpSession session = request.getSession();
                    if (session != null) {
                        session.invalidate();
                    }
                    request.getRequestDispatcher("/WEB-INF/login-bloqueo.jsp").forward(request, response);
                    return;
                }
                //Dependiendo el numero de pruebas, tendría que escoger el usuario o si es una sola el sistema no pregunta
                if (emp.getPruebas().size() > 1) {
                    request.getRequestDispatcher("/WEB-INF/sesionActiva.jsp").forward(request, response);
                    return;
                }
                else{
                    emp.setRolActual(((PruebasxPersona)emp.getPruebas().get(0)).getGrupo());
                    emp.setPruebaActual(((PruebasxPersona)emp.getPruebas().get(0)).getPrueba());                    
                }

                response.sendRedirect(LoginUtil.obtenerPanelPorRol(request));

            } catch (ServletException ex) {
                // Error de autenticacion                
                HttpSession session = request.getSession();
                    if (session != null) {
                        session.invalidate();
                    }
                LoginUtil.eliminarToken(response);                
                request.getRequestDispatcher("/WEB-INF/login-error.jsp").forward(request, response);
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                // El WS genero error lo que indica que el core de servicios esta presentando fallos por lo cual 
                // no se permite autenticar al usuario
                HttpSession session = request.getSession();
                    if (session != null) {
                        session.invalidate();
                    }
                LoginUtil.eliminarToken(response);                
                request.getRequestDispatcher("/WEB-INF/login-error.jsp").forward(request, response);
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                LoginUtil.eliminarToken(response);
                HttpSession session = request.getSession();
                    if (session != null) {
                        session.invalidate();
                    }
                request.getRequestDispatcher("/WEB-INF/login-error.jsp").forward(request, response);
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/login-error.jsp").forward(request, response);
        }
    }

}
