/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import java.util.List;
import java.io.File;
import co.com.grupoasd.nomina.portal.util.Filtro;

/**
 *
 * @author Pedro Rodriguez
 */
public class UploadFileServlet extends HttpServlet {

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
        String ubicacionArchivo = "/data/Elecciones/img";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024);
        factory.setRepository(new File(ubicacionArchivo));
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Importar Archivos</title>");
            out.println("</head>");
            out.println("<body>");

            String idDpto = "";
            String idEmpleado = "";
            String nrodoc = "";
            String tipoDocumental = "";

            try {
           
                List<FileItem> partes = upload.parseRequest(request);
                EmpleadoDao empleadoDao = new EmpleadoDao();

        
                for (FileItem item : partes) {
         
                    if (item.isFormField()) {
                     
                        // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                        String fieldname = item.getFieldName();

                        if (fieldname.equals("idDpto")) {
                            idDpto = item.getString();
                        }

                        if (fieldname.equals("idEmpleado")) {
                            idEmpleado = item.getString();
                        
                        }

                        if (fieldname.equals("idPersona")) {
                            nrodoc = item.getString();
                        }

                    } else {

                        destino = ubicacionArchivo;
                        File directorioDpto = new File(destino);
                        if (!directorioDpto.exists()) {
                            directorioDpto.mkdir();
                        }

                        String nombreArchivo = item.getFieldName();

                        if (nombreArchivo.equals("file")) {
                            destino = ubicacionArchivo + "/" + "dociden";
                            tipoDocumental = "imgdociden";
                        }

                        if (nombreArchivo.equals("file2")) {
                            destino = ubicacionArchivo + "/" + "ahorroalamano";
                            tipoDocumental = "ahorroalamano";
                        }

                        if (nombreArchivo.equals("file3")) {
                            destino = ubicacionArchivo + "/" + "certestudio";
                            tipoDocumental = "imgcertestudio";
                        }

                        if (nombreArchivo.equals("file4")) {
                            destino = ubicacionArchivo + "/" + "afiliaeps";
                            tipoDocumental = "imgafiliaeps";
                        }

                        File directorio = new File(destino);
                        if (!directorio.exists()) {
                            directorio.mkdir();
                        }

                        String[] lista;
                        lista = directorio.list(new Filtro(idEmpleado));
                        if (!tipoDocumental.equals("ahorroalamano")) {
                            if (lista.length != 0) {
                                File fileActual = new File(destino, idEmpleado + ".pdf");
                                File file = new File(destino, idEmpleado + "_" + (lista.length) + ".pdf");
                                fileActual.renameTo(file);
                            }
                        }
                        File file = new File(destino, idEmpleado + ".pdf");

                        if (item.getSize() > 0) {
                            empleadoDao.ActulizarImagen(Integer.parseInt(idEmpleado), tipoDocumental);
                            item.write(file);
                        }
                    }
                }

                out.println("{\"errorCode:\":\"OK\",\"errorMessage\":\"Upload Success!\"}");

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
