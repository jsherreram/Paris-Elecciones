/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.Funcion;
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
public class FuncionDao {
    private Operaciones conex = new Operaciones();
    
    public List<Funcion> listaFunciones(){
        StringBuffer sb = new StringBuffer();
        final ArrayList<Funcion> funciones = new ArrayList<Funcion>();
        sb.append("select * from funciones;");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while(resultado.next()){
                        Funcion funcion = new Funcion();
                        funcion.setIdFuncion(resultado.getLong(1));
                        funcion.setCodigoFuncion(resultado.getString(2));
                        funcion.setDescripcionFuncion(resultado.getString(3));
                        funcion.setActivo(resultado.getInt(4));
                        funciones.add(funcion);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FuncionDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return funciones;
    }
    
    public Funcion buscarxCodigo(String codigo){
        final Funcion funcion = new Funcion();
        StringBuilder sb = new StringBuilder();
        sb.append("select idFuncion,codigoFuncion,descripcionFuncion,activo from funciones where codigoFuncion = '");
        sb.append(codigo);
        sb.append("' ");
        conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet resultado) {
                try {
                    while(resultado.next()){
                        funcion.setIdFuncion(resultado.getLong(1));
                        funcion.setCodigoFuncion(resultado.getString(2));
                        funcion.setDescripcionFuncion(resultado.getString(3));
                        funcion.setActivo(resultado.getInt(4));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FuncionDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return funcion;
    }
}
