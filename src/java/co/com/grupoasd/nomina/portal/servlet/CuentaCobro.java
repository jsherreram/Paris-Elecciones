/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.portal.servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import co.com.grupoasd.nomina.conexion.DbConnection;
import co.com.grupoasd.nomina.conexion.OperacionesReporte;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
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

/**
 *
 * @author Pedro Rodriguez
 */
public class CuentaCobro extends HttpServlet {

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

        try {
            String codigoDepartamento = request.getParameter("codigoDepartamento");
            String codigoMunicipio = request.getParameter("codigoMunicipio");
            String codigoCargo = request.getParameter("codigoCargo");
            int codigoEvento = Integer.parseInt(request.getParameter("codigoEvento"));
            String tipoReporte = request.getParameter("tipoReporte");

      

            Map parameters = new HashMap();

            parameters.put("p_codigodepartamento", codigoDepartamento);
            parameters.put("p_cargo", codigoCargo);
            parameters.put("p_evento", codigoEvento);

            JasperReport jasperReport;
            if (tipoReporte.equals("departamental")) {
                parameters.put("p_cargo", "5");
                jasperReport = JasperCompileManager.compileReport("/data/report/cuentacobrodptal.jrxml");
            } else if (tipoReporte.equals("reporteIndividual")) {
                String nrodoc= request.getParameter("nrodoc");
                parameters.clear();
                parameters.put("nrodoc", nrodoc);
                parameters.put("prueba", Integer.parseInt(request.getParameter("idPrueba")));
                jasperReport = JasperCompileManager.compileReport("/data/report/cuentacobroeleccionesIndividual.jrxml");

            }
              else if(tipoReporte.equals("reporte")){
                parameters.clear();
                parameters.put("p_cargo", codigoCargo);
                parameters.put("p_codigodepartamento", codigoDepartamento);
                parameters.put("p_codigomunicipio", codigoMunicipio);
                parameters.put("prueba", Integer.parseInt(request.getParameter("idPrueba")));
                jasperReport=JasperCompileManager.compileReport("/data/report/cuentacobroelecciones.jrxml");
            }
            else {
                parameters.put("p_cargo", codigoCargo);
                parameters.put("p_codigomunicipio", codigoMunicipio);
                jasperReport = JasperCompileManager.compileReport("/data/report/cuentacobro.jrxml");
            }
            
            jasperReport.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");
            byte[] bytes = OperacionesReporte.ejecutar(jasperReport, parameters);

            //byte[] bytes = JasperRunManager.runReportToPdf("/data/report/ListaAsiste.jasper", parameters, conn ) ;
            response.setContentLength(bytes.length);

            ServletOutputStream out = response.getOutputStream();

            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();

        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
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
