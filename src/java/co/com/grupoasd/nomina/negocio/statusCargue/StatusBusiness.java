/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.statusCargue;

import co.com.grupoasd.nomina.dao.StatusCargueDao;
import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.modelo.wrapper.DivitransConsultaAdmin;
import static co.com.grupoasd.nomina.negocio.Divitrans.DivitransBussines.COLUMN_NAMES;
import static co.com.grupoasd.nomina.negocio.Divitrans.DivitransBussines.LINE_FEED;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Rodríguez
 */
public class StatusBusiness implements IStatus {

    @Override
    public List<StatusCargue> listar(String usuario, int idTipoCargue) {
        StatusCargueDao statusCargueDao = new StatusCargueDao();
        List<StatusCargue> statusCargues = statusCargueDao.listar(usuario, idTipoCargue);
        return statusCargues;
    }

    @Override
    public List<StatusCargue> listar(String usuario, int idTipoCargue, int identificadorRegistro) {
        StatusCargueDao statusCargueDao = new StatusCargueDao();
        List<StatusCargue> statusCargues = statusCargueDao.listar(usuario, idTipoCargue, identificadorRegistro);
        return statusCargues;
    }

    @Override
    public int Insertar(StatusCargue s) {
        StatusCargueDao statusCargueDao = new StatusCargueDao();
        return statusCargueDao.insertar(s);
    }

    @Override
    public Boolean ActualizarAvance(StatusCargue s) {
        StatusCargueDao statusCargueDao = new StatusCargueDao();
        return statusCargueDao.actualizarAvance(s);
    }

    @Override
    public Boolean Finalizar(StatusCargue s, StringBuilder sb) {
        StatusCargueDao statusCargueDao = new StatusCargueDao();
        return statusCargueDao.finalizar(s, sb);
    }

    /**
     * Metodo para generar el archivo de errores del cargue
     *
     * @param idStatusCargue
     * @return
     */
    @Override
    public File generarReporteErrores(int idStatusCargue) {
        StatusCargueDao dao = new StatusCargueDao();
        String errores = dao.getErrores(idStatusCargue);
        File f = new File("erroresCargueTemp");
        try (FileWriter writer = new FileWriter(f)) {
            writer.append(errores);
        } catch (IOException ex) {
            Logger.getLogger(StatusBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return f;
    }

}
