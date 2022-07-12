/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.nomina.negocio.carguepagos.NotificacionPagoBusiness;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ASD
 */
@WebServlet(name = "NotificacionPagoServlet", urlPatterns = {"/NotificacionPagoServlet"})
public class NotificacionPagoServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        try {
            //Se Obtienen los datos del archivo
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> partes = upload.parseRequest(request);
            FileItem prueba = partes.get(0);
            FileItem file = partes.get(1);
            //Se crea el directorio del sitio segun la prueba en el servidor (repositorio de imagenes)
            String ubicacionArchivo = "/data/tmp/";
            File dir = new File(ubicacionArchivo);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //Se genera un caracter aleatorio para concatenarlo al nombre del archivo
            String nombreArchivo = file.getName();
            Random rnd = new Random();
            char n;
            for (int i = 0; i < 10; i++) {
                n = (char) (rnd.nextDouble() * 26.0 + 65.0);
                nombreArchivo = n + nombreArchivo;
            }
            //Se guardar la imagen del sitio en la ubicacion
            factory.setRepository(dir);
            upload.setFileItemFactory(factory);
            file.write(new File(ubicacionArchivo, nombreArchivo));

            String usuario = request.getUserPrincipal().getName();
            String pathArchivo = "/data/tmp/" + nombreArchivo;
            //Se realizan las actualizaciones de negocio correspondientes
            NotificacionPagoBusiness notificacionNegocio = new NotificacionPagoBusiness();
            notificacionNegocio.cargarPagoParaNotificar(file.getName(), pathArchivo, Integer.parseInt(prueba.getString()), usuario);

            String urlResult = request.getContextPath() + "/panel/NotificacionPago/main.jsp";
            response.sendRedirect(urlResult);

        } catch (FileUploadException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
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
