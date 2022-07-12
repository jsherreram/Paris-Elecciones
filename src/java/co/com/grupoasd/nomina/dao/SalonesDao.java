/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
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
public class SalonesDao {
    
    private Operaciones conex = new Operaciones();
    
    public Boolean Ejecutar(String ejecutar) {
        Boolean resultado     = false;
        resultado = conex.ejecutar(ejecutar);
        return resultado;
      }

}
