/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.nomina.dao.SoporteAsistenciaDao;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.Departamento;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.SoporteAsistencia;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * @author Armando Florez
 */
public class UploadFileServletAsistencia extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
            
            try{
                List<FileItem> partes = upload.parseRequest(request);

                String idDpto = "";
                String idMpio = "";
                String idZona = "";
                String idPuesto = "";
                String idCargo = "";
                int idEvento = 0;
                String usuario= "";
                
                int idSoporte ;
                
                for(FileItem item : partes)
                {
                    if (item.isFormField()) {
                        // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                        String fieldname = item.getFieldName();
                        
                        if(fieldname.equals("idDpto"))
                        {
                            idDpto = item.getString();
                        }
                        
                        if(fieldname.equals("idMpio"))
                        {
                            idMpio = item.getString();
                        }

                        if(fieldname.equals("idZona"))
                        {
                            idZona = item.getString();
                        }

                        if(fieldname.equals("idPuesto"))
                        {
                            idPuesto = item.getString();
                        }

                        if(fieldname.equals("idCargo"))
                        {
                            idCargo = item.getString();
                        }

                        if(fieldname.equals("idEvento"))
                        {
                            idEvento = Integer.parseInt(item.getString());
                        }
                        
                        if(fieldname.equals("usuario"))
                        {
                            usuario = item.getString();
                        }
                        
                        
                    }else
                    {
                        ubicacionArchivo = ubicacionArchivo + "/listas/" + idDpto;

                        File directorio = new File(ubicacionArchivo); 
                        if (!directorio.exists())
                        {
                            directorio.mkdir();
                        }
                        
                        SoporteAsistenciaDao soporteAsistenciaDao = new SoporteAsistenciaDao();
                        SoporteAsistencia soporte = new SoporteAsistencia();
                        soporte.setId(0);
                        Municipio mun = new Municipio();
                        Departamento dpto = new Departamento();
                        dpto.setCodigo(idDpto);
                        mun.setDepartamento(dpto);
                        mun.setCodigoMunicipio(idMpio);
                        soporte.setMunicipio(mun);
                        soporte.setZona(idZona);
                        soporte.setPuesto(idPuesto);
                        Cargo cargo = new Cargo();
                        cargo.setCodigoCargo(idCargo);
                        soporte.setCargo(cargo);
                        Evento evento = new Evento();
                        evento.setCodigoEvento(idEvento);
                        soporte.setEvento(evento);
                        soporte.setUsuario(usuario);
                        SoporteAsistencia soporteResult = soporteAsistenciaDao.insertar(soporte);
                        
                        if(soporteResult.getId()>0)
                        {
                            File file = new File(ubicacionArchivo, soporteResult.getId() + ".pdf");
                            item.write(file);
                        }
                    }
                }
                
                response.setContentType("text/html");
                String site = "panel/ReportarAsistencia/main.jsp#/Ubicacion/"+idEvento+"/"+idDpto+"/"+idMpio+"/"+idCargo+"/"+idZona+"/-/"+idPuesto+"/-/";
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);                
                
            }catch(FileUploadException ex)
            {
                out.println("<h1>Error al subir el archivo " + ex.getMessage() + "</h1>");
            }   
            catch (Exception ex) {
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
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
