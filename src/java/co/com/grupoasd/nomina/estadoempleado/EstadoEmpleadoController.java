/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.estadoempleado;

import co.com.grupoasd.nomina.dao.EstadosPersonalDao;
import co.com.grupoasd.nomina.modelo.EstadoEmpleado;
import java.util.List;

/**
 *
 * @author Grupo ASD
 */
public class EstadoEmpleadoController implements IEstadoEmpleado{

    @Override
    public List<EstadoEmpleado> listar() {
        EstadosPersonalDao dao = new EstadosPersonalDao();
        return dao.listar();
    }    
}
