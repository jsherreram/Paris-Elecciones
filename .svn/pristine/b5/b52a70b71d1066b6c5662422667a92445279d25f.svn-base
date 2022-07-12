/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.negocio.empleado.IEmpleadoImpl;
import co.com.grupoasd.nomina.negocio.usuario.UsuarioController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grupo ASD
 */
@WebServlet(name = "recuperaContrasena", urlPatterns = {"/recuperaContrasena"})
public class recuperaContrasena extends HttpServlet {

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

        //processRequest(request, response);
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
        UsuarioController usuario = new UsuarioController();
        EmpleadoSesion empleado = new EmpleadoSesion();
        IEmpleadoImpl emp = new IEmpleadoImpl();
        empleado = emp.getEmpleadoSesion(request.getParameter("documento"));
        empleado.setEmail(request.getParameter("correo"));

        try {
            if (usuario.compruebaCambioContrasena(empleado)) {
                usuario.recuperarContraseña(empleado.getIdEmpleado().toString(), true);
                request.getRequestDispatcher("/WEB-INF/login-cambio.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/login-error.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(recuperaContrasena.class.getName()).log(Level.SEVERE, null, ex);
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
