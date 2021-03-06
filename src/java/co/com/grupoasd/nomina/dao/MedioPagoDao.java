/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.MedioPago;
import co.com.grupoasd.nomina.modelo.TipoMedioPago;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Pedro Rodriguez
 */
public class MedioPagoDao {

    private Operaciones conex = new Operaciones();

    public MedioPagoDao() {

    }

    public List<MedioPago> listar(int idEmp, int idPrueba) {
        final List<MedioPago> lstMedioPago = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" mp.idmedio_pago,");
            query.append(" mp.nombre,");
            query.append(" mp.activo,");
            query.append(" mp.idTipoMedioPago,");
            query.append(" mp.solicitarEntidadBancaria,");
            query.append(" mp.solicitarNumeroCuenta,");
            query.append(" mp.solicitarTipoCuenta,");
            query.append(" mp.auxilioTransporte,");
            query.append(" mp.costoTransaccion,");
            query.append(" mp.porcentajeTransaccion,");
            query.append(" mp.descripcion ");
            query.append(" FROM medio_pago mp ");
            query.append(" where mp.activo=1 ");
            query.append(" and Exists( select 1 FROM empleado_x_prueba_x_estado eps,divipol divi,cobertura_mediopago cmp ");
            query.append(" Where eps.idempleado= ? and eps.idprueba= ? and eps.iddivipol=divi.idDivipol ");
            query.append(" and cmp.activo=1 and cmp.idmedio_pago=mp.idmedio_pago and cmp.codigomunicipio=divi.codigomunicipio ");
            query.append(" and (ifnull(cmp.idprueba,0)=0 or ifnull(cmp.idprueba,0)=eps.idprueba) )  ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MedioPago mediopago = new MedioPago();
                            mediopago.setId_medio(res.getInt("idmedio_pago"));
                            mediopago.setNombre(res.getString("nombre"));
                            mediopago.setActivo(res.getInt("activo"));
                            mediopago.setIdTipoMedioPago(res.getInt("idTipoMedioPago"));
                            mediopago.setSolicitarEntidadBancaria(res.getInt("solicitarEntidadBancaria") == 1);
                            mediopago.setSolicitarNumeroCuenta(res.getInt("solicitarNumeroCuenta") == 1);
                            mediopago.setSolicitarTipoCuenta(res.getInt("solicitarTipoCuenta") == 1);
                            mediopago.setAuxilioTransporte(res.getInt("auxilioTransporte"));
                            mediopago.setCostoTransaccion(res.getInt("costoTransaccion"));
                            mediopago.setPorcentajeTransaccion(res.getFloat("porcentajeTransaccion"));
                            mediopago.setDescripcion(res.getString("descripcion"));
                            lstMedioPago.add(mediopago);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString(), idEmp, idPrueba);

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstMedioPago;
    }

    public String listarMediosPagoJson(int idPrueba) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" mp.idmedio_pago,");
            query.append(" mp.nombre,");
            query.append(" case when mp.activo=1 then 'Si' when mp.activo=0 then 'No' end as activo,");
            query.append(" tp.descripcion,");
            query.append(" case when mp.solicitarEntidadBancaria=1 then 'Si' when mp.solicitarEntidadBancaria=0 then 'No' end as solicitarEntidadBancaria,");
            query.append(" case when mp.solicitarNumeroCuenta=1 then 'Si' when mp.solicitarNumeroCuenta=0 then 'No' end as solicitarNumeroCuenta,");
            query.append(" case when mp.solicitarTipoCuenta=1 then 'Si' when mp.solicitarTipoCuenta=0 then 'No' end as solicitarTipoCuenta,");
            query.append(" mp.auxilioTransporte,");
            query.append(" mp.costoTransaccion,");
            query.append(" mp.porcentajeTransaccion,");
            query.append(" mp.auxilioTransporte, mp.costoTransaccionPorValor ");

            query.append(" FROM medio_pago mp ");
            query.append(" inner join tipo_medio_pago tp on (tp.idTipoMedioPago=mp.idTipoMedioPago) where mp.idPrueba=? ");
            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        result[0] = ResultSetConverter.convert(res);
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString(), idPrueba);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return json.toString();

    }

    public List<TipoMedioPago> listarTiposMediosPago() {
        final List<TipoMedioPago> lstTipoMedioPago = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" tp.idTipoMedioPago,");
            query.append(" tp.descripcion");
            query.append(" FROM tipo_medio_pago tp ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            TipoMedioPago tipoMediopago = new TipoMedioPago();
                            tipoMediopago.setIdTipoMedioPago(res.getInt("idTipoMedioPago"));
                            tipoMediopago.setDescripcion(res.getString("descripcion"));
                            lstTipoMedioPago.add(tipoMediopago);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString());

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstTipoMedioPago;
    }

    /**
     * Permite registrar un medio de pago
     *
     * @param Medio de Pago
     */
    public int insertar(MedioPago medioPago) {
        final Object[] idMedioPago = new Object[1];
        String sql
                = " INSERT INTO medio_pago ("
                + " nombre,activo,idTipoMedioPago,"
                + " solicitarEntidadBancaria,solicitarNumeroCuenta,solicitarTipoCuenta,"
                + " costoTransaccion,porcentajeTransaccion,descripcion,fecha_actualiza,"
                + " auxilioTransporte,costoTransaccionPorValor, idprueba)"
                + " VALUES (?,?,?,?,?,?,?,?,?, current_timestamp,?,?,?)";

        conex.ejecutar(new Operaciones.OperacionEjecutar() {

            @Override
            public void llaveGenerada(ResultSet generatedKeys) {
                try {
                    if (generatedKeys.next()) {
                        idMedioPago[0] = (int) generatedKeys.getLong(1);
                        //medioPago.setId_medio((int) generatedKeys.getLong(1));
                    } else {
                        idMedioPago[0] = 0;
                        // medioPago.setId_medio(0);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }, sql, medioPago.getNombre().toUpperCase().trim(),
                medioPago.getActivo(),
                medioPago.getIdTipoMedioPago(),
                medioPago.getSolicitarEntidadBancaria(),
                medioPago.getSolicitarNumeroCuenta(),
                medioPago.getSolicitarTipoCuenta(),
                medioPago.isCostoTransaccionPorValor() == true ? medioPago.getCostoTransaccion() : 0,
                medioPago.isCostoTransaccionPorValor() == false ? medioPago.getPorcentajeTransaccion() : 0.00,
                medioPago.getDescripcion(),
                medioPago.getAuxilioTransporte(),
                medioPago.isCostoTransaccionPorValor(),
                medioPago.getIdPrueba()
        );
        return (int) idMedioPago[0];
    }

    /**
     * Permite actualizar un medio de pago
     *
     * @param Medio de Pago
     */
    public boolean actualizar(MedioPago medioPago) {
        boolean resultado = false;
        String sql
                = " UPDATE medio_pago SET "
                + " nombre =?, activo =?, idTipoMedioPago =?,"
                + " solicitarEntidadBancaria = ?,solicitarNumeroCuenta=?,solicitarTipoCuenta=?,"
                + " costoTransaccion=?,porcentajeTransaccion=?,descripcion=?,auxilioTransporte=?, "
                + " costoTransaccionPorValor=? ,fecha_actualiza=current_timestamp "
                + " WHERE idmedio_pago = ? ";

        resultado = conex.ejecutar(sql,
                medioPago.getNombre().toUpperCase().trim(),
                medioPago.getActivo(),
                medioPago.getIdTipoMedioPago(),
                medioPago.getSolicitarEntidadBancaria(),
                medioPago.getSolicitarNumeroCuenta(),
                medioPago.getSolicitarTipoCuenta(),
                medioPago.isCostoTransaccionPorValor() == true ? medioPago.getCostoTransaccion() : 0,
                medioPago.isCostoTransaccionPorValor() == false ? medioPago.getPorcentajeTransaccion() : 0.00,
                medioPago.getDescripcion(),
                medioPago.getAuxilioTransporte(),
                medioPago.isCostoTransaccionPorValor(),
                medioPago.getId_medio());

        return resultado;

    }

    /**
     * Consulta un registro de medio de pago
     *
     * @param idMedioPago
     */
    public MedioPago findMedioPago(int idMedioPago, int idprueba) {
        final Object[] respuesta = new Object[1];
        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" mp.idmedio_pago,");
            query.append(" mp.nombre,");
            query.append(" mp.activo, ");
            query.append(" mp.idTipoMedioPago,");
            query.append(" mp.solicitarEntidadBancaria, ");
            query.append(" mp.solicitarNumeroCuenta, ");
            query.append(" mp.solicitarTipoCuenta, ");
            query.append(" mp.auxilioTransporte,");
            query.append(" mp.costoTransaccion,");
            query.append(" mp.porcentajeTransaccion, mp.descripcion, ");
            query.append(" mp.costoTransaccionPorValor ");
            query.append(" FROM medio_pago mp ");
            query.append(" where mp.idmedio_pago= ? and idPrueba=? ");
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.INFO, query.toString());
            conex.consultar(new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MedioPago mediopago = new MedioPago();
                            mediopago.setId_medio(res.getInt("idmedio_pago"));
                            mediopago.setNombre(res.getString("nombre"));
                            mediopago.setActivo(res.getInt("activo"));
                            mediopago.setIdTipoMedioPago(res.getInt("idTipoMedioPago"));
                            mediopago.setSolicitarEntidadBancaria(res.getInt("solicitarEntidadBancaria") == 1);
                            mediopago.setSolicitarNumeroCuenta(res.getInt("solicitarNumeroCuenta") == 1);
                            mediopago.setSolicitarTipoCuenta(res.getInt("solicitarTipoCuenta") == 1);
                            mediopago.setAuxilioTransporte(res.getInt("auxilioTransporte"));
                            mediopago.setCostoTransaccion(res.getInt("costoTransaccion"));
                            mediopago.setPorcentajeTransaccion(res.getFloat("porcentajeTransaccion"));
                            mediopago.setDescripcion(res.getString("descripcion"));
                            mediopago.setCostoTransaccionPorValor(res.getInt("costoTransaccionPorValor") == 1);
                            respuesta[0] = mediopago;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString(), idMedioPago, idprueba);

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (MedioPago) respuesta[0];
    }

     public List<MedioPago> listarMediosPago() {
        final List<MedioPago> lstMedioPago = new ArrayList<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" idmedio_pago,");
            query.append(" nombre");
            query.append(" FROM medio_pago ");
            
            conex.consultar( query.toString(), new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            MedioPago mediopago = new MedioPago();
                            mediopago.setId_medio(res.getInt("idmedio_pago"));
                            mediopago.setNombre(res.getString("nombre"));
                            lstMedioPago.add(mediopago);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lstMedioPago;
    }
    
}
