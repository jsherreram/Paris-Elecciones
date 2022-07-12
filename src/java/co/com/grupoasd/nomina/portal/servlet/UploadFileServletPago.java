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

@WebServlet(name = "UploadFileServletPago", urlPatterns = {"/UploadFileServletPago"})
public class UploadFileServletPago extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        String destino = "";
        String ubicacionArchivo = "/data/Elecciones/img/listas/pagos";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024);
        factory.setRepository(new File(ubicacionArchivo));
        ServletFileUpload upload = new ServletFileUpload(factory);
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Importar Archivos</title>");
            out.println("</head>");
            out.println("<body>");

            String idPrueba = "";
            String codSitio = "";
            String multiple = "";

            try {
                List<FileItem> partes = upload.parseRequest(request);
                for (FileItem item : partes) {
                    if (item.isFormField()) {
                        // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                        String fieldname = item.getFieldName();

                        if (fieldname.equals("idPrueba")) {
                            idPrueba = item.getString();
                        }

                        if (fieldname.equals("codSitio")) {
                            codSitio = item.getString();
                        }
                        if (fieldname.equals("multiple")) {
                            multiple = item.getString();
                        }

                    } else {
                        destino = ubicacionArchivo;
                        File directorioPago = new File(destino);
                        if (!directorioPago.exists()) {
                            directorioPago.mkdir();
                        }

                        String nombreArchivo = item.getFieldName();

                        if (nombreArchivo.equals("file")) {
                            destino = ubicacionArchivo + "/" + idPrueba;
                        }

                        File directorio = new File(destino);
                        if (!directorio.exists()) {
                            directorio.mkdir();
                        }
                        //Si es un solo archivo, coloca el nombre como el codigo del sitio, si no deja el nombre que trae
                        StringBuilder name = new StringBuilder();
                        if (multiple.equals("multiple")) {
                            name.append(item.getName());
                        } else {
                            name.append(codSitio);
                            name.append(".pdf");
                        }

                        File file = new File(destino, name.toString());
                        if (item.getSize() > 0) {
                            item.write(file);
                        }
                    }
                }

                response.setContentType("text/html");
                String site = "panel/ArchivoPago/main.jsp#/general";

                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
                request.getSession().setAttribute("mensaje", "El archivo se guardó correctamente");

            } catch (FileUploadException ex) {
                out.println("<h1>Error al subir el archivo " + ex.getMessage() + "</h1>");
            } catch (Exception ex) {
                out.println("<h1>Error al subir el archivo " + ex.getMessage() + "</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
