/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.nomina.modelo.CarneForma;
import co.com.grupoasd.nomina.modelo.CarnesDatasource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRStyledText;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author Armando Florez
 */
public class Carnet extends HttpServlet {

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

       try {
            String cargo = "";
            String nombreArchivo = "";
            Random rnd = new Random();
            
            char n;
            
            for (int i=0; i < 10 ; i++) { 
                n = (char)(rnd.nextDouble() * 26.0 + 65.0 ); 
                nombreArchivo += n; 
            } 

           
            String ubicacionArchivo = "/data/";
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024);
            factory.setRepository(new File(ubicacionArchivo));
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> partes = upload.parseRequest(request);
                
            for(FileItem item : partes)
            {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldname = item.getFieldName();

                    if(fieldname.equals("cargo"))
                    {
                        cargo = item.getString();
                    }

                }else
                {
                    ubicacionArchivo = ubicacionArchivo + "carne";

                    File directorio = new File(ubicacionArchivo); 
                    if (!directorio.exists())
                    {
                        directorio.mkdir();
                    }

                    File file = new File(ubicacionArchivo, nombreArchivo + ".csv");
                    item.write(file);
                }
            }

            String csvFile = "/data/carne/"+ nombreArchivo + ".csv";
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ";";
            String ubicacion = "";
        
            br = new BufferedReader(new FileReader(csvFile));
            
            CarnesDatasource datasource = new CarnesDatasource();  

            while ((line = br.readLine()) != null) {
                    String[] lineas = line.split(cvsSplitBy);
                    
                    if(lineas[0].matches("-?\\d+(\\.\\d+)?"))
                    {
                        if (lineas.length >2)
                        {
                            ubicacion = lineas[2];
                        }else
                        {
                            ubicacion = "";
                        }
                        
                        CarneForma cf = new CarneForma(Integer.parseInt(lineas[0]), lineas[1], cargo, ubicacion, ""); 
                        datasource.addParticipante(cf); 
                    }
            }
            
            br.close();

            Map parameters = new HashMap();

            parameters.put("ruta", "/data/report/carne/");
            parameters.put("archivo", cargo + ".jpg");

            JasperReport jasperReport = JasperCompileManager.compileReport("/data/report/Carne.jrxml");
            jasperReport.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, datasource);

            response.setContentLength(bytes.length);

            ServletOutputStream out = response.getOutputStream();

            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();        
         }
        catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(FileUploadException ex)
        {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex.getMessage());
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
