/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.EstadosGeneral;
import co.com.grupoasd.nomina.modelo.EstadosOrden;
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
public class EstadosOrdenDao {
    private Operaciones conex = new Operaciones();
    
    public List<EstadosOrden> listar(){
        final ArrayList<EstadosOrden> estados = new ArrayList<EstadosOrden>();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from estadosordenessuplencia where activo = 2");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
            @Override
            public void respuestaConsulta(ResultSet resultado) {
                
                try {
                    resultado.beforeFirst();
                    while(resultado.next()){
                        EstadosOrden estado = new EstadosOrden();
                        estado.setIdEstadoOrden(resultado.getLong(1));
                        estado.setCodigoEstado(resultado.getString(2));
                        estado.setDescripcion(resultado.getString(3));
                        estado.setActivo(resultado.getLong(4));
                        estados.add(estado);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EstadosOrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return estados;
    }
    

    public EstadosOrden buscarPorId(long id){
        final EstadosOrden respuesta = new EstadosOrden();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from estadosordenessuplencia where idEstadoOrden = ");
        sb.append(id);
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    respuesta.setIdEstadoOrden(resultado.getLong("idEstadoOrden"));
                    respuesta.setCodigoEstado(resultado.getString("codigoEstado"));
                    respuesta.setDescripcion(resultado.getString("descripcion"));
                    respuesta.setActivo(resultado.getLong("activo"));
                } catch (SQLException ex) {
                    Logger.getLogger(EstadosOrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        return respuesta;
    }
    
    public EstadosOrden buscarPorCodigo(String codigo){        
        final EstadosOrden respuesta = new EstadosOrden();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from estadosordenessuplencia where codigoEstado = '");
        sb.append(codigo);
        sb.append(" '");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    respuesta.setIdEstadoOrden(resultado.getLong("idEstadoOrden"));
                    respuesta.setCodigoEstado(resultado.getString("codigoEstado"));
                    respuesta.setDescripcion(resultado.getString("descripcion"));
                    respuesta.setActivo(resultado.getLong("activo"));
                } catch (SQLException ex) {
                    Logger.getLogger(EstadosOrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        return respuesta;        
    }
    
    public EstadosGeneral buscarPorCodigoEstadoAsignacion(String codigo){
        final EstadosGeneral respuesta = new EstadosGeneral();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from estadosatencionordenessuplencia where codigoEstadoAtencion = '");
        sb.append(codigo);
        sb.append("'"); 
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    respuesta.setId(resultado.getLong("idEstadoAtencionOrden"));                    
                    respuesta.setCodigo(resultado.getString("codigoEstadoAtencion"));
                    respuesta.setDescripcion(resultado.getString("descripcion"));
                    respuesta.setActivo(resultado.getInt("activo"));
                } catch (SQLException ex) {
                    Logger.getLogger(EstadosOrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        return respuesta;
    }
    
    public EstadosGeneral buscarPorIdEstadoAsignacion(String codigo){
        final EstadosGeneral respuesta = new EstadosGeneral();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from estadosatencionordenessuplencia where idEstadoAtencionOrden = '");
        sb.append(codigo);
        sb.append("'"); 
   
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.first();
                    respuesta.setId(resultado.getLong("idEstadoAtencionOrden"));                    
                    respuesta.setCodigo(resultado.getString("codigoEstadoAtencion"));
                    respuesta.setDescripcion(resultado.getString("descripcion"));
                    respuesta.setActivo(resultado.getInt("activo"));
                } catch (SQLException ex) {
                    Logger.getLogger(EstadosOrdenDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        return respuesta;
    }
    
}
    
