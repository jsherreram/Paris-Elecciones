package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.nomina.portal.model.EstadoPersonaEnPrueba;
import co.com.grupoasd.nomina.portal.util.ConfigUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jccastellanos
 * 2015-04-10
 */
@WebServlet(name = "GlobalJSServlet", urlPatterns = {"/global.js"})
public class GlobalJSServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/javascript;charset=UTF-8");
        request.setAttribute("app", ConfigUtil.getValue("orbeon_app"));
        request.setAttribute("api", ConfigUtil.getValue("portal_api_public_url"));
        request.setAttribute("formEngine", ConfigUtil.getValue("orbeon_form_runner"));
        request.setAttribute("formatoFecha", ConfigUtil.getValue("portal_date_format"));
        request.setAttribute("authErrorStatus", ConfigUtil.getValue("portal_auth_http_error"));
        request.setAttribute("cookieTokenKey", ConfigUtil.getValue("portal_cookie_token_key"));
        request.setAttribute("headerTokenKey", ConfigUtil.getValue("portal_header_token_key"));
        request.setAttribute("logoutAction", ConfigUtil.getValue("portal_action_logout"));
        request.setAttribute("estadoForma", EstadoPersonaEnPrueba.getEstados());
        request.getRequestDispatcher("/WEB-INF/global-js.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
