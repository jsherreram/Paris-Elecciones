/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.gestionmediopago;

import co.com.grupoasd.nomina.modelo.PagoViaticos;
import java.util.List;

/**
 *
 * @author ASD
 */
public interface IPagoViaticos {
    public List<PagoViaticos> listar(int idEmp);
    
}
