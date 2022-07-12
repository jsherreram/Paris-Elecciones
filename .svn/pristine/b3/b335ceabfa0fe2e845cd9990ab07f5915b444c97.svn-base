/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

import co.com.grupoasd.nomina.conexion.OperacionesReporte;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRStyledText;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author ASD
 */
@WebServlet(name = "Contrato", urlPatterns = {"/Contrato"})
public class Contrato extends HttpServlet {

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
       response.setContentType("application/pdf"); 
        try {
          
            int idEmpleado = Integer.parseInt(request.getParameter("id"));
            String cargo = request.getParameter("cargo");
            int idPrueba= Integer.parseInt(request.getParameter("prueba"));
                       
            Map parameters = new HashMap();
            parameters.put("idempleado", idEmpleado);
            parameters.put("idprueba", idPrueba);
            parameters.put("codCargo", cargo);
            
            JasperReport jasperReport = null;
            
            jasperReport = JasperCompileManager.compileReport("/data/report/contrato.jrxml");
           
            
           jasperReport.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");
           byte[] bytes = OperacionesReporte.ejecutar(jasperReport, parameters);
            
            //DbConnection conn=new DbConnection();
            
            //byte[] bytes = JasperRunManager.runReportToPdf("/data/report/ListaAsiste.jasper", parameters, conn ) ;
            response.setContentLength( bytes.length );
            
            //Preparamos la ruta para exportar
           /** String ruta="/data/contratos/"+idPrueba+"_"+cargo+"_"+idEmpleado+".pdf";
            JRExporter exporter=new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(ruta));
            exporter.exportReport();*/
            ServletOutputStream out = response.getOutputStream(); 
            out.write( bytes, 0, bytes.length );
            out.flush();
            out.close();  
           
        }
        catch (JRException ex) {
            Logger.getLogger(Contrato.class.getName()).log(Level.SEVERE, null, ex);
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
