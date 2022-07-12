/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Grupo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grupo ASD
 */
public class GrupoDao {
    private Operaciones conex = new Operaciones();
    
    public List<Grupo> listarGrupos(){
        StringBuilder sb = new StringBuilder();
        final ArrayList<Grupo> grupos = new ArrayList<Grupo>();
        sb.append("select * from grupos");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while(resultado.next()){
                        Grupo grupo = new Grupo();
                        grupo.setIdGrupo(resultado.getLong(1));
                        grupo.setCodigoGrupo(resultado.getString(2));
                        grupo.setDescripcionGrupo(resultado.getString(3));
                        grupo.setActivo(resultado.getInt(4));
                        grupos.add(grupo);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return grupos;
    }
    
    public Grupo buscarxCodigo(String codigo){
        final Grupo grupo = new Grupo();
        StringBuilder sb = new StringBuilder();
        sb.append("select idGrupo,codigoGrupo,descripcion,estado from grupos where codigoGrupo = '");
        sb.append(codigo);
        sb.append("' ");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    if(resultado.next()){
                        grupo.setIdGrupo(resultado.getLong(1));
                        grupo.setCodigoGrupo(resultado.getString(2));
                        grupo.setDescripcionGrupo(resultado.getString(3));
                        grupo.setActivo(resultado.getInt(4));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        return grupo;
    }
    
     public List<Grupo> listarGruposFuncionarios(){
        StringBuilder sb = new StringBuilder();
        final ArrayList<Grupo> grupos = new ArrayList<Grupo>();
        sb.append("select g.idGrupo,g.codigoGrupo,g.descripcion from grupos g ");
        sb.append("where g.estado=1 and g.codigoGrupo in ('administrador','coordinador','coordinador_icfes',");
        sb.append("'administrador_icfes','contabilidad','call','contabilidad_asd') ");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    resultado.beforeFirst();
                    while(resultado.next()){
                        Grupo grupo = new Grupo();
                        grupo.setIdGrupo(resultado.getLong("idGrupo"));
                        grupo.setCodigoGrupo(resultado.getString("codigoGrupo"));
                        grupo.setDescripcionGrupo(resultado.getString("descripcion"));

                        grupos.add(grupo);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GrupoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return grupos;
    }
    
}