/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.common.util.StringUtil;
import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.MedioPago;
import co.com.grupoasd.nomina.modelo.NotificacionPago;
import co.com.grupoasd.nomina.modelo.Prueba;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASD
 */
public class NotificacionPagoDao {

    private final Operaciones conex = new Operaciones();

    /**
     * Metodo DAO encargado de realizar la inserción del registro de
     * notificacion
     *
     * @param notificacionPago
     * @return
     */
    public long insertarNotificacionPago(NotificacionPago notificacionPago) {
        StringBuilder insert = new StringBuilder();
        final long[] result = new long[1];
        insert.append("INSERT INTO notificacion_pago (idEmpleado,idPrueba,idMedioPago,valor,fecha_pago,observacionesPago) VALUES(");
        insert.append(notificacionPago.getEmpleado().getIdEmpleado()).append(",");
        insert.append(notificacionPago.getPrueba().getIdprueba()).append(",");
        insert.append(notificacionPago.getMedioPago().getId_medio()).append(",");
        insert.append(notificacionPago.getValor()).append(",");
        insert.append("'").append(notificacionPago.getFechaPago()).append("',");
        insert.append("'").append(notificacionPago.getObservacionesPago()).append("')");
        conex.ejecutar(new Operaciones.OperacionEjecutar() {
            @Override
            public void llaveGenerada(ResultSet genKeys) {
                try {
                    while (genKeys.next()) {
                        result[0] = (long) genKeys.getLong(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NotificacionPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, insert.toString());
        return result[0];
    }

    /**
     * Metodo DAO encargado de retornar la informacion de las notificaciones de
     * pago segun la prueba
     *
     * @param idPrueba
     * @return
     */
    public List<NotificacionPago> getAllNotificacionPago(int idPrueba) {
        final List<NotificacionPago> listNotificacionPago = new ArrayList<>();
        StringBuilder consulta = new StringBuilder();

        final String link = System.getProperty("urlConvocatorias") + "/panel/notificacionPago/index.jsp#/";

        consulta.append("SELECT em.idEmpleado,em.nombre1,em.nombre2,em.apellido1,");
        consulta.append("em.apellido2,em.nrodoc,np.observacionesPago,np.confirmado,");
        consulta.append("mp.nombre,mp.idmedio_pago,np.valor,np.id,np.fecha_pago,");
        consulta.append("pr.idprueba,pr.nombre AS nombrePrueba,np.observaciones,");
        consulta.append("np.notificado,np.leido,np.fecha_actualiza, ");
        consulta.append("ifnull(case when length(trim(em.celular))=10 then trim(em.celular) else trim(em.telefono) end,0)  telefono ");
        consulta.append("FROM notificacion_pago np ");
        consulta.append("INNER JOIN medio_pago mp ON np.idMedioPago = mp.idmedio_pago ");
        consulta.append("INNER JOIN empleado em ON np.idEmpleado = em.idEmpleado ");
        consulta.append("INNER JOIN prueba pr ON np.idPrueba = pr.idprueba ");
        consulta.append("WHERE np.idPrueba = ").append(idPrueba);

        Logger.getLogger(NotificacionPagoDao.class.getName()).log(Level.INFO, consulta.toString());
        conex.consultar(consulta.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                        NotificacionPago notificacionPago = new NotificacionPago();
                        notificacionPago.setEmpleado(new Empleado());
                        notificacionPago.getEmpleado().setIdEmpleado(resultado.getInt("idEmpleado"));
                        notificacionPago.getEmpleado().setNombre1(resultado.getString("nombre1"));
                        notificacionPago.getEmpleado().setNombre2(resultado.getString("nombre2"));
                        notificacionPago.getEmpleado().setApellido1(resultado.getString("apellido1"));
                        notificacionPago.getEmpleado().setApellido2(resultado.getString("apellido2"));
                        notificacionPago.getEmpleado().setNrodoc(resultado.getLong("nrodoc"));
                        notificacionPago.setMedioPago(new MedioPago());
                        notificacionPago.getMedioPago().setNombre(resultado.getString("nombre"));
                        notificacionPago.getMedioPago().setId_medio(resultado.getInt("idmedio_pago"));
                        notificacionPago.setValor(resultado.getLong("valor"));
                        notificacionPago.setId(resultado.getLong("id"));
                        notificacionPago.setFechaPago(resultado.getString("fecha_pago"));
                        notificacionPago.setPrueba(new Prueba());
                        notificacionPago.getPrueba().setIdprueba(resultado.getInt("idprueba"));
                        notificacionPago.getPrueba().setNombre(resultado.getString("nombrePrueba"));
                        notificacionPago.setObservaciones(resultado.getString("observaciones"));
                        notificacionPago.setObservacionesPago(resultado.getString("observacionesPago"));
                        notificacionPago.setConfirmado(resultado.getInt("confirmado"));
                        notificacionPago.setNotificado(resultado.getInt("notificado"));
                        notificacionPago.setLeido(resultado.getInt("leido"));
                        notificacionPago.setFechaActualiza(resultado.getString("fecha_actualiza"));
                        notificacionPago.setTelefono(resultado.getString("telefono"));
                        String id = StringUtil.generateMD5(resultado.getString("id") + "*" + resultado.getInt("idEmpleado"));
                        notificacionPago.setLink(link.concat(id));
                        listNotificacionPago.add(notificacionPago);
                    }
                } catch (SQLException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(NotificacionPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return listNotificacionPago;
    }

}
