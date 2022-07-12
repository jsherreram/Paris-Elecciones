/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Administrador
 */
public class OperacionesReporte {

    private static DbConnection connection = new DbConnection();

    public static byte[] ejecutar(JasperReport jasperReport, Map parameters) {
        Connection conn = null;
        try {
            connection.conectar();
            conn = connection.getConexion();
            //conn.setReadOnly(true);
            byte[] res = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
            //conn.setReadOnly(false);
            return res;
        } catch (JRException ex) {
            Logger.getLogger(OperacionesReporte.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
