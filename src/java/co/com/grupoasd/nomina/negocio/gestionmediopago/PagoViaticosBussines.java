/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.gestionmediopago;

import co.com.grupoasd.nomina.dao.GestionPagosDao;
import co.com.grupoasd.nomina.modelo.PagoViaticos;
import java.util.List;

/**
 *
 * @author Wilfer
 */
public class PagoViaticosBussines implements IPagoViaticos {

    @Override
    public List<PagoViaticos> listar(int idEmp) {
        GestionPagosDao gestiondao = new GestionPagosDao();
        List<PagoViaticos> MisViaticos = gestiondao.listarPagoViaticos(idEmp);
        return MisViaticos;
    }
}
