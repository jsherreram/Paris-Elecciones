/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.CuentaCobro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASD
 */
public class CuentaCobroDao {
   private Operaciones conex = new Operaciones();
   public CuentaCobroDao() {
    }

   public int Existe(int idcuenta, int id_empleado) {
        final Object id[] = new Object[1];
        id[0] = 0;
      try {
          StringBuilder sql = new StringBuilder();
          sql.append(" select count(*) as cnt from cuentacobro where id = ? and idempleado = ? ");
              conex.consultar(new Operaciones.OperacionConsulta() {
              @Override
              public void respuestaConsulta(ResultSet res) {
                  try {
                      while (res.next()) {
                          if (res.getInt("cnt") > 0 ){
                              id[0] = 1;
                            }
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(CuentaCobroDao.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
        }, sql.toString(),idcuenta,id_empleado);
      } catch (Exception e) {
          Logger.getLogger(CuentaCobroDao.class.getName()).log(Level.SEVERE, null, e);
      }
      return (int) id[0];
  }

   public int EstaPago(int idcuenta, int id_empleado) {
        final Object id[] = new Object[1];
        id[0] = 0;
      try {
          StringBuilder sql = new StringBuilder();
          sql.append(" select pagado from cuentacobro where id = ? and idempleado = ? ");

              conex.consultar(new Operaciones.OperacionConsulta() {
              @Override
              public void respuestaConsulta(ResultSet res) {
                  try {
                      while (res.next()) {
                          if (res.getInt("pagado") == 1 ){
                              id[0] = 1;
                            }
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(CuentaCobroDao.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
        }, sql.toString(),idcuenta,id_empleado);
      } catch (Exception e) {
          Logger.getLogger(CuentaCobroDao.class.getName()).log(Level.SEVERE, null, e);
      }
      return (int) id[0];
  }

   public Boolean Actualizar(CuentaCobro cc) {
        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();
        sql.append(" update cuentacobro set fecha_pago=? ,referencia_pago=? ,valor_pagado=?  ,");
        sql.append(" observaciones=? ,pagado=1");
        sql.append(" where id = ? and idempleado = ? ");
        resultado = conex.ejecutar(sql.toString(),cc.getFecha_pago(),cc.getReferencia_pago(),cc.getValor_pagado(),cc.getObservaciones(),cc.getId(),cc.getIdempleado());
        return resultado;
  }
  
}