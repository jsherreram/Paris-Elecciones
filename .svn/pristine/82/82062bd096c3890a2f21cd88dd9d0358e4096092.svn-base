/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grupo ASD
 */
public class FuncionGrupoDao {
    private Operaciones conex = new Operaciones();
    
    public Boolean existeFuncion(Long grupo, Long funcion){
        final Respuesta respuesta = new Respuesta();
        respuesta.setRespuesta(null);
        StringBuilder sb = new StringBuilder();
        sb.append("select count(0) from grupo_funcion where idGrupo = ");
        sb.append(grupo);
        sb.append(" and idFuncion = ");
        sb.append(funcion);   
        
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    if(resultado.getInt(1) > 0)
                        respuesta.setRespuesta(Boolean.TRUE);
                    else
                        respuesta.setRespuesta(Boolean.FALSE);                        
                } catch (SQLException ex) {
                    
                    Logger.getLogger(FuncionGrupoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return respuesta.getRespuesta();
      //  return null;
    }
}

class Respuesta{
    Boolean respuesta = null;
    
    public Boolean getRespuesta(){
        return this.respuesta;
        
    }
    public void setRespuesta(Boolean r){
        this.respuesta = r;
    }
}
