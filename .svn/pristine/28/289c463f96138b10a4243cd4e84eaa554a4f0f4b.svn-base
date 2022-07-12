package co.com.grupoasd.nomina.portal.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.com.grupoasd.nomina.negocio.usuario.UsuarioController;
import co.com.grupoasd.nomina.portal.util.LoginUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Grupo ASD
 */
@WebServlet(name = "cambioContrasena", urlPatterns = {"/cambioContrasena"})
public class RestableceContrasena extends HttpServlet {
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
        String nuevoPassword = request.getParameter("nuevoPassword");
        String nuevoPasswordConfirm = request.getParameter("nuevoPasswordConfirma");
        Logger.getLogger(RestableceContrasena.class.getName()).log(Level.INFO, "Nueva contraseña:".concat(nuevoPassword));
        Logger.getLogger(RestableceContrasena.class.getName()).log(Level.INFO, "Confirma nueva contraseña:".concat(nuevoPasswordConfirm));
        
        String usuario = request.getUserPrincipal().getName();

        if (nuevoPassword.equals(nuevoPasswordConfirm)) {
            try {
                new UsuarioController().cambiarContrasena(nuevoPassword, usuario);
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                try {
                    LoginUtil.eliminarToken(response);
                    HttpSession session = request.getSession();
                    if (session != null) {
                        session.invalidate();
                    }
                    request.logout();
                    response.sendRedirect(request.getContextPath());
                } finally {
                    out.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(RestableceContrasena.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(RestableceContrasena.class.getName()).log(Level.INFO, "Error restableciendo la contraseña");
            request.setAttribute("error", "Las contraseñas no coinciden");
            request.getRequestDispatcher("/WEB-INF/reestablecerContrasena.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
