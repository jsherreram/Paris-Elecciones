/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.estadosOrden;

import co.com.grupoasd.nomina.dao.EstadosOrdenDao;
import co.com.grupoasd.nomina.modelo.EstadosOrden;
import java.util.List;

/**
 *
 * @author CristianAlexander
 */
public class EstadosOrdenController implements IEstadosOrden{

    @Override
    public List<EstadosOrden> listar() throws Exception {
        EstadosOrdenDao dao = new EstadosOrdenDao();
        return dao.listar();
    }
    
}
