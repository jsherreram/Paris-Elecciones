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
public class AplicacionDao {
    
    private Operaciones conex = new Operaciones();
    
    public Boolean Ejecutar(String ejecutar) {
        Boolean resultado     = false;
        resultado = conex.ejecutar(ejecutar);
        return resultado;
      }

   public List<String> CargosHabilitados(int idPrueba) {

        final List<String> lst = new ArrayList<>();
        String s1 = Integer.toString(idPrueba);
        StringBuilder sb = new StringBuilder();
        sb.append(" select distinct tsc.codigoCargo from tsesion_cargo tsc,tipo_sesion ts,evento e ");
        sb.append(" where  tsc.idtipo_sesion= ts.id ");
        sb.append(" and    ts.tipoSesion    = e.tipoSesion ");
        sb.append(" and    e.idprueba       = ").append(s1);
        sb.append(" and    tsc.activo=1 and ts.activo=1 and e.activo=1; ");
        try {
            conex.consultar(sb.toString(), new Operaciones.OperacionConsulta() {
                @Override
                public void respuestaConsulta(ResultSet res) {
                    try {
                        while (res.next()) {
                        lst.add(res.getString("codigoCargo"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return lst;
    }


}
