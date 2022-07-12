/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.CoberturaMedioPago;
import co.com.grupoasd.nomina.modelo.seguimiento.ResultSetConverter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASD
 */
public class CoberturaMedioPagoDao {

    private Operaciones conex = new Operaciones();

    /**
     * Permite registrar un medio de pago
     *
     * @param Medio de Pago
     */
    public int insertar(CoberturaMedioPago coberturaMedioPago) {
        final Object[] idCoberturaMedioPago = new Object[1];
        String sql
                = " INSERT INTO cobertura_mediopago ("
                + " idmedio_pago,idPrueba,codigoMunicipio,"
                + " activo,fecha_actualiza)"
                + " VALUES (?,?,?,?, current_timestamp)";

        conex.ejecutar(new Operaciones.OperacionEjecutar() {

            @Override
            public void llaveGenerada(ResultSet generatedKeys) {
                try {
                    if (generatedKeys.next()) {
                        idCoberturaMedioPago[0] = (int) generatedKeys.getLong(1);
                    } else {
                        idCoberturaMedioPago[0] = 0;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }, sql, coberturaMedioPago.getIdmedio_pago(),
                coberturaMedioPago.getIdPrueba(),
                coberturaMedioPago.getCodigoMunicipio(),
                coberturaMedioPago.isActivo()
        );
        return (int) idCoberturaMedioPago[0];
    }

    /**
     * Permite actualizar un medio de pago
     *
     * @param Medio de Pago
     */
    public boolean actualizar(CoberturaMedioPago coberturaMedioPago) {
        boolean resultado = false;

        String sql
                = " UPDATE cobertura_mediopago SET "
                + " activo = ?,fecha_actualiza= current_timestamp "
                + " WHERE idMedio_pago = ? and idPrueba=? and codigoMunicipio=?";

        resultado = conex.ejecutar(sql,
                coberturaMedioPago.isActivo(),
                coberturaMedioPago.getIdmedio_pago(),
                coberturaMedioPago.getIdPrueba(),
                coberturaMedioPago.getCodigoMunicipio());
        return resultado;

    }

    public int existeCoberturaMedioPago(int idMedioPago, int idPrueba, String municipio) {
        final Object[] respuesta = new Object[1];
        respuesta[0] = 0;

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select count(*) as total ");
            query.append(" FROM cobertura_mediopago  ");
            query.append(" where idmedio_pago=? and idPrueba=? and codigoMunicipio=?  ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            if (res.getInt("total") > 0) {
                                respuesta[0] = 1;
                            }
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString(), idMedioPago, idPrueba, municipio);

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return (int) respuesta[0];
    }

    public String listarMediosMunicipioJson(String codMunicipio, int medioPagoActivo, int coberturaActiva, int idPrueba) {

        final Object[] result = new Object[1];
        JSONArray json = null;
        try {
            StringBuilder query = new StringBuilder();

            query.append(" SELECT mp.idmedio_pago, mp.nombre, ");
            query.append(" (Select case when count(*)>0 then 1 else  0 end from cobertura_mediopago cm ");
            query.append(" where cm.idmedio_pago=mp.idmedio_pago and cm.codigoMunicipio=? and cm.activo=?  and cm.idPrueba= ? ) as activoParaMunicipio ");
            query.append(" FROM medio_pago mp where mp.activo=? and mp.idPrueba= ? ");

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
            }, query.toString(), codMunicipio, coberturaActiva,idPrueba,medioPagoActivo,idPrueba);

            json = (JSONArray) result[0];

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return json.toString();

    }

    /**
     * Permite actualizar un medio de pago
     *
     * @param Medio de Pago
     */
    public boolean actualizarRegistros(int medioPago, int prueba, int activo) {
        boolean resultado = false;
        try {
            String sql
                    = " UPDATE cobertura_mediopago SET "
                    + " activo = ?,fecha_actualiza= current_timestamp "
                    + " WHERE idMedio_pago = ? and idPrueba=? ";

            resultado = conex.ejecutar(sql,
                    activo,
                    medioPago,
                    prueba);
        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;

    }

    /**
     * Permite actualizar un medio de pago
     *
     * @param Medio de Pago
     */
    public boolean actualizarRegistros(int prueba, int activo, String codMunicipio) {
        boolean resultado = false;
        try {
            String sql
                    = " UPDATE cobertura_mediopago SET "
                    + " activo = ?,fecha_actualiza= current_timestamp "
                    + " WHERE idPrueba=?  and codigoMunicipio=? ";

            resultado = conex.ejecutar(sql,
                    activo,
                    prueba, codMunicipio);
        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return resultado;

    }

    public String listarMediosMunicipioAsignados(String codMunicipio, int medioPagoActivo, int coberturaActiva , int idPrueba) {
        final Object[] result = new Object[1];
        JSONArray jsonList = new JSONArray();

        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT mp.idmedio_pago ");
            query.append("FROM medio_pago mp ");
            query.append("inner join cobertura_mediopago cm on (cm.idmedio_pago=mp.idmedio_pago)");
            query.append("where mp.activo=? and cm.codigoMunicipio=?  and cm.activo=? and cm.idPrueba= ?  and mp.idPrueba= ? ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {

                        JSONArray jsonTemp = new JSONArray();
                        int i = 0;
                        while (res.next()) {
                            jsonTemp.put(i, res.getInt("idmedio_pago"));
                            i++;
                        }

                        result[0] = jsonTemp;
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(CoberturaMedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString(), medioPagoActivo, codMunicipio, coberturaActiva,idPrueba,idPrueba);

            jsonList = (JSONArray) result[0];
        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return jsonList.toString();
    }

    /**
     * Consulta un registro de cobertura medio de pago
     *
     * @param idMedioPago
     */
    public CoberturaMedioPago findCoberturaMedioPago(int idMedioPago, int prueba, String codMunicipio) {
        final Object[] respuesta = new Object[1];

        try {
            StringBuilder query = new StringBuilder();
            query.append(" select ");
            query.append(" cm.Id,");
            query.append(" cm.idmedio_pago,");
            query.append(" cm.idPrueba,");
            query.append(" cm.codigoMunicipio, ");
            query.append(" cm.activo ");

            query.append(" FROM cobertura_mediopago cm  ");
            query.append(" where cm.idmedio_pago=? and cm.idPrueba=? and cm.codigoMunicipio=? ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                            CoberturaMedioPago coberturaMedioPago = new CoberturaMedioPago();
                            coberturaMedioPago.setId(res.getInt("Id"));
                            coberturaMedioPago.setIdmedio_pago(res.getInt("idmedio_pago"));
                            coberturaMedioPago.setIdPrueba(res.getInt("idPrueba"));
                            coberturaMedioPago.setCodigoMunicipio(res.getString("codigoMunicipio"));
                            coberturaMedioPago.setActivo(res.getBoolean("activo"));

                            respuesta[0] = coberturaMedioPago;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString(), idMedioPago, prueba, codMunicipio);

        } catch (Exception e) {
            Logger.getLogger(MedioPagoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return (CoberturaMedioPago) respuesta[0];
    }

    public void generaCoberturaPorMunicipio(int idPrueba) {

        final Object[] result = new Object[1];
        String nombreArchivo = "/data//COBERTURA_MEDIOS_PAGO.csv";

        String texto = "Nombre Departamento;Codigo Municipio;Nombre Municipio;"
                + "Medio de pago; Tipo de Medio de Pago\n";
        try {
            FileWriter fwriter = new FileWriter(nombreArchivo);
            StringBuilder query = new StringBuilder();

            query.append(" SELECT d.nombre ,cm.codigoMunicipio , m.nombre as mun, mp.nombre as medioPago, tp.descripcion ");
            query.append(" FROM cobertura_mediopago cm  ");
            query.append(" inner join medio_pago mp on (mp.idmedio_pago=cm.idmedio_pago) ");
            query.append(" inner join tipo_medio_pago tp on (tp.idTipoMedioPago=mp.idTipoMedioPago) ");
            query.append(" inner join municipio_dane m on (m.codigoMunicipio= cm.codigoMunicipio) ");
            query.append(" inner join departamento_dane d on (d.codigo=m.codigoDepartamento) ");
            query.append(" where cm.activo=1 and  cm.idPrueba=? ");
            query.append(" order by d.codigo, m.codigoMunicipio ");

            conex.consultar(new Operaciones.OperacionConsulta() {

                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        StringBuilder sb = new StringBuilder("");

                        while (res.next()) {

                            sb.append(res.getString("nombre"));
                            sb.append(";");
                            sb.append(res.getString("codigoMunicipio"));
                            sb.append(";");
                            sb.append(res.getString("mun"));
                            sb.append(";");
                            sb.append(res.getString("medioPago"));
                            sb.append(";");
                            sb.append(res.getString("descripcion"));
                            sb.append("\n");
                        }
                        result[0] = sb.toString();
                    } catch (SQLException ex) {
                        Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, query.toString(), idPrueba);

            texto += ((String) result[0]);

            fwriter.write(texto);
            fwriter.flush();
            fwriter.close();

        } catch (Exception e) {
            Logger.getLogger(GeneraSolicitudesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
