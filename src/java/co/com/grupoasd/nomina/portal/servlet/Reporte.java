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
public class Reporte extends HttpServlet {

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
            String codigoDepartamento = request.getParameter("codigodepartamento");
            String codigoMunicipio = request.getParameter("codigomunicipio");
            String codigoZona = request.getParameter("codigozona");
            String codigoPuesto = request.getParameter("codigopuesto");
            String codigoCargo = request.getParameter("codigoCargo");
            int codigoEvento = Integer.parseInt(request.getParameter("codigoevento"));
            String tipoReporte = request.getParameter("tipoReporte");

            Map parameters = new HashMap();

            parameters.put("codigoDepartamento", codigoDepartamento);
            parameters.put("codigoMunicipio", codigoMunicipio);
            parameters.put("codigoZona", codigoZona);
            parameters.put("codigoEvento", codigoEvento);

            JasperReport jasperReport;
            if (tipoReporte.equals("zona")) {
                parameters.put("codigoCargo", codigoCargo);
                jasperReport = JasperCompileManager.compileReport("/data/report/ListaAsisteZonaAsd.jrxml");
            } else if (tipoReporte.equals("listaAsistenciaCapacitacion")) {
                parameters.put("codigoPuesto", codigoPuesto);
                parameters.put("codigoCargo", codigoCargo);
                jasperReport = JasperCompileManager.compileReport("/data/report/icfesAsistencia.jrxml");
            } else if (tipoReporte.equals("asistenciaPuesto")) {
                parameters.put("codigoPuesto", codigoPuesto);
                jasperReport = JasperCompileManager.compileReport("/data/report/eleccionesAsistenciaSitio.jrxml");
            } else if (tipoReporte.equals("StickerViaticos")) {
                parameters.put("codigoPuesto", codigoPuesto);
                jasperReport = JasperCompileManager.compileReport("/data/report/icfesViaticos.jrxml");
            } else if (tipoReporte.equals("StickerSillas")) {
                parameters.put("codigoPuesto", codigoPuesto);
                jasperReport = JasperCompileManager.compileReport("/data/report/icfesSillas.jrxml");
            } else if (tipoReporte.equals("contrato")) {
                int idEmpleado = Integer.parseInt(request.getParameter("id"));
                String cargo = request.getParameter("cargo");
                int idPrueba = Integer.parseInt(request.getParameter("prueba"));
                parameters.put("idempleado", idEmpleado);
                parameters.put("idprueba", idPrueba);
                parameters.put("codCargo", cargo);
                parameters.put("imagen", "/data/report/imagenes/" + cargo + ".png");

                jasperReport = JasperCompileManager.compileReport("/data/report/contrato.jrxml");

            } else if (tipoReporte.equals("ordenes")) {
                int idOrden = Integer.parseInt(request.getParameter("idOrden"));

                parameters.put("idOrden", idOrden);
                jasperReport = JasperCompileManager.compileReport("/data/report/suplencia.jrxml");
            } else if (tipoReporte.equals("ordenesDespacho")) {
                int idDivipolPDS = Integer.parseInt(request.getParameter("idDivipolPDS"));
                int idOrden = Integer.parseInt(request.getParameter("idOrden"));
                String hora = request.getParameter("hora");
                parameters.put("idOrden", idOrden);
                parameters.put("hora", hora);
                parameters.put("idDivipolPDS", idDivipolPDS);
                jasperReport = JasperCompileManager.compileReport("/data/report/suplenciaPersonalEnviado.jrxml");
            } else if (tipoReporte.equals("ordenesDespachoUltimo")) {
                int idDivipolPDS = Integer.parseInt(request.getParameter("idDivipolPDS"));
                int idOrden = Integer.parseInt(request.getParameter("idOrden"));
                parameters.put("idOrden", idOrden);
                parameters.put("idDivipolPDS", idDivipolPDS);
                jasperReport = JasperCompileManager.compileReport("/data/report/suplenciaPersonalEnviadoUltimo.jrxml");
            } else if (tipoReporte.equals("planillaPagoRps")) {
                int idPrueba = Integer.parseInt(request.getParameter("prueba"));
                parameters.put("idPrueba", idPrueba);
                parameters.put("codigoPuesto", codigoPuesto);
                jasperReport = JasperCompileManager.compileReport("/data/report/icfesPagoRps.jrxml");
            } else if (tipoReporte.equals("asistenciaMunicipio")) {
                jasperReport = JasperCompileManager.compileReport("/data/report/eleccionesAsistenciaMunicipio.jrxml");
            } else {
                parameters.put("codigoPuesto", codigoPuesto);
                jasperReport = JasperCompileManager.compileReport("/data/report/icfesViaticos.jrxml");
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
