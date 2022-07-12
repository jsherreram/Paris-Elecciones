/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Orden;
import co.com.grupoasd.nomina.modelo.Sitio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CristianAlexander
 */
public class DivipolDao {
    
    private Operaciones conex = new Operaciones();
    
    public Orden getIdByCodigoSitio(String codigoSitio, String idPrueba) throws Exception{
        StringBuilder sb = new StringBuilder();
        final Orden sitio = new Orden();
        sb.append("select idDivipol from divipol where codigoPuesto = '");
        sb.append(codigoSitio);
        sb.append("'");
        sb.append(" and idPrueba = ");
        sb.append(idPrueba);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    sitio.setIdDivipolStio(resultado.getLong(1));
                } catch (SQLException ex) {
                    Logger.getLogger(DivipolDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return sitio;
    }
    
    public List<Long> getListDivipolPorDpto(String codigoDepartamento, int idPrueba) throws Exception{
        StringBuilder sb = new StringBuilder();
        final List<Long> divipol = new ArrayList<>();
        sb.append("select idDivipol from divipol where codigoDepartamento =? ");
        sb.append(" and idPrueba = ? ");

        conex.consultar( new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while (resultado.next()) {
                       divipol.add(resultado.getLong("idDivipol"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DivipolDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        },sb.toString(),codigoDepartamento,idPrueba);
        return divipol;
    }
    
    public Boolean actualizardesconectado(Sitio sitio) {
        Boolean resultado = false;
        int desconectado = 0;
        if (sitio.getEstado().equalsIgnoreCase("true")){
            desconectado = 1;
        }
        String  s1 =    Integer.toString(desconectado);
        String  s2 =    Integer.toString(sitio.getIddivipol());
        String  s3 =    Integer.toString(sitio.getIdPrueba());
        StringBuilder sql = new StringBuilder();
                sql.append(" update divipol set desconectado=").append(s1);
                sql.append(" where idPrueba=").append(sitio.getIdPrueba());
                sql.append(" and idDivipol=").append(sitio.getIddivipol());
        resultado = conex.ejecutar(sql.toString());
        return resultado;
    }

}
