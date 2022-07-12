/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.ParametrosCorreo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grupo ASD
 */
public class MiscDao {

    private Operaciones conex = new Operaciones();

    public ParametrosCorreo getParametrosCorreo(String id) {
        final ParametrosCorreo parametros = new ParametrosCorreo();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from propiedadescorreo where id = '");
        sb.append(id);
        sb.append("'");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    parametros.setId(resultado.getString(1));
                    parametros.setHost(resultado.getString(2));
                    parametros.setPort(resultado.getString(3));
                    parametros.setEnable(resultado.getString(4));
                    parametros.setStarttlsEnable(resultado.getString(5));
                    parametros.setUserEmailSmtp(resultado.getString(6));
                    parametros.setUserEmail(resultado.getString(7));
                    parametros.setPasswordEmail(resultado.getString(8));
                    parametros.setServidorDB(resultado.getString(9));
                    parametros.setUsuarioDB(resultado.getString(10));
                    parametros.setPasswordDB(resultado.getString(11));
                    parametros.setRutaReporte(resultado.getString(12));
                    parametros.setAuth(resultado.getString(13));
                    parametros.setProtocol(resultado.getString(14));

                } catch (SQLException ex) {
                    Logger.getLogger(MiscDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return parametros;

    }

    public HashMap getParametrosCorreo() {
        final HashMap respuesta = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from propiedadescorreo");

        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while (resultado.next()) {
                        final ParametrosCorreo parametros = new ParametrosCorreo();
                        parametros.setId(resultado.getString(1));
                        parametros.setHost(resultado.getString(2));
                        parametros.setPort(resultado.getString(3));
                        parametros.setEnable(resultado.getString(4));
                        parametros.setStarttlsEnable(resultado.getString(5));
                        parametros.setUserEmailSmtp(resultado.getString(6));
                        parametros.setUserEmail(resultado.getString(7));
                        parametros.setPasswordEmail(resultado.getString(8));
                        parametros.setServidorDB(resultado.getString(9));
                        parametros.setUsuarioDB(resultado.getString(10));
                        parametros.setPasswordDB(resultado.getString(11));
                        parametros.setRutaReporte(resultado.getString(12));
                        parametros.setAuth(resultado.getString(13));
                        parametros.setProtocol(resultado.getString(14));                        
                        respuesta.put(parametros.getId(), parametros);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(MiscDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return respuesta;
    }
    
    public ArrayList<String> getCorreosOrdenSuplencia(Long idOrden){
        final ArrayList<String> respuesta = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct * from ");
        sb.append("(select distinct email ");
        sb.append("from ordensuplencia a ");
        sb.append("inner join relacion_pds b on (b.idDivipolSitio = a.idDivipolSitio and b.prioridad = 1) ");
        sb.append("inner join divipol c on (b.idDivipolPds = c.iddivipol) ");
        sb.append("where idOrden = ");
        sb.append(idOrden);
        sb.append(" union ");
        sb.append("select distinct email ");
        sb.append("from ordensuplenciadetalle a ");
        sb.append("inner join suplenciaxdetalle b on (a.idOrdenDetalle = b.idOrdenDetalle) ");
        sb.append("inner join divipol c on (b.idDivipolPds = c.iddivipol) ");
        sb.append("where idOrden = ");//181) a ");
        sb.append(idOrden);
        sb.append(" ) a");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while(resultado.next()){
                        String correo = resultado.getString(1);
                        respuesta.add(correo);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MiscDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        return respuesta;
    }
}
