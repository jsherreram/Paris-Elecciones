/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dao;

import co.com.grupoasd.nomina.conexion.Operaciones;
import co.com.grupoasd.nomina.modelo.CategoriaSitio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASD
 */
public class CategoriaSitioDao {
    
    
      private Operaciones conex = new Operaciones();
      
      
      public List<CategoriaSitio> listar() {
        final List<CategoriaSitio> categorias = new ArrayList<>();
        String sql = "select * from categoria_sitio";
        conex.consultar(sql, new Operaciones.OperacionConsulta() {

            @Override
            public void respuestaConsulta(ResultSet res) {
                try {
                    while (res.next()) {
                        CategoriaSitio categoria = new CategoriaSitio();
                        categoria.setIdCategoria(res.getInt("idCategoria"));
                        categoria.setNombre(res.getString("nombre"));
                        categorias.add(categoria);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DepartamentoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        return categorias;
    }

    
}
