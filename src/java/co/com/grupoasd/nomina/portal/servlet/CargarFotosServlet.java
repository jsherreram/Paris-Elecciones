/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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
public class CargarFotosServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            //Se Obtienen los datos del archivo
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> partes = upload.parseRequest(request);
            FileItem prueba = partes.get(0);
            FileItem sitio = partes.get(1);
            FileItem folder = partes.get(2);
            FileItem evento=partes.get(3);
            FileItem file = partes.get(4);
            //Se crea el directorio del sitio segun la prueba en el servidor (repositorio de imagenes)
            String ubicacionArchivo = "/data/Elecciones/img/fotosSitioElecciones/prueba_" + prueba.getString() + "/sitio_" + sitio.getString() +"/evento_"+evento.getString()+ "/" + folder.getString();
            File dir = new File(ubicacionArchivo);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //Se guardar la imagen del sitio en la ubicacion
            factory.setRepository(dir);
            upload.setFileItemFactory(factory);
            file.write(new File(ubicacionArchivo, sitio.getString()+".pdf"));

            out.println("{\"errorCode:\":\"OK\",\"errorMessage\":\"Upload Success!\"}");
        } catch (FileUploadException ex) {
            Logger.getLogger(CargarFotosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CargarFotosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
